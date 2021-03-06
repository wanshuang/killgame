package com.gzp.game.killgame.data.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;


/**
 * @author ws
 * @date 2019/12/25
 */
@Entity
@Table(name = "t_user")
@Accessors(chain = true)
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String pwd;

}