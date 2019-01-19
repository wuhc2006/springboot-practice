package com.whc.config.exception;

/**
 * @ClassName UnauthorizedException
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/5 15:51
 * @Version 1.0
 */
public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String msg){
        super(msg);
    }

    public UnauthorizedException(){
        super();
    }
}
