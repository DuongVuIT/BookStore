package com.example.BookStore.services;



import com.example.BookStore.entity.CustomUserDetail;
import com.example.BookStore.entity.User;
import com.example.BookStore.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException { User user = authRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException("User not found");
        return new CustomUserDetail(user,authRepository);
    }
}


