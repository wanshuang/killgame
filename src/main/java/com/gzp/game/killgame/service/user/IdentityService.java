package com.gzp.game.killgame.service.user;

import com.gzp.game.killgame.data.entity.User;
import com.gzp.game.killgame.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ws
 * @date 2019/12/26
 */
@Service
public class IdentityService {

    @Autowired
    UserRepository userRepository;

    /**
     * 获取用户
     * @param account
     * @param pwd
     * @return
     */
    public User identityVerification(String account, String pwd) {
        List<User> users = userRepository.findByAccountAndPwd(account, pwd);
        return (users != null && users.size() > 0) ? users.get(0) : null;
    }


}
