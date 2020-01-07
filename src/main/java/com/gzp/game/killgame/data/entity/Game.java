package com.gzp.game.killgame.data.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ws
 * @date 2020/1/6
 */
@Entity
@Table(name = "t_game")
@Accessors(chain = true)
@Data
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long gameTypeId;

    private Long createUserId;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date creationTime;

    @Enumerated(EnumType.STRING)
    private GameStatus gameStatus;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    public enum GameStatus {
        wait,
        ready,
        start,
        end
    }
}
