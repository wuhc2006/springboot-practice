package com.whc.config.exception;

/**
 * @author Administrator
 * @date 2019/1/5 15:51
 */
public class UnauthorizedException extends RuntimeException{

    private static final long serialVersionUID = 1082692679846863960L;

    public UnauthorizedException(String msg){
        super(msg);
    }

    public UnauthorizedException(){
        super();
    }
}
