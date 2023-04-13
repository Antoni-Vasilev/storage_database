package com.storage.service.Impl;

import com.storage.model.Token;
import com.storage.repository.TokenRepository;
import com.storage.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String generateToken(String email) {
        Token findToken = tokenRepository.findByUser_Email(email);
        if (findToken != null) return findToken.getToken();

        String token;
        Token fToken;

        do {
            token = String.valueOf(UUID.randomUUID());
            fToken = tokenRepository.findByToken(token);
        } while (fToken != null);

        return token;
    }

    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Long getTokenId(String token) {
        Token t = tokenRepository.findByToken(token);
        if (t == null) return null;
        return t.getId();
    }
}
