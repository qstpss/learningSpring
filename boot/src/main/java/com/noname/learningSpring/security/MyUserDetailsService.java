package com.noname.learningSpring.security;

import com.noname.learningSpring.entities.Account;
import com.noname.learningSpring.entities.Privilege;
import com.noname.learningSpring.entities.Role;
import com.noname.learningSpring.repositories.AccountRepository;
import com.noname.learningSpring.security.matchers.id.IdMatcherFactory;
import com.noname.learningSpring.security.matchers.request.RequestMatcherFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final LocalSecurityContext context;

    public MyUserDetailsService(LocalSecurityContext context) {
        this.context = context;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Optional<Account> account = context.getAccountRepository().findByUserName(username);
        if (!account.isPresent()) {
            throw new UsernameNotFoundException(username);
        }
        return new CustomUserDetails(account.get(), context.getIdMatcherFactory(), context.getRequestMatcherFactory());
    }


}
