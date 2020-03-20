package com.gzp.game.killgame.api.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gzp.game.killgame.data.entity.GameRoom;
import com.gzp.game.killgame.service.room.GameRoomService;
import com.gzp.game.killgame.util.web.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author ws
 * @date 2019/12/27
 */
@Controller
@RequestMapping(value = "/gameroom")
public class GameRoomController {

    @Autowired
    GameRoomService gameRoomService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JSONObject createGameRoom(HttpServletRequest request,
                                     @RequestParam(value = "typeId") Long typeId) {
        GameRoom room = gameRoomService.createGameRoom(WebUtils.getUser(request),typeId);
        JSONObject result = new JSONObject();
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/get/active", method = RequestMethod.GET)
    public JSONObject getAllActiveRoom(
            HttpServletRequest request) {
        List<GameRoom> rooms = gameRoomService.getAllActive();
        JSONObject result = new JSONObject();
        JSONArray array = new JSONArray();
        array.addAll(rooms);
        result.put("rooms", array.toJSONString());
        return result;
    }


    @RequestMapping(value = "/into", method = RequestMethod.GET)
    public ModelAndView intoRoom(
            HttpServletRequest request,
            @RequestParam(value = "id") Long id) {
        GameRoom gameRoom = gameRoomService.getById(id);
        if(gameRoom == null){
           return null;
        }
        ModelAndView mav = new ModelAndView();
        if(gameRoom.getGameTypeId() == 1){
            mav.setViewName("room_chat");
        }else{
            mav.setViewName("room_killgame");
        }
        mav.addObject("userId", WebUtils.getUser(request).getId());
        mav.addObject("roomId", id);
        return mav;
    }

}
