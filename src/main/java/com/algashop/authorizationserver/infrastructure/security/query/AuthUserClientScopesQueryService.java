package com.algashop.authorizationserver.infrastructure.security.query;

import com.algashop.authorizationserver.domain.model.user.AuthUserType;

import java.util.Set;

public interface AuthUserClientScopesQueryService {
    Set<String> findAllowedScopesByRoleAndClientId(AuthUserType role, String clienteId);
}
