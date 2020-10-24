package id.mni.job.models.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import id.mni.job.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonDeserialize(as = UserDto.class)
public class UserDto {
    private String username;
    private String password;
    private Set<String> role;
}
