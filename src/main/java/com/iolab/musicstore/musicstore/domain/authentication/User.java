package com.iolab.musicstore.musicstore.domain.authentication;

import com.iolab.musicstore.musicstore.domain.base.entity.BaseEntity;
import com.iolab.musicstore.musicstore.domain.upload.FileAttach;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String username;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private FileAttach avatar;
}
