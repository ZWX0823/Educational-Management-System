package com.springmvc.po;

import java.util.ArrayList;
import java.util.List;

public class TermExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TermExample() {
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

        public Criteria andTermIDIsNull() {
            addCriterion("TermID is null");
            return (Criteria) this;
        }

        public Criteria andTermIDIsNotNull() {
            addCriterion("TermID is not null");
            return (Criteria) this;
        }

        public Criteria andTermIDEqualTo(String value) {
            addCriterion("TermID =", value, "termID");
            return (Criteria) this;
        }

        public Criteria andTermIDNotEqualTo(String value) {
            addCriterion("TermID <>", value, "termID");
            return (Criteria) this;
        }

        public Criteria andTermIDGreaterThan(String value) {
            addCriterion("TermID >", value, "termID");
            return (Criteria) this;
        }

        public Criteria andTermIDGreaterThanOrEqualTo(String value) {
            addCriterion("TermID >=", value, "termID");
            return (Criteria) this;
        }

        public Criteria andTermIDLessThan(String value) {
            addCriterion("TermID <", value, "termID");
            return (Criteria) this;
        }

        public Criteria andTermIDLessThanOrEqualTo(String value) {
            addCriterion("TermID <=", value, "termID");
            return (Criteria) this;
        }

        public Criteria andTermIDLike(String value) {
            addCriterion("TermID like", value, "termID");
            return (Criteria) this;
        }

        public Criteria andTermIDNotLike(String value) {
            addCriterion("TermID not like", value, "termID");
            return (Criteria) this;
        }

        public Criteria andTermIDIn(List<String> values) {
            addCriterion("TermID in", values, "termID");
            return (Criteria) this;
        }

        public Criteria andTermIDNotIn(List<String> values) {
            addCriterion("TermID not in", values, "termID");
            return (Criteria) this;
        }

        public Criteria andTermIDBetween(String value1, String value2) {
            addCriterion("TermID between", value1, value2, "termID");
            return (Criteria) this;
        }

        public Criteria andTermIDNotBetween(String value1, String value2) {
            addCriterion("TermID not between", value1, value2, "termID");
            return (Criteria) this;
        }

        public Criteria andTermNameIsNull() {
            addCriterion("TermName is null");
            return (Criteria) this;
        }

        public Criteria andTermNameIsNotNull() {
            addCriterion("TermName is not null");
            return (Criteria) this;
        }

        public Criteria andTermNameEqualTo(String value) {
            addCriterion("TermName =", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameNotEqualTo(String value) {
            addCriterion("TermName <>", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameGreaterThan(String value) {
            addCriterion("TermName >", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameGreaterThanOrEqualTo(String value) {
            addCriterion("TermName >=", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameLessThan(String value) {
            addCriterion("TermName <", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameLessThanOrEqualTo(String value) {
            addCriterion("TermName <=", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameLike(String value) {
            addCriterion("TermName like", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameNotLike(String value) {
            addCriterion("TermName not like", value, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameIn(List<String> values) {
            addCriterion("TermName in", values, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameNotIn(List<String> values) {
            addCriterion("TermName not in", values, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameBetween(String value1, String value2) {
            addCriterion("TermName between", value1, value2, "termName");
            return (Criteria) this;
        }

        public Criteria andTermNameNotBetween(String value1, String value2) {
            addCriterion("TermName not between", value1, value2, "termName");
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