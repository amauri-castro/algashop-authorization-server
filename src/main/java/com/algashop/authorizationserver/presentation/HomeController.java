package com.algashop.authorizationserver.presentation;

import com.algashop.authorizationserver.infrastructure.security.AlgaShopSecurityProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AlgaShopSecurityProperties properties;

    @GetMapping("/")
    public String home() {
        return "redirect:" + properties.getDefaultRedirectUri();
    }
}
