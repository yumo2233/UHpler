package com.allspark.uhelper.service.impl;

import com.allspark.uhelper.common.resp.classTree.NAryTree;
import com.allspark.uhelper.db.mapper.GraduateInfoMapper;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.GraduateTargetInfo;
import com.allspark.uhelper.service.GraduateTargetInfoService;
import com.allspark.uhelper.db.mapper.GraduateTargetInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* @author 86159
* @description 针对表【graduate_target_info(指标点信息表)】的数据库操作Service实现
* @createDate 2023-01-20 19:07:15
 */
@Service
@DS("u_graduateinfo")
public class GraduateTargetInfoServiceImpl extends ServiceImpl<GraduateTargetInfoMapper, GraduateTargetInfo>
        implements GraduateTargetInfoService {


    @Resource
    private GraduateInfoMapper graduateInfoMapper;

    @Resource
    private GraduateTargetInfoMapper graduateTargetInfoMapper;

    public List<NAryTree> listAll2() {
        List<NAryTree> graduateTree = new ArrayList<>();
        List<GraduateTargetInfo> list = list();
        HashMap<Long, NAryTree> graMap = new HashMap<>();

        for (GraduateTargetInfo graduateTargetInfo : list) {
            if (!graMap.containsKey(graduateTargetInfo.getGraduate_id())) {
                NAryTree graduate = new NAryTree();
                graduate.setValue(graduateTargetInfo.getGraduate_id());
                graduate.setLabel(graduateInfoMapper.selectNameById(graduateTargetInfo.getGraduate_id()));
                List<NAryTree> graChild = new ArrayList<>();
                NAryTree target = new NAryTree();
                target.setValue(graduateTargetInfo.getId());
                target.setLabel(graduateTargetInfo.getName());
                graChild.add(target);
                graduate.setChildren(graChild);
                graduateTree.add(graduate);
            } else {
                NAryTree graduate = graMap.get(graduateTargetInfo.getGraduate_id());
                NAryTree target = new NAryTree();
                target.setValue(graduateTargetInfo.getId());
                target.setLabel(graduateTargetInfo.getName());
                graduate.getChildren().add(target);
            }
        }
        return graduateTree;
    }


    @Override
    public HashMap searchTargetById(int id) {
        HashMap map = graduateTargetInfoMapper.searchTargetById(id);
        //JSONArray tag = JSONUtil.parseArray(MapUtil.getStr(map, "tag"));
        //map.replace(tag,"tag");
        return map;
    }

    @Override
    public void insertTarget(long id) {
        graduateTargetInfoMapper.insertTarget(id);
    }


    @Override
    public boolean deleteTarget(long id) {
        boolean flag = false;
        if (graduateTargetInfoMapper.deleteTarget(id)) {
            flag = true;
        }
        return flag;
    }
}




