package com.reviewMicroservice.reviewMicroservice.Review.impl;

import com.reviewMicroservice.reviewMicroservice.Review.Review;
import com.reviewMicroservice.reviewMicroservice.Review.ReviewRepository;
import com.reviewMicroservice.reviewMicroservice.Review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImplementation implements ReviewService {

    private ReviewRepository reviewRepository;

    public ReviewServiceImplementation(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);

        return reviews ;
    }

    @Override
    public boolean addReview(Long companyId, Review review) {

        if (companyId !=null && review!= null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview( Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview( Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!= null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRatings(updatedReview.getRatings());
            reviewRepository.save(review);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review!=null){
            reviewRepository.delete(review);
            return true;
        }else{
            return false;
        }
    }
}
