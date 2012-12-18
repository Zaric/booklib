package com.book.taglib;

import com.book.model.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

/**
 * 页码标签程序，特点： 1：5页后当前页码总在中间 2：页码上的URL地址支持多种方式，如：
 * page.setUrl("http://www.zhangjihao.com/article/list_*.do");
 * “*”号会被替换成页码，Spring MVC支持这种伪静态地址
 * page.setUrl("http://www.zhangjihao.com/article/list.do"); 地址后面会自动加上页码参数
 * page.setUrl("http://www.zhangjihao.com/article/list.do?cid=1");
 * 地址后面会自动加上页码参数，不影响多参数连接。 3：页码显示样式可由外面css样式传入
 *
 * @author zhangzuoqiang
 * @Email z.zuoqiang@gmail.com
 * @time 2012-3-13 下午2:39:31
 * @Modify
 */
public class PagePrint extends TagSupport {

    private static final long serialVersionUID = 1L;

    /**
     * 是否显示总记录数
     */
    private boolean totalcount = false; // 如果外面不指定，将默认为显示

    /**
     * 是否显示总页数
     */
    private boolean pagecount = false; // 如果外面不指定，将默认为显示

    /**
     * 当前页码的显示样式 *
     */
    private String curPageNumberStyle = "pagenumbervisited"; // 如果外面不指定，将按默认值

    /**
     * 带链接的页码的显示样式 *
     */
    private String linkPageNumberStyle = "pagenumber";// 如果外面不指定，将按默认值

    public int doStartTag() throws JspException {
        // 在标签类中定义Request对象
        HttpServletRequest request = (HttpServletRequest) pageContext
                .getRequest();
        String curPath = request.getContextPath();

        if (request.getAttribute("page") != null) {
            // 大家还记得我们在控制层将page对象放到了Request作用里吗？现在是用它的时候了
            Page page = (Page) request.getAttribute("page");
            JspWriter out = pageContext.getOut();
            StringBuffer sb = new StringBuffer("");

            try {
                // 如果设定了要打印总页数，则在JSP页面中输出当前页和总页数
                if (this.isPagecount()) {
                    sb.append("当前页/总页数：" + page.getPageIndex() + "/"
                            + page.getPageCount() + "&nbsp;&nbsp;&nbsp;");
                }
                // sb.append("页码：");
                // 如果当前页码大于1时，应该输出开始一页和上一页的连接
                if (page.getPageIndex() > 1) {
                    sb.append("<a title=\"首页\" href=\""
                            + conversionUrl(page.getUrl(), 1)
                            + "\">"
                            + "<img src=\""
                            + curPath
                            + "/pageimgs/navigation_top.png\" border=\"0\"/></a>");
                    sb.append("<a title=\"上一页\"   href=\""
                            + conversionUrl(page.getUrl(),
                            page.getPageIndex() - 1)
                            + "\"><img src=\""
                            + curPath
                            + "/pageimgs/navigation_previous.png\" border=\"0\"/></a>&nbsp;&nbsp;");
                }

                // 开始在总页数里循环
                for (int cur = 1; cur <= page.getPageCount(); cur++) {
                    // 假如有几十页、几百页、甚至n多，我们不能全部将页码输出吧？这里只取10页
                    if (page.getPageIndex() < 5 && cur < 10) {
                        if (cur == page.getPageIndex()) {
                            // 当前页不应该有连接
                            sb.append("<span class=\"pagenumbervisited\">"
                                    + cur + "</span>&nbsp;");
                        } else {
                            sb.append("<a href=\""
                                    + conversionUrl(page.getUrl(), cur)
                                    + "\" class=\"pagenumber\" title=\"第" + cur
                                    + "页\">" + cur + "</a>&nbsp;");
                        }
                    } else {
                        // 进入5页以后，要让当前页码总在中间。
                        if (cur > page.getPageIndex() - 5
                                && cur < page.getPageIndex() + 5) {
                            if (cur == page.getPageIndex()) {
                                sb.append("<span class=\"pagenumbervisited\">"
                                        + cur + "</span>&nbsp;");
                            } else {
                                sb.append("<a href=\""
                                        + conversionUrl(page.getUrl(), cur)
                                        + "\"class=\"pagenumber\" title=\"第"
                                        + cur + "页\">" + cur + "</a>&nbsp;");
                            }
                        }
                    }
                }

                // 只要没有进到最后一页，都应该输出下一面和最后一页连接
                if (page.getPageIndex() < page.getPageCount()) {
                    sb.append("&nbsp;&nbsp;<a title=\"下一页\" href='"
                            + conversionUrl(page.getUrl(),
                            page.getPageIndex() + 1)
                            + "'><img src=\""
                            + curPath
                            + "/pageimgs/navigation_next.png\" border=\"0\"/></a>");
                    sb.append("<a title=\"尾页\" href='"
                            + conversionUrl(page.getUrl(), page.getPageCount())
                            + "'><img src=\""
                            + curPath
                            + "/pageimgs/navigation_bott.png\" border=\"0\"/></a>\n");
                }

                // 标记中调用是否指定输出每页记录数和总记录数
                if (this.isTotalcount())
                    sb.append("&nbsp;&nbsp;&nbsp;每页记录数/总记录数："
                            + page.getPageSize() + "/" + page.getTotalCount());

            } catch (Exception e) {
                sb.append(e.getMessage());
            } finally {

                // 往页面上输出
                try {
                    out.println(sb.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return TagSupport.SKIP_BODY;
    }

    // ////////////////////////////////////辅助方法////////////////////////////////////////////

    private String conversionUrl(String url, int pageindex) {
        if (url == null || url.length() < 1) {
            return "index.jsp";
        }

        if (url.indexOf('*') > -1) {
            return url.replace("*", String.valueOf(pageindex));
        } else {
            StringBuffer sb = new StringBuffer(url);
            if (sb.indexOf("?") > -1) {
                char c = sb.charAt(sb.length() - 1);
                if (c == '?' || c == '&') {
                    sb.append(Page.pageNumberParameterName + "=" + pageindex);
                } else {
                    sb.append("&" + Page.pageNumberParameterName + "="
                            + pageindex);
                }
            } else {
                sb.append("?" + Page.pageNumberParameterName + "=" + pageindex);
            }
            return sb.toString();
        }
    }

    // ///////////////////////////////Setter&Getter/////////////////////////////////

    public boolean isTotalcount() {
        return totalcount;
    }

    public boolean isPagecount() {
        return pagecount;
    }

    public void setTotalcount(boolean totalcount) {
        this.totalcount = totalcount;
    }

    public void setPagecount(boolean pagecount) {
        this.pagecount = pagecount;
    }

    public String getCurPageNumberStyle() {
        return curPageNumberStyle;
    }

    public void setCurPageNumberStyle(String curPageNumberStyle) {
        this.curPageNumberStyle = curPageNumberStyle;
    }

    public String getLinkPageNumberStyle() {
        return linkPageNumberStyle;
    }

    public void setLinkPageNumberStyle(String linkPageNumberStyle) {
        this.linkPageNumberStyle = linkPageNumberStyle;
    }

}