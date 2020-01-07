package com.gzp.game.killgame.data.repository;

import com.gzp.game.killgame.data.entity.GameType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ws
 * @date 2020/1/6
 */
@Repository
public interface GameTypeRepository extends CrudRepository<GameType, Long> {

}
