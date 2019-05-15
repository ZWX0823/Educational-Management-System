package com.springmvc.po;

import java.util.ArrayList;
import java.util.List;

public class TimeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TimeExample() {
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

        public Criteria andTimeIDIsNull() {
            addCriterion("TimeID is null");
            return (Criteria) this;
        }

        public Criteria andTimeIDIsNotNull() {
            addCriterion("TimeID is not null");
            return (Criteria) this;
        }

        public Criteria andTimeIDEqualTo(String value) {
            addCriterion("TimeID =", value, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeIDNotEqualTo(String value) {
            addCriterion("TimeID <>", value, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeIDGreaterThan(String value) {
            addCriterion("TimeID >", value, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeIDGreaterThanOrEqualTo(String value) {
            addCriterion("TimeID >=", value, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeIDLessThan(String value) {
            addCriterion("TimeID <", value, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeIDLessThanOrEqualTo(String value) {
            addCriterion("TimeID <=", value, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeIDLike(String value) {
            addCriterion("TimeID like", value, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeIDNotLike(String value) {
            addCriterion("TimeID not like", value, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeIDIn(List<String> values) {
            addCriterion("TimeID in", values, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeIDNotIn(List<String> values) {
            addCriterion("TimeID not in", values, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeIDBetween(String value1, String value2) {
            addCriterion("TimeID between", value1, value2, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeIDNotBetween(String value1, String value2) {
            addCriterion("TimeID not between", value1, value2, "timeID");
            return (Criteria) this;
        }

        public Criteria andTimeNameIsNull() {
            addCriterion("TimeName is null");
            return (Criteria) this;
        }

        public Criteria andTimeNameIsNotNull() {
            addCriterion("TimeName is not null");
            return (Criteria) this;
        }

        public Criteria andTimeNameEqualTo(String value) {
            addCriterion("TimeName =", value, "timeName");
            return (Criteria) this;
        }

        public Criteria andTimeNameNotEqualTo(String value) {
            addCriterion("TimeName <>", value, "timeName");
            return (Criteria) this;
        }

        public Criteria andTimeNameGreaterThan(String value) {
            addCriterion("TimeName >", value, "timeName");
            return (Criteria) this;
        }

        public Criteria andTimeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TimeName >=", value, "timeName");
            return (Criteria) this;
        }

        public Criteria andTimeNameLessThan(String value) {
            addCriterion("TimeName <", value, "timeName");
            return (Criteria) this;
        }

        public Criteria andTimeNameLessThanOrEqualTo(String value) {
            addCriterion("TimeName <=", value, "timeName");
            return (Criteria) this;
        }

        public Criteria andTimeNameLike(String value) {
            addCriterion("TimeName like", value, "timeName");
            return (Criteria) this;
        }

        public Criteria andTimeNameNotLike(String value) {
            addCriterion("TimeName not like", value, "timeName");
            return (Criteria) this;
        }

        public Criteria andTimeNameIn(List<String> values) {
            addCriterion("TimeName in", values, "timeName");
            return (Criteria) this;
        }

        public Criteria andTimeNameNotIn(List<String> values) {
            addCriterion("TimeName not in", values, "timeName");
            return (Criteria) this;
        }

        public Criteria andTimeNameBetween(String value1, String value2) {
            addCriterion("TimeName between", value1, value2, "timeName");
            return (Criteria) this;
        }

        public Criteria andTimeNameNotBetween(String value1, String value2) {
            addCriterion("TimeName not between", value1, value2, "timeName");
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