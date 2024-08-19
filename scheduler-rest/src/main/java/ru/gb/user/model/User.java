package ru.gb.user.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.task.model.Task;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "email")
    private String userEmail;

    @Column(name = "password")
    private String userPassword;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
//    @ApiModelProperty(hidden = true)
    private List<Task> tasks;

//    @Column(name = "phone")
//    private String phone;


    public User(String email, String password) {
        this.userEmail = email;
        this.userPassword = password;
    }

}
