package com.demo.bbs.entity;

import lombok.Data;

import java.util.List;

@Data
public class PageEntity {

    private Long total;


    private List<UserEntity> userEntity;
}
