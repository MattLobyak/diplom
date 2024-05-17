package ru.matthew.MyShop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.matthew.MyShop.models.Role;
import ru.matthew.MyShop.models.User;
import ru.matthew.MyShop.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public boolean registerUser(User user) {
        if (userRepository.findUserByEmail(user.getEmail())!= null) return false;
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        System.out.println("Пароль                 -                 " + passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public void updateUser(User userToUpdate, Long id) {
        User user = userRepository.findById(id).orElse(null);
        user.setName(userToUpdate.getName());
        user.setSecondName(userToUpdate.getSecondName());
        user.setEmail(userToUpdate.getEmail());
        user.setPhoneNumber(userToUpdate.getPhoneNumber());
        userRepository.save(user);
    }

//    public void banUser(Long id) {
//        User userToBan = userRepository.getById(id);
//        userToBan.getRoles().
//        log.info("Banning user {}",userToBan.getId());
//        userRepository.save(userToBan);
//    }

//    public void unbanUser(Long id) {
//        User userToUnban = userRepository.getById(id);
//        userToUnban.setRole(Role.ROLE_USER);
//        userRepository.save(userToUnban);
//    }

    public void deleteUser(Long id) {
        log.info("Deleting user with id {}", id);
        userRepository.delete(userRepository.findById(id).orElse(null));
    }

//    //@PreAuthorize("hasRole('ADMIN')")
//    public boolean isEmailUsed(String email) {
//        return userRepository.findUserByEmail(email).isPresent();
//    }

    //@PreAuthorize("hasRole('ADMIN')")
    public boolean isMobilePhoneNumberUsed(String PhoneNumber) {
        return userRepository.findUserByPhoneNumber(PhoneNumber).isPresent();
    }
}
