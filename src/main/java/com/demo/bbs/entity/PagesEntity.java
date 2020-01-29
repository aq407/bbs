package com.demo.bbs.entity;

import lombok.Data;

@Data
public class PagesEntity {
    private int page =1;//当前页数
    private int PageSize =5;
    private int PageTotal; //数据总量
    private int aPage ; //有多少页

    private boolean showPrevious;//上一页
    private boolean showFirstPage;//第一页
    private boolean showNext;//下一页
    private boolean showEndPage;//最后一页
}
