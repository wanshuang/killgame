package com.gzp.game.killgame.data.repository;

import com.gzp.game.killgame.data.entity.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ws
 * @date 2020/1/2
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    public List<Message> findByRoomId(Long roomId);

    public List<Message> findByIdGreaterThanAndRoomId(Long Id,Long roomId);

}
