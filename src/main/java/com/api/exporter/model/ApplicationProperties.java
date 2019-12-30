package com.api.exporter.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Component
@PropertySource("classpath:application.properties")
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationProperties {

    @Value("${withings.api.refreshtoken}")
    private String refreshToken;

    @Value("${withings.api.accesstoken}")
    private String accessToken;

    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}