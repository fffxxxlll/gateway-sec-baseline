package com.group4.secbaselinebackend.valueObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Token {
    @JsonProperty("token")
    private String token;

    @JsonProperty("expires_in")
    private long expiresIn;

    @JsonProperty("expires_time")
    private long expiresTime;

}
