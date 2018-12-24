package com.dianping.hui.web;

import com.dianping.hui.entity.GF;
import com.dianping.hui.repository.LansingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: lanxinyu@meituan.com  2018-10-12 下午6:31
 * @Description:
 */
@Controller
public class LansingController {

    LansingRepository lansingRepository;

    @Autowired
    public LansingController(LansingRepository lansingRepository) {
        this.lansingRepository = lansingRepository;
    }

    /**
     * /book/123 和 /book?id=123 的差别
     * 都可以查询id=123的书，但是就面向资源的角度看 /book?id=123 不够理想
     * 要识别的资源应该通过URL（统一资源定位符）路径进行标示，而不是通过查询参数。
     * 对 /book/123 发起get请求要优于 对 /book?id=123 发起的请求。前者能识别出要查询的资源，
     * 而后者描述的是带有参数的一个操作——本质上是通过HTTP发起的RPC。
     * @param model
     * @return
     */
    @RequestMapping(value = "/lansing",method = RequestMethod.GET)
    public String marry(Model model) {
        Map map = new HashMap() {
            {
                put("gf", lansingRepository.getGirlFriend());
            }
        };
        model.addAllAttributes(map);
        //返回视图名
        return "lansing";
    }

    /**
     * 没有返回视图名，也没有显示的设定模型
     * 这个方法返回的是String列表，模型key根据类型推断得出，也就是stringList
     * 逻辑视图名根据请求路径得出，也就是 viewTuiduan
     * @return
     */
    @RequestMapping(value = "/viewTuiduan/{gfName}",method = RequestMethod.GET)
    public String viewTuiduan(
            @PathVariable String gfName, Map viewData
    ) {
        System.out.println(gfName);

        viewData.put("gfNames",lansingRepository.getGirlFriends());
        return "viewTuiduan";
    }

    @RequestMapping(value = "/submitForm",method = RequestMethod.POST)
    public String submitForm(@Validated GF request, Errors errors
    ) {
        if(errors.hasErrors()) {
            //把错误返回到registerForm 视图
            return "registerForm";
        }
        GF gf = lansingRepository.save(request);
        return "redirect:/viewTuiduan/" + gf.getName();
    }

}
