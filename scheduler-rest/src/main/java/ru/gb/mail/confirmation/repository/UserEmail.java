package ru.gb.mail.confirmation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.user.model.User;

@Repository
public interface UserEmail extends JpaRepository<User,Long> {
    User findByUserEmailIgnoreCase(String email);
    Boolean existsByUserEmail(String email);
}
