package com.springmvc.po;

import java.util.ArrayList;
import java.util.List;

public class SpecialtyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpecialtyExample() {
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

        public Criteria andSpecialtyIDIsNull() {
            addCriterion("SpecialtyID is null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDIsNotNull() {
            addCriterion("SpecialtyID is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDEqualTo(String value) {
            addCriterion("SpecialtyID =", value, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDNotEqualTo(String value) {
            addCriterion("SpecialtyID <>", value, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDGreaterThan(String value) {
            addCriterion("SpecialtyID >", value, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDGreaterThanOrEqualTo(String value) {
            addCriterion("SpecialtyID >=", value, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDLessThan(String value) {
            addCriterion("SpecialtyID <", value, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDLessThanOrEqualTo(String value) {
            addCriterion("SpecialtyID <=", value, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDLike(String value) {
            addCriterion("SpecialtyID like", value, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDNotLike(String value) {
            addCriterion("SpecialtyID not like", value, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDIn(List<String> values) {
            addCriterion("SpecialtyID in", values, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDNotIn(List<String> values) {
            addCriterion("SpecialtyID not in", values, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDBetween(String value1, String value2) {
            addCriterion("SpecialtyID between", value1, value2, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIDNotBetween(String value1, String value2) {
            addCriterion("SpecialtyID not between", value1, value2, "specialtyID");
            return (Criteria) this;
        }

        public Criteria andDeptIsNull() {
            addCriterion("Dept is null");
            return (Criteria) this;
        }

        public Criteria andDeptIsNotNull() {
            addCriterion("Dept is not null");
            return (Criteria) this;
        }

        public Criteria andDeptEqualTo(String value) {
            addCriterion("Dept =", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotEqualTo(String value) {
            addCriterion("Dept <>", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptGreaterThan(String value) {
            addCriterion("Dept >", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptGreaterThanOrEqualTo(String value) {
            addCriterion("Dept >=", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLessThan(String value) {
            addCriterion("Dept <", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLessThanOrEqualTo(String value) {
            addCriterion("Dept <=", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptLike(String value) {
            addCriterion("Dept like", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotLike(String value) {
            addCriterion("Dept not like", value, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptIn(List<String> values) {
            addCriterion("Dept in", values, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotIn(List<String> values) {
            addCriterion("Dept not in", values, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptBetween(String value1, String value2) {
            addCriterion("Dept between", value1, value2, "dept");
            return (Criteria) this;
        }

        public Criteria andDeptNotBetween(String value1, String value2) {
            addCriterion("Dept not between", value1, value2, "dept");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameIsNull() {
            addCriterion("SpecialtyName is null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameIsNotNull() {
            addCriterion("SpecialtyName is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameEqualTo(String value) {
            addCriterion("SpecialtyName =", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameNotEqualTo(String value) {
            addCriterion("SpecialtyName <>", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameGreaterThan(String value) {
            addCriterion("SpecialtyName >", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameGreaterThanOrEqualTo(String value) {
            addCriterion("SpecialtyName >=", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameLessThan(String value) {
            addCriterion("SpecialtyName <", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameLessThanOrEqualTo(String value) {
            addCriterion("SpecialtyName <=", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameLike(String value) {
            addCriterion("SpecialtyName like", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameNotLike(String value) {
            addCriterion("SpecialtyName not like", value, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameIn(List<String> values) {
            addCriterion("SpecialtyName in", values, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameNotIn(List<String> values) {
            addCriterion("SpecialtyName not in", values, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameBetween(String value1, String value2) {
            addCriterion("SpecialtyName between", value1, value2, "specialtyName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNameNotBetween(String value1, String value2) {
            addCriterion("SpecialtyName not between", value1, value2, "specialtyName");
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