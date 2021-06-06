package ru.rav.controll.common;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Accessors(chain = true)
public class MessageEntity {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "message_body")
    private String messageBody;

    @Column(name = "queue_name")
    private String queueName;

    @Column(name = "timestamp")
    private Timestamp timestamp;

}
