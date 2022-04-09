package com.myshop.testshop.security;

import com.myshop.testshop.entities.User;
import com.myshop.testshop.repositories.UserRepositoryAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    private UserRepositoryAuth userRepositoryAuth;

    @Autowired
    public UserDetailServiceImpl(UserRepositoryAuth userRepositoryAuth){
        this.userRepositoryAuth = userRepositoryAuth;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepositoryAuth.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }
}
