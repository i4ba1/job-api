package id.mni.job.services.interfaces;

import id.mni.job.models.dto.UserDto;

public interface InterfaceUser {
    int createNewUser(UserDto user);
    int login(UserDto user);
}
