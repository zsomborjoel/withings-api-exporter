package com.api.exporter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class ApplicationProperties {

    @Value("${withings.api.tokenhost}")
    private String apiHost;

    @Value("${withings.api.clientid}")
    private String clientId;

    @Value("${withings.api.clientsecret}")
    private String clientSecret;

    @Value("${withings.api.refreshtoken}")
    private String refreshToken;

    @Value("${withings.api.accesstoken}")
    private String accessToken;

    public String getApiHost() {
        return this.apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return this.clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

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