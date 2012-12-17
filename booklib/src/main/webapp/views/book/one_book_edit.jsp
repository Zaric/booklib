<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改书籍信息</title>
</head>
<body>
<h1 style="text-align: center">修改书的详细信息</h1>

<div align="right">
    <a href="/booklib/book">返回书目列表</a>
</div>
<div align="center">
    <form action="/booklib/book/${book.uuid }/update" method="post">
        书的ID：${book.uuid }<br>
        <input type="hidden" value="${book.uuid }" name="uuid"/>
        书的名称：${book.name }<br>
        书的价格：<input type="text" value="${book.price }" name="price"/><br>
        书的作者：${book.author}<br>
        书的简介：<textarea rows="20" cols="60" value="${book.content }" name="content"></textarea><br>
        书的创建时间：${book.createTime }<br>
        <input type="submit" value="提交修改"/><br>
        <font color=red>${error }</font>
    </form>
</div>
</body>
</html>