package ru.matthew.MyShop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.matthew.MyShop.models.Advertisment;
import ru.matthew.MyShop.models.Status;
import ru.matthew.MyShop.models.UserOrder;
import ru.matthew.MyShop.repository.AdvertismentRepository;
import ru.matthew.MyShop.repository.UserOrderRepository;
import ru.matthew.MyShop.repository.UserRepository;

import java.security.Principal;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserOrderService {
    private final UserRepository userRepository;
    private final AdvertismentService advertismentService;
    private final UserOrderRepository userOrderRepository;
    private final AdvertismentRepository advertismentRepository;

    public void createOrder(UserOrder userOrder, long id, Principal principal){
        Advertisment advertisment = advertismentRepository.getById(id);
        userOrder.setId(null);
        userOrder.setItem(advertisment);
        userOrder.setStatus(Status.MODERATION);
        userOrder.setOwner(userRepository.findUserByEmail(principal.getName()));
        userOrder.setPrice(advertisment.getPrice());
        advertismentService.deleteAdvertismentFromBusket(id, principal);
        userOrderRepository.save(userOrder);
    }

    public Page<UserOrder> getOrdersByStatus(){
        return userOrderRepository.findUserOrderByStatusEquals(Status.MODERATION, PageRequest.of(0, 10));
    }


}
