package com.demo.bbs.entity;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class PagesEntity {

    private Long total;


    private List<PublicEntity> publicEntities;

}
