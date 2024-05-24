package ru.matthew.MyShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matthew.MyShop.models.Advertisment;
import ru.matthew.MyShop.models.Basket;
import ru.matthew.MyShop.models.User;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Long> {
    List<Basket> findBasketByOwner(User user);
}
