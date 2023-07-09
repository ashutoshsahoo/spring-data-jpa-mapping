package com.ashu.one2one.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LockerDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 918911470220449128L;

    private Long id;

    private String lockerNo;

    private Long employeeId;

}
