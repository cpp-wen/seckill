package com.example.seckill.service.impl;

import com.example.seckill.Exception.RepeatKillException;
import com.example.seckill.Exception.SeckillCloseException;
import com.example.seckill.dto.Exposer;
import com.example.seckill.dto.SeckillExecution;
import com.example.seckill.entity.Seckill;
import com.example.seckill.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SeckillServiceImplTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void findAll() {
        List<Seckill> all = seckillService.findAll();
        logger.info("all={}", all);
    }

    @org.junit.Test
    public void findByIdFormRedis() {

    }

    //集成测试上述两个方法
    @Test
    public void testSeckillLogic() throws Exception {
        Exposer exposer = seckillService.exportSeckillUrl(1l);
        if (exposer.isExposed()) {
            long id = exposer.getSeckillId();
            BigDecimal money = BigDecimal.valueOf(200.00);
            long userPhone = 137337879;
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, money, userPhone, md5);
                logger.info("result={}", exposer);
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            } catch (RepeatKillException e1) {
                logger.error(e1.getMessage());
            }
        } else {
            //秒杀未开启
            logger.warn("exposer={}", exposer);
        }
    }
}