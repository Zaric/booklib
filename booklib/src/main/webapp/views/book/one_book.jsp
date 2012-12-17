<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../taglibs.jsp" %>
<%@ taglib uri="http://weibo.com/zzuoqiang/page" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查看书籍信息</title>
</head>
<body>
<h1 style="text-align: center">查看书籍信息</h1>

<div align="right">
    <a href="/booklib/book/${book.uuid }?edit=true">修改该书信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/booklib/book">返回书目列表</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<div align="left">
    书的UUID：${book.uuid }<br>
    书的名称：${book.name }<br>
    书的价格：${book.price }<br>
    书的作者：${book.author }<br>
    书的简介：${book.content}<br>
    书的创建时间：${book.createTime }<br>
</div>
<h2>书籍的评论</h2>

<form action="/booklib/book/${book.uuid }/remark/deleteAll" method="post">
    <input type="hidden" value="${book.uuid }" name="bookUid"/>
    <input type="submit" value="删除所有评论"/>
</form>

<table width="800" border="1">
    <c:forEach var="item" items="${remarks }">
        <tr>
            <td>
                    ${item.uuid }&nbsp;&nbsp;
                    ${item.userName }&nbsp;&nbsp;
                    ${item.bookUid }&nbsp;&nbsp;
                    ${item.createTime }<br>
                    ${item.essay }
                <form action="/booklib/book/${item.bookUid }/remark/${item.uuid }/delete?pageIndex=${page.pageIndex }"
                      method="post">
                    <input type="hidden" value="${item.bookUid }" name="bookUid"/>
                    <input type="hidden" value="${item.uuid }" name="remarkUid"/>
                    <input type="submit" value="删除此评论"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    <!-- 分页 -->
    <tr bgcolor="#CCCCCC">
        <td colspan="1" align="right" valign="middle">
            <!-- 注意了，两个字母实现通用分页 -->
            <t:p/>
        </td>
    </tr>
</table>
<br>

<form action="/booklib/book/${book.uuid }/remark/add" method="post">
    <input type="hidden" value="${book.uuid }" name="bookUid"/>
    <input type="text" size="80" value="" name="essay"/>
    <input type="submit" value="提交评论"/>
</form>
<br>
<font color=red>${remark_error}</font>
</body>
</html>