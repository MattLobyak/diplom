package ru.matthew.MyShop.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import ru.matthew.MyShop.models.User;

import ru.matthew.MyShop.service.UserService;
import ru.matthew.MyShop.service.UserValidate;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserValidate userValidate;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/index";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "auth/registrate";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/show";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        userValidate.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "users/new";
        userService.registerUser(user);
        return "redirect:/users/index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}/ban")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin/index";
    }

    @GetMapping("/{id}/unban")
    public String userUnban(@PathVariable("id") Long id) {
        userService.unbanUser(id);
        return "redirect:/admin/index";
    }

    @GetMapping("/{id}/edit")
    public String userEdit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        return "users/edit";
    }
    @PostMapping("/{id}/edituser")
    public String userEditPost(@PathVariable("id") long id, @ModelAttribute("user") @Valid User user,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "users/edit";

        userService.updateUser(user, id);
        return "redirect:/users/index";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users/index";
    }

//    @GetMapping("/{id}/ban")
//    public String ban(@PathVariable("id") long id) {
//        User user = userService.getUserById(id);
//        user.setSta;
//        return "redirect:/users/index";
//    }

//    @GetMapping("/{id}/unban")
//    public String unban(@PathVariable("id") long id) {
//        userService.unbanUser(id);
//        return "redirect:/users/index";
//    }

}
