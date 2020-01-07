package com.gzp.game.killgame.service.room;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.gzp.game.killgame.data.entity.GameRoom;
import com.gzp.game.killgame.data.entity.GameType;
import com.gzp.game.killgame.data.entity.User;
import com.gzp.game.killgame.data.repository.GameRoomRepository;
import com.gzp.game.killgame.data.repository.GameTypeRepository;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ws
 * @date 2019/12/27
 */
@Service
public class GameRoomService {

    @Autowired
    GameRoomRepository gameRoomRepository;

    @Autowired
    GameTypeRepository gameTypeRepository;

    public GameRoom createGameRoom(User user) {
        GameType gameType = gameTypeRepository.findById(1l).get();
        return gameRoomRepository.save(new GameRoom().setCreateUserId(user.getId()).setCreationTime(new Date()).setStatus(GameRoom.Status.active).setGameTypeId(gameType.getId()));
    }

    public List<GameRoom> getAllActive() {
        List<GameRoom> rooms = gameRoomRepository.findBystatus(GameRoom.Status.active);
        Map<Long, GameType> maps = Maps.uniqueIndex(gameTypeRepository.findAll(), new Function<GameType, Long>() {
            @Nullable
            @Override
            public Long apply(@Nullable GameType gameType) {
                return gameType.getId();
            }
        });
        rooms.forEach(room -> {
            room.setGameName(maps.get(room.getGameTypeId()).getName());
        });

        return rooms;
    }

}
