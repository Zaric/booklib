package com.book.controllers;

import com.book.util.Utils;
import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.rest.Get;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 图形验证码
 */
public class CaptchaController {

    @Get("")
    public String requestCaptcha(final Invocation inv) throws IOException {
        // 验证码图片的宽度
        int width = 80;
        // 验证码图片的高度
        int height = 35;
        BufferedImage buffImg = Utils.BUFFERED_IMAGE;
        Graphics2D g = buffImg.createGraphics();

        // 创建一个随机数生成器类
        Random random = Utils.RANDOM;

        // 设定图像背景色(因为是做背景，所以偏淡)
        g.setColor(Utils.getRandColor(180, 250));
        g.fillRect(0, 0, width, height);
        // 创建字体，字体的大小应该根据图片的高度来定
        Font font = Utils.CAPTCHA_FONT;
        // 设置字体
        g.setFont(font);

        // 画边框。
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);
        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到
        g.setColor(Color.GRAY);
        for (int i = 0; i < 50; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证
        StringBuffer randomCode = new StringBuffer();

        // 设置默认生成4个验证码
        int length = 4;
        // 设置备选验证码:包括"a-z", "A-Z"和数字"0-9" 除去字母 l o 和数字 1 0 增加用户体验
        String base = "abcdefghijkmnpqrstuvwxyz23456789ABCDEFJKMNPQRSTUVWXYZ";

        int size = base.length();

        // 随机产生4位数字的验证码
        for (int i = 0; i < length; i++) {
            // 得到随机产生的验证码数字
            int start = random.nextInt(size);
            String strRand = base.substring(start, start + 1);

            // 用随机产生的颜色将验证码绘制到图像中
            // g.setColor(new Color(red,green,blue));
            // 生成随机颜色(因为是做前景，所以偏深)
            g.setColor(Utils.getRandColor(1, 100));
            g.drawString(strRand, 13 * i + 6, 28);

            // 将产生的四个随机数组合在一起
            randomCode.append(strRand);
        }
        // 将四位数字的验证码保存到Session中
        HttpSession session = inv.getRequest().getSession();
        session.setAttribute(Utils.VERIFY_CODE, randomCode.toString());

        HttpServletResponse response = inv.getResponse();

        // 禁止图像缓存
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        response.setContentType("image/jpeg");

        // 将图像输出到Servlet输出流中
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();

        return null;
    }

}
