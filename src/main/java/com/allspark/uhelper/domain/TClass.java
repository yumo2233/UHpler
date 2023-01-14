package com.allspark.uhelper.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 班级的基本信息表
 * @TableName t_class
 */
@TableName(value ="t_class")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TClass implements Serializable {
    /**
     * 班级id
     */
    @TableId
    private Integer clId;

    /**
     * 班级人数
     */
    private Integer clHeadcount;

    /**
     * 外键链接毕业要求
     */
    private Object clFkCourse;

    /**
     * 外键链接课程
     */
    private Object clFkGraduate;

    /**
     * 班级名称
     */
    private Integer clName;

    /**
     * 年级
     */
    private Integer clGrade;

    /**
     * 专业
     */
    private String clProfessional;

    /**
     * 学院
     */
    private String clCollege;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TClass other = (TClass) that;
        return (this.getClId() == null ? other.getClId() == null : this.getClId().equals(other.getClId()))
            && (this.getClHeadcount() == null ? other.getClHeadcount() == null : this.getClHeadcount().equals(other.getClHeadcount()))
            && (this.getClFkCourse() == null ? other.getClFkCourse() == null : this.getClFkCourse().equals(other.getClFkCourse()))
            && (this.getClFkGraduate() == null ? other.getClFkGraduate() == null : this.getClFkGraduate().equals(other.getClFkGraduate()))
            && (this.getClName() == null ? other.getClName() == null : this.getClName().equals(other.getClName()))
            && (this.getClGrade() == null ? other.getClGrade() == null : this.getClGrade().equals(other.getClGrade()))
            && (this.getClProfessional() == null ? other.getClProfessional() == null : this.getClProfessional().equals(other.getClProfessional()))
            && (this.getClCollege() == null ? other.getClCollege() == null : this.getClCollege().equals(other.getClCollege()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClId() == null) ? 0 : getClId().hashCode());
        result = prime * result + ((getClHeadcount() == null) ? 0 : getClHeadcount().hashCode());
        result = prime * result + ((getClFkCourse() == null) ? 0 : getClFkCourse().hashCode());
        result = prime * result + ((getClFkGraduate() == null) ? 0 : getClFkGraduate().hashCode());
        result = prime * result + ((getClName() == null) ? 0 : getClName().hashCode());
        result = prime * result + ((getClGrade() == null) ? 0 : getClGrade().hashCode());
        result = prime * result + ((getClProfessional() == null) ? 0 : getClProfessional().hashCode());
        result = prime * result + ((getClCollege() == null) ? 0 : getClCollege().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clId=").append(clId);
        sb.append(", clHeadcount=").append(clHeadcount);
        sb.append(", clFkCourse=").append(clFkCourse);
        sb.append(", clFkGraduate=").append(clFkGraduate);
        sb.append(", clName=").append(clName);
        sb.append(", clGrade=").append(clGrade);
        sb.append(", clProfessional=").append(clProfessional);
        sb.append(", clCollege=").append(clCollege);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}