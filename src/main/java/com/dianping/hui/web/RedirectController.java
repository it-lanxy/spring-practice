package com.dianping.hui.web;

import com.dianping.hui.entity.GF;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author: lanxinyu@meituan.com  2018-11-02 下午5:23
 * @Description:
 */
@Controller
@Slf4j
public class RedirectController {

    /**
     * 正在发起重定向功能的方法该如何发送数据给重定向的目标方法呢？
     * 一般来讲，当一个处理器方法完成之后，该方法所指定的模型数据将会复制到请求中，并作为请求中的属性，请求会（forward）到仕途上进行渲染。
     * 因为控制器方法和视图所处理的是同一个请求，所以在转发的过程中，请求属性能够得以保存
     * @param model
     * @return
     */
    @RequestMapping(value = "/redirectfrom", method = RequestMethod.GET)
    public String redirectfrom(Model model) {
        model.addAttribute("fromName","lansing");
        model.addAttribute("id","123");

        //通过url模板进行重定向 http://localhost:8080/redirecto/lansing?id=123
        //局限性，使用url重定向只能发送string或int类型的，并不能发送更加复杂的值，但这正是flash属性能够提供帮助的领域
        return "redirect:/redirecto/{fromName}";
    }

    @RequestMapping(value = "/redirecto/{fromName}", method = RequestMethod.GET)
    public String redirecto(@PathVariable String fromName, @RequestParam String id) {
        log.info(fromName);
        log.info(id);
        return "success";
    }

    /**
     * 模型数据最终是以请求参数的形式复制到请求中的，当重定向发生的时候，这些数据就会丢失。因此我们需要将GF对象放到一个位置，使其能够在重定向的过程中存活下来
     * 有个方案是将GF放到会话（session）中。会话能够长期存在，并且夸多个请求。
     * 所以我们可以在重定向发生之前将GF放到会话中，并在重定向后，从会话中将其取出。当然，我们还要负责在重定向后再会话中将其清理掉。
     *
     * 实际上，spring也认为将跨重定向 存活的数据 放到会话中是一个很不错的方式。但是spring认为我们不需要管理这些数据，相反，spring提供了将数据发送为flash属性的功能。
     */

    @RequestMapping(value = "/flashredirectfrom", method = RequestMethod.GET)
    public String flashRedirectfrom(RedirectAttributes model) {
        model.addAttribute("fromName","lansing");
        model.addAttribute("id","123");
        GF gf = new GF();
        gf.setBody("beautiful");
        gf.setName("wangjingyu");
        gf.setIndex(1);
        model.addFlashAttribute("gf",gf);
        return "redirect:/flashredirecto/{fromName}";
    }

    @RequestMapping(value = "/flashredirecto/{fromName}", method = RequestMethod.GET)
    public String flashRedirecto(
            @PathVariable String fromName, @RequestParam String id, Model model) {
        log.info(fromName);
        log.info(id);

        if(model.containsAttribute("gf")) {
            log.info("name : " + ((GF)model.asMap().get("gf")).getName());
            log.info("body : " + ((GF)model.asMap().get("gf")).getBody());
            log.info("index : " + ((GF)model.asMap().get("gf")).getIndex());
        }
        return "success";
    }
}
