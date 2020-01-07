package com.gzp.game.killgame.data.repository;

import com.gzp.game.killgame.data.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ws
 * @date 2019/12/25
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    List<User> findByAccountAndPwd(String account, String pwd);

}
