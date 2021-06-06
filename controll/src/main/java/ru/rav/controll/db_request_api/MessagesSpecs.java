package ru.rav.controll.db_request_api;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import ru.rav.controll.common.MessageEntity;

import java.sql.Timestamp;

@Component
public class MessagesSpecs {

    private Specification<MessageEntity> timeMoreThan(Timestamp minTime) {
        return (Specification<MessageEntity>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("timestamp"), minTime);
    }

    private Specification<MessageEntity> timeLessThan(Timestamp maxTime) {
        return (Specification<MessageEntity>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("timestamp"), maxTime);
    }

    private Specification<MessageEntity> queueLike(String qName) {
        return (Specification<MessageEntity>) (root, criteriaQuery, criteriaBuilder) ->
                criteriaBuilder.like(root.get("queueName"), qName);
    }

    public Specification<MessageEntity> build(MultiValueMap<String, String> params) {
        Specification<MessageEntity> spec = Specification.where(null);
        if (params.containsKey("minTime") && !params.getFirst("minTime").isBlank()) {
            spec = spec.and(timeMoreThan(Timestamp.valueOf(params.getFirst("minTime"))));
        }
        if (params.containsKey("maxTime") && !params.getFirst("maxTime").isBlank()) {
            spec = spec.and(timeLessThan(Timestamp.valueOf(params.getFirst("maxTime"))));
        }
        if (params.containsKey("qName") && !params.getFirst("qName").isBlank()) {
            spec = spec.and(queueLike(params.getFirst("qName")));
        }
        return spec;
    }

}
