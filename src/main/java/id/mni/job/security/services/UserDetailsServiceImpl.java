package id.mni.job.security.services;

import id.mni.job.models.User;
import id.mni.job.repository.InterfaceUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final InterfaceUserRepository userRepository;

    public UserDetailsServiceImpl(InterfaceUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username: "+s));
        return UserDetailsImpl.build(user);
    }
}
