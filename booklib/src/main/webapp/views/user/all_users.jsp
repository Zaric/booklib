<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../taglibs.jsp" %>
<%@ taglib uri="http://weibo.com/zzuoqiang/page" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>所有用户</title>
</head>
<body>
<h1 style="text-align: center">所有用户</h1>

<div align="right">
    <a href="/booklib/user/add">增加一个用户</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/booklib/book">返回书目列表</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>

<div align="center">
    <table border="1">
        <tr>
            <th>用户UUID</th>
            <th>登录名</th>
            <th>电邮</th>
            <th>姓名</th>
            <th>身份证</th>
            <th>性别</th>
            <th>出生日期</th>
            <th>籍贯</th>
            <th>状态</th>
            <th>用户组别</th>
            <th>创建时间</th>
            <th>激活时间</th>
        </tr>

        <c:forEach var="item" items="${users}">
            <tr>
                <td align="center">${item.uuid }</td>
                <td><a href="/booklib/user/${item.uuid }">${item.loginName }</a></td>
                <td>${item.email }</td>
                <td>${item.name }</td>
                <td>${item.cardid }</td>
                <td>
                    <c:choose>
                        <c:when test="${item.sex == 0 }">未知</c:when>
                        <c:when test="${item.sex == 1 }">男</c:when>
                        <c:when test="${item.sex == 2 }">女</c:when>
                    </c:choose>
                </td>
                <td>${item.birthday }</td>
                <td>${item.homeAddr }</td>
                <td>
                    <c:choose>
                        <c:when test="${item.status == 0 }">未激活</c:when>
                        <c:when test="${item.status == 1 }">已激活</c:when>
                        <c:when test="${item.status == 2 }">已注销</c:when>
                        <c:otherwise>未知</c:otherwise>
                    </c:choose>
                </td>
                <td>
                    <c:choose>
                        <c:when test="${item.groups == 0 }">普通用户</c:when>
                        <c:otherwise>管理用户</c:otherwise>
                    </c:choose>
                </td>
                <td>${item.createTime }</td>
                <td>${item.formatTime }</td>
            </tr>
        </c:forEach>

        <!-- 分页 -->
        <tr bgcolor="#CCCCCC">
            <td colspan="12" align="right" valign="middle">
                <!-- 注意了，两个字母实现通用分页 -->
                <t:p/>
            </td>
        </tr>
    </table>
</div>
</body>
</html>