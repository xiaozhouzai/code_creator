package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "whitelist")
public class WhitelistProperties {

    private List<String> urls = new ArrayList<>();

    public WhitelistProperties() {
    }

    public WhitelistProperties(List<String> urls) {
        this.urls = urls;
    }

    /**
     * 获取
     * @return urls
     */
    public List<String> getUrls() {
        return urls;
    }

    /**
     * 设置
     * @param urls
     */
    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

}
