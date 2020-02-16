/*
 * betahouse.us
 * CopyRight (c) 2012 - 2020
 */
package com.example.seckill.dto;

import com.example.seckill.entity.SeckillOrder;
import com.example.seckill.enums.SeckillStatEnum;

/**
 * @author guofan.cp
 * @version : SeckillExecution.java 2020/02/15 21:46 guofan.cp
 */
public class SeckillExecution {
    private Long seckillId;
    private int state;
    private String stateInfo;
    private SeckillOrder seckillOrder;


    public SeckillExecution(Long seckillId, SeckillStatEnum state, SeckillOrder seckillOrder) {
        this.seckillId = seckillId;
        this.state = state.getState();
        this.seckillOrder = seckillOrder;
    }

    public SeckillExecution(Long seckillId, int state, String stateInfo, SeckillOrder seckillOrder) {
        this.seckillId = seckillId;
        this.state = state;
        this.stateInfo = stateInfo;
        this.seckillOrder = seckillOrder;
    }

    public Long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(Long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public SeckillOrder getSeckillOrder() {
        return seckillOrder;
    }

    public void setSeckillOrder(SeckillOrder seckillOrder) {
        this.seckillOrder = seckillOrder;
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", seckillOrder=" + seckillOrder +
                '}';
    }
}
