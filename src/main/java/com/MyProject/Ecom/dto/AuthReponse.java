package com.MyProject.Ecom.dto;

import com.MyProject.Ecom.entity.UserRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthReponse {

    @JsonProperty("access_token")
    private String accessToken;
    private UserRole role;
    private  int id ;

}
