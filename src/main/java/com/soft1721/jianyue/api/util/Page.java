package com.soft1721.jianyue.api.util;

import lombok.Data;

import java.awt.*;

/**
 * Created by 张文旭 on 2019/4/15.
 */
@Data
public class Page {
    private  int pageSize = 0;
    private int pageIndex = 0;
    private int totalPageCount = 0;
    private int recore = 0;
    private Integer nextPage;
    private Integer prePage;

}
