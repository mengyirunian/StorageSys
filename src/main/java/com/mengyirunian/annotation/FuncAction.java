package com.mengyirunian.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Jiaxiayuan on 2018/1/12
 */
@Target(ElementType.METHOD)
@Retention(RUNTIME)
public @interface FuncAction {

    String parent();
    String name();

}
