package com.gzp.game.killgame.data.repository;

import com.gzp.game.killgame.data.entity.GameRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ws
 * @date 2019/12/27
 */
@Repository
public interface GameRoomRepository extends CrudRepository<GameRoom, Long> {

    List<GameRoom> findBystatus(GameRoom.Status status);

}
