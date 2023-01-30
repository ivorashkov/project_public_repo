package com.example.web;

import com.example.web.repository.UserRepository;
import com.example.web.util.ValidatorUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestWebAppRunner implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ValidatorUtil validatorUtil;

    public TestWebAppRunner(UserRepository userRepository, ValidatorUtil validatorUtil) {
        this.userRepository = userRepository;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public void run(String... args) throws Exception {

//        isActiveValidationTest(validatorUtil, userRepository);
//        isAdminValidationTest(validatorUtil, userRepository);
    }

    public static void isAdminValidationTest(ValidatorUtil validatorUtil, UserRepository userRepository) {
        boolean isAdmin = validatorUtil.isAdmin(userRepository.findUserEntityByFirstName("ivo").orElse(null));
        boolean isAdmin1 = validatorUtil.isAdmin(userRepository.findUserEntityByFirstName("kolo").orElse(null));
        boolean isAdmin2 = validatorUtil.isAdmin(userRepository.findUserEntityByFirstName("deno").orElse(null));
        System.out.println("ivo -> " + isAdmin);
        System.out.println("kolo -> " + isAdmin1);
        System.out.println("deno -> " + isAdmin2);
    }

    public static void isActiveValidationTest(ValidatorUtil validatorUtil, UserRepository userRepository) {
        boolean isActive = validatorUtil.isActive(userRepository.findUserEntityByFirstName("ivo").orElse(null));
        boolean isActive1 = validatorUtil.isActive(userRepository.findUserEntityByFirstName("kolo").orElse(null));
        boolean isActive2 = validatorUtil.isActive(userRepository.findUserEntityByFirstName("deno").orElse(null));
        System.out.println("ivo -> " + isActive);
        System.out.println("kolo -> " + isActive1);
        System.out.println("deno -> " + isActive2);
    }
}
