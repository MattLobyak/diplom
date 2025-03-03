package ru.matthew.MyShop.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;
import ru.matthew.MyShop.models.*;
import ru.matthew.MyShop.repository.AdvertismentRepository;
import ru.matthew.MyShop.repository.ReviewRepository;
import ru.matthew.MyShop.repository.UserOrderRepository;
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
    private final UserOrderRepository userOrderRepository;

    @PostConstruct
    public void init() {
        User adminUser = userRepository.findUserByEmail("addmin@gmail.com");
        if (adminUser == null) {
            System.out.println("Не был найден ни один аккаунт. Будет создана запись");
            //adminUser = new User(null, "Матвей", "Лобяк", "2222", "addmin@gmail.com", "375290000000", LocalDate.now(), LocalDate.now(), Role.ROLE_ADMIN);
            adminUser = new User(null, "Матвей", "Лобяк", "2222", "admin@gmail.com", "+375290000000", Role.ROLE_ADMIN, Status.ACTIVE, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            userService.registerUser(adminUser);
            //userRepository.saveAndFlush(adminUser);
            User defaultUser = new User(null, "Петя", "Иванов", "3333", "petya@gmail.com", "+375291111111", Role.ROLE_USER, Status.ACTIVE, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
            userService.registerUser(defaultUser);
            adminUser.setRole(Role.ROLE_ADMIN);
            userRepository.saveAndFlush(adminUser);
            userRepository.saveAndFlush(new User(null, "Вася", "Сидоров", "3333", "aboba@gmail.com", "+375291111111", Role.ROLE_USER, Status.ACTIVE, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));
            userRepository.saveAndFlush(new User(null, "Катя", "Абубенчик", "3333", "aboba@gmail.com", "+375291111111", Role.ROLE_USER, Status.ACTIVE, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));

        }
        Advertisment baseAdvertisment = advertismentRepository.findAdvertismentByName("basic").orElse(null);
        if (baseAdvertisment == null) {
            baseAdvertisment = new Advertisment(null, "Ноутбук Asus", "15.6\" 1920 x 1080, IPS, 144 Гц, AMD Ryzen 5 7535HS, 16 ГБ DDR5, SSD 512 ГБ, видеокарта NVIDIA GeForce RTX 2050 4 ГБ, без ОС, цвет крышки черный, аккумулятор 48 Вт·ч", Payment.PRICE, Category.ELECTRONICS,2500, LocalDate.now(), City.MINSK, State.NEW, Status.ACTIVE, adminUser, "https://img.sila.by/catalog/img14/tovar147014.jpg", "https://fk.by/uploads/images/cache/2020/12/05/noutbuk-lenovo-ideapad-3-15ada05-81w100apre-1100x500.jpeg", "https://img.sila.by/catalog/img14/tovar147014.jpg", new ArrayList<>(), new ArrayList<>());
            Advertisment testAdvertisment = new Advertisment(null, "Мышь компьютерная", "дполноразмерная мышь для ПК, проводная USB, сенсор оптический 1200 dpi, 3 кнопки, колесо с нажатием, цвет черный", Payment.FREE, Category.ELECTRONICS,2000, LocalDate.now(), City.MINSK, State.PASTINUSAGE, Status.ACTIVE,adminUser, "https://img.sila.by/catalog/img4/tovar46626.jpg", "https://img.sila.by/catalog/img4/tovar46626.jpg", "https://img.sila.by/catalog/img4/tovar46626.jpg", new ArrayList<>(), new ArrayList<>());
            Advertisment testAdvertisment2 = new Advertisment(null, "XIAOMI REDMI 10 2022 6GB/128GB (СЕРЫЙ)", "Версия ОС: Android 11, Диагональ экрана: 6.5, Разрешение экрана: 1080x2400 px, Частота обновления матрицы: 90 Гц", Payment.FREE, Category.ELECTRONICS,500, LocalDate.now(), City.MINSK, State.PASTINUSAGE, Status.ACTIVE,adminUser, "https://shop.mts.by/upload/resize_cache/webp/iblock/6e5/vcrdzeng5jg8nfulcodp1m9881kcazol/270_500_1/zagruzhennoe-_9_.webp", "", "", new ArrayList<>(), new ArrayList<>());
            advertismentRepository.saveAndFlush(baseAdvertisment);
            advertismentRepository.saveAndFlush(testAdvertisment);
            advertismentRepository.saveAndFlush(testAdvertisment2);

            UserOrder order = new UserOrder(null, OrderPayment.CASH, "Изяяя", 200, "+375290000000", "Вавилова", Status.MODERATION, "", "", "", "", adminUser, baseAdvertisment);
            userOrderRepository.saveAndFlush(order);
            adminUser.getUserOrders().add(order);
        }

        Review basicReview = new Review(null, 4, "Хороший продукт", LocalDate.now(), baseAdvertisment, adminUser);
        Review basicReview2 = new Review(null, 5, "Соответствует описанию. Рекомендую", LocalDate.now(), baseAdvertisment, adminUser);
        Review basicReview3 = new Review(null, 1, "Пришел битый", LocalDate.now(), baseAdvertisment, adminUser);
        Review basicReview4 = new Review(null, 5, "Отличный товар", LocalDate.now(), baseAdvertisment, adminUser);
        System.out.println(basicReview.toString());
        reviewRepository.saveAndFlush(basicReview);
        reviewRepository.saveAndFlush(basicReview2);
        reviewRepository.saveAndFlush(basicReview3);
        reviewRepository.saveAndFlush(basicReview4);

    }
}
