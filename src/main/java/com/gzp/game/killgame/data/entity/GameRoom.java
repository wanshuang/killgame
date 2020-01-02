package com.gzp.game.killgame.data.entity;

import com.gzp.game.killgame.data.GameType;
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

    private Date creationTime;

    @Enumerated(EnumType.STRING)
    private GameType gameType;

    public enum Status {
        active,
        start,
        hold,
        close
    }
}
