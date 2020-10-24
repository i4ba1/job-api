package id.mni.job.controllers;

import id.mni.job.models.ERole;
import id.mni.job.models.Role;
import id.mni.job.models.User;
import id.mni.job.models.dto.*;
import id.mni.job.repository.InterfaceRoleRepository;
import id.mni.job.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder encoder;
    private final InterfaceRoleRepository roleRepository;

    public UserController(UserService userService, PasswordEncoder encoder, InterfaceRoleRepository roleRepository) {
        this.userService = userService;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/createNewUser")
    public ResponseEntity<String> createNewUser(@RequestBody UserDto userDto){
        //User newUser = new User(userDto.getUsername(), userDto.getPassword(), new HashSet<String>().add("user"), Timestamp.from(Instant.now()),  Timestamp.from(Instant.now()));
        User newUser = new User();
        int result = userService.createNewUser(newUser);
        ResponseDto responseDto = new ResponseDto();
        if (result == -1) {
            return new ResponseEntity<>("Username can not be blank", HttpStatus.BAD_REQUEST);
        }else if (result == -2) {
            return new ResponseEntity<>("Password can not be blank", HttpStatus.BAD_REQUEST);
        }else if (result == -3) {
            return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
        }else if (result == -4) {
            return new ResponseEntity<>("Problem when insert new user", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Successfully create new user", HttpStatus.OK);
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginRequest){
        ResponseEntity<JwtResponse> login = userService.login(loginRequest);
        return login;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto signUpRequest) {
        if (userService.isUsernameExist(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDto("Error: Username is already taken!"));
        }

        // Create new user's account
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setCreatedAt(Timestamp.from(Instant.now()));
        user.setUpdatedAt(Timestamp.from(Instant.now()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            for (String role : strRoles) {
                if ("admin".equals(role)) {
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else if ("mod".equals(role)) {
                    Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);
                } else {
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            }
        }

        user.setRoles(roles);
        userService.createNewUser(user);

        return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));
    }
}
