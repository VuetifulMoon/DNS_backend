package org.vuetiful.DNS.domain.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.vuetiful.DNS.domain.notification.entity.Notification;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {
    @Query("select n from Notification n where n.member.memberId=:reciverId and n.readStatus=false ")
    List<Notification> nonReadNotification(@Param("reciverId") int reciverId);
}
