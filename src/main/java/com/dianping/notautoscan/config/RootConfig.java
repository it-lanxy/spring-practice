package com.dianping.notautoscan.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author: lanxinyu@meituan.com  2018-09-27 上午10:00
 * @Description:
 */
@Configuration
@ComponentScan(
        basePackages = {"com.dianping.hui"},
        excludeFilters = {
                @ComponentScan.Filter(type=FilterType.ANNOTATION, value={EnableWebMvc.class})
        }
)
public class RootConfig {
        @Bean public SimpleAsyncTaskExecutor simpleAsyncTaskExecutor() {
                return new SimpleAsyncTaskExecutor();
        }

        @Bean public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory(
                @Qualifier("simpleAsyncTaskExecutor") SimpleAsyncTaskExecutor simpleAsyncTaskExecutor) {
                SimpleClientHttpRequestFactory simpleClientHttpRequestFactory = new SimpleClientHttpRequestFactory();
                simpleClientHttpRequestFactory.setConnectTimeout(800);
                simpleClientHttpRequestFactory.setTaskExecutor(simpleAsyncTaskExecutor);
                return simpleClientHttpRequestFactory;
        }

        @Bean public RestTemplate restTemplate(@Qualifier("simpleClientHttpRequestFactory")
                                               SimpleClientHttpRequestFactory simpleClientHttpRequestFactory) {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.setRequestFactory(simpleClientHttpRequestFactory);
                return restTemplate;
        }
}
