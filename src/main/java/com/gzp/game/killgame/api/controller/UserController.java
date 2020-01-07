package com.gzp.game.killgame.api.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gzp.game.killgame.data.entity.User;
import com.gzp.game.killgame.util.web.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ws
 * @date 2019/12/27
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @ResponseBody
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public JSONObject getInfo(
            HttpServletRequest request) {
        User user = WebUtils.getUser(request);
        JSONObject result = new JSONObject();
        result.put("user", JSON.toJSONString(user));
        return result;
    }

}
