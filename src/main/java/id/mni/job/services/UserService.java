package id.mni.job.services;

import id.mni.job.models.User;
import id.mni.job.models.dto.UserDto;
import id.mni.job.repository.InterfaceUserRepository;
import id.mni.job.services.interfaces.InterfaceUser;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
public class UserService implements InterfaceUser {

    private final InterfaceUserRepository userRepository;

    public UserService(InterfaceUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public int createNewUser(UserDto user) {
        if (user.getUsername().length() <= 0 || user.getUsername().equals(null)) {
            return -1;
        } else if(user.getPassword().length() <= 0 || user.getPassword().equals(null)){
            return -2;
        } else if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return -3;
        }

        User newUser = new User(user.getUsername(), user.getPassword(), Timestamp.from(Instant.now()), Timestamp.from(Instant.now()));
        User result = userRepository.save(newUser);
        if (!result.equals(null)){
            return 0;
        }
        return -4;
    }

    @Override
    public int login(UserDto user) {
        return 0;
    }
}
