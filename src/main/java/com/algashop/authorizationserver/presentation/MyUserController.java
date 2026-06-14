package com.algashop.authorizationserver.presentation;

import com.algashop.authorizationserver.application.security.SecurityCheckApplicationService;
import com.algashop.authorizationserver.application.user.query.AuthUserOutput;
import com.algashop.authorizationserver.application.user.query.AuthUserQueryService;
import com.algashop.authorizationserver.infrastructure.security.check.SecurityAnnotations;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users/me")
@RequiredArgsConstructor
public class MyUserController {

    private final SecurityCheckApplicationService securityCheck;
    private final AuthUserQueryService authUserQueryService;

    @GetMapping
    @SecurityAnnotations.CanAccessOwnProfile
    public AuthUserOutput getMe() {
        UUID authenticatedUserId = securityCheck.getAuthenticatedUserId();
        return authUserQueryService.findById(authenticatedUserId);
    }
}
