package com.dianping.hui.web;

import com.alibaba.fastjson.JSON;
import com.dianping.hui.entity.GF;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: lanxinyu@meituan.com  2018-10-12 下午2:08
 * @Description:
 */

/**
 * 声明一个控制器
 */
@Controller
@RequestMapping({"/", "/homePage"})
@Slf4j
public class BasicController {

    /**
     * 处理对 "/" 的Get请求
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    //@ResponseBody
    public String home() {
        return "return string not view.";
    }

    @RequestMapping(value = "/pathVariable/{gfName}",method = RequestMethod.GET)
    public String pathVariable(
            @PathVariable String gfName
    ) {
        log.info(gfName);
        return "success";
    }

    @RequestMapping(value = "/requestParam",method = RequestMethod.GET)
    public String requestParam(
            @RequestParam String gfName
    ) {
        log.info(gfName);
        return "success";
    }

    @RequestMapping(value = "/post",method = RequestMethod.POST)
    public String post(
            GF gf
    ) {
        log.info(JSON.toJSONString(gf));
        return "success";
    }


}
