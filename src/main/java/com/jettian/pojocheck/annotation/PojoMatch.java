package com.jettian.pojocheck.annotation;

import com.jettian.pojocheck.checkintf.PojoCheckIntf;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tianjie
 * @version V1.0
 * @description 校验长度,作用于实体上
 * @date 2018/8/20 09:02
 * @email tj_work@yeah.net
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PojoMatch {

    //校验此作用字段长度
    int value() default -1;

    //校验字段是否能为空,默认为允许
    boolean cannull() default true;

    //正则表达式校验
    String RE() default "";

    //自定义校验类校验，实现校验接口
    Class<? extends PojoCheckIntf> checkClass() default PojoCheckIntf.class;
}
