package com.nbadnjarevic.vacationservice.rest;

import com.nbadnjarevic.vacationservice.domain.User;
import com.nbadnjarevic.vacationservice.domain.UserRole;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.ResourceAccessException;

public class ControllerUtil {

    public static void checkPermission(User user) {

        List<String> userRoles = SecurityContextHolder.getContext().getAuthentication().getAuthorities()
            .stream().map(Object::toString).collect(Collectors.toCollection(ArrayList::new));

        if (!user.getRole().equals(UserRole.ADMINISTRATOR) || !userRoles.contains(user.getRole()))
            throw new ResourceAccessException("Nemate privilegije za datog korisnika!");
    }

}

