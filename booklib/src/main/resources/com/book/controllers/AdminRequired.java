package com.book.controllers;


import java.lang.annotation.*;

/**
 * @Author zhangzuoqiang
 * @Date Nov 23, 2012 12:29:15 AM
 */
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AdminRequired {

}
