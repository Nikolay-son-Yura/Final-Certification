package ru.gb.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.user.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserEmailIgnoreCase(String email);
    Boolean existsByUserEmail(String email);
    Optional<User> findById(Long id);
}
