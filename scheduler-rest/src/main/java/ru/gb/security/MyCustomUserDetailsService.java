//package ru.gb.security;
//
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import ru.gb.user.model.Role;
//import ru.gb.user.model.User;
//import ru.gb.user.model.UserRole;
//import ru.gb.user.repository.RoleRepository;
//import ru.gb.user.repository.UserRepository;
//import ru.gb.user.repository.UserRoleRepository;
//
//import java.util.List;
//
//@Component
//@RequiredArgsConstructor
//public class MyCustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    private final UserRoleRepository userRoleRepository;
//    private final RoleRepository roleRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(userEmail);
//
//
//        List<Role> userRole = roleRepository.findAllById(userRoleRepository.findByUserId(user.getId()).stream()
//                .map(UserRole::getRoleId).toList());
//        List<SimpleGrantedAuthority> userRoles = userRole
//                .stream().map(it -> new SimpleGrantedAuthority(it.getName()))
//                .toList();
//        return new org.springframework.security.core.userdetails.User(
//                user.getUserEmail(),
//                user.getUserPassword(),
//                userRoles
//        );
//    }
//}
