/*
 * betahouse.us
 * CopyRight (c) 2012 - 2020
 */
package com.example.seckill.controller;

import com.example.seckill.Exception.RepeatKillException;
import com.example.seckill.Exception.SeckillCloseException;
import com.example.seckill.Exception.SeckillException;
import com.example.seckill.dto.Exposer;
import com.example.seckill.dto.SeckillExecution;
import com.example.seckill.dto.SeckillResult;
import com.example.seckill.entity.Seckill;
import com.example.seckill.enums.SeckillStatEnum;
import com.example.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author guofan.cp
 * @version : Seckillcontroller.java 2020/02/16 10:27 guofan.cp
 */
@Controller
@RequestMapping("/seckill")
public class Seckillcontroller {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;

    @GetMapping(value = "/list")
    public String findSeckillList(Model model) {
        List<Seckill> list = seckillService.findAll();
        model.addAttribute("list", list);
        return "page/seckill";

    }

    /**
     * @param id seckillId
     * @return 秒杀商品详细信息
     */
    @ResponseBody
    @GetMapping(value = "findById")
    public Seckill findByid(@RequestParam("id") Long id) {
        return seckillService.findById(id);
    }

    @GetMapping(value = "/{seckillId}/detail")
    public String detail(@PathVariable("seckillId") Long seckillId, Model model) {
        if (seckillId == null) {
            return "page/seckill";
        }
        Seckill seckill = seckillService.findById(seckillId);
        model.addAttribute("seckill", seckill);
        if (seckill == null) {
            return "page/seckill";
        }
        return "page/seckill_detail";
    }

    /**
     * 暴露秒杀接口
     *
     * @param seckillId
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/{seckillId}/exposer", produces = {"application/json;charset=UTF-8"})
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }


    /**
     * 秒杀确认接口
     *
     * @param seckillId
     * @param md5
     * @param money
     * @param userPhone
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/{seckillId}/{md5}/execution", produces = {"application/json;charset=UTF-8"})
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @RequestParam("money") BigDecimal money,
                                                   @CookieValue(value = "killPhone", required = false) Long userPhone) {
        if (userPhone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        try {
            SeckillExecution exception = seckillService.executeSeckill(seckillId, money, userPhone, md5);
            return new SeckillResult<>(true, exception);
        } catch (RepeatKillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (SeckillCloseException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.END);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        } catch (SeckillException e) {
            SeckillExecution seckillExecution = new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(true, seckillExecution);
        }
    }


    /**
     * 返回当前时间接口
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/time/now")
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult<>(true, now.getTime());
    }
}
