package org.vuetiful.DNS.domain.notification.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.vuetiful.DNS.domain.notification.dto.NoticeResponse;
import org.vuetiful.DNS.domain.notification.service.NotificationService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    private final NotificationService notificationService;
    public static Map<Integer, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    @GetMapping(value = "/connect/{memberId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect(@PathVariable int memberId) {
        SseEmitter sseEmitter = notificationService.subscribe(memberId);

        return sseEmitter;
    }

    @GetMapping(value = "/{memberId}")
    public List<NoticeResponse> readAll(@PathVariable int memberId) {
        return notificationService.allNotice(memberId);
    }

    @PostMapping(value = "/{notificationId}")
    public void check(@PathVariable int notificationId) {
        notificationService.check(notificationId);
    }
}
