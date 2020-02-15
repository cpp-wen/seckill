/*
 * betahouse.us
 * CopyRight (c) 2012 - 2020
 */
package com.example.seckill.dto;

/**
 * @author guofan.cp
 * @version : Exposer.java 2020/02/15 21:18 guofan.cp
 */
public class Exposer {
    private  boolean exposed;
    private String md5;

    private long seckillId;
    private long now;
    private long start;
    private long end;


    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }


    public Exposer(boolean exposed, long seckillId, long now, long start, long end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }

    public Exposer(boolean exposed, String md5, long seckillId, long now, long start, long end) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }
}
