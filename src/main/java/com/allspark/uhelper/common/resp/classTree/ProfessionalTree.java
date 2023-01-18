package com.allspark.uhelper.common.resp.classTree;


import lombok.Data;

import java.util.Set;

/**
 * @ClassName ProfessionalTree
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/18 20:18
 * @Version 1.0
 **/
@Data
public class ProfessionalTree {

    String professionalName;

    Set<ClassTree> classTrees;
}
