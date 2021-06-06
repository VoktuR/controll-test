package ru.rav.controll.db_request_api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.rav.controll.common.MessageEntity;
import ru.rav.controll.common.MessagesRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesRepository messageRepository;

    public Optional<MessageDto> getMessage(Long id) {
        return messageRepository.findById(id).map(MessageDto::new);
    }

    public Page<MessageDto> getMessages(Specification<MessageEntity> spec, Integer page, Integer pageSize) {
        return messageRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(MessageDto::new);
    }

}
