package com.algashop.authorizationserver.application.user.management;

import com.algashop.authorizationserver.application.security.SecurityCheckApplicationService;
import com.algashop.authorizationserver.application.user.query.AuthUserNotFoundException;
import com.algashop.authorizationserver.application.user.query.AuthUserOutput;
import com.algashop.authorizationserver.domain.model.user.AuthUser;
import com.algashop.authorizationserver.domain.model.user.AuthUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthUserManagementApplicationService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityCheckApplicationService securityCheck;

    public AuthUserOutput create(AuthUserInput input) {
        if (!securityCheck.canRegisterUserOfType(input.getType())) {
            throw new AccessDeniedException("Cannot register user of type " + input.getType());
        }

        if (authUserRepository.existsByEmail(input.getEmail())) {
            throw new AuthUserEmailAlreadyInUseException(input.getEmail());
        }

        String tempPassword = RandomStringUtils.secure().nextAlphanumeric(12);

        System.out.println(tempPassword); //todo enviar via email

        String passwordHash = passwordEncoder.encode(tempPassword);

        AuthUser user = AuthUser.brandNew(
                input.getEmail(),
                input.getName(),
                input.getType(),
                passwordHash
        );

        user = authUserRepository.save(user);

        return AuthUserOutput.from(user);
    }

    public AuthUserOutput update(UUID userId, AuthUserUpdateInput input) {
        AuthUser user = authUserRepository.findById(userId)
                .orElseThrow(() -> new AuthUserNotFoundException(userId));

        user.setName(input.getName());
        user.setType(input.getType());
        user.setEnabled(input.isEnabled());
        return AuthUserOutput.from(authUserRepository.save(user));
    }

    public void delete(UUID userId) {
        AuthUser user = authUserRepository.findById(userId)
                .orElseThrow(() -> new AuthUserNotFoundException(userId));

        user.anonymize();
        authUserRepository.save(user);
    }
}
