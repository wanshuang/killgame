package com.gzp.game.killgame.service.room;

import com.gzp.game.killgame.data.GameType;
import com.gzp.game.killgame.data.entity.GameRoom;
import com.gzp.game.killgame.data.entity.User;
import com.gzp.game.killgame.data.repository.GameRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ws
 * @date 2019/12/27
 */
@Service
public class GameRoomService {

    @Autowired
    GameRoomRepository gameRoomRepository;

    public GameRoom createGameRoom(User user){
        return gameRoomRepository.save(new GameRoom().setCreateUserId(user.getId()).setCreationTime(new Date()).setStatus(GameRoom.Status.active).setGameType(GameType.CHAT));
    }

    public List<GameRoom> getAllActive(){
        return gameRoomRepository.findBystatus(GameRoom.Status.active);
    }

}
