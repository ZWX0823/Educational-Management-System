package com.springmvc.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SpecialtyYearExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SpecialtyYearExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andSpecialty_YearIDIsNull() {
            addCriterion("Specialty_YearID is null");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDIsNotNull() {
            addCriterion("Specialty_YearID is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDEqualTo(String value) {
            addCriterion("Specialty_YearID =", value, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDNotEqualTo(String value) {
            addCriterion("Specialty_YearID <>", value, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDGreaterThan(String value) {
            addCriterion("Specialty_YearID >", value, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDGreaterThanOrEqualTo(String value) {
            addCriterion("Specialty_YearID >=", value, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDLessThan(String value) {
            addCriterion("Specialty_YearID <", value, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDLessThanOrEqualTo(String value) {
            addCriterion("Specialty_YearID <=", value, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDLike(String value) {
            addCriterion("Specialty_YearID like", value, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDNotLike(String value) {
            addCriterion("Specialty_YearID not like", value, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDIn(List<String> values) {
            addCriterion("Specialty_YearID in", values, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDNotIn(List<String> values) {
            addCriterion("Specialty_YearID not in", values, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDBetween(String value1, String value2) {
            addCriterion("Specialty_YearID between", value1, value2, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearIDNotBetween(String value1, String value2) {
            addCriterion("Specialty_YearID not between", value1, value2, "specialty_YearID");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameIsNull() {
            addCriterion("Specialty_YearName is null");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameIsNotNull() {
            addCriterion("Specialty_YearName is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameEqualTo(String value) {
            addCriterion("Specialty_YearName =", value, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameNotEqualTo(String value) {
            addCriterion("Specialty_YearName <>", value, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameGreaterThan(String value) {
            addCriterion("Specialty_YearName >", value, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameGreaterThanOrEqualTo(String value) {
            addCriterion("Specialty_YearName >=", value, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameLessThan(String value) {
            addCriterion("Specialty_YearName <", value, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameLessThanOrEqualTo(String value) {
            addCriterion("Specialty_YearName <=", value, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameLike(String value) {
            addCriterion("Specialty_YearName like", value, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameNotLike(String value) {
            addCriterion("Specialty_YearName not like", value, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameIn(List<String> values) {
            addCriterion("Specialty_YearName in", values, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameNotIn(List<String> values) {
            addCriterion("Specialty_YearName not in", values, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameBetween(String value1, String value2) {
            addCriterion("Specialty_YearName between", value1, value2, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialty_YearNameNotBetween(String value1, String value2) {
            addCriterion("Specialty_YearName not between", value1, value2, "specialty_YearName");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNull() {
            addCriterion("Specialty is null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIsNotNull() {
            addCriterion("Specialty is not null");
            return (Criteria) this;
        }

        public Criteria andSpecialtyEqualTo(String value) {
            addCriterion("Specialty =", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotEqualTo(String value) {
            addCriterion("Specialty <>", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThan(String value) {
            addCriterion("Specialty >", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyGreaterThanOrEqualTo(String value) {
            addCriterion("Specialty >=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThan(String value) {
            addCriterion("Specialty <", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLessThanOrEqualTo(String value) {
            addCriterion("Specialty <=", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyLike(String value) {
            addCriterion("Specialty like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotLike(String value) {
            addCriterion("Specialty not like", value, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyIn(List<String> values) {
            addCriterion("Specialty in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotIn(List<String> values) {
            addCriterion("Specialty not in", values, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyBetween(String value1, String value2) {
            addCriterion("Specialty between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andSpecialtyNotBetween(String value1, String value2) {
            addCriterion("Specialty not between", value1, value2, "specialty");
            return (Criteria) this;
        }

        public Criteria andNumberIsNull() {
            addCriterion("Number is null");
            return (Criteria) this;
        }

        public Criteria andNumberIsNotNull() {
            addCriterion("Number is not null");
            return (Criteria) this;
        }

        public Criteria andNumberEqualTo(Integer value) {
            addCriterion("Number =", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotEqualTo(Integer value) {
            addCriterion("Number <>", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThan(Integer value) {
            addCriterion("Number >", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("Number >=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThan(Integer value) {
            addCriterion("Number <", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberLessThanOrEqualTo(Integer value) {
            addCriterion("Number <=", value, "number");
            return (Criteria) this;
        }

        public Criteria andNumberIn(List<Integer> values) {
            addCriterion("Number in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotIn(List<Integer> values) {
            addCriterion("Number not in", values, "number");
            return (Criteria) this;
        }

        public Criteria andNumberBetween(Integer value1, Integer value2) {
            addCriterion("Number between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("Number not between", value1, value2, "number");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateIsNull() {
            addCriterion("AdmissionDate is null");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateIsNotNull() {
            addCriterion("AdmissionDate is not null");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateEqualTo(Date value) {
            addCriterionForJDBCDate("AdmissionDate =", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("AdmissionDate <>", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateGreaterThan(Date value) {
            addCriterionForJDBCDate("AdmissionDate >", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("AdmissionDate >=", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateLessThan(Date value) {
            addCriterionForJDBCDate("AdmissionDate <", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("AdmissionDate <=", value, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateIn(List<Date> values) {
            addCriterionForJDBCDate("AdmissionDate in", values, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("AdmissionDate not in", values, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("AdmissionDate between", value1, value2, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andAdmissionDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("AdmissionDate not between", value1, value2, "admissionDate");
            return (Criteria) this;
        }

        public Criteria andFinishIsNull() {
            addCriterion("Finish is null");
            return (Criteria) this;
        }

        public Criteria andFinishIsNotNull() {
            addCriterion("Finish is not null");
            return (Criteria) this;
        }

        public Criteria andFinishEqualTo(String value) {
            addCriterion("Finish =", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishNotEqualTo(String value) {
            addCriterion("Finish <>", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishGreaterThan(String value) {
            addCriterion("Finish >", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishGreaterThanOrEqualTo(String value) {
            addCriterion("Finish >=", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishLessThan(String value) {
            addCriterion("Finish <", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishLessThanOrEqualTo(String value) {
            addCriterion("Finish <=", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishLike(String value) {
            addCriterion("Finish like", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishNotLike(String value) {
            addCriterion("Finish not like", value, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishIn(List<String> values) {
            addCriterion("Finish in", values, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishNotIn(List<String> values) {
            addCriterion("Finish not in", values, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishBetween(String value1, String value2) {
            addCriterion("Finish between", value1, value2, "finish");
            return (Criteria) this;
        }

        public Criteria andFinishNotBetween(String value1, String value2) {
            addCriterion("Finish not between", value1, value2, "finish");
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

        public Criteria andClassNumberIsNull() {
            addCriterion("ClassNumber is null");
            return (Criteria) this;
        }

        public Criteria andClassNumberIsNotNull() {
            addCriterion("ClassNumber is not null");
            return (Criteria) this;
        }

        public Criteria andClassNumberEqualTo(Short value) {
            addCriterion("ClassNumber =", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberNotEqualTo(Short value) {
            addCriterion("ClassNumber <>", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberGreaterThan(Short value) {
            addCriterion("ClassNumber >", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberGreaterThanOrEqualTo(Short value) {
            addCriterion("ClassNumber >=", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberLessThan(Short value) {
            addCriterion("ClassNumber <", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberLessThanOrEqualTo(Short value) {
            addCriterion("ClassNumber <=", value, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberIn(List<Short> values) {
            addCriterion("ClassNumber in", values, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberNotIn(List<Short> values) {
            addCriterion("ClassNumber not in", values, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberBetween(Short value1, Short value2) {
            addCriterion("ClassNumber between", value1, value2, "classNumber");
            return (Criteria) this;
        }

        public Criteria andClassNumberNotBetween(Short value1, Short value2) {
            addCriterion("ClassNumber not between", value1, value2, "classNumber");
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