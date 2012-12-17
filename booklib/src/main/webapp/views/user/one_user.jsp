<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查看用户信息</title>
</head>
<body>
<h1 style="text-align: center">查看用户信息</h1>

<div align="right">
    <a href="/booklib/user/${user.uuid }?edit=true">修改该用户的信息</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/booklib/book">返回书目列表</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<div align="center">
    UUID：${user.uuid }<br> 登录名：${user.loginName }<br>
    姓名：${user.name}<br>
    身份证：${user.cardid}<br>
    性别：
    <c:choose>
        <c:when test="${user.sex == 0 }">未知</c:when>
        <c:when test="${user.sex == 1 }">男</c:when>
        <c:when test="${user.sex == 2 }">女</c:when>
    </c:choose><br>
    出生日期：${user.birthday}<br>
    籍贯：${user.homeAddr}<br>
    电邮：${user.email}<br>
    状态：
    <c:choose>
        <c:when test="${user.status == 0 }">未激活</c:when>
        <c:when test="${user.status == 1 }">已激活</c:when>
        <c:when test="${user.status == 2 }">已注销</c:when>
        <c:otherwise>未知</c:otherwise>
    </c:choose><br>
    用户组别：
    <c:choose>
        <c:when test="${user.groups == 0 }">普通用户</c:when>
        <c:otherwise>管理用户</c:otherwise>
    </c:choose>
    <br> 创建时间：${user.createTime }
    <br> 激活时间：${user.formatTime }<br>

    <form action="/booklib/user/${user.uuid }/delete" method="post">
        <input type="submit" value="注销此用户"/><br>
        <font color=green>${info}</font>
    </form>
</div>
</body>
</html>