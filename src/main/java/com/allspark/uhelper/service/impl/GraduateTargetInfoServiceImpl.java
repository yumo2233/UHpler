package com.allspark.uhelper.service.impl;

import com.allspark.uhelper.common.form.GraduateTargetForm;
import com.allspark.uhelper.common.resp.classTree.NAryTree;
import com.allspark.uhelper.db.mapper.GraduateInfoMapper;
import com.allspark.uhelper.db.pojo.GraduateInfo;
import com.allspark.uhelper.service.GraduateInfoService;
import com.allspark.uhelper.utils.UuidUtils;
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
        List<GraduateTargetInfo> list = graduateTargetInfoMapper.selectAll();
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

    //更新毕业指标数
    private int updateTargetCount(GraduateTargetInfo graduateTargetInfo, int n) {
        int count = n;
        //更新指标点数目
        GraduateInfo graduateInfo = new GraduateInfo();
        //确定毕业信息表的Id
        graduateInfo.setGrade(graduateTargetInfo.getGrade());
        graduateInfo.setProfessional(graduateTargetInfo.getProfessional());
        graduateInfo.setName(graduateTargetInfo.getGraduate_name());
        long id = graduateInfoMapper.selectId(graduateInfo);
        graduateInfo.setId(id);
        graduateInfo.setGraduate_target_count(count);
        graduateInfoMapper.updateGraduate_target_count(graduateInfo);
        return count;
    }

    //返回毕业要求的id
    private long getGraduate_idOfDb(GraduateTargetInfo graduateTargetInfo) {
        GraduateInfo graduateInfo = new GraduateInfo();
        graduateInfo.setGrade(graduateTargetInfo.getGrade());
        graduateInfo.setProfessional(graduateTargetInfo.getProfessional());
        graduateInfo.setName(graduateTargetInfo.getGraduate_name());
        long id = graduateInfoMapper.selectId(graduateInfo);
        return id;
    }

    //查找毕业要求对应的指标点个数
    private int selectgraduate_target_count(GraduateTargetInfo graduateTargetInfo) {
        GraduateInfo graduateInfo = new GraduateInfo();
        graduateInfo.setGrade(graduateTargetInfo.getGrade());
        graduateInfo.setProfessional(graduateTargetInfo.getProfessional());
        graduateInfo.setName(graduateTargetInfo.getGraduate_name());
        int id = graduateInfoMapper.selectGraduate_target_count(graduateInfo);
        return id;
    }


    @Override
    public GraduateTargetInfo transferTarget(GraduateTargetForm targetForm) {
        GraduateTargetInfo graduateTargetInfo = new GraduateTargetInfo();
        graduateTargetInfo.setId(targetForm.getId());
        graduateTargetInfo.setName(targetForm.getName());
        graduateTargetInfo.setContent(targetForm.getContent());
        graduateTargetInfo.setGraduate_id(targetForm.getGraduate_id());
        graduateTargetInfo.setGrade(targetForm.getGrade());
        graduateTargetInfo.setProfessional(targetForm.getProfessional());
        graduateTargetInfo.setGraduate_name(targetForm.getGraduate_name());
        return graduateTargetInfo;
    }

    @Override
    public GraduateTargetInfo searchTargetById(Long id) {
        GraduateTargetInfo graduateTargetInfo = graduateTargetInfoMapper.searchTargetById(id);
        //JSONArray tag = JSONUtil.parseArray(MapUtil.getStr(map, "tag"));
        //map.replace(tag,"tag");
        return graduateTargetInfo;
    }

    @Override
    public ArrayList<HashMap> searchTargetBatch(GraduateTargetInfo graduateTargetInfo) {
        long graduate_id = getGraduate_idOfDb(graduateTargetInfo);
        ArrayList<HashMap> list = graduateTargetInfoMapper.searchTargetBatch(graduate_id);
        return list;
    }

    @Override
    public int insertTarget(GraduateTargetInfo graduateTargetInfo) {
        //设置指标点的uuid
        graduateTargetInfo.setId(UuidUtils.getUuId());
        //设置外键Id
        graduateTargetInfo.setGraduate_id(getGraduate_idOfDb(graduateTargetInfo));
        //插入数据
        graduateTargetInfoMapper.insertTarget(graduateTargetInfo);
        //获取指标点数目
        int count = selectgraduate_target_count(graduateTargetInfo);
        return updateTargetCount(graduateTargetInfo, ++count);
    }

    @Override
    public int insertTargetBatch(List<GraduateTargetInfo> list) {
        int count = 0;
        for (int i = 0; i < list.size(); ++i) {
            GraduateTargetInfo graduateTargetInfo = list.get(i);
            insertTarget(graduateTargetInfo);
            if (i == list.size() - 1) {
                count = selectgraduate_target_count(graduateTargetInfo);
            }
        }
        return count;
        //graduateTargetInfoMapper.insertTargetBatch(list);
    }


    @Override
    public boolean deleteTarget(GraduateTargetInfo graduateTargetInfo) {
        graduateTargetInfo.setGraduate_id(getGraduate_idOfDb(graduateTargetInfo));
        boolean flag = false;
        if (graduateTargetInfoMapper.deleteTarget(graduateTargetInfo)) {
            flag = true;
        }
        int count = selectgraduate_target_count(graduateTargetInfo);
        GraduateInfo graduateInfo = new GraduateInfo();
        graduateInfo.setId(graduateTargetInfo.getGraduate_id());
        graduateInfo.setGraduate_target_count(--count);
        graduateInfoMapper.updateGraduate_target_count(graduateInfo);
        return flag;
    }

    @Override
    public boolean updateTarget(GraduateTargetInfo graduateTargetInfo) {
        boolean flag = false;
        //graduateTargetInfo.setGraduate_id(getGraduate_idOfDb(graduateTargetInfo));
        if (graduateTargetInfoMapper.updateTarget(graduateTargetInfo)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void updateTargetBatch(ArrayList<GraduateTargetInfo> arrayList) {
        //boolean flag = false;
        for (int i = 0; i < arrayList.size(); ++i) {
            GraduateTargetInfo graduateTargetInfo = arrayList.get(i);
            // graduateTargetInfo.setGraduate_id(getGraduate_idOfDb(graduateTargetInfo));
            updateTarget(graduateTargetInfo);
        }
        // if (graduateTargetInfoMapper.updateTargetBatch(arrayList)) {
        //    flag = true;
        // }
        // return true;
    }

    @Override
    public int selectGraduateTargetCount(long graduate_id) {
        int count = graduateTargetInfoMapper.selectGraduateTargetCount(graduate_id);
        return count;
    }
}




