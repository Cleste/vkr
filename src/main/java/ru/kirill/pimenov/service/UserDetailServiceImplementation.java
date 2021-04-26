package ru.kirill.pimenov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kirill.pimenov.mapper.UserMapper;
import ru.kirill.pimenov.pojo.entity.User;
import ru.kirill.pimenov.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImplementation implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserMapper.INSTANCE.toUserDetailsBO(user);
    }

}
