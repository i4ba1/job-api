package id.mni.job.services.interfaces;

import id.mni.job.models.User;
import id.mni.job.models.dto.JwtResponse;
import id.mni.job.models.dto.LoginDto;
import id.mni.job.models.dto.UserDto;
import org.springframework.http.ResponseEntity;

public interface InterfaceUser {
    int createNewUser(User user);
    ResponseEntity<JwtResponse> login(LoginDto user);
    boolean isUsernameExist(String username);
}
