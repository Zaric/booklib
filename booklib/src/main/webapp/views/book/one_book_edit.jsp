<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改书籍信息</title>

    <link rel="stylesheet" href="<%=contextPath %>/resources/kindeditor/themes/default/default.css"/>

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
        书的简介：
        <textarea id="editor_id" name="content" style="width:800px;height:400px;">
            ${book.content}
        </textarea>
        <br>
        书的创建时间：${book.createTime }<br>
        <input type="submit" value="提交修改"/><br>
        <font color=red>${error }</font>
    </form>
</div>

<script charset="GBK" src="<%=contextPath %>/resources/kindeditor/kindeditor.js"></script>
<script charset="GBK" src="<%=contextPath %>/resources/kindeditor/lang/zh_CN.js"></script>

<script>
    var editor;
    KindEditor.ready(function (K) {
        editor = K.create(
                'textarea[name="content"]',
                {
                    allowPreviewEmoticons: false,
                    autoHeightMode: true,
                    uploadJson: '../resources/kindeditor/jsp/upload_json.jsp',
                    fileManagerJson: '../resources/kindeditor/jsp/file_manager_json.jsp',
                    allowFileManager: true,
                    afterCreate: function () {
                        this.loadPlugin('autoheight');
                        this.loadPlugin('insertfile');
                    }
                });
    });

    // 取得HTML内容
    html = editor.html();

    // 同步数据后可以直接取得textarea的value
    editor.sync();
    html = document.getElementById('editor_id').value; // 原生API
    html = K('#editor_id').val(); // KindEditor Node API
    html = $('#editor_id').val(); // jQuery

    // 设置HTML内容
    editor.html('HTML内容');

</script>
</body>
</html>