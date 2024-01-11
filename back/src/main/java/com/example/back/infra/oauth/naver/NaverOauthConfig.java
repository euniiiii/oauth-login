package com.example.back.infra.oauth.naver;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.naver") // *.properties, *.property를 자바 클래스에 값을 가져와서(바인딩) 사용할 수 있게 해주는 어노테이션
public record NaverOauthConfig(
        String redirectUri,
        String clientId,
        String clientSecret,
        String[] scope,
        String state
) {
}
