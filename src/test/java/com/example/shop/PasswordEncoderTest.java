package com.example.shop;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

public class PasswordEncoderTest {

    @Test
    public void testEncode() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPass = "thao1994";
        String encodedPass = passwordEncoder.encode(rawPass);

        System.out.println(encodedPass);
        boolean matches = passwordEncoder.matches(rawPass, encodedPass);
        assertThat(matches).isTrue();
    }
}
