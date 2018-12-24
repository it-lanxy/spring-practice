package com.dianping.hui.web;

import com.alibaba.fastjson.JSON;
import com.dianping.hui.entity.GF;
import com.dianping.hui.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author: lanxinyu@meituan.com  2018-10-22 下午2:27
 * @Description: 视图解析器练习Controller
 */

/**
 * 我们所编写的控制器方法都没有直接产生浏览器中渲染所需的HTML。
 * 这些方法只是将一些数据填充到模型中，然后将模型传递给一个用来渲染的视图。
 * 这些方法会返回一个String类型的值，这个值是视图的逻辑名称，不会直接引用具体的视图实现。
 *
 * 将控制器中请求处理的逻辑和视图中的渲染实现解耦是Spring MVC的一个重要特性。如果
 * 控制器中的方法直接负责产生HTML的话，就很难在不影响请求处理逻辑的前提下，维护和更新视图。
 *
 * 控制器方法和视图的实现会在模型内容上达成一致，这是两者最大的挂链，除此之外，两者应该保持足够的距离。
 *
 * 但是，如果控制器只通过逻辑视图名来了解视图的话，那Spring该如何确定使用哪一个视图实现来渲染模型呢？
 * 这就是视图解析器的任务了
 */
@Controller
@RequestMapping("/view")
@Slf4j
public class ViewPracticeController {

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String showGFS(Model model) {
        User user = new User();
        user.setGf(new GF(1, "金丽红"));
        user.setAge(1);
        user.setEmail("13020079696@163.com");
        user.setId(12);
        user.setName("兰鑫宇");
        user.setPassword("123");
        model.addAttribute("user", user);
        return "jspractice/springform";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerGFS(@Valid User user, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("user", user);
            //return "jspractice/success";
        }
        log.info(JSON.toJSONString(user));
        return "jspractice/springform";
    }
}
