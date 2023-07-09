package com.ashu.one2one.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployee implements Serializable {

    @Serial
    private static final long serialVersionUID = -7752434247855900569L;

    private String name;

}
