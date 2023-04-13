package com.storage.converter;

import com.storage.dto.Token.TokenPublic;
import com.storage.model.Token;
import org.springframework.stereotype.Component;

@Component
public class TokenConverter {

    public TokenPublic TokenToTokenPublic(Token token) {
        return TokenPublic.builder()
                .token(token.getToken())
                .build();
    }
}
