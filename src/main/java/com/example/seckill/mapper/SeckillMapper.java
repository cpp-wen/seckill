/*
 * betahouse.us
 * CopyRight (c) 2012 - 2020
 */
package com.example.seckill.mapper;

import com.example.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author guofan.cp
 * @version : SeckillMapper.java 2020/02/15 17:02 guofan.cp
 */
@Mapper
public interface SeckillMapper {
    /**
     * 查询所有秒杀商品的信息记录
     *
     * @return
     */
    List<Seckill> findAll();

    /**
     * 根据主键查询当前秒杀商品的数据
     *
     * @param id
     * @return
     */
    Seckill findById(long id);

    /**
     * 减少库存 对于多个参数的方法需要使用 @Param 注解标识字段名称
     *
     * @param seckillId
     * @param killTime
     * @return 返回此sql 更新的记录数 ，如果>1 表示更新成功
     */
    int reduceStock(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);
}
