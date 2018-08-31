package com.jettian.pojocheck.exception;

/**
 * @author tianjie
 * @version V1.0
 * @description 校验失败错误类;
 * @date 2018/8/20 10:03
 * @email tj_work@yeah.net
 */
public class PojoWrongException extends  RuntimeException{

    public PojoWrongException(String message) {
        super(message);
    }
}
