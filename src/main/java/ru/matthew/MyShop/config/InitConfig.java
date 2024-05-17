package ru.matthew.MyShop.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import ru.matthew.MyShop.models.*;
import ru.matthew.MyShop.repository.AdvertismentRepository;
import ru.matthew.MyShop.repository.ReviewRepository;
import ru.matthew.MyShop.repository.UserRepository;
import ru.matthew.MyShop.service.ReviewService;
import ru.matthew.MyShop.service.UserService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

import static java.lang.Boolean.TRUE;

@Component
@RequiredArgsConstructor
@Log
public class InitConfig {

    private final  UserRepository userRepository;
    private final UserService userService;
    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;
    private final AdvertismentRepository advertismentRepository;

    @PostConstruct
    public void init() {
        User adminUser = userRepository.findUserByEmail("addmin@gmail.com");
        if (adminUser == null) {
            System.out.println("Не был найден ни один аккаунт. Будет создана запись");
            //adminUser = new User(null, "Матвей", "Лобяк", "2222", "addmin@gmail.com", "375290000000", LocalDate.now(), LocalDate.now(), Role.ROLE_ADMIN);
            adminUser = new User(null, "Матвей", "Лобяк", "2222", "admin@gmail.com", "+375290000000", Role.ROLE_ADMIN, new ArrayList<>(), new ArrayList<>());
            userService.registerUser(adminUser);
            //userRepository.saveAndFlush(adminUser);
            User defaultUser = new User(null, "Петя", "Иванов", "3333", "aboba@gmail.com", "+375291111111", Role.ROLE_USER, new ArrayList<>(), new ArrayList<>());
            userService.registerUser(defaultUser);
            userRepository.saveAndFlush(adminUser);
            userRepository.saveAndFlush(new User(null, "Вася", "Сидоров", "3333", "aboba@gmail.com", "+375291111111", Role.ROLE_USER, new ArrayList<>(), new ArrayList<>()));
            userRepository.saveAndFlush(new User(null, "Катя", "Абубенчик", "3333", "aboba@gmail.com", "+375291111111", Role.ROLE_USER, new ArrayList<>(), new ArrayList<>()));

        }
        Advertisment baseAdvertisment = advertismentRepository.findAdvertismentByName("basic").orElse(null);
        if (baseAdvertisment == null) {
            baseAdvertisment = new Advertisment(null, "basic", "дешево дешево дешево дешево дешево дешеводешево дешево дешево", Payment.PRICE, LocalDate.now(), City.BREST, adminUser, new ArrayList<>());
            Advertisment testAdvertisment = new Advertisment(null, "test", "дешево дешево дешево дешево дешево дешеводешево дешево дешево", Payment.FREE, LocalDate.now(), City.MINSK, adminUser, new ArrayList<>());
            advertismentRepository.saveAndFlush(baseAdvertisment);
            advertismentRepository.saveAndFlush(testAdvertisment);
        }

        Review basicReview = new Review(null, 5, "Нормальный кал говна", LocalDate.now(), baseAdvertisment, adminUser);
        System.out.println(basicReview.toString());
        reviewRepository.saveAndFlush(basicReview);

    }
}
