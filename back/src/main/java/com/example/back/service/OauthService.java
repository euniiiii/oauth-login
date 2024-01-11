package com.example.back.service;

import com.example.back.domain.OauthId;
import com.example.back.domain.OauthMember;
import com.example.back.domain.OauthServerType;
import com.example.back.domain.authcode.AuthCodeRequestUrlProviderComposite;
import com.example.back.domain.client.OauthMemberClientComposite;
import com.example.back.repository.OauthMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final AuthCodeRequestUrlProviderComposite authCodeRequestUrlProviderComposite;
    private final OauthMemberClientComposite oauthMemberClientComposite;
    private final OauthMemberRepository oauthMemberRepository;

    public String getAuthCodeRequestUrl(OauthServerType oauthServerType) {
        return authCodeRequestUrlProviderComposite.provide(oauthServerType);
    }

    @Transactional(readOnly = true)
    public Long login(OauthServerType oauthServerType, String authCode) {
        OauthMember oauthMember = oauthMemberClientComposite.fetch(oauthServerType, authCode); // 로그인 진행하려는 oauth server type에 해당하는 회원을 auth code로 조회
        OauthMember saved = oauthMemberRepository.findByOauthId(oauthMember.oauthId()) // OauthId를 통해 데이터베이스에서 회원을 찾아옴
                .orElseGet(() -> oauthMemberRepository.save(oauthMember)); // 만약 가입되어 있지 않다면 저장(회원가입)을 함
        return saved.id(); // JWT를 사용하면 JWT로 Access Token을 생성하여 반환, 현재는 id를 반환하는 것으로 함
    }
}
