package com.algashop.authorizationserver.application.user.query;

import org.springframework.data.domain.Page;

import java.util.UUID;


public interface AuthUserQueryService {
    AuthUserOutput findById(UUID userId);
    PageModel<AuthUserOutput> findAll(AuthUserFilter filter);
}
