package com.algashop.authorizationserver.application.user.query;

import com.algashop.authorizationserver.domain.model.user.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthUserQueryService {

    private final AuthUserRepository authUserRepository;

    public AuthUserOutput findById(UUID userId) {
        return authUserRepository.findById(userId)
                .map(AuthUserOutput::from)
                .orElseThrow(() -> new AuthUserNotFoundException(userId));
    }
}
