package com.ashu.one2one.model;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Employee implements Serializable {

    private static final String EMP_SEQ = "EMPLOYEE_SEQUENCE";

    @Serial
    private static final long serialVersionUID = 5670679535633620886L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = EMP_SEQ)
    @SequenceGenerator(name = EMP_SEQ, sequenceName = EMP_SEQ, allocationSize = 1, initialValue = 1001)
    @Column(name = "ID")
    private Long id;

    private String name;

    // @OneToOne(mappedBy = "employee", cascade = CascadeType.ALL, fetch =
    // FetchType.LAZY, optional = false)
    //private Locker locker;

    public Employee(String name) {
        super();
        this.name = name;
    }

}
