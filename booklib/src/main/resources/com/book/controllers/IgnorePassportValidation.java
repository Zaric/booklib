package com.book.controllers;

import java.lang.annotation.*;

/**
 * @Author zhangzuoqiang
 * @Date Nov 23, 2012 12:36:05 AM
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface IgnorePassportValidation {

}
