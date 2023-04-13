package com.storage.service;

import com.storage.model.Token;

public interface TokenService {

    String generateToken(String email);

    Token save(Token token);

    Long getTokenId(String token);
}
