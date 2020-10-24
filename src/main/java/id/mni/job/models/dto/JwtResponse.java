package id.mni.job.models.dto;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private BigInteger id;
    private String username;
    private List<String> roles;

    public JwtResponse(String jwt, BigInteger id, String username, List<String> roles) {

    }
}
