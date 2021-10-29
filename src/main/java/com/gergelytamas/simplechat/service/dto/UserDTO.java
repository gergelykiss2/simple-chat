package com.gergelytamas.simplechat.service.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class UserDTO implements Serializable {

    private Integer id;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String passwordHash;

    private String firstName;

    private String lastName;
}
