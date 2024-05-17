package ru.matthew.MyShop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.matthew.MyShop.models.Advertisment;
import ru.matthew.MyShop.models.Review;
import ru.matthew.MyShop.models.User;
import ru.matthew.MyShop.repository.AdvertismentRepository;
import ru.matthew.MyShop.repository.ReviewRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final AdvertismentRepository advertismentRepository;

    public Review getReviewById(long id) {
        return reviewRepository.findReviewById(id);
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public void createReview(Review review){
        review.setDateOfPublishing(LocalDate.now());
        reviewRepository.save(review);
    }

    public void createReviewToAdvertisment(Review review, Long id){
        Advertisment advertisment = advertismentRepository.findById(id).orElse(null);
        System.out.println("ID объявления  -----------------      "+ id);
        review.setDateOfPublishing(LocalDate.now());
        review.setAdvertisment(advertisment);
        System.out.println(review.toString());
        reviewRepository.save(review);
    }

}
