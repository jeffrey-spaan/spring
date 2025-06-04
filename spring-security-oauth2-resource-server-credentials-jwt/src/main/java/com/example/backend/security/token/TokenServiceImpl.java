package com.example.backend.security.token;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
record TokenServiceImpl(
        TokenRepository tokenRepository
) implements TokenService {

    @Override
    public void invalidateToken(UUID jwtId, TokenType tokenType) {
        tokenRepository.findByJwtIdAndTypeAndValidTrue(jwtId, tokenType)
                .ifPresent(jwt -> {
                    jwt.setValid(false);
                    tokenRepository.save(jwt);
        });
    }

    @Override
    public void invalidateAllTokensByUserId(UUID userId) {
        tokenRepository.findByUserIdAndValidTrue(userId)
                .forEach(jwt -> {
                    jwt.setValid(false);
                    tokenRepository.save(jwt);
                });
    }

    @Override
    public boolean isTokenValid(UUID jwtId, TokenType tokenType) {
        return tokenRepository.findByJwtIdAndTypeAndValidTrue(jwtId, tokenType).isPresent();
    }

    @Override
    public void saveValidToken(UUID jwtId, UUID userId, String token, TokenType tokenType) {
        tokenRepository.save(Token.builder()
                .jwtId(jwtId)
                .userId(userId)
                .valid(true)
                .type(tokenType)
                .build());
    }
}