package http;


import com.alibaba.fastjson.JSON;
import com.dianping.notautoscan.config.RootConfig;
import com.dianping.hui.entity.GF;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author: lanxinyu@meituan.com  2018-10-22 上午11:08
 * @Description:
 */
@Slf4j
public class HttpLansingTest {
    private RestTemplate restTemplate;

    @Before public void init() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);

        restTemplate = applicationContext.getBean(RestTemplate.class);
    }

    @Test public void testForm() {
        post("http://localhost:8080/submitForm");
    }

    protected void post(final String url) {
        try {
            GF gf = new GF(null,null);
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            params.add("index", "213");
            RequestEntity<MultiValueMap<String, String>> requestEntity  =
                    new RequestEntity(params,HttpMethod.POST, URI.create(url));
            System.out.println(JSON.toJSONString(restTemplate.postForEntity(url, params, String.class)));
        } catch (Exception e) {
            log.error("syncGet url「{}」 error :", url, e);
            return;
        }
    }

}
