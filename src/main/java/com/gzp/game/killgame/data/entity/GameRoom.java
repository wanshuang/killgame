package com.gzp.game.killgame.data.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ws
 * @date 2019/12/27
 */
@Entity
@Table(name = "t_game_room")
@Accessors(chain = true)
@Data
public class GameRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Long createUserId;

    @Transient
    private String userName;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    private Long gameTypeId;

    @Transient
    private String gameName;

    public enum Status {
        active,
        start,
        hold,
        close
    }
}
