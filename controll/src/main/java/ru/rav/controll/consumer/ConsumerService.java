package ru.rav.controll.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.JmsException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import ru.rav.controll.common.MessageEntity;
import ru.rav.controll.common.MessagesRepository;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.transaction.Transactional;
import java.sql.Timestamp;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerService {

    private final MessagesRepository messageRepository;

    private final String listenedQueue = "ACTIVE_QUEUE";

    @JmsListener(destination = listenedQueue, containerFactory = "testTaskListenerFactory")
    @Transactional
    public void processMessage(TextMessage content) {
        try {
            messageRepository.save(
                    new MessageEntity()
                            .setId(content.getLongProperty("id"))
                            .setMessageBody(content.getText())
                            .setQueueName(listenedQueue)
                            .setTimestamp(new Timestamp(System.currentTimeMillis()))
            );
            log.info(String.format("Msg %s were saved", content.getLongProperty("id")));
        } catch (JmsException | JMSException e) {
            e.printStackTrace();
        }
    }


}
