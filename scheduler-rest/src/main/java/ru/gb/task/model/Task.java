package ru.gb.task.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ru.gb.user.model.User;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "task")
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private boolean status;

    @Column(name = "date creation")
    private LocalDateTime dateCreation;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User owner;

//    public Task(String header, String description, User owner) {
//        this.header = header;
//        this.description = description;
//        this.owner = owner;
//    }
//
//    public Task(String header, String description) {
//        this.header = header;
//        this.description = description;
//    }
}
