package com.springmvc.po;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentCourseExample() {
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

    protected abstract static class GeneratedCriteria {
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

        public Criteria andStudentIDIsNull() {
            addCriterion("StudentID is null");
            return (Criteria) this;
        }

        public Criteria andStudentIDIsNotNull() {
            addCriterion("StudentID is not null");
            return (Criteria) this;
        }

        public Criteria andStudentIDEqualTo(String value) {
            addCriterion("StudentID =", value, "studentID");
            return (Criteria) this;
        }

        public Criteria andStudentIDNotEqualTo(String value) {
            addCriterion("StudentID <>", value, "studentID");
            return (Criteria) this;
        }

        public Criteria andStudentIDGreaterThan(String value) {
            addCriterion("StudentID >", value, "studentID");
            return (Criteria) this;
        }

        public Criteria andStudentIDGreaterThanOrEqualTo(String value) {
            addCriterion("StudentID >=", value, "studentID");
            return (Criteria) this;
        }

        public Criteria andStudentIDLessThan(String value) {
            addCriterion("StudentID <", value, "studentID");
            return (Criteria) this;
        }

        public Criteria andStudentIDLessThanOrEqualTo(String value) {
            addCriterion("StudentID <=", value, "studentID");
            return (Criteria) this;
        }

        public Criteria andStudentIDLike(String value) {
            addCriterion("StudentID like", value, "studentID");
            return (Criteria) this;
        }

        public Criteria andStudentIDNotLike(String value) {
            addCriterion("StudentID not like", value, "studentID");
            return (Criteria) this;
        }

        public Criteria andStudentIDIn(List<String> values) {
            addCriterion("StudentID in", values, "studentID");
            return (Criteria) this;
        }

        public Criteria andStudentIDNotIn(List<String> values) {
            addCriterion("StudentID not in", values, "studentID");
            return (Criteria) this;
        }

        public Criteria andStudentIDBetween(String value1, String value2) {
            addCriterion("StudentID between", value1, value2, "studentID");
            return (Criteria) this;
        }

        public Criteria andStudentIDNotBetween(String value1, String value2) {
            addCriterion("StudentID not between", value1, value2, "studentID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDIsNull() {
            addCriterion("Teacher_CourseID is null");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDIsNotNull() {
            addCriterion("Teacher_CourseID is not null");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDEqualTo(String value) {
            addCriterion("Teacher_CourseID =", value, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDNotEqualTo(String value) {
            addCriterion("Teacher_CourseID <>", value, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDGreaterThan(String value) {
            addCriterion("Teacher_CourseID >", value, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDGreaterThanOrEqualTo(String value) {
            addCriterion("Teacher_CourseID >=", value, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDLessThan(String value) {
            addCriterion("Teacher_CourseID <", value, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDLessThanOrEqualTo(String value) {
            addCriterion("Teacher_CourseID <=", value, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDLike(String value) {
            addCriterion("Teacher_CourseID like", value, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDNotLike(String value) {
            addCriterion("Teacher_CourseID not like", value, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDIn(List<String> values) {
            addCriterion("Teacher_CourseID in", values, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDNotIn(List<String> values) {
            addCriterion("Teacher_CourseID not in", values, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDBetween(String value1, String value2) {
            addCriterion("Teacher_CourseID between", value1, value2, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andTeacher_CourseIDNotBetween(String value1, String value2) {
            addCriterion("Teacher_CourseID not between", value1, value2, "teacher_CourseID");
            return (Criteria) this;
        }

        public Criteria andGradeIsNull() {
            addCriterion("Grade is null");
            return (Criteria) this;
        }

        public Criteria andGradeIsNotNull() {
            addCriterion("Grade is not null");
            return (Criteria) this;
        }

        public Criteria andGradeEqualTo(Integer value) {
            addCriterion("Grade =", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotEqualTo(Integer value) {
            addCriterion("Grade <>", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThan(Integer value) {
            addCriterion("Grade >", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Grade >=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThan(Integer value) {
            addCriterion("Grade <", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeLessThanOrEqualTo(Integer value) {
            addCriterion("Grade <=", value, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeIn(List<Integer> values) {
            addCriterion("Grade in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotIn(List<Integer> values) {
            addCriterion("Grade not in", values, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeBetween(Integer value1, Integer value2) {
            addCriterion("Grade between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andGradeNotBetween(Integer value1, Integer value2) {
            addCriterion("Grade not between", value1, value2, "grade");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("Comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("Comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("Comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("Comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("Comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("Comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("Comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("Comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("Comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("Comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("Comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("Comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("Comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("Comment not between", value1, value2, "comment");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
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