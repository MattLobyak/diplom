package ru.matthew.MyShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matthew.MyShop.models.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAll();
    Review findReviewById(Long id);
}
