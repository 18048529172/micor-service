package com.micro.exception;

/**
 * 说明：参数验证异常
 *
 * @author liw@suncd.com
 * @date 2017/12/25 11:30
 */
public class ArgumentCheckException extends AbsException {

    public ArgumentCheckException(String message) {
        super(ExceptionCodes.ARGUMENT_ERROR,message);
    }

}
