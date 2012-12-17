package com.book.controllers;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import com.book.util.Utils;

/**
 * @author zhangzuoqiang
 * @Email z.zuoqiang@gmail.com
 * @time 2012-3-12 下午6:02:28
 * @Todo 主页控制器，使用自定义路径映射@Path
 * @Modify
 */
@Path("")
public class HomeController {

    @Get("")
    public String redirect(final Invocation inv) {
        return "r:" + Utils.path(inv) + "/book";
    }
}
