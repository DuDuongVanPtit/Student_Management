package com.dudv.student_management.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter @Setter @Entity @Table(name = "student")
public class StudentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fullname")
    private String fullName;

    @Column(name = "hometown")
    private String homeTown;

    @Column(name = "gender")
    private String gender;

    @Column(name = "averagemark")
    private Double averageMark;

    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "faculty")
    private String faculty;

    @Column(name = "classname")
    private String className;
}
