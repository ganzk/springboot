package com.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Guest {

    private Integer id;

    @NotBlank
    private String name;

    @NotBlank
    private String role;

}
