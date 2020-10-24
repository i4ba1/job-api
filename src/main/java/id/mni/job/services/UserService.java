package id.mni.job.services;

import id.mni.job.models.User;
import id.mni.job.models.dto.JwtResponse;
import id.mni.job.models.dto.LoginDto;
import id.mni.job.repository.InterfaceUserRepository;
import id.mni.job.security.jwt.JwtUtils;
import id.mni.job.security.services.UserDetailsImpl;
import id.mni.job.services.interfaces.InterfaceUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements InterfaceUser {

    private final InterfaceUserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UserService(InterfaceUserRepository userRepository, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public int createNewUser(User user) {
        if (user.getUsername().length() <= 0 || user.getUsername().equals(null)) {
            return -1;
        } else if(user.getPassword().length() <= 0 || user.getPassword().equals(null)){
            return -2;
        } else if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return -3;
        }

        User result = userRepository.save(user);
        if (!result.equals(null)){
            return 0;
        }
        return -4;
    }

    public ResponseEntity<JwtResponse> login(LoginDto loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    @Override
    public boolean isUsernameExist(String username) {
        return userRepository.existsByUsername(username);
    }
}
