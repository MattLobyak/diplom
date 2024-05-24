package ru.matthew.MyShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.matthew.MyShop.models.Advertisment;
import ru.matthew.MyShop.models.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAll();
    List<Review> findReviewByAdvertisment_Id(Long id);
    void deleteReviewsByAdvertisment(Advertisment advertisment);
    Review findReviewById(Long id);
}
