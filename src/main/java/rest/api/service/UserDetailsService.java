package rest.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rest.api.repository.UserJpaRepo;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserJpaRepo userJpaRepo;

    public UserDetails loadUserByUsername(String userPk) {
        return userJpaRepo.findById(Long.valueOf(userPk)).orElseThrow(CUserNotFoundException::new);
    }
}