package com.storage.controller;

import com.storage.converter.TokenConverter;
import com.storage.converter.UserConverter;
import com.storage.dto.Token.TokenPublic;
import com.storage.dto.User.UserLoginDto;
import com.storage.dto.User.UserPublic;
import com.storage.dto.User.UserRegisterDto;
import com.storage.exception.ForbiddenAccessException;
import com.storage.model.Token;
import com.storage.model.User;
import com.storage.service.TokenService;
import com.storage.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    @Autowired
    public UserController(UserService userService, UserConverter userConverter, TokenService tokenService, TokenConverter tokenConverter) {
        this.userService = userService;
        this.userConverter = userConverter;
        this.tokenService = tokenService;
        this.tokenConverter = tokenConverter;
    }

    @PostMapping("/register")
    public ResponseEntity<UserPublic> register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        User user = userConverter.UserRegisterDtoToUser(userRegisterDto);

        userService.checkUser(user);

        User registeredUser = userService.register(user);
        UserPublic userPublic = userConverter.UserToUserPublic(registeredUser);
        return ResponseEntity.ok(userPublic);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenPublic> login(@RequestBody @Valid UserLoginDto userLoginDto) {
        User convertUser = userConverter.UserLoginDtoToUser(userLoginDto);
        User findUser = userService.findByEmail(userLoginDto.getEmail());

        if (!convertUser.getPassword().equals(findUser.getPassword()))
            throw new ForbiddenAccessException("Your email or password is incorrect", "Wrong email or password", null);

        Token token = Token.builder()
                .id(tokenService.getTokenId(tokenService.generateToken(userLoginDto.getEmail())))
                .token(tokenService.generateToken(userLoginDto.getEmail()))
                .deviceID(userLoginDto.getDeviceID())
                .user(findUser)
                .build();

        Token savedToken = tokenService.save(token);

        return ResponseEntity.ok(tokenConverter.TokenToTokenPublic(savedToken));
    }
}
