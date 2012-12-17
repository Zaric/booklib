<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>图书管理系统</title>
</head>
<body>
登录规定 进入图书库系统必须进行登录，但一旦登录之后，不同用户之间的权限没有太大区别，
但是所有的操作都有记录。这些操作记录是只读记录，且只有一个名为rose的用户才能看到。 用户的帐号由rose用户进行创建和注销。
用户登录到系统，要列出所有的书本清单，每页100本。 功能 任何用户可增加书、更改书的信息，但只有rose用户才能删书。
任何用户都可以为书编写备注、备注的个数不限制个数。任何人不能删除备注，包括自己的备注，但rose管理员可以删除。
<br>
<br>
<a href="/booklib/book">进入应用</a>
</body>
</html>