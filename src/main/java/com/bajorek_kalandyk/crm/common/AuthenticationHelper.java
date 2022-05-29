package com.bajorek_kalandyk.crm.common;

import com.bajorek_kalandyk.crm.domain.UserAccountDetails;
import com.bajorek_kalandyk.crm.domain.model.User;
import com.bajorek_kalandyk.crm.exception.AuthenticatedUserMissingException;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationHelper
{
    public static User GetCurrentUser() throws AuthenticatedUserMissingException
    {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserAccountDetails)
        {
            return ((UserAccountDetails) principal).getUser();
        }
        throw new AuthenticatedUserMissingException("Authenticated user is null!");
    }
}
