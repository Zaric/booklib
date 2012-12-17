package com.book.controllers;

import com.book.dao.UserDAO;
import com.book.model.User;
import com.book.util.GenerateLinkUtils;
import com.book.util.Utils;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.rest.Get;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: Mooqu.cn
 * Date: 12-12-12
 * Time: 下午4:24
 * To change this template use File | Settings | File Templates.
 */
public class ActivateAccountController {

    @Autowired
    private UserDAO userDAO;

    @Get("")
    public String activation(final Invocation inv, @Param("loginName") final String loginName,
                             @Param("checkCode") final String checkCode) {
        final User user = this.userDAO.getByLoginName(loginName);
        if (null == user
                || !GenerateLinkUtils.verifyCheckcode(user, checkCode)) {
            inv.addModel("error", "您的激活链接无效！");
            return "r:" + Utils.path(inv);
        }

        if (GenerateLinkUtils.verifyCheckcode(user, checkCode) && user.getStatus() == 2) {
            inv.addModel("error", "您的账户已注销！");
            return "r:" + Utils.path(inv);
        }

        if (GenerateLinkUtils.verifyCheckcode(user, checkCode) && 1 == user.getStatus()) {
            inv.addModel("error", "您的账户已在[ " + user.getFormatTime() + " ]成功激活！请登录！");
        }

        //  更新激活状态和激活时间，并保存
        user.setStatus(1);
        user.setActivedTime(Utils.getCurrentTime());
        this.userDAO.updateStatus(user);

        inv.addModel("loginName", loginName);

        return "r:" + Utils.path(inv) + "/login";
    }

}
