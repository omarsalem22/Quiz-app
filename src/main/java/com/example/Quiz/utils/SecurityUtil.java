package com.example.Quiz.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.Quiz.models.User;

@Component
public class SecurityUtil {

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated() ||
            "anonymousUser".equals(authentication.getPrincipal())) {
            throw new RuntimeException("No authenticated user found");
        }

        String username = null;

        // Case 1: Principal is UserDetails (your current case)
        if (authentication.getPrincipal() instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        }
        // Case 2: You later store full User entity in principal (optional improvement)
        else if (authentication.getPrincipal() instanceof User user) {
            return user.getId(); // direct access
        }
        // Case 3: Principal is just String (rare)
        else if (authentication.getPrincipal() instanceof String str) {
            username = str;
        }

        if (username == null) {
            throw new RuntimeException("Could not extract username from authentication");
        }

       
        throw new UnsupportedOperationException("Use AuthenticatedUserService instead (see next step)");
    }
}