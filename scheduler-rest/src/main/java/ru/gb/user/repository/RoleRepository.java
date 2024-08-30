package ru.gb.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.user.model.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
