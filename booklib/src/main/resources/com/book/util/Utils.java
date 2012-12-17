package com.book.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import net.paoding.rose.web.Invocation;

/**
 * @author zhangzuoqiang
 */
public class Utils {

    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final Random RANDOM = new Random();

    // 验证码图片的宽度
    public static final int WIDTH = 80;
    // 验证码图片的高度
    public static final int HEIGHT = 35;
    public static final BufferedImage BUFFERED_IMAGE = new BufferedImage(WIDTH, HEIGHT,
            BufferedImage.TYPE_INT_RGB);
    // 创建字体，字体的大小应该根据图片的高度来定
    public static final Font CAPTCHA_FONT = new Font("Times New Roman", Font.PLAIN, 28);

    public static final String VERIFY_CODE = "verifyCode";

    /**
     * @param fc
     * @param bc
     * @return
     */
    public static Color getRandColor(int fc, int bc) {
        // 给定范围获得随机颜色
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + RANDOM.nextInt(bc - fc);
        int g = fc + RANDOM.nextInt(bc - fc);
        int b = fc + RANDOM.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * @return
     */
    public static String getCurrentTime() {
        long currentTime = System.currentTimeMillis();
        Date date = new Date(currentTime);
        return DATE_FORMAT.format(date);
    }

    public static String getFormatTime(String intTime) {
        return DATE_FORMAT.format(new Date(Long.parseLong(intTime) * 1000));
    }

    /**
     * @param inv
     * @return
     * @Todo 获得web项目名
     */
    public static String path(final Invocation inv) {
        if (null == inv) {
            return "booklib";
        }
        return inv.getRequest().getContextPath();
    }

    /**
     * @param src
     * @param newFieldModel
     * @Todo 根据一个model里的值更新另一个model里的值，如果newFieldModel的某个字段为null就不更新，表示以src的值为准 。
     * 典型的应用是在保存一个POJO时，从页面传过来的POJO只包含了要更新的值，而我们的DAO的更新语句一般都是把字段都带上了的
     * 所以这时候就需要从数据库查询出model，然后将页面的POJO的不为null值赋值给数据库的POJO，然后保存至数据库。
     * @Modify
     */
    public static <T> void updateModel(final T src, final T newFieldModel) {
        final Field[] fields = src.getClass().getDeclaredFields();
        for (final Field f : fields) {
            f.setAccessible(true);
            try {
                final Object value = f.get(newFieldModel);
                if (value != null) {
                    f.set(src, value);
                }
            } catch (final Exception e) {
                e.printStackTrace();
            }
        }
    }
}
