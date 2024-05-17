package ru.matthew.MyShop.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.matthew.MyShop.models.Review;
import ru.matthew.MyShop.models.User;
import ru.matthew.MyShop.service.ReviewService;
import ru.matthew.MyShop.service.UserService;

@Controller
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews/index";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("review", new Review());
        return "reviews/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("review") @Valid Review review,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "reviews/new";
        reviewService.createReview(review);
        return "redirect:/reviews/index";
    }
}
