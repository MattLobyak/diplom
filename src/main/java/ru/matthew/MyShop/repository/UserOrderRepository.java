package ru.matthew.MyShop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.matthew.MyShop.models.Advertisment;
import ru.matthew.MyShop.models.Status;
import ru.matthew.MyShop.models.User;
import ru.matthew.MyShop.models.UserOrder;

import java.util.List;

public interface UserOrderRepository  extends JpaRepository<UserOrder, Long> {
    List<UserOrder> findUserOrderByOwnerEquals(User user);

    Page<UserOrder> findUserOrderByStatusEquals(Status status, Pageable pageable);
}
