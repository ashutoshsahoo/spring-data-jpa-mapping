package com.ashu.one2one.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Locker implements Serializable {

    @Serial
    private static final long serialVersionUID = 7176130763100798174L;

    @Id
    private Long id;

    private String lockerNo;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Employee employee;

    public Locker(Employee employee, String lockerNo) {
        super();
        this.employee = employee;
        this.lockerNo = lockerNo;
    }

}
