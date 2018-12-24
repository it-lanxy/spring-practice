package com.dianping.hui.web;

import com.alibaba.fastjson.JSON;
import com.dianping.hui.entity.GF;
import com.dianping.hui.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

/**
 * @author: lanxinyu@meituan.com  2018-10-31 下午6:07
 * @Description:
 */
@Controller
@Slf4j
public class MultipartController {

    @RequestMapping(value = "/preupload", method = RequestMethod.GET)
    public String preUpload(Model model) {
        GF gf = new GF();
        gf.setIndex(1);
        gf.setName("wangjingyu");
        gf.setBody("beautiful");
        model.addAttribute("gf", gf);
        return "upload";
    }


    /**
    *   使用上传文件的原始byte[]比较简单但功能有限，因此Spring还提供了MultipartFile接口
    */
    @RequestMapping(value = "/upload1", method = RequestMethod.POST)
    public String upload1(
            @RequestPart("pic") byte[] pic, @Valid GF gf, Errors errors
    ) {
        if(errors.hasErrors()) {
            log.error(JSON.toJSONString(errors.getAllErrors()));
        }
        log.info(JSON.toJSONString(gf));
        return "upload";
    }

    @RequestMapping(value = "/upload2", method = RequestMethod.POST)
    public String upload2(
            @RequestPart("pic") MultipartFile pic, @Valid GF gf, Errors errors
    ) throws IOException {
        if(errors.hasErrors()) {
            log.error(JSON.toJSONString(errors.getAllErrors()));
        }
        log.info(pic.getName());
        log.info(pic.getContentType());
        log.info(pic.getOriginalFilename());
        log.info(pic.getSize()+"");
        pic.transferTo(new File("/tmp/lansing/" + gf.getName() + ".png"));
        return "success";
    }

    /**
     * 如果servlet3.0以上可直接使用 Part 并且不必配置 {@link com.dianping.notautoscan.config.WebConfig.multipartResolver}
     *  感觉更加方便
     * @param pic
     * @param gf
     * @param errors
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload3(
            @RequestPart("pic") Part pic, @Valid GF gf, Errors errors
    ) throws IOException {
        if(errors.hasErrors()) {
            log.error(JSON.toJSONString(errors.getAllErrors()));
        }
        log.info(pic.getName());
        log.info(pic.getContentType());
        log.info(pic.getName());
        log.info(pic.getSize()+"");
        pic.write("/tmp/lansing/" + gf.getName() + ".png");
        throw new ResourceNotFoundException();
        //return "success";
    }
}
