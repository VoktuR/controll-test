package ru.rav.controll.db_request_api;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/msg")
public class MessagesController {

    private final MessagesService messagesService;
    private final MessagesSpecs messagesSpecs;

    @GetMapping("/{id}")
    public MessageDto getMessage(@PathVariable Long id) {
        return messagesService.getMessage(id).get();
    }

    @GetMapping
    public Page<MessageDto> getMessages(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page,
            @RequestParam(name = "pSize", defaultValue = "10") Integer pageSize
    ) {
        return messagesService.getMessages(messagesSpecs.build(params), page, pageSize);
    }

}
