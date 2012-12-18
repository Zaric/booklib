package com.book.controllers.user;

import com.book.biz.idchecker.check.Checker;
import com.book.controllers.AdminRequired;
import com.book.controllers.LoginRequired;
import com.book.dao.UserDAO;
import com.book.model.Page;
import com.book.model.User;
import com.book.util.CryptUtils;
import com.book.util.EmailUtils;
import com.book.util.Utils;
import com.book.util.WebUtil;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import java.util.List;

@LoginRequired
@Path("")
public class UserController {

    private static final int PER_PAGE_LIMIT = 10;

    @Autowired
    private UserDAO userDAO;

    @Get("")
    public String list(final Invocation inv) {
        int pageIndex = WebUtil.getIntByRequestParament(inv, "pageIndex", 1);
        // (当前页码-1)*页面容量 pageIndex, limit
        int preLimit = (pageIndex - 1) * PER_PAGE_LIMIT;

        final List<User> users = this.userDAO.find(preLimit, PER_PAGE_LIMIT);

        // 构造一个page对象，第1个参数是当前页，第2个参数是该页最大记录数，第3个是页码上的连接地址
        Page page = new Page(pageIndex, PER_PAGE_LIMIT, "user");
        page.setTotalCount(this.userDAO.rows());
        // 出来后的page对象已经有了总记录数了，自然就有了页码信息
        inv.addModel("page", page);

        inv.addModel("users", users);
        return "all_users";
    }

    @Get("add")
    public String showAdd() {
        return "add_user";
    }

    @Post("add")
    public String add(final Invocation inv,
                      @Param("password2") final String password2, final User user) {
        if (StringUtils.isEmpty(user.getLoginName().trim())
                || StringUtils.isEmpty(user.getPassword().trim())
                || StringUtils.isEmpty(user.getName().trim())
                || StringUtils.isEmpty(user.getEmail().trim())) {
            inv.addModel("error", "不能为空");
            return "add_user";
        }
        final User userFromDB = this.userDAO
                .getByLoginName(user.getLoginName().trim());
        if (userFromDB != null) {
            inv.addModel("error", "用户名已存在");
            return "add_user";
        }
        if (!password2.equals(user.getPassword().trim())) {
            inv.addModel("error", "两次输入的密码不一致");
            return "add_user";
        }

        Checker checker = new Checker(user.getCardid().trim());
        if (!checker.check()) {
            String errMsgs = "";
            for (String msg : checker.getErrorMsgs()) {
                errMsgs += (msg + " | ");
            }
            inv.addModel("error", "身份证号码验证不通过！原因：" + errMsgs);
            return "add_user";
        }

        user.setBirthday(checker.getPureBirth());
        user.setHomeAddr(checker.getAddr());
        user.setSex(checker.getIntSex());

        //  加密
        user.setPassword(CryptUtils.encrypt(user.getPassword(), CryptUtils.CRYPT_KEY));

        this.userDAO.save(user);
        //  发送激活邮件?????????????????????????性能不好
        EmailUtils.sendAccountActivateEmail(user);

        return "r:" + Utils.path(inv) + "/user";
    }

    /**
     * 个人中心
     *
     * @param inv
     * @return
     */
    @Get("/me")
    public String showMe(final Invocation inv) {
        final User user = (User) inv.getRequest().getSession()
                .getAttribute("loginUser");
        inv.addModel("user", user);
        return "one_user";
    }

    @Get("{uuid:[0-9a-z]{8}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{12}}")
    public String show(final Invocation inv, @Param("uuid") final String uuid,
                       @Param("edit") final boolean edit) {
        final User user = this.userDAO.get(uuid);
        inv.addModel("user", user);
        if (edit) {
            return "one_user_edit";
        } else {
            return "one_user";
        }
    }

    @Post("{uuid:[0-9a-z]{8}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{12}}/info/update")
    public String updateUser(final Invocation inv,
                             @Param("uuid") final String uuid, final User user) {
        final User userFromDB = this.userDAO.get(uuid);
        // 服务器端验证字符的合法性
        if (StringUtils.isEmpty(user.getName().trim())) {
            inv.addModel("user", userFromDB);
            inv.addModel("error", "任何一项都不能为空！");
            return "one_user_edit";
        }
        Utils.updateModel(userFromDB, user);
        this.userDAO.update(userFromDB);
        return "r:" + Utils.path(inv) + "/user/" + uuid;
    }

    @Post("{uuid:[0-9a-z]{8}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{12}}/password/update")
    public String updatePassword(final Invocation inv,
                                 @Param("uuid") final String uuid,
                                 @Param("old_password") final String oldPassword,
                                 @Param("password") final String password,
                                 @Param("password2") final String password2) {
        final User userFromDB = this.userDAO.get(uuid);
        inv.addModel("user", userFromDB);
        // 服务器端验证字符的合法性
        if (StringUtils.isEmpty(oldPassword.trim())
                || StringUtils.isEmpty(password.trim())
                || StringUtils.isEmpty(password2.trim())) {
            inv.addModel("error", "任何一项都不能为空！");
            return "one_user_edit";
        }
        if (!password.equals(password2.trim())) {
            inv.addModel("error", "两次输入的密码不相等！");
            return "one_user_edit";
        }

        final User user = this.userDAO.get(uuid);
        String oldPwd = CryptUtils.encrypt(oldPassword.trim(), CryptUtils.CRYPT_KEY);
        if (!StringUtils.equals(user.getPassword(), oldPwd)) {
            inv.addModel("error", "原密码错误！");
            return "one_user_edit";
        }
        // 加密
        user.setPassword(CryptUtils.encrypt(password.trim(), CryptUtils.CRYPT_KEY));

        this.userDAO.update(user);

        return "r:" + Utils.path(inv) + "/user/" + uuid;
    }

    @Post("{uuid:[0-9a-z]{8}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{12}}/delete")
    @AdminRequired
    public String delete(final Invocation inv, @Param("uuid") final String uuid) {
        //  注销用户
        this.userDAO.canceled(uuid);
        final User user = (User) inv.getRequest().getSession()
                .getAttribute("loginUser");
        if (uuid.equals(user.getUuid())) {
            return logout(inv);
        }
        return "r:" + Utils.path(inv) + "/user";
    }

    @Get("logout")
    public String logout(final Invocation inv) {
        final User user = (User) inv.getRequest().getSession()
                .getAttribute("loginUser");
        final Cookie[] cookies = inv.getRequest().getCookies();
        for (final Cookie cookie : cookies) {
            if (cookie.getName().equals("user")) {
                final String uuid = cookie.getValue();
                if (user.getUuid().equals(uuid)) {
                    final Cookie c = new Cookie(cookie.getName(), null);
                    c.setPath("/");
                    c.setMaxAge(0);
                    inv.getResponse().addCookie(c);
                    break;
                }
            }
        }
        inv.getRequest().getSession().removeAttribute("loginUser");
        inv.getRequest().getSession().invalidate();
        return "r:" + Utils.path(inv) + "";
    }


}
