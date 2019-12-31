package com.gzp.game.killgame.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * @author ws
 * @date 2019/12/27
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

}
