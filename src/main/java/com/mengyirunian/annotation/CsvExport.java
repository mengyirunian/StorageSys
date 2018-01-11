package com.mengyirunian.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Jiaxiayuan on 2017/12/29
 */
@Target({ FIELD })
@Retention(RUNTIME)
public @interface CsvExport {

    /**
     * 表头信息
     *
     * @return
     */
    String head() default "";

}
