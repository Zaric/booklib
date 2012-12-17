<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>增加用户</title>
</head>
<body>
<h1 style="text-align: center">增加一名用户</h1>

<div align="right">
    <a href="/booklib/book">返回书目列表</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="/booklib/user">返回用户列表</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</div>
<div align="center">
    <form action="/booklib/user/add" method="post">
        登录名：<input type="text" value="" name="loginName"/><br>
        电邮：<input type="text" value="" name="email"/><br>
        密码： <input type="password" value="" name="password"/><br>
        确认密码：<input type="password" value="" name="password2"/><br>
        用户组别：
        <select name="groups">
            <option value="0">普通用户</option>
            <option value="1">管理用户</option>
        </select><br>
        姓名：<input type="text" value="" name="name"/><br>
        身份证：<input type="text" value="" name="cardid"/><br>
        <input type="submit" value="增加"/><br>
        <font color=red>${error}</font>
    </form>
</div>
</body>
</html>