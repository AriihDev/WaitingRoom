package sa.aristide.waitingroom.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import sa.aristide.waitingroom.entities.WaitingRoom;

public interface WaitingRoomRepository extends JpaRepository<WaitingRoom, Date> {

}
