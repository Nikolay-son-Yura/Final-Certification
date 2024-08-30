package ru.gb.user.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.task.model.Task;

import jakarta.persistence.*;

import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String userEmail;


    @Column(name = "password")
    private String userPassword;

    @Column(name = "enabled")
    private boolean isEnabled;

    @Column(name = "roleId")
    private Long roleId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Task> tasks;

//    @Column(name = "phone")
//    private String phone;


    public User(String email, String password) {
        this.userEmail = email;
        this.userPassword = password;
    }

    public User(String userEmail, String userPassword, boolean isEnabled) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.isEnabled = isEnabled;
    }


}
