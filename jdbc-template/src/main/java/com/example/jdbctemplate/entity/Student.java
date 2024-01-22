package com.example.jdbctemplate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Checks;
import org.hibernate.annotations.Formula;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @Formula("CONCAT(last_name, ' ', middle_name, ' ', first_name)")
    private String fullName;

    @Temporal(value = TemporalType.DATE)
    private Date dob;

    private String address;

    public Student(Long id, String code, String firstName, String middleName, String lastName, Date dob, String address) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.dob = dob;
        this.address = address;
    }
}
