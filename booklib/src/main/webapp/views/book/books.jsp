<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../taglibs.jsp" %>
<%@ taglib uri="http://weibo.com/zzuoqiang/page" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>浏览书目</title>
</head>
<body>
<h1 style="text-align: center">所有书籍</h1>

<div align="center">
    <a href="/booklib/book/add">增加一本书</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/booklib/user">所有用户</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/booklib/user/add">增加一个新用户</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/booklib/user/me">用户中心</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/booklib/logs">操作日志</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/booklib/user/logout">退出登录</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <table border="1">
        <tr>
            <th>书目UUID</th>
            <th>书名</th>
            <th>价格</th>
            <th>作者</th>
            <th>摘要</th>
            <th>添加时间</th>
            <th>操作</th>
        </tr>
        <c:forEach var="item" items="${books}">
            <tr>
                <td>${item.uuid }</td>
                <td><a href="/booklib/book/${item.uuid }">${item.name }</a></td>
                <td>${item.price }</td>
                <td>${item.author }</td>
                <td>${item.summary }</td>
                <td>${item.createTime }</td>
                <td>
                    <form action="/booklib/book/${item.uuid}/delete" method="post">
                        <input type="hidden" value="${item.uuid}" name="uuid"/>
                        <input type="submit" value="删除">
                    </form>
                </td>
            </tr>
        </c:forEach>
        <!-- 分页 -->
        <tr bgcolor="#CCCCCC">
            <td colspan="9" align="right" valign="middle">
                <!-- 注意了，两个字母实现通用分页 -->
                <t:p/>
            </td>
        </tr>
    </table>
</div>
</body>
</html>