package ru.matthew.MyShop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.matthew.MyShop.models.Category;
import ru.matthew.MyShop.models.City;
import ru.matthew.MyShop.models.State;
import ru.matthew.MyShop.models.Status;
import ru.matthew.MyShop.repository.UserOrderRepository;
import ru.matthew.MyShop.service.AdvertismentService;
import ru.matthew.MyShop.service.ReviewService;
import ru.matthew.MyShop.service.UserOrderService;
import ru.matthew.MyShop.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final UserService userService;
    private final UserOrderService userOrderService;
    private final AdvertismentService advertismentService;
    private final ReviewService reviewService;

    @GetMapping("/index")
    public String index(Principal principal, Model model) {
        model.addAttribute("advertismentsToMod", advertismentService.getAllAdvertismentsToModerate());
        model.addAttribute("advertismentsActive", advertismentService.getAllAdvertisments());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("modorders", userOrderService.getOrdersByStatus());
        return "admin/info";
    }
}
