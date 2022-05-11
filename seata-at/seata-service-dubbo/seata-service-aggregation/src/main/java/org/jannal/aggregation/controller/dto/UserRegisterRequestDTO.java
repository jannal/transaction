package org.jannal.aggregation.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@Setter
@Getter
@AllArgsConstructor
@SuperBuilder
@NoArgsConstructor
public class UserRegisterRequestDTO implements Serializable {
    private static final long serialVersionUID = 6851794280101498332L;

    private String username;

    private String password;

}