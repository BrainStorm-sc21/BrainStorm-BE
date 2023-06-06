package com.brainstrom.meokjang.review.service;

import com.brainstrom.meokjang.deal.domain.Deal;
import com.brainstrom.meokjang.deal.repository.DealRepository;
import com.brainstrom.meokjang.review.domain.Review;
import com.brainstrom.meokjang.review.dto.request.ReviewRequest;
import com.brainstrom.meokjang.review.dto.response.ReviewResponse;
import com.brainstrom.meokjang.review.repository.ReviewRepository;
import com.brainstrom.meokjang.user.domain.User;
import com.brainstrom.meokjang.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final DealRepository dealRepository;
    private final UserRepository userRepository;

    public ReviewService(ReviewRepository reviewRepository,
                         DealRepository dealRepository,
                         UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.dealRepository = dealRepository;
        this.userRepository = userRepository;
    }

    public void save(Long dealId, ReviewRequest reviewRequest) {
        if (reviewRequest.getRating() < 0 || reviewRequest.getRating() > 5) {
            throw new IllegalStateException("평점은 0점 이상 5점 이하로 입력해주세요.");
        } else if (reviewRequest.getReviewContent().length() > 1000) {
            throw new IllegalStateException("리뷰 내용은 1000자 이하로 입력해주세요.");
        } else if (reviewRequest.getReviewContent().length() == 0) {
            throw new IllegalStateException("리뷰 내용을 입력해주세요.");
        } else if (Objects.equals(reviewRequest.getReviewFrom(), reviewRequest.getReviewTo())) {
            throw new IllegalStateException("자신에게 리뷰를 작성할 수 없습니다.");
        } else {
            User reviewFrom = userRepository.findById(reviewRequest.getReviewFrom()).orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));
            User reviewTo = userRepository.findById(reviewRequest.getReviewTo()).orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));
            Deal deal = dealRepository.findById(dealId).orElseThrow(() -> new IllegalStateException("존재하지 않는 거래입니다."));
            Review review = Review.builder()
                    .reviewFrom(reviewFrom.getUserId())
                    .reviewTo(reviewTo.getUserId())
                    .dealId(deal.getDealId())
                    .rating(reviewRequest.getRating())
                    .reviewContent(reviewRequest.getReviewContent())
                    .build();
//            Review review = Review.builder()
//                    .reviewFrom(reviewRequest.getReviewFrom())
//                    .reviewTo(reviewRequest.getReviewTo())
//                    .dealId(dealId)
//                    .rating(reviewRequest.getRating())
//                    .reviewContent(reviewRequest.getReviewContent())
//                    .build();
            reviewRepository.save(review);
            updateReliability(reviewTo.getUserId(), reviewRequest.getRating());
        }
    }

    public Map<String, List<ReviewResponse>> getReviewList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));
        return Map.of(
                "reviewFrom", reviewRepository.findAllByReviewFrom(user.getUserId()),
                "reviewTo", reviewRepository.findAllByReviewTo(user.getUserId())
        );
    }

    public void updateReliability(Long reviewTo, Float rating) {
        User user = userRepository.findById(reviewTo).orElseThrow(() -> new IllegalStateException("존재하지 않는 유저입니다."));
        int result = userRepository.updateReliabilityById(reviewTo, user.getReliability() + rating);
        if (result != 1) {
            throw new IllegalStateException("신뢰도 업데이트에 실패하였습니다.");
        }
    }
}
