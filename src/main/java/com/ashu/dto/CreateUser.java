package com.ashu.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

}
