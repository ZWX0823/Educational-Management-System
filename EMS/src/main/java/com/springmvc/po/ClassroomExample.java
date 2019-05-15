package com.springmvc.po;

import java.util.ArrayList;
import java.util.List;

public class ClassroomExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClassroomExample() {
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

        public Criteria andClassroomIDIsNull() {
            addCriterion("ClassroomID is null");
            return (Criteria) this;
        }

        public Criteria andClassroomIDIsNotNull() {
            addCriterion("ClassroomID is not null");
            return (Criteria) this;
        }

        public Criteria andClassroomIDEqualTo(String value) {
            addCriterion("ClassroomID =", value, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomIDNotEqualTo(String value) {
            addCriterion("ClassroomID <>", value, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomIDGreaterThan(String value) {
            addCriterion("ClassroomID >", value, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomIDGreaterThanOrEqualTo(String value) {
            addCriterion("ClassroomID >=", value, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomIDLessThan(String value) {
            addCriterion("ClassroomID <", value, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomIDLessThanOrEqualTo(String value) {
            addCriterion("ClassroomID <=", value, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomIDLike(String value) {
            addCriterion("ClassroomID like", value, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomIDNotLike(String value) {
            addCriterion("ClassroomID not like", value, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomIDIn(List<String> values) {
            addCriterion("ClassroomID in", values, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomIDNotIn(List<String> values) {
            addCriterion("ClassroomID not in", values, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomIDBetween(String value1, String value2) {
            addCriterion("ClassroomID between", value1, value2, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomIDNotBetween(String value1, String value2) {
            addCriterion("ClassroomID not between", value1, value2, "classroomID");
            return (Criteria) this;
        }

        public Criteria andClassroomNameIsNull() {
            addCriterion("ClassroomName is null");
            return (Criteria) this;
        }

        public Criteria andClassroomNameIsNotNull() {
            addCriterion("ClassroomName is not null");
            return (Criteria) this;
        }

        public Criteria andClassroomNameEqualTo(String value) {
            addCriterion("ClassroomName =", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameNotEqualTo(String value) {
            addCriterion("ClassroomName <>", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameGreaterThan(String value) {
            addCriterion("ClassroomName >", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameGreaterThanOrEqualTo(String value) {
            addCriterion("ClassroomName >=", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameLessThan(String value) {
            addCriterion("ClassroomName <", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameLessThanOrEqualTo(String value) {
            addCriterion("ClassroomName <=", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameLike(String value) {
            addCriterion("ClassroomName like", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameNotLike(String value) {
            addCriterion("ClassroomName not like", value, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameIn(List<String> values) {
            addCriterion("ClassroomName in", values, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameNotIn(List<String> values) {
            addCriterion("ClassroomName not in", values, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameBetween(String value1, String value2) {
            addCriterion("ClassroomName between", value1, value2, "classroomName");
            return (Criteria) this;
        }

        public Criteria andClassroomNameNotBetween(String value1, String value2) {
            addCriterion("ClassroomName not between", value1, value2, "classroomName");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("Type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("Type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("Type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("Type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("Type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("Type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("Type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("Type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("Type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("Type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("Type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("Type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("Type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("Type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("Size is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("Size is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Integer value) {
            addCriterion("Size =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Integer value) {
            addCriterion("Size <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Integer value) {
            addCriterion("Size >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("Size >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Integer value) {
            addCriterion("Size <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Integer value) {
            addCriterion("Size <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Integer> values) {
            addCriterion("Size in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Integer> values) {
            addCriterion("Size not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Integer value1, Integer value2) {
            addCriterion("Size between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("Size not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andEquipmentIsNull() {
            addCriterion("Equipment is null");
            return (Criteria) this;
        }

        public Criteria andEquipmentIsNotNull() {
            addCriterion("Equipment is not null");
            return (Criteria) this;
        }

        public Criteria andEquipmentEqualTo(String value) {
            addCriterion("Equipment =", value, "equipment");
            return (Criteria) this;
        }

        public Criteria andEquipmentNotEqualTo(String value) {
            addCriterion("Equipment <>", value, "equipment");
            return (Criteria) this;
        }

        public Criteria andEquipmentGreaterThan(String value) {
            addCriterion("Equipment >", value, "equipment");
            return (Criteria) this;
        }

        public Criteria andEquipmentGreaterThanOrEqualTo(String value) {
            addCriterion("Equipment >=", value, "equipment");
            return (Criteria) this;
        }

        public Criteria andEquipmentLessThan(String value) {
            addCriterion("Equipment <", value, "equipment");
            return (Criteria) this;
        }

        public Criteria andEquipmentLessThanOrEqualTo(String value) {
            addCriterion("Equipment <=", value, "equipment");
            return (Criteria) this;
        }

        public Criteria andEquipmentLike(String value) {
            addCriterion("Equipment like", value, "equipment");
            return (Criteria) this;
        }

        public Criteria andEquipmentNotLike(String value) {
            addCriterion("Equipment not like", value, "equipment");
            return (Criteria) this;
        }

        public Criteria andEquipmentIn(List<String> values) {
            addCriterion("Equipment in", values, "equipment");
            return (Criteria) this;
        }

        public Criteria andEquipmentNotIn(List<String> values) {
            addCriterion("Equipment not in", values, "equipment");
            return (Criteria) this;
        }

        public Criteria andEquipmentBetween(String value1, String value2) {
            addCriterion("Equipment between", value1, value2, "equipment");
            return (Criteria) this;
        }

        public Criteria andEquipmentNotBetween(String value1, String value2) {
            addCriterion("Equipment not between", value1, value2, "equipment");
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