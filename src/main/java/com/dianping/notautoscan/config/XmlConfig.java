package com.dianping.notautoscan.config;

import com.dianping.hui.entity.GF;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lanxinyu@meituan.com  2018-11-22 4:24 PM
 * @Description:
 */
public class XmlConfig {


    public static void main(String[] args) throws IOException {
        ObjectMapper xmlMapper = new XmlMapper();
         // serializing

        List<GF> value = xmlMapper.readValue(new File("/Users/lan/Spring/spring-practice/src/main/resources/gf3.xml"),
                List.class);
        String xml = xmlMapper.writeValueAsString(value);
        List s = new ArrayList();
        s.add(value);
        s.add(value);
        xmlMapper.writeValue(new File("/Users/lan/Spring/spring-practice/src/main/resources/gf3.xml"), s);
        System.out.println(value);
        System.err.println(xml);
        FileWriter fileWriter = new FileWriter("/Users/lan/Spring/spring-practice/src/main/resources/gf2.xml");
        FileCopyUtils.copy(xml, fileWriter);
    }
}
