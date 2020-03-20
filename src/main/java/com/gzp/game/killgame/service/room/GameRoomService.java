package com.gzp.game.killgame.service.room;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.gzp.game.killgame.data.entity.GameRoom;
import com.gzp.game.killgame.data.entity.GameType;
import com.gzp.game.killgame.data.entity.User;
import com.gzp.game.killgame.data.repository.GameRoomRepository;
import com.gzp.game.killgame.data.repository.GameTypeRepository;
import com.gzp.game.killgame.data.repository.UserRepository;
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

    @Autowired
    UserRepository userRepository;

    public GameRoom createGameRoom(User user,Long typeId) {
        GameType gameType = gameTypeRepository.findById(typeId).get();
        return gameRoomRepository.save(new GameRoom().setCreateUserId(user.getId()).setCreationTime(new Date()).setStatus(GameRoom.Status.active).setGameTypeId(gameType.getId()));
    }

    public GameRoom getById(Long id){
        return gameRoomRepository.findById(id).get();
    }

    public List<GameRoom> getAllActive() {
        List<GameRoom> rooms = gameRoomRepository.findBystatus(GameRoom.Status.active);
        Map<Long, GameType> gtMap = Maps.uniqueIndex(gameTypeRepository.findAll(), new Function<GameType, Long>() {
            @Nullable
            @Override
            public Long apply(@Nullable GameType gameType) {
                return gameType.getId();
            }
        });

        Map<Long, User> uMap = Maps.uniqueIndex(userRepository.findAll(), new Function<User, Long>() {

            @Nullable
            @Override
            public Long apply(@Nullable User user) {
                return user.getId();
            }
        });

        rooms.forEach(room -> {
            room.setGameName(gtMap.get(room.getGameTypeId()).getName());
            room.setUserName(uMap.get(room.getCreateUserId()).getAccount());
        });

        return rooms;
    }

}
