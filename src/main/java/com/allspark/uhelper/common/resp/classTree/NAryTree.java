package com.allspark.uhelper.common.resp.classTree;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @ClassName NAryTree
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/19 16:25
 * @Version 1.0
 **/
@Data
@EqualsAndHashCode
public class NAryTree {

    public Long value;

    public String label;


    public List<NAryTree> children;

}
