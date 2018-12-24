package com.dianping.hui.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author: lanxinyu@meituan.com  2018-10-22 上午11:19
 * @Description:
 */
@Slf4j
@Component
public class HttpUtils {

    @Autowired
    RestTemplate restTemplate;

    protected <T> T syncGet(final String url, Class<T> clazz) {
        try {
            RequestEntity<MultiValueMap<String, String>> requestEntity  =
                    new RequestEntity(HttpMethod.GET, URI.create(url));
            ResponseEntity<String> response = restTemplate.exchange(requestEntity, String.class);
            return JSONUtils.parseObject(response.getBody(), clazz);
        } catch (Exception e) {
            log.error("syncGet url「{}」 error :", url, e);
            return null;
        }
    }
}
