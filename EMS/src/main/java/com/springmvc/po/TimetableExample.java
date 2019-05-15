package com.springmvc.po;

import java.util.ArrayList;
import java.util.List;

public class TimetableExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TimetableExample() {
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

        public Criteria andSpecialty_YearIsNull() {
            addCriterion("Specialty_Year is null");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIsNotNull() {
            addCriterion("Specialty_Year is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearEqualTo(String value) {
            addCriterion("Specialty_Year =", value, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNotEqualTo(String value) {
            addCriterion("Specialty_Year <>", value, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearGreaterThan(String value) {
            addCriterion("Specialty_Year >", value, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearGreaterThanOrEqualTo(String value) {
            addCriterion("Specialty_Year >=", value, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearLessThan(String value) {
            addCriterion("Specialty_Year <", value, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearLessThanOrEqualTo(String value) {
            addCriterion("Specialty_Year <=", value, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearLike(String value) {
            addCriterion("Specialty_Year like", value, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNotLike(String value) {
            addCriterion("Specialty_Year not like", value, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIn(List<String> values) {
            addCriterion("Specialty_Year in", values, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNotIn(List<String> values) {
            addCriterion("Specialty_Year not in", values, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearBetween(String value1, String value2) {
            addCriterion("Specialty_Year between", value1, value2, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNotBetween(String value1, String value2) {
            addCriterion("Specialty_Year not between", value1, value2, "specialty_Year");
            return (Criteria) this;
        }

        public Criteria andClassroomIsNull() {
            addCriterion("Classroom is null");
            return (Criteria) this;
        }

        public Criteria andClassroomIsNotNull() {
            addCriterion("Classroom is not null");
            return (Criteria) this;
        }

        public Criteria andClassroomEqualTo(String value) {
            addCriterion("Classroom =", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomNotEqualTo(String value) {
            addCriterion("Classroom <>", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomGreaterThan(String value) {
            addCriterion("Classroom >", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomGreaterThanOrEqualTo(String value) {
            addCriterion("Classroom >=", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomLessThan(String value) {
            addCriterion("Classroom <", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomLessThanOrEqualTo(String value) {
            addCriterion("Classroom <=", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomLike(String value) {
            addCriterion("Classroom like", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomNotLike(String value) {
            addCriterion("Classroom not like", value, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomIn(List<String> values) {
            addCriterion("Classroom in", values, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomNotIn(List<String> values) {
            addCriterion("Classroom not in", values, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomBetween(String value1, String value2) {
            addCriterion("Classroom between", value1, value2, "classroom");
            return (Criteria) this;
        }

        public Criteria andClassroomNotBetween(String value1, String value2) {
            addCriterion("Classroom not between", value1, value2, "classroom");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("Time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("Time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(String value) {
            addCriterion("Time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(String value) {
            addCriterion("Time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(String value) {
            addCriterion("Time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(String value) {
            addCriterion("Time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(String value) {
            addCriterion("Time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(String value) {
            addCriterion("Time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLike(String value) {
            addCriterion("Time like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotLike(String value) {
            addCriterion("Time not like", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<String> values) {
            addCriterion("Time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<String> values) {
            addCriterion("Time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(String value1, String value2) {
            addCriterion("Time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(String value1, String value2) {
            addCriterion("Time not between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTermIsNull() {
            addCriterion("Term is null");
            return (Criteria) this;
        }

        public Criteria andTermIsNotNull() {
            addCriterion("Term is not null");
            return (Criteria) this;
        }

        public Criteria andTermEqualTo(String value) {
            addCriterion("Term =", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotEqualTo(String value) {
            addCriterion("Term <>", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermGreaterThan(String value) {
            addCriterion("Term >", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermGreaterThanOrEqualTo(String value) {
            addCriterion("Term >=", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermLessThan(String value) {
            addCriterion("Term <", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermLessThanOrEqualTo(String value) {
            addCriterion("Term <=", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermLike(String value) {
            addCriterion("Term like", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotLike(String value) {
            addCriterion("Term not like", value, "term");
            return (Criteria) this;
        }

        public Criteria andTermIn(List<String> values) {
            addCriterion("Term in", values, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotIn(List<String> values) {
            addCriterion("Term not in", values, "term");
            return (Criteria) this;
        }

        public Criteria andTermBetween(String value1, String value2) {
            addCriterion("Term between", value1, value2, "term");
            return (Criteria) this;
        }

        public Criteria andTermNotBetween(String value1, String value2) {
            addCriterion("Term not between", value1, value2, "term");
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