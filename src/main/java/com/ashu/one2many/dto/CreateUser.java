package com.ashu.one2many.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUser implements Serializable {

    @Serial
    private static final long serialVersionUID = 2780272306283710537L;

    private String name;

}
