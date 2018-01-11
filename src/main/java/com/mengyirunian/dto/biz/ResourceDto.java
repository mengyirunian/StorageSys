package com.mengyirunian.dto.biz;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by Jiaxiayuan on 2018/1/12
 */
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceDto {

    private String funcParent;
    private String funcAction;
    private String funcParentName;
    private String funcActionName;

}
