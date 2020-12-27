package com.ascon.firebase.controller;

import com.ascon.firebase.config.FCMInitializer;
import com.ascon.firebase.payload.MessagePayload;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class RestController {

    private final FCMInitializer fcmInitializer;

    @PostMapping(value = "/notification", produces = MediaType.TEXT_HTML_VALUE)
    public String sendNotification(@RequestBody MessagePayload messagePayload) {
        fcmInitializer.sendMessage(messagePayload.getToken(), messagePayload.getTitle(), messagePayload.getBody(), messagePayload.getData());
        return "Success";
    }
}
