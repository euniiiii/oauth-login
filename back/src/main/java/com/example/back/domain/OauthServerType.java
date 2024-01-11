package com.example.back.domain;

import java.util.Locale;

public enum OauthServerType { // 카카오, 구글, 네이버 등 Oauth2.0 인증을 제공하는 서버의 종류를 명시할 Enum

    KAKAO, NAVER;

    public static OauthServerType fromName(String type) { // "kakao"를 통해 OauthServerType.KAKAO를 찾아오기 위한 메서드
        return OauthServerType.valueOf(type.toUpperCase(Locale.ENGLISH));
    }
}
