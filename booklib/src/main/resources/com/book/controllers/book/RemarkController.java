package com.book.controllers.book;

import com.book.controllers.AdminRequired;
import com.book.controllers.LoginRequired;
import com.book.dao.RemarkDAO;
import com.book.model.Page;
import com.book.model.Remark;
import com.book.model.User;
import com.book.util.Utils;
import com.book.util.WebUtil;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Post;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangzuoqiang
 * @Todo 书本评论控制器
 * @Modify
 */
@LoginRequired
@Path("{uuid:[0-9a-z]{8}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{4}[0-9a-z]{12}}/remark")
public class RemarkController {

    private static final int PER_PAGE_LIMIT = 10;

    @Autowired
    private RemarkDAO remarkDAO;

    /**
     * @param inv
     * @param remark
     * @return
     * @Todo 增加一个评论
     * @Modify
     */
    @Post("add")
    public String add(final Invocation inv, final Remark remark) {
        if (StringUtils.isEmpty(remark.getEssay())) {
            inv.addModel("book",
                    inv.getRequest().getSession().getAttribute("book"));
            inv.addModel("remarks",
                    inv.getRequest().getSession().getAttribute("remarks"));

            int pageIndex = WebUtil
                    .getIntByRequestParament(inv, "pageIndex", 1);

            // 构造一个page对象，第1个参数是当前页，第2个参数是该页最大记录数，第3个是页码上的连接地址
            Page page = new Page(pageIndex, PER_PAGE_LIMIT, remark.getBookUid());
            page.setTotalCount(this.remarkDAO.rows(remark.getBookUid()));
            // 出来后的page对象已经有了总记录数了，自然就有了页码信息
            inv.addModel("page", page);

            inv.addModel("remark_error", "评论内容不能为空");
            // 使用绝对路径时，要加上JSP后缀名
            return "/views/book/one_book.jsp";
        }
        final User user = (User) inv.getRequest().getSession()
                .getAttribute("loginUser");
        remark.setUserName(user.getLoginName());
        this.remarkDAO.save(remark);
        return "r:" + Utils.path(inv) + "/book/" + remark.getBookUid();
    }

    /**
     * @param bookUid
     * @return
     * @Todo 清除所有评论
     * @Modify
     */
    @Post("deleteAll")
    @AdminRequired
    public String clear(final Invocation inv, @Param("bookUid") final String bookUid) {
        this.remarkDAO.deleteByBook(bookUid);
        return "r:" + Utils.path(inv) + "/book/" + bookUid;
    }

    /**
     * @param bookUid
     * @param remarkUid
     * @param pageIndex
     * @return
     * @Todo 删除指定UUID的评论
     * @Modify
     */
    @Post("{remarkUid}/delete")
    @AdminRequired
    public String delete(final Invocation inv,
                         @Param("bookUid") final String bookUid,
                         @Param("remarkUid") final String remarkUid,
                         @Param("pageIndex") final int pageIndex) {
        this.remarkDAO.delete(remarkUid);
        return "r:" + Utils.path(inv) + "/book/" + bookUid + "?pageIndex="
                + pageIndex;
    }
}
