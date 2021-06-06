package ru.rav.controll.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.rav.controll.common.RequestDto;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProducerService {

    private final JmsTemplate jmsTemplate;

    @Transactional
    public void saveToDb(RequestDto requestDto) {
        jmsTemplate.convertAndSend(requestDto.getQueueName(), requestDto.getMessage(),
                message -> {
                    message.setLongProperty("id", requestDto.getMessageId());
                    return message;
                }
        );
        log.info(String.format("Message %s were sent", requestDto.getMessageId()));
    }

}
