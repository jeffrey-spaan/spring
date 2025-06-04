package com.example.backend.security.auth;

import com.example.backend.common.constant.Constant;
import com.example.backend.security.cookie.CookieService;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constant.Request.AUTH)
record AuthController(
        AuthService authService,
        CookieService cookieService
) {

    @PostMapping("/login")
    ResponseEntity<AuthDto> login(@Valid @RequestBody AuthLoginDto loginDto) {
        AuthRequestNormalizer.normalize(loginDto);
        return createAuthResponse(authService.login(loginDto));
    }

    @PostMapping("/register")
    ResponseEntity<AuthDto> register(@Valid @RequestBody AuthRegisterDto authRegisterDto) {
        AuthRequestNormalizer.normalize(authRegisterDto);
        return createAuthResponse(authService.register(authRegisterDto));
    }

    @GetMapping("/refresh")
    ResponseEntity<AuthDto> refresh() {
        return createAuthResponse(authService.refresh());
    }

    private ResponseEntity<AuthDto> createAuthResponse(AuthDto authDto) {
        ResponseCookie refreshTokenCookie = cookieService.generateRefreshTokenCookie(authDto.getRefreshToken());

        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, authDto.getAccessToken())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(authDto);
    }
}