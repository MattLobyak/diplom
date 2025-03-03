package ru.matthew.MyShop.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.matthew.MyShop.models.User;
import ru.matthew.MyShop.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class UserValidate implements Validator {
    private final UserRepository userRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof User user) {
            if (userRepository.findUserByEmail(user.getEmail()) != null) {
                errors.rejectValue("email", "already.in.use", "Данный email уже используется");
            }
            if (userRepository.findUserByPhoneNumber(user.getPhoneNumber()).isPresent()) {
                errors.rejectValue("phoneNumber", "already.in.use", "Данный номер телефона уже используется");
            }

        }
    }
}
