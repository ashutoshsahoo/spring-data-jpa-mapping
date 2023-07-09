package com.ashu.one2one.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -5710458663727220815L;

    private Long id;

    private String name;
}
