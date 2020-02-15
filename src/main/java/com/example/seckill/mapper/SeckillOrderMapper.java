/*
 * betahouse.us
 * CopyRight (c) 2012 - 2020
 */
package com.example.seckill.mapper;

import com.example.seckill.entity.SeckillOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author guofan.cp
 * @version : SeckillOrderMapper.java 2020/02/15 17:06 guofan.cp
 */
@Mapper
public interface SeckillOrderMapper {

    /**
     * 插入购买订单明细
     * @param seckillId
     * @param money
     * @param userPhone
     * @return 返回该sql更新的记录数，如果>=1 则更新成功
     */
    int insertOrder(@Param("seckillId") long seckillId, @Param("money")BigDecimal money,@Param("userPhone")long userPhone);


    /**
     * 根据秒杀商品的id查询订单明细数据并得到对应秒杀商品的数据
     * @param seckillId
     * @param userPhone
     * @return
     */
    SeckillOrder findById(@Param("seckillId") long seckillId,@Param("userPhone")long userPhone);
}
