package com.yuanzong.beans.model.auto;

import com.yuanzong.utils.PageHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class QuestionDefExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    protected PageHelper pageHelper;

    public QuestionDefExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPageHelper(PageHelper pageHelper) {
        this.pageHelper=pageHelper;
    }

    public PageHelper getPageHelper() {
        return pageHelper;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("`id` is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("`id` is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("`id` =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("`id` <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("`id` >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`id` >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("`id` <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("`id` <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("`id` in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("`id` not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("`id` between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("`id` not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andFigureIdIsNull() {
            addCriterion("`figure_id` is null");
            return (Criteria) this;
        }

        public Criteria andFigureIdIsNotNull() {
            addCriterion("`figure_id` is not null");
            return (Criteria) this;
        }

        public Criteria andFigureIdEqualTo(Long value) {
            addCriterion("`figure_id` =", value, "figureId");
            return (Criteria) this;
        }

        public Criteria andFigureIdNotEqualTo(Long value) {
            addCriterion("`figure_id` <>", value, "figureId");
            return (Criteria) this;
        }

        public Criteria andFigureIdGreaterThan(Long value) {
            addCriterion("`figure_id` >", value, "figureId");
            return (Criteria) this;
        }

        public Criteria andFigureIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`figure_id` >=", value, "figureId");
            return (Criteria) this;
        }

        public Criteria andFigureIdLessThan(Long value) {
            addCriterion("`figure_id` <", value, "figureId");
            return (Criteria) this;
        }

        public Criteria andFigureIdLessThanOrEqualTo(Long value) {
            addCriterion("`figure_id` <=", value, "figureId");
            return (Criteria) this;
        }

        public Criteria andFigureIdIn(List<Long> values) {
            addCriterion("`figure_id` in", values, "figureId");
            return (Criteria) this;
        }

        public Criteria andFigureIdNotIn(List<Long> values) {
            addCriterion("`figure_id` not in", values, "figureId");
            return (Criteria) this;
        }

        public Criteria andFigureIdBetween(Long value1, Long value2) {
            addCriterion("`figure_id` between", value1, value2, "figureId");
            return (Criteria) this;
        }

        public Criteria andFigureIdNotBetween(Long value1, Long value2) {
            addCriterion("`figure_id` not between", value1, value2, "figureId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNull() {
            addCriterion("`subject_id` is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIsNotNull() {
            addCriterion("`subject_id` is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectIdEqualTo(Long value) {
            addCriterion("`subject_id` =", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotEqualTo(Long value) {
            addCriterion("`subject_id` <>", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThan(Long value) {
            addCriterion("`subject_id` >", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`subject_id` >=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThan(Long value) {
            addCriterion("`subject_id` <", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdLessThanOrEqualTo(Long value) {
            addCriterion("`subject_id` <=", value, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdIn(List<Long> values) {
            addCriterion("`subject_id` in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotIn(List<Long> values) {
            addCriterion("`subject_id` not in", values, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdBetween(Long value1, Long value2) {
            addCriterion("`subject_id` between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andSubjectIdNotBetween(Long value1, Long value2) {
            addCriterion("`subject_id` not between", value1, value2, "subjectId");
            return (Criteria) this;
        }

        public Criteria andYearIsNull() {
            addCriterion("`year` is null");
            return (Criteria) this;
        }

        public Criteria andYearIsNotNull() {
            addCriterion("`year` is not null");
            return (Criteria) this;
        }

        public Criteria andYearEqualTo(Integer value) {
            addCriterion("`year` =", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotEqualTo(Integer value) {
            addCriterion("`year` <>", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThan(Integer value) {
            addCriterion("`year` >", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearGreaterThanOrEqualTo(Integer value) {
            addCriterion("`year` >=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThan(Integer value) {
            addCriterion("`year` <", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearLessThanOrEqualTo(Integer value) {
            addCriterion("`year` <=", value, "year");
            return (Criteria) this;
        }

        public Criteria andYearIn(List<Integer> values) {
            addCriterion("`year` in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotIn(List<Integer> values) {
            addCriterion("`year` not in", values, "year");
            return (Criteria) this;
        }

        public Criteria andYearBetween(Integer value1, Integer value2) {
            addCriterion("`year` between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andYearNotBetween(Integer value1, Integer value2) {
            addCriterion("`year` not between", value1, value2, "year");
            return (Criteria) this;
        }

        public Criteria andExamAimIsNull() {
            addCriterion("`exam_aim` is null");
            return (Criteria) this;
        }

        public Criteria andExamAimIsNotNull() {
            addCriterion("`exam_aim` is not null");
            return (Criteria) this;
        }

        public Criteria andExamAimEqualTo(Integer value) {
            addCriterion("`exam_aim` =", value, "examAim");
            return (Criteria) this;
        }

        public Criteria andExamAimNotEqualTo(Integer value) {
            addCriterion("`exam_aim` <>", value, "examAim");
            return (Criteria) this;
        }

        public Criteria andExamAimGreaterThan(Integer value) {
            addCriterion("`exam_aim` >", value, "examAim");
            return (Criteria) this;
        }

        public Criteria andExamAimGreaterThanOrEqualTo(Integer value) {
            addCriterion("`exam_aim` >=", value, "examAim");
            return (Criteria) this;
        }

        public Criteria andExamAimLessThan(Integer value) {
            addCriterion("`exam_aim` <", value, "examAim");
            return (Criteria) this;
        }

        public Criteria andExamAimLessThanOrEqualTo(Integer value) {
            addCriterion("`exam_aim` <=", value, "examAim");
            return (Criteria) this;
        }

        public Criteria andExamAimIn(List<Integer> values) {
            addCriterion("`exam_aim` in", values, "examAim");
            return (Criteria) this;
        }

        public Criteria andExamAimNotIn(List<Integer> values) {
            addCriterion("`exam_aim` not in", values, "examAim");
            return (Criteria) this;
        }

        public Criteria andExamAimBetween(Integer value1, Integer value2) {
            addCriterion("`exam_aim` between", value1, value2, "examAim");
            return (Criteria) this;
        }

        public Criteria andExamAimNotBetween(Integer value1, Integer value2) {
            addCriterion("`exam_aim` not between", value1, value2, "examAim");
            return (Criteria) this;
        }

        public Criteria andExamTypeIsNull() {
            addCriterion("`exam_type` is null");
            return (Criteria) this;
        }

        public Criteria andExamTypeIsNotNull() {
            addCriterion("`exam_type` is not null");
            return (Criteria) this;
        }

        public Criteria andExamTypeEqualTo(Integer value) {
            addCriterion("`exam_type` =", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotEqualTo(Integer value) {
            addCriterion("`exam_type` <>", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeGreaterThan(Integer value) {
            addCriterion("`exam_type` >", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`exam_type` >=", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeLessThan(Integer value) {
            addCriterion("`exam_type` <", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeLessThanOrEqualTo(Integer value) {
            addCriterion("`exam_type` <=", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeIn(List<Integer> values) {
            addCriterion("`exam_type` in", values, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotIn(List<Integer> values) {
            addCriterion("`exam_type` not in", values, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeBetween(Integer value1, Integer value2) {
            addCriterion("`exam_type` between", value1, value2, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("`exam_type` not between", value1, value2, "examType");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNull() {
            addCriterion("`province_id` is null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIsNotNull() {
            addCriterion("`province_id` is not null");
            return (Criteria) this;
        }

        public Criteria andProvinceIdEqualTo(Integer value) {
            addCriterion("`province_id` =", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotEqualTo(Integer value) {
            addCriterion("`province_id` <>", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThan(Integer value) {
            addCriterion("`province_id` >", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("`province_id` >=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThan(Integer value) {
            addCriterion("`province_id` <", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdLessThanOrEqualTo(Integer value) {
            addCriterion("`province_id` <=", value, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdIn(List<Integer> values) {
            addCriterion("`province_id` in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotIn(List<Integer> values) {
            addCriterion("`province_id` not in", values, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdBetween(Integer value1, Integer value2) {
            addCriterion("`province_id` between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andProvinceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("`province_id` not between", value1, value2, "provinceId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("`type` is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("`type` is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("`type` =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("`type` <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("`type` >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`type` >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("`type` <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("`type` <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("`type` in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("`type` not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("`type` between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("`type` not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andDifficultIsNull() {
            addCriterion("`difficult` is null");
            return (Criteria) this;
        }

        public Criteria andDifficultIsNotNull() {
            addCriterion("`difficult` is not null");
            return (Criteria) this;
        }

        public Criteria andDifficultEqualTo(Integer value) {
            addCriterion("`difficult` =", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultNotEqualTo(Integer value) {
            addCriterion("`difficult` <>", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultGreaterThan(Integer value) {
            addCriterion("`difficult` >", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultGreaterThanOrEqualTo(Integer value) {
            addCriterion("`difficult` >=", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultLessThan(Integer value) {
            addCriterion("`difficult` <", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultLessThanOrEqualTo(Integer value) {
            addCriterion("`difficult` <=", value, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultIn(List<Integer> values) {
            addCriterion("`difficult` in", values, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultNotIn(List<Integer> values) {
            addCriterion("`difficult` not in", values, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultBetween(Integer value1, Integer value2) {
            addCriterion("`difficult` between", value1, value2, "difficult");
            return (Criteria) this;
        }

        public Criteria andDifficultNotBetween(Integer value1, Integer value2) {
            addCriterion("`difficult` not between", value1, value2, "difficult");
            return (Criteria) this;
        }

        public Criteria andGradeIdIsNull() {
            addCriterion("`grade_id` is null");
            return (Criteria) this;
        }

        public Criteria andGradeIdIsNotNull() {
            addCriterion("`grade_id` is not null");
            return (Criteria) this;
        }

        public Criteria andGradeIdEqualTo(String value) {
            addCriterion("`grade_id` =", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotEqualTo(String value) {
            addCriterion("`grade_id` <>", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdGreaterThan(String value) {
            addCriterion("`grade_id` >", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdGreaterThanOrEqualTo(String value) {
            addCriterion("`grade_id` >=", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLessThan(String value) {
            addCriterion("`grade_id` <", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLessThanOrEqualTo(String value) {
            addCriterion("`grade_id` <=", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdLike(String value) {
            addCriterion("`grade_id` like", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotLike(String value) {
            addCriterion("`grade_id` not like", value, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdIn(List<String> values) {
            addCriterion("`grade_id` in", values, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotIn(List<String> values) {
            addCriterion("`grade_id` not in", values, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdBetween(String value1, String value2) {
            addCriterion("`grade_id` between", value1, value2, "gradeId");
            return (Criteria) this;
        }

        public Criteria andGradeIdNotBetween(String value1, String value2) {
            addCriterion("`grade_id` not between", value1, value2, "gradeId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("`parent_id` is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("`parent_id` is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Long value) {
            addCriterion("`parent_id` =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Long value) {
            addCriterion("`parent_id` <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Long value) {
            addCriterion("`parent_id` >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`parent_id` >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Long value) {
            addCriterion("`parent_id` <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Long value) {
            addCriterion("`parent_id` <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Long> values) {
            addCriterion("`parent_id` in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Long> values) {
            addCriterion("`parent_id` not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Long value1, Long value2) {
            addCriterion("`parent_id` between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Long value1, Long value2) {
            addCriterion("`parent_id` not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("`status` is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("`status` is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("`status` =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("`status` <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("`status` >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("`status` >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("`status` <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("`status` <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("`status` in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("`status` not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("`status` between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("`status` not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNull() {
            addCriterion("`customer_id` is null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIsNotNull() {
            addCriterion("`customer_id` is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerIdEqualTo(Long value) {
            addCriterion("`customer_id` =", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotEqualTo(Long value) {
            addCriterion("`customer_id` <>", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThan(Long value) {
            addCriterion("`customer_id` >", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("`customer_id` >=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThan(Long value) {
            addCriterion("`customer_id` <", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdLessThanOrEqualTo(Long value) {
            addCriterion("`customer_id` <=", value, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdIn(List<Long> values) {
            addCriterion("`customer_id` in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotIn(List<Long> values) {
            addCriterion("`customer_id` not in", values, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdBetween(Long value1, Long value2) {
            addCriterion("`customer_id` between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCustomerIdNotBetween(Long value1, Long value2) {
            addCriterion("`customer_id` not between", value1, value2, "customerId");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("`created_time` is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("`created_time` is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Integer value) {
            addCriterion("`created_time` =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Integer value) {
            addCriterion("`created_time` <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Integer value) {
            addCriterion("`created_time` >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`created_time` >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Integer value) {
            addCriterion("`created_time` <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Integer value) {
            addCriterion("`created_time` <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Integer> values) {
            addCriterion("`created_time` in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Integer> values) {
            addCriterion("`created_time` not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Integer value1, Integer value2) {
            addCriterion("`created_time` between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("`created_time` not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNull() {
            addCriterion("`modified_time` is null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIsNotNull() {
            addCriterion("`modified_time` is not null");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeEqualTo(Integer value) {
            addCriterion("`modified_time` =", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotEqualTo(Integer value) {
            addCriterion("`modified_time` <>", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThan(Integer value) {
            addCriterion("`modified_time` >", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`modified_time` >=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThan(Integer value) {
            addCriterion("`modified_time` <", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeLessThanOrEqualTo(Integer value) {
            addCriterion("`modified_time` <=", value, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeIn(List<Integer> values) {
            addCriterion("`modified_time` in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotIn(List<Integer> values) {
            addCriterion("`modified_time` not in", values, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeBetween(Integer value1, Integer value2) {
            addCriterion("`modified_time` between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andModifiedTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("`modified_time` not between", value1, value2, "modifiedTime");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("`deleted` is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("`deleted` is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Short value) {
            addCriterion("`deleted` =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Short value) {
            addCriterion("`deleted` <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Short value) {
            addCriterion("`deleted` >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Short value) {
            addCriterion("`deleted` >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Short value) {
            addCriterion("`deleted` <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Short value) {
            addCriterion("`deleted` <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Short> values) {
            addCriterion("`deleted` in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Short> values) {
            addCriterion("`deleted` not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Short value1, Short value2) {
            addCriterion("`deleted` between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Short value1, Short value2) {
            addCriterion("`deleted` not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNull() {
            addCriterion("`creator` is null");
            return (Criteria) this;
        }

        public Criteria andCreatorIsNotNull() {
            addCriterion("`creator` is not null");
            return (Criteria) this;
        }

        public Criteria andCreatorEqualTo(Long value) {
            addCriterion("`creator` =", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotEqualTo(Long value) {
            addCriterion("`creator` <>", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThan(Long value) {
            addCriterion("`creator` >", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorGreaterThanOrEqualTo(Long value) {
            addCriterion("`creator` >=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThan(Long value) {
            addCriterion("`creator` <", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorLessThanOrEqualTo(Long value) {
            addCriterion("`creator` <=", value, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorIn(List<Long> values) {
            addCriterion("`creator` in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotIn(List<Long> values) {
            addCriterion("`creator` not in", values, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorBetween(Long value1, Long value2) {
            addCriterion("`creator` between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andCreatorNotBetween(Long value1, Long value2) {
            addCriterion("`creator` not between", value1, value2, "creator");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIsNull() {
            addCriterion("`detail_type` is null");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIsNotNull() {
            addCriterion("`detail_type` is not null");
            return (Criteria) this;
        }

        public Criteria andDetailTypeEqualTo(Long value) {
            addCriterion("`detail_type` =", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNotEqualTo(Long value) {
            addCriterion("`detail_type` <>", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeGreaterThan(Long value) {
            addCriterion("`detail_type` >", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("`detail_type` >=", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeLessThan(Long value) {
            addCriterion("`detail_type` <", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeLessThanOrEqualTo(Long value) {
            addCriterion("`detail_type` <=", value, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeIn(List<Long> values) {
            addCriterion("`detail_type` in", values, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNotIn(List<Long> values) {
            addCriterion("`detail_type` not in", values, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeBetween(Long value1, Long value2) {
            addCriterion("`detail_type` between", value1, value2, "detailType");
            return (Criteria) this;
        }

        public Criteria andDetailTypeNotBetween(Long value1, Long value2) {
            addCriterion("`detail_type` not between", value1, value2, "detailType");
            return (Criteria) this;
        }

        public Criteria andSortidxIsNull() {
            addCriterion("`sortIdx` is null");
            return (Criteria) this;
        }

        public Criteria andSortidxIsNotNull() {
            addCriterion("`sortIdx` is not null");
            return (Criteria) this;
        }

        public Criteria andSortidxEqualTo(Integer value) {
            addCriterion("`sortIdx` =", value, "sortidx");
            return (Criteria) this;
        }

        public Criteria andSortidxNotEqualTo(Integer value) {
            addCriterion("`sortIdx` <>", value, "sortidx");
            return (Criteria) this;
        }

        public Criteria andSortidxGreaterThan(Integer value) {
            addCriterion("`sortIdx` >", value, "sortidx");
            return (Criteria) this;
        }

        public Criteria andSortidxGreaterThanOrEqualTo(Integer value) {
            addCriterion("`sortIdx` >=", value, "sortidx");
            return (Criteria) this;
        }

        public Criteria andSortidxLessThan(Integer value) {
            addCriterion("`sortIdx` <", value, "sortidx");
            return (Criteria) this;
        }

        public Criteria andSortidxLessThanOrEqualTo(Integer value) {
            addCriterion("`sortIdx` <=", value, "sortidx");
            return (Criteria) this;
        }

        public Criteria andSortidxIn(List<Integer> values) {
            addCriterion("`sortIdx` in", values, "sortidx");
            return (Criteria) this;
        }

        public Criteria andSortidxNotIn(List<Integer> values) {
            addCriterion("`sortIdx` not in", values, "sortidx");
            return (Criteria) this;
        }

        public Criteria andSortidxBetween(Integer value1, Integer value2) {
            addCriterion("`sortIdx` between", value1, value2, "sortidx");
            return (Criteria) this;
        }

        public Criteria andSortidxNotBetween(Integer value1, Integer value2) {
            addCriterion("`sortIdx` not between", value1, value2, "sortidx");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("`state` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("`state` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("`state` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("`state` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("`state` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("`state` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("`state` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("`state` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("`state` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("`state` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("`state` not between", value1, value2, "state");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}