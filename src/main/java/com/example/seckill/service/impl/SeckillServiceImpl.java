/*
 * betahouse.us
 * CopyRight (c) 2012 - 2020
 */
package com.example.seckill.service.impl;

import com.example.seckill.Exception.RepeatKillException;
import com.example.seckill.Exception.SeckillCloseException;
import com.example.seckill.Exception.SeckillException;
import com.example.seckill.dto.Exposer;
import com.example.seckill.dto.SeckillExecution;
import com.example.seckill.entity.Seckill;
import com.example.seckill.entity.SeckillOrder;
import com.example.seckill.enums.SeckillStatEnum;
import com.example.seckill.mapper.SeckillMapper;
import com.example.seckill.mapper.SeckillOrderMapper;
import com.example.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author guofan.cp
 * @version : SeckillServiceImpl.java 2020/02/15 21:54 guofan.cp
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private static final String salt = "asfs234fd-ajfasw";
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillMapper seckillMapper;
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;

    @Override
    public List<Seckill> findAll() {
        return seckillMapper.findAll();
    }

    @Override
    public Seckill findById(long seckillId) {
        return seckillMapper.findById(seckillId);
    }

    @Override
    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillMapper.findById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMd5(long seckillId) {
        String base = seckillId + "/" + salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }


    @Override
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, BigDecimal money, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(getMd5(seckillId))) {
            throw new SeckillException("seckill data rewrtite");
        }
        Date nowTime = new Date();
        try {
            int insertCount = seckillOrderMapper.insertOrder(seckillId, money, userPhone);
            if (insertCount <= 0) {
                throw new RepeatKillException("seckill repeated");
            } else {
                SeckillOrder seckillOrder = seckillOrderMapper.findById(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, seckillOrder);
            }
        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage(), e);
            //编译器异常转换为运行期间异常
            throw new SeckillException("seckill inner errot ");
        }
    }
}
