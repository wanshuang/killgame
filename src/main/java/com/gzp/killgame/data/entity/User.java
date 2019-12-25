package com.gzp.killgame.data.entity;

import lombok.Data;

import javax.persistence.*;


/**
 * @author ws
 * @date 2019/12/25
 */
@Entity
@Table(name = "t_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String account;

    private String pwd;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", url='" + pwd + '\'' +
                '}';
    }
}