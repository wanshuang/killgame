package com.gzp.game.killgame.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author ws
 * @date 2020/1/6
 */
@Entity
@Table(name = "t_game_type")
@Accessors(chain = true)
@Data
public class GameType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String describe;

    @Enumerated(EnumType.STRING)
    private Status status;

    public enum Status {
        active,
        invalid
    }
}
