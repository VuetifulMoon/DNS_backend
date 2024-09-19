package org.vuetiful.DNS.domain.dmRoom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.vuetiful.DNS.domain.dmRoom.entity.DmRoom;

@Repository
public interface DmRoomRepository extends JpaRepository<DmRoom, Integer> {
}
