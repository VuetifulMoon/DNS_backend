package org.vuetiful.DNS.domain.notification.dto;

import lombok.*;
import org.vuetiful.DNS.domain.notification.entity.Notification;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class NoticeResponse {
    int notificationId;
    String notificationContent;

    public NoticeResponse(Notification notification) {
        this.notificationId = notification.getNotificationId();
        this.notificationContent = notification.getNotificationContent();
    }
}
