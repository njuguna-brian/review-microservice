package com.brian.reviewms.review.imp;


import com.brian.reviewms.review.Review;
import com.brian.reviewms.review.ReviewRepository;
import com.brian.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if (companyId != null && review != null) {
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long reviewId) {
        return reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public boolean updateReview(Long reviewId, Review review) {
        Review reviewToUpdate = reviewRepository.findById(reviewId).orElse(null);
        if (reviewToUpdate != null) {
            reviewToUpdate.setReview(review.getReview());
            reviewToUpdate.setDescription(review.getDescription());
            reviewToUpdate.setRating(review.getRating());
            reviewToUpdate.setCompanyId(review.getCompanyId());
            reviewRepository.save(reviewToUpdate);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review reviewToDelete = reviewRepository.findById(reviewId).orElse(null);
        if (reviewToDelete != null) {
            reviewRepository.delete(reviewToDelete);
            return true;
        }
        return false;
    }

}
