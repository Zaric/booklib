<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../taglibs.jsp" %>
<%@ taglib uri="http://weibo.com/zzuoqiang/page" prefix="t" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>操作记录</title>
</head>
<body>
<h1 style="text-align: center">操作记录</h1>

<div align="right">
    <a href="/booklib/book">返回书目列表</a>
</div>
<div align="center">
    <table border="1">
        <tr>
            <th>操作UUID</th>
            <th>操作人</th>
            <th>IP</th>
            <th>资源模式</th>
            <th>资源地址</th>
            <th>是否成功</th>
            <th>额外说明</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        <c:forEach var="item" items="${logs}">
            <tr>
                <td>${item.uuid }</td>
                <td>${item.userName }</td>
                <td>${item.ip }</td>
                <td>${item.resourcePattern }</td>
                <td>${item.resourceId }</td>
                <td>${item.success }</td>
                <td>${item.remarks }</td>
                <td>${item.createTime }</td>
                <td>
                    <form
                            action="/booklib/logs/${item.uuid }/delete?pageIndex=${page.pageIndex }"
                            method="post">
                        <input type="hidden" value="${item.uuid }" name="uuid"/> <input
                            type="submit" value="删除">
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