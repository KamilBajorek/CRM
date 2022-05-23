package com.bajorek_kalandyk.crm.service;

import com.bajorek_kalandyk.crm.domain.UserAccountDetails;
import com.bajorek_kalandyk.crm.domain.model.User;
import com.bajorek_kalandyk.crm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByLogin(username);
        if (user == null)
        {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserAccountDetails(user);
    }
}
