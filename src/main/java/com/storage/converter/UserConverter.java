package com.storage.converter;

import com.storage.dto.User.UserLoginDto;
import com.storage.dto.User.UserPublic;
import com.storage.dto.User.UserRegisterDto;
import com.storage.model.User;
import com.storage.service.PlanService;
import com.storage.service.UserService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;

@Component
public class UserConverter {

    private final PlanService planService;
    private final UserService userService;

    @Autowired
    public UserConverter(PlanService planService, UserService userService) {
        this.planService = planService;
        this.userService = userService;
    }

    public UserPublic UserToUserPublic(User user) {
        return UserPublic.builder()
                .id(user.getId())
                .username(user.getUsername())
                .planName(user.getPlan().getName())
                .email(user.getEmail())
                .build();
    }

    @SneakyThrows
    public User UserRegisterDtoToUser(UserRegisterDto userRegisterDto) {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] messageDigest = md.digest(userRegisterDto.getPassword().getBytes());
        BigInteger bigInteger = new BigInteger(1, messageDigest);
        String newPassword = bigInteger.toString(16);

        return User.builder()
                .username(userRegisterDto.getUsername())
                .plan(planService.getFree())
                .email(userRegisterDto.getEmail())
                .password(newPassword)
                .build();
    }

    @SneakyThrows
    public User UserLoginDtoToUser(UserLoginDto userLoginDto) {
        User findUser = userService.findByEmail(userLoginDto.getEmail());

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] messageDigest = md.digest(userLoginDto.getPassword().getBytes());
        BigInteger bigInteger = new BigInteger(1, messageDigest);
        String newPassword = bigInteger.toString(16);

        return User.builder()
                .id(findUser.getId())
                .username(findUser.getUsername())
                .email(findUser.getEmail())
                .plan(findUser.getPlan())
                .password(newPassword)
                .build();
    }
}
