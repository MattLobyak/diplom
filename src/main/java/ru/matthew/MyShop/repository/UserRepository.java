package ru.matthew.MyShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matthew.MyShop.models.Advertisment;
import ru.matthew.MyShop.models.Role;
import ru.matthew.MyShop.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    //List<User> findByRolesContains(Role role);
    User findUserByEmail(String email);
    User findUserByAdvertismentsContains(Advertisment advertisment);
    Optional<User> findUserByPhoneNumber(String mobilePhoneNumber);
}
