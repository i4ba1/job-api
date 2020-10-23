package id.mni.job.controllers;

import id.mni.job.models.dto.ResponseDto;
import id.mni.job.models.dto.UserDto;
import id.mni.job.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createNewUser")
    public ResponseEntity<String> createNewUser(@RequestBody UserDto userDto){
        int newUser = userService.createNewUser(userDto);
        ResponseDto responseDto = new ResponseDto();
        if (newUser == -1) {
            return new ResponseEntity<>("Username can not be blank", HttpStatus.BAD_REQUEST);
        }else if (newUser == -2) {
            return new ResponseEntity<>("Password can not be blank", HttpStatus.BAD_REQUEST);
        }else if (newUser == -3) {
            return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
        }else if (newUser == -4) {
            return new ResponseEntity<>("Problem when insert new user", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Successfully create new user", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(){
        return null;
    }
}
