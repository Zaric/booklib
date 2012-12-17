<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>登陆</title>
</head>
<body>
<h1 style="text-align: center">登录</h1>

<div align="center">
    <form action="/booklib/login" method="post">
        登录名：<input type="text" value="${loginName }" name="loginName"/>
        <br>&nbsp;&nbsp;&nbsp;
        密码：<input type="password" value="" name="password"/><br>

        验证码：<input type="text" value="" maxlength="4" size="8" name="verifyCode"/>
        <img alt="" src="captcha" onclick="changeimg();" id="captchaImg"
             width="60" height="21" style="margin-bottom: -5px">
        <a href="javascript:changeimg();">看不清? 换一张</a><br>

        <input type="submit" value="登陆"/><br>
        <font color=red>${error}</font>
    </form>
</div>

<script>
    function changeimg() {
        var captchaImg = document.getElementById("captchaImg");
        //随机生成一个数字，让图片缓冲区认为不是同一个缓冲区
        captchaImg.src = "captcha?" + Math.random();
        event.cancelBubble = true;
    }
</script>
</body>
</html>