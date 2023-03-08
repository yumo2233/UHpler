package com.allspark.uhelper.service.impl;

import com.allspark.uhelper.common.form.GraduateInfoForm;
import com.allspark.uhelper.common.resp.GradeAndProfessionalResp;
import com.allspark.uhelper.db.mapper.GraduateTargetInfoMapper;
import com.allspark.uhelper.myenum.CollegeEnum;
import com.allspark.uhelper.utils.UuidUtils;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.allspark.uhelper.db.pojo.GraduateInfo;
import com.allspark.uhelper.service.GraduateInfoService;
import com.allspark.uhelper.db.mapper.GraduateInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 86159
 * @description 针对表【graduate_info(毕业要求的信息表)】的数据库操作Service实现
 * @createDate 2023-01-20 21:05:54
 */
@Service
@DS("u_graduateinfo")
public class GraduateInfoServiceImpl extends ServiceImpl<GraduateInfoMapper, GraduateInfo>
        implements GraduateInfoService {

    @Resource
    private GraduateInfoMapper graduateInfoMapper;

    @Resource
    GraduateTargetInfoMapper graduateTargetInfoMapper;

    public GraduateInfo transfer(GraduateInfoForm targetForm) {
        GraduateInfo graduateInfo = new GraduateInfo();
        graduateInfo.setId(targetForm.getId());
        graduateInfo.setCollege(targetForm.getCollege());
        graduateInfo.setGrade(targetForm.getGrade());
        graduateInfo.setProfessional(targetForm.getProfessional());
        graduateInfo.setGraduate_count(targetForm.getGraduate_count());
        graduateInfo.setGraduate_target_count(targetForm.getGraduate_target_count());
        graduateInfo.setName(targetForm.getName());
        graduateInfo.setUser_id(targetForm.getUser_id());

        return graduateInfo;
    }

    @Override
    public void insertStudent(Map param) {
        graduateInfoMapper.insertStudent(param);

    }

    @Override
    public ArrayList<HashMap> selectCollegeAndGrade() {
        ArrayList<HashMap> list = graduateInfoMapper.selectCollegeAndGrade();
        ArrayList<HashMap> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            HashMap hashMap = new HashMap();
            HashMap map = list.get(i);
            hashMap.put("graduateCount", map.get("graduate_count"));
            hashMap.put("graduateTargetCount", map.get("graduate_target_count"));
            hashMap.put("userId", map.get("user_id"));
            hashMap.put("id", map.get("id"));
            int m = (int) map.get("college");

            String type = CollegeEnum.getEnumType(m);
            if (type == null) {
                hashMap.put("college", "不存在相关学院");
            }

            hashMap.put("college", type);
            hashMap.put("grade", map.get("grade"));
            hashMap.put("professional", map.get("professional"));
            hashMap.put("name", map.get("name"));
            list1.add(hashMap);
        }
        return list1;
    }

    @Override
    public int insertGraduateInfo(GraduateInfo graduateTargetInfo) {
        //Long uuId = UuidUtils.getUuId();
        //graduateTargetInfo.setId(uuId);
        int count = graduateTargetInfoMapper.selectGraduateTargetCount(graduateTargetInfo.getId());
        graduateTargetInfo.setGraduate_target_count(count);
        graduateInfoMapper.insertGraduateInfo(graduateTargetInfo);
        return 1;
    }

    @Override
    public void insertGraduateInfoBatch(List<GraduateInfo> graduateInfoList) {
        for (int i = 0; i < graduateInfoList.size(); ++i) {
            GraduateInfo graduateInfo = graduateInfoList.get(i);
            this.insertGraduateInfo(graduateInfo);
        }
    }

    @Override
    public boolean deleteGraduateInfo(long id) {
        boolean b = graduateInfoMapper.deleteGraduateInfo(id);
        return b;
    }

    @Override
    public boolean updateGraduationName(GraduateInfo graduateInfo) {
        boolean b = graduateInfoMapper.updateGraduationName(graduateInfo);
        return b;
    }

    @Override
    public ArrayList<HashMap> selectGraduateInfoByGradeAndProfessional(GraduateInfo graduateInfo) {
        ArrayList<HashMap> list = graduateInfoMapper.selectGraduateInfoByGradeAndProfessional(graduateInfo);
        return list;
    }

    @Override
    public ArrayList<HashMap> selectGrade() {
        ArrayList<Integer> list = graduateInfoMapper.selectGrade();
        ArrayList<HashMap> list1 = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            Integer integer = list.get(i);
            HashMap map = new HashMap();
            map.put("label", integer);
            map.put("value", integer);
            list1.add(map);
        }
        return list1;
    }

    @Override
    public ArrayList<HashMap> selectProfessional() {
        ArrayList<String> list = graduateInfoMapper.selectProfessional();
        ArrayList<HashMap> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); ++i) {
            String s = list.get(i);
            HashMap map = new HashMap();
            map.put("value", s);
            map.put("label", s);
            arrayList.add(map);
        }
        return arrayList;
    }

    @Override
    public ArrayList<HashMap> selectCollege() {
        ArrayList arrayList = new ArrayList();
        HashMap map1 = new HashMap();
        map1.put("value", "1");
        map1.put("label", "通信与信息工程学院（人工智能学院）");
        arrayList.add(map1);

        HashMap map2 = new HashMap();
        map2.put("value", "2");
        map2.put("label", "电子工程学院");
        arrayList.add(map2);

        HashMap map3 = new HashMap();
        map3.put("value", "3");
        map3.put("label", "自动化学院");
        arrayList.add(map3);

        HashMap map4 = new HashMap();
        map4.put("value", "4");
        map4.put("label", "计算机学院");
        arrayList.add(map4);

        HashMap map5 = new HashMap();
        map5.put("value", "5");
        map5.put("label", "经济与管理学院");
        arrayList.add(map5);
        return arrayList;
    }

    @Override
    public ArrayList<GradeAndProfessionalResp> selectGradeAndProfessional() {
        ArrayList<GraduateInfo> list = graduateInfoMapper.selectGradeAndProfessional();
        ArrayList<GradeAndProfessionalResp> arrayList = new ArrayList();
        for (int i = 0; i < list.size(); ++i) {
            GradeAndProfessionalResp resp = new GradeAndProfessionalResp();
            GraduateInfo graduateInfo = list.get(i);
            resp.setProfessional(graduateInfo.getProfessional());
            resp.setGrade(graduateInfo.getGrade());
            arrayList.add(resp);
        }
        return arrayList;
    }

    @Override
    public ArrayList<HashMap> selectall(long id) {
        GraduateInfo graduateInfo = graduateInfoMapper.selectGAP(id);
        ArrayList<HashMap> list = graduateInfoMapper.selectall(graduateInfo);
        // ArrayList<HashMap> list = graduateInfoMapper.selectall(id);

        ArrayList<HashMap> arrayList = new ArrayList<>();
        if (list != null) {
            HashMap hashMap = new HashMap();
            HashMap map = list.get(0);
            hashMap.put("college", CollegeEnum.getEnumType((Integer) map.get("college")));
            hashMap.put("grade", map.get("grade"));
            hashMap.put("professional", map.get("professional"));
            // arrayList.add(hashMap);
//            //循环毕业要求
//            for (int i = 0; i < list.size(); ++i) {
//                //做毕业要求
//                // ArrayList<HashMap> hashMaps = new ArrayList<>();
//                HashMap hashMap1 = list.get(i);
//                HashMap hashMap2 = new HashMap();
//                ArrayList<HashMap> arrayList2 = new ArrayList<>();
//                hashMap2.put("graduateName", hashMap1.get("name"));
//                arrayList2.add(hashMap2);
//                ArrayList<HashMap> arrayList1 = graduateTargetInfoMapper.selectGraduateTarget((Long) hashMap1.get("id"));
//                for (int j = 0; j < arrayList1.size(); ++j) {
//                    HashMap hashMap4 = arrayList1.get(j);
//                    HashMap hashMap3 = new HashMap();
//                    hashMap3.put("name", hashMap4.get("name"));
//                    hashMap3.put("content", hashMap4.get("content"));
//                    arrayList2.add(hashMap3);
//                }
//                hashMap.put("graduateName", arrayList2);
//            }
            ArrayList<HashMap> arrayList1 = new ArrayList();
            //一次循环对应一个hashmap
            for (int i = 0; i < list.size(); ++i) {
                HashMap hashMap1 = list.get(i);
                HashMap hashMap2 = new HashMap();
                hashMap2.put("graduateId", hashMap1.get("id"));
                hashMap2.put("graduateName", hashMap1.get("name"));
                arrayList1.add(hashMap2);
                ArrayList<HashMap> arrayList3 = graduateTargetInfoMapper.selectGraduateTarget((Long) hashMap1.get("id"));
                ArrayList arrayList4 = new ArrayList();
                for (int j = 0; j < arrayList3.size(); ++j) {
                    HashMap hashMap3 = arrayList3.get(j);
                    HashMap hashMap4 = new HashMap();
                    hashMap4.put("number", (i + 1) + "." + (j + 1));
                    hashMap4.put("name", hashMap3.get("name"));
                    hashMap4.put("id", hashMap3.get("id"));
                    hashMap4.put("content", hashMap3.get("content"));
                    arrayList4.add(hashMap4);
                }
                //hashmap
                HashMap hashMap5 = new HashMap();
                hashMap5.put("graduateTargetInfo", arrayList4);
                arrayList1.add(hashMap5);
            }
            hashMap.put("graduateName", arrayList1);
            arrayList.add(hashMap);
            return arrayList;
        }
        return null;
    }
}




