package com.gzp.game.killgame.util.web;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.gzp.game.killgame.data.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @author ws
 * @date 2019/12/26
 */
public class WebUtils {

    private static String COOKIE_USER = "t_user";

    public static User getUser(HttpServletRequest request){
        User result = null;
        if(request.getCookies() == null ||request.getCookies().length == 0){
            return result;
        }
        for(Cookie cookie : request.getCookies()){
            if(cookie.getName().equals(COOKIE_USER)){
                result = JSONObject.parseObject(JWTUtils.decrypt(cookie.getValue()),User.class);
            }
        }
        return result;
    }

    public static void setUser(HttpServletResponse response, User user){
        if(user != null) {
            response.addCookie(new Cookie(COOKIE_USER, JWTUtils.encrypt(JSONObject.toJSONString(user))));
        }
    }

}
