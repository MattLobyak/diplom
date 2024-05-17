package ru.matthew.MyShop.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotEmpty(message = "Имя не может быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов длиной")
    private String name;

    @Column(name = "secondName", nullable = false)
    @NotEmpty(message = "Фамилия не может быть пустым")
    @Size(min = 2, max = 30, message = "Фамилия должна быть от 2 до 30 символов длиной")
    private String secondName;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 2, max = 100, message = "Пароль должен быть от 8 до 100 символов длиной")
    private String password;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "email не может быть пустым")
    @Size(min = 2, max = 100, message = "email должен быть от 8 до 100 символов длиной")
    @Pattern(regexp = ".+@.+\\..+", message = "Некорректный адрес")
    private String email;

    @Column(name = "phoneNumber", nullable = false)
    @NotEmpty(message = "Номер телефона не может быть пустым")
    @Pattern(regexp = "(\\+375\\d{9})?", message = "Должен состоять из 12 цифр +375292222222")
    private String phoneNumber;

//    @Column(name = "registrationDate", nullable = false)
//    private LocalDate registrationDate;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Advertisment> advertisments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Review> reviews;

}