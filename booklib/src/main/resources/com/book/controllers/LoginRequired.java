package com.book.controllers;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//这是一个annotation，所谓annotation就是一个“标签”，他的职责是“表明”
//至于表明之后该怎么样？则由其他代码来处理
//对于LoginRequired具体的处理代码是LoginRequiredInterceptor.java
@Inherited
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginRequired {

}
