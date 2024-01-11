package com.example.back.infra.oauth.naver.authcode;

import com.example.back.domain.OauthServerType;
import com.example.back.domain.authcode.AuthCodeRequestUrlProvider;
import com.example.back.infra.oauth.naver.NaverOauthConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class NaverAuthCodeRequestUriProvider implements AuthCodeRequestUrlProvider {

    private final NaverOauthConfig naverOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.NAVER;
    }

    @Override
    public String provide() {
        return UriComponentsBuilder
                .fromUriString("https://nid.naver.com/oauth2.0/authorize")
                .queryParam("response_type", "code")
                .queryParam("client_id", naverOauthConfig.clientId())
                .queryParam("state", naverOauthConfig.state())
                .queryParam("redirect_uri", naverOauthConfig.redirectUri())
//                .queryParam("state", naverOauthConfig.state())

                .build()
                .toUriString();
    }
}
