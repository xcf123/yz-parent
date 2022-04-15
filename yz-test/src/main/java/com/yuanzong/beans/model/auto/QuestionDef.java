package com.yuanzong.beans.model.auto;

import java.io.Serializable;

public class QuestionDef implements Serializable {
    private Long id;

    private Long figureId;

    private Long subjectId;

    private Integer year;

    private Integer examAim;

    private Integer examType;

    private Integer provinceId;

    private Integer type;

    private Integer difficult;

    private String gradeId;

    private Long parentId;

    private Integer status;

    private Long customerId;

    private Integer createdTime;

    private Integer modifiedTime;

    private Short deleted;

    private Long creator;

    private Long detailType;

    private Integer sortidx;

    private Integer state;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFigureId() {
        return figureId;
    }

    public void setFigureId(Long figureId) {
        this.figureId = figureId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getExamAim() {
        return examAim;
    }

    public void setExamAim(Integer examAim) {
        this.examAim = examAim;
    }

    public Integer getExamType() {
        return examType;
    }

    public void setExamType(Integer examType) {
        this.examType = examType;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public String getGradeId() {
        return gradeId;
    }

    public void setGradeId(String gradeId) {
        this.gradeId = gradeId == null ? null : gradeId.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Integer createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Integer modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public Short getDeleted() {
        return deleted;
    }

    public void setDeleted(Short deleted) {
        this.deleted = deleted;
    }

    public Long getCreator() {
        return creator;
    }

    public void setCreator(Long creator) {
        this.creator = creator;
    }

    public Long getDetailType() {
        return detailType;
    }

    public void setDetailType(Long detailType) {
        this.detailType = detailType;
    }

    public Integer getSortidx() {
        return sortidx;
    }

    public void setSortidx(Integer sortidx) {
        this.sortidx = sortidx;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    private QuestionDef(Builder b) {
        id = b.id;
        figureId = b.figureId;
        subjectId = b.subjectId;
        year = b.year;
        examAim = b.examAim;
        examType = b.examType;
        provinceId = b.provinceId;
        type = b.type;
        difficult = b.difficult;
        gradeId = b.gradeId;
        parentId = b.parentId;
        status = b.status;
        customerId = b.customerId;
        createdTime = b.createdTime;
        modifiedTime = b.modifiedTime;
        deleted = b.deleted;
        creator = b.creator;
        detailType = b.detailType;
        sortidx = b.sortidx;
        state = b.state;
    }

    public QuestionDef() {
        super();
    }

    public static class Builder {
        private Long id;

        private Long figureId;

        private Long subjectId;

        private Integer year;

        private Integer examAim;

        private Integer examType;

        private Integer provinceId;

        private Integer type;

        private Integer difficult;

        private String gradeId;

        private Long parentId;

        private Integer status;

        private Long customerId;

        private Integer createdTime;

        private Integer modifiedTime;

        private Short deleted;

        private Long creator;

        private Long detailType;

        private Integer sortidx;

        private Integer state;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder figureId(Long figureId) {
            this.figureId = figureId;
            return this;
        }

        public Builder subjectId(Long subjectId) {
            this.subjectId = subjectId;
            return this;
        }

        public Builder year(Integer year) {
            this.year = year;
            return this;
        }

        public Builder examAim(Integer examAim) {
            this.examAim = examAim;
            return this;
        }

        public Builder examType(Integer examType) {
            this.examType = examType;
            return this;
        }

        public Builder provinceId(Integer provinceId) {
            this.provinceId = provinceId;
            return this;
        }

        public Builder type(Integer type) {
            this.type = type;
            return this;
        }

        public Builder difficult(Integer difficult) {
            this.difficult = difficult;
            return this;
        }

        public Builder gradeId(String gradeId) {
            this.gradeId = gradeId;
            return this;
        }

        public Builder parentId(Long parentId) {
            this.parentId = parentId;
            return this;
        }

        public Builder status(Integer status) {
            this.status = status;
            return this;
        }

        public Builder customerId(Long customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder createdTime(Integer createdTime) {
            this.createdTime = createdTime;
            return this;
        }

        public Builder modifiedTime(Integer modifiedTime) {
            this.modifiedTime = modifiedTime;
            return this;
        }

        public Builder deleted(Short deleted) {
            this.deleted = deleted;
            return this;
        }

        public Builder creator(Long creator) {
            this.creator = creator;
            return this;
        }

        public Builder detailType(Long detailType) {
            this.detailType = detailType;
            return this;
        }

        public Builder sortidx(Integer sortidx) {
            this.sortidx = sortidx;
            return this;
        }

        public Builder state(Integer state) {
            this.state = state;
            return this;
        }

        public QuestionDef build() {
            return new QuestionDef(this);
        }
    }
}