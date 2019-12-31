package com.gzp.game.killgame;

import com.alibaba.fastjson.JSONObject;
import com.gzp.game.killgame.data.entity.User;
import com.gzp.game.killgame.util.web.JWTUtils;
import org.junit.jupiter.api.Test;

/**
 * @author ws
 * @date 2019/12/27
 */
public class cookieTest {

    @Test
    public void test() {
        User user = new User();
        user.setId(1l);
        user.setAccount("ws");
        user.setPwd("ws");
        String cookie = JWTUtils.encrypt(JSONObject.toJSONString(user));
        System.out.println(cookie);
        String cookieValue = JWTUtils.decrypt(cookie);
        System.out.println(cookieValue);

    }
}
