package com.yeamy.pattern;

import android.support.annotation.IdRes;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 在ContentView内绑定点击事件
 */
@Retention(RUNTIME)
@Target({METHOD})
public @interface BindClick {
    @IdRes int value();
}