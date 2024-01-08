package com.example.back.domain.client;

import com.example.back.domain.OauthMember;
import com.example.back.domain.OauthServerType;

public interface OauthMemberClient {

    OauthServerType supportServer();

    OauthMember fetch(String code);
}
