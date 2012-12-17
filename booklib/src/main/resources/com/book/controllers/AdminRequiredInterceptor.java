package com.book.controllers;

import java.lang.annotation.Annotation;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;

import com.book.model.User;

/**
 * @author zhangzuoqiang
 */
public class AdminRequiredInterceptor extends ControllerInterceptorAdapter {

    public AdminRequiredInterceptor() {
        this.setPriority(800);
    }

    @Override
    protected Class<? extends Annotation> getRequiredAnnotationClass() {
        return AdminRequired.class;
    }

    @Override
    protected Object before(final Invocation inv) throws Exception {
        final User loginUser = (User) inv.getRequest().getSession()
                .getAttribute("loginUser");
        // 如果不是管理员
        if (!("1").equals(loginUser.getGroups())) {
            // 没有返回true或null，表示要中断整个处理流程，即不再继续调用其他拦截器以及最终的控制器
            inv.addModel("error", "你没有管理员权限");
            // 使用绝对路径，如果直接return "error"，删除remark的时候，路径是在book下，会出现404
            // 使用绝对路径时，要加上JSP后缀名
            return "/views/error.jsp";
        }
        // 返回true或null，表示继续整个流程
        return true;
    }
}
