package com.microservice.reviewservice.messaging;

import com.microservice.reviewservice.dto.ReviewMessage;
import com.microservice.reviewservice.modal.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@RequiredArgsConstructor
public class ReviewMessageProducer {
    private final RabbitTemplate rabbitTemplate;

   public void sendMessage(Review reviewObj ) {
       ReviewMessage reviewMessage = new ReviewMessage();
       reviewMessage.setId(reviewObj.getId());
       reviewMessage.setTitle(reviewObj.getTitle());
       reviewMessage.setDescription(reviewObj.getDescription());
       reviewMessage.setRating(reviewObj.getRating());
       reviewMessage.setCompanyId(reviewObj.getCompanyId());
       rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);

   }
}
