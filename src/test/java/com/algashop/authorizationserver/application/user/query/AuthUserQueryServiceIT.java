package com.algashop.authorizationserver.application.user.query;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


class AuthUserQueryServiceIT {

    @Autowired
    private AuthUserQueryService authUserQueryService;

    @Test
    public void shouldFindById() {
        UUID userId = UUID.fromString("019d7764-3b02-7be2-9112-039fda30e965");
        AuthUserOutput authUserOutput = authUserQueryService.findById(userId);


        Assertions.assertThat(authUserOutput.getId()).isEqualTo(userId);
    }

}