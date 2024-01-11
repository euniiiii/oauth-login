package com.example.back.controller;

import com.example.back.domain.OauthServerType;
import com.example.back.service.OauthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/oauth")
@RestController
@Slf4j
public class OauthController {

    private final OauthService oauthService;

    @SneakyThrows // java에서 메서드 선언부에 Throws를 정의하지 않고도, 검사 된 예외를 Throw 할 수 있도록 하는 Lombok에서 제공하는 어노테이션
    @GetMapping("/{oauthServerType}")
    public ResponseEntity<Void> redirectAuthCodeRequestUrl(
            @PathVariable OauthServerType oauthServerType,
            HttpServletResponse response
    ) {
        String redirectUrl = oauthService.getAuthCodeRequestUrl(oauthServerType);
        response.sendRedirect(redirectUrl);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login/{oauthServerType}")
    public ResponseEntity<Long> login(
            @PathVariable OauthServerType oauthServerType,
            @RequestParam("code") String code
    ) {
        Long login = oauthService.login(oauthServerType, code);
        return ResponseEntity.ok(login);
    }

//    @RequestMapping("/login/naver")
//    public ResponseEntity<Long> login(
////            @RequestParam("code") String code
//    ) {
//        log.info("123213133112331132123132");
////        Long login = oauthService.login(oauthServerType, code);
////        return ResponseEntity.ok(login);
//        return null;
//    }
}
