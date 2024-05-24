package ru.matthew.MyShop.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.matthew.MyShop.models.*;
import ru.matthew.MyShop.service.AdvertismentService;
import ru.matthew.MyShop.service.ReviewService;
import ru.matthew.MyShop.service.UserService;

import java.io.IOException;
import java.security.Principal;

@Controller
@RequestMapping("/advertisments")
@RequiredArgsConstructor
public class AdvertismentController {

    private final AdvertismentService advertismentService;
    private final ReviewService reviewService;
    private final UserService userService;

    //Выдача главной страницы с объявлениями
    @GetMapping("/index")
    public String index(@RequestParam(name = "title", required = false) String title,
                        @RequestParam(name = "category", required = false) Category сategory,
                        @RequestParam(name = "city", required = false) City city,
                        @RequestParam(name = "state", required = false) State state,
                        @RequestParam(name = "price_from", required = false) Integer fromprice,
                        @RequestParam(name = "price_to", required = false) Integer toprice,
                        Principal principal, Model model) {
        if (title == null) {
            model.addAttribute("advertisments", advertismentService.getAllAdvertisments());
        }else{
            model.addAttribute("advertisments", advertismentService.getFilterAdvertisments(title, сategory, city, state, fromprice, toprice));
        }
        //model.addAttribute("advertisments", advertismentService.getAllAdvertisments());
        //model.addAttribute("advertisment", new Advertisment());
        model.addAttribute("user", advertismentService.getUserByPrincipal(principal));
        return "advertisments/index";
    }

    //Выдача страницы для создания объявления
    @GetMapping("/new")
    public String newAdvertisment(Model model, Principal principal) {
        model.addAttribute("advertisment", new Advertisment());
        return "advertisments/new";
    }

    @GetMapping("/basket")
    public String basket(Model model, Principal principal) {
        model.addAttribute("advertisments", advertismentService.findBasketAddvetisments(principal));
        return "advertisments/basket";
    }

    //Выдача объявления по ID
    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Principal principal, Model model) {
        Advertisment advertisment = advertismentService.getAdvertisementById(id);
        model.addAttribute("advertisment", advertisment);
        model.addAttribute("user", advertismentService.getUserByPrincipal(principal));
        model.addAttribute("newReview", new Review());
        //model.addAttribute("images", );
        return "advertisments/show";
    }

    //Создание объявления
    @PostMapping()
    public String createAdvertisment(@ModelAttribute("advertisment") @Valid Advertisment advertisment,
                                     BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors())
            return "advertisments/new";
        System.out.println("До вызова сервиса регистрации");
        advertismentService.registerAdvertisment(principal, advertisment);
        //advertismentService.registerAdvertisment(advertisment, file1, file2, file3);
        return "redirect:/advertisments/index";
    }

    //Изменение объявления
    @PostMapping("/{id}")
    public String advertismentEdit(@PathVariable("id") long id, @ModelAttribute("advertisment") @Valid Advertisment advertisment,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "advertisments/edit";

        advertismentService.updateAdvertisment(advertisment, id);
        return "redirect:/advertisments/index";
    }

    //Создание отзыва на конкретное объявление
    @PostMapping("/{id}/newReview")
    public String createReviewToAdvertisment(@ModelAttribute("newReview") @Valid Review review,
                                             @PathVariable("id") long id,
                                             BindingResult bindingResult, Principal principal){
        if (bindingResult.hasErrors())
            return "advertisments/new";
        System.out.println("Создание отзыва к объявлению");
        System.out.println(review.toString());

        reviewService.createReviewToAdvertisment(review, id, principal);
        //advertismentService.registerAdvertisment(advertisment, file1, file2, file3);
        return "redirect:/advertisments/index";
    }

    //Удалить конкретное объявление
    //@Query("delete from Advertisment a where a.id=:id")
    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id, Principal principal) {
        userService.removeAdvertisment(id, principal);
        return "redirect:/advertisments/index";
    }

    @GetMapping("/{id}/tobasket")
    public String addToBusket(@PathVariable("id") long id, Principal principal) {
        advertismentService.addAdvertismentToBusket(id, principal);
        return "redirect:/advertisments/index";
    }

    //Изменить конкретное объявления
    @GetMapping("/{id}/edit")
    public String delete(@PathVariable("id") long id, Model model) {
        model.addAttribute("advertisment", advertismentService.getAdvertisementById(id));
        return "redirect:/advertisments/edit";
    }
}
