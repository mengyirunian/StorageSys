package com.mengyirunian.dto.support;

import lombok.Data;

/**
 * Created by Jiaxiayuan on 2018/1/14
 */
@Data
public abstract class Page {

    protected Integer pageNo;
    protected Integer pageSize;
    protected Long counts;

}
