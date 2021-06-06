package ru.rav.controll.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.rav.controll.common.RequestDto;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/send")
public class ProducerController {

    private final ProducerService producerService;

    @PostMapping
    public ResponseEntity<String> saveMessage(@RequestBody RequestDto requestDto) {
        producerService.saveToDb(requestDto);
        return new ResponseEntity<>("Server received your message", HttpStatus.CREATED);
    }

}
