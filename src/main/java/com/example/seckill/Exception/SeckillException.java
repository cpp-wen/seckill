/*
 * betahouse.us
 * CopyRight (c) 2012 - 2020
 */
package com.example.seckill.Exception;

import com.example.seckill.dto.SeckillExecution;

/**
 * @author guofan.cp
 * @version : SeckillException.java 2020/02/15 21:51 guofan.cp
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }
    public SeckillException(String message, Throwable cause){
        super(message,cause);
    }
}
