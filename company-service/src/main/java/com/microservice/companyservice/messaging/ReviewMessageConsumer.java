package com.microservice.companyservice.messaging;

import com.microservice.companyservice.dto.ReviewMessage;
import com.microservice.companyservice.service.CompanyService;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMessageConsumer {

    private final CompanyService companyService;

    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessage reviewObj) {
        companyService.updateCompany(reviewObj);
    }
}
