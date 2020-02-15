/*
 * betahouse.us
 * CopyRight (c) 2012 - 2020
 */
package com.example.seckill.service;

import com.example.seckill.Exception.RepeatKillException;
import com.example.seckill.Exception.SeckillCloseException;
import com.example.seckill.Exception.SeckillException;
import com.example.seckill.dto.Exposer;
import com.example.seckill.dto.SeckillExecution;
import com.example.seckill.entity.Seckill;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author guofan.cp
 * @version : SeckillService.java 2020/02/15 20:27 guofan.cp
 */
public interface SeckillService {
    /**
     * 获取所有秒杀商品列表
     * @return
     */
    List<Seckill> findAll();

    /**
     * 获取某一条商品秒杀信息
     * @param seckillId
     * @return
     */
    Seckill findById(long seckillId);

    Exposer exportSeckillUrl(long seckillId);

    SeckillExecution executeSeckill(long seckillId, BigDecimal money,long userPhone,String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;
}
