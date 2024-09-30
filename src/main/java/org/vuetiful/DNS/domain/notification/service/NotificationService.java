package org.vuetiful.DNS.domain.notification.service;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import org.vuetiful.DNS.domain.notification.controller.NotificationController;
import org.vuetiful.DNS.domain.notification.dto.NoticeResponse;
import org.vuetiful.DNS.domain.notification.entity.Notification;
import org.vuetiful.DNS.domain.notification.repository.NotificationRepository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public SseEmitter subscribe(int memberId){
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

        try{
            sseEmitter.send(SseEmitter.event().name("connect"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        NotificationController.sseEmitters.put(memberId, sseEmitter);

        sseEmitter.onCompletion(() -> NotificationController.sseEmitters.remove(memberId));
        sseEmitter.onTimeout(() -> NotificationController.sseEmitters.remove(memberId));
        sseEmitter.onError((e) -> NotificationController.sseEmitters.remove(memberId));

        return sseEmitter;
    }

    public List<NoticeResponse> allNotice(int receiverId) {
        return notificationRepository.nonReadNotification(receiverId).stream().map(notice ->
                new NoticeResponse(notice)).toList();
    }

    public void check(int notificationId) {
        Notification notice = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new IllegalArgumentException("해당 알림이 없습니다. id=" + notificationId));

        notice.check();
        notificationRepository.save(notice);
    }

    public void dmNotice(int receiverId) {
        if(NotificationController.sseEmitters.containsKey(receiverId)){
            SseEmitter sseEmitterReceiver = NotificationController.sseEmitters.get(receiverId);
            try{
                sseEmitterReceiver.send(SseEmitter.event().name("DM 알림").data(""));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void commentNotice(int receiverId) {
        if(NotificationController.sseEmitters.containsKey(receiverId)){
            SseEmitter sseEmitterReceiver = NotificationController.sseEmitters.get(receiverId);
            try{
                sseEmitterReceiver.send(SseEmitter.event().name("댓글 알림").data(""));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
