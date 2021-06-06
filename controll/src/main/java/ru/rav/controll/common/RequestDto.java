package ru.rav.controll.common;

import lombok.Data;

@Data
public class RequestDto {

    private Long messageId;

    private String message;

    private String queueName;

}
