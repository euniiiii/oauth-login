package com.example.back.infra.oauth.kakao;

import com.example.back.domain.OauthMember;
import com.example.back.domain.OauthServerType;
import com.example.back.domain.client.OauthMemberClient;
import com.example.back.infra.oauth.kakao.client.KakaoApiClient;
import com.example.back.infra.oauth.kakao.dto.KakaoMemberResponse;
import com.example.back.infra.oauth.kakao.dto.KakaoToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class KakaoMemberClient implements OauthMemberClient {

    private final KakaoApiClient kakaoApiClient;
    private final KakaoOauthConfig kakaoOauthConfig;

    @Override
    public OauthServerType supportServer() {
        return OauthServerType.KAKAO;
    }

    @Override
    public OauthMember fetch(String authcode) {
        KakaoToken tokenInfo = kakaoApiClient.fetchToken(tokenRequestParams(authcode)); // 먼저 authcode를 통해 Access Token을 조회
        KakaoMemberResponse kakaoMemberResponse = kakaoApiClient.fetchMember("Bearer " + tokenInfo.accessToken()); // Access Token으로 회원 정보를 받아옴
        return kakaoMemberResponse.toDomain(); // 회원 정보를 OauthMember 객체로 변환
    }

    private MultiValueMap<String, String> tokenRequestParams(String authCode) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", kakaoOauthConfig.clientId());
        params.add("redirect_uri", kakaoOauthConfig.redirectUri());
        params.add("code", authCode);
        params.add("client_secret", kakaoOauthConfig.clientSecret());
        return params;
    }
}
