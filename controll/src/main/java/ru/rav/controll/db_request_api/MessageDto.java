package ru.rav.controll.db_request_api;

import lombok.Data;
import ru.rav.controll.common.MessageEntity;

import java.sql.Timestamp;

@Data
public class MessageDto {

    private Long messageId;

    private Timestamp messageTime;

    private String queueName;

    public MessageDto(MessageEntity messageEntity) {
        this.messageId = messageEntity.getId();
        this.messageTime = messageEntity.getTimestamp();
        this.queueName = messageEntity.getQueueName();
    }

}
