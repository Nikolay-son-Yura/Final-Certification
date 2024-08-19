package ru.gb.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "idDepartment")
    private Long idDepartment;

    @Column(name = "idPost")
    private Long idPost;

    @Column(name = "email")
    private String email;


}
