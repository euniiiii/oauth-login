package com.example.back.domain.authcode;

import com.example.back.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider { // 인터페이스, AuthCode를 발급한 URL을 제공하는 기능을 제공

    OauthServerType supportServer(); // 어떤 OauthServer Type을 지원할 수 있는지 나타냄
                                    //  ex) KakaoAuthCodeRequestUrlProvider는 OauthServerType으로 KAKAO를 반환할 것
    String provide(); // provide()를 통해 URL을 생성하여 반환. 해당 주소로 Redirect하면 카카오 동의항목 화면이 나옴
}
