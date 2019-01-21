package cn.com.gene.pojo;

import java.util.ArrayList;
import java.util.List;

public class EducationExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EducationExample() {
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

        public Criteria andEducationidIsNull() {
            addCriterion("educationid is null");
            return (Criteria) this;
        }

        public Criteria andEducationidIsNotNull() {
            addCriterion("educationid is not null");
            return (Criteria) this;
        }

        public Criteria andEducationidEqualTo(Integer value) {
            addCriterion("educationid =", value, "educationid");
            return (Criteria) this;
        }

        public Criteria andEducationidNotEqualTo(Integer value) {
            addCriterion("educationid <>", value, "educationid");
            return (Criteria) this;
        }

        public Criteria andEducationidGreaterThan(Integer value) {
            addCriterion("educationid >", value, "educationid");
            return (Criteria) this;
        }

        public Criteria andEducationidGreaterThanOrEqualTo(Integer value) {
            addCriterion("educationid >=", value, "educationid");
            return (Criteria) this;
        }

        public Criteria andEducationidLessThan(Integer value) {
            addCriterion("educationid <", value, "educationid");
            return (Criteria) this;
        }

        public Criteria andEducationidLessThanOrEqualTo(Integer value) {
            addCriterion("educationid <=", value, "educationid");
            return (Criteria) this;
        }

        public Criteria andEducationidIn(List<Integer> values) {
            addCriterion("educationid in", values, "educationid");
            return (Criteria) this;
        }

        public Criteria andEducationidNotIn(List<Integer> values) {
            addCriterion("educationid not in", values, "educationid");
            return (Criteria) this;
        }

        public Criteria andEducationidBetween(Integer value1, Integer value2) {
            addCriterion("educationid between", value1, value2, "educationid");
            return (Criteria) this;
        }

        public Criteria andEducationidNotBetween(Integer value1, Integer value2) {
            addCriterion("educationid not between", value1, value2, "educationid");
            return (Criteria) this;
        }

        public Criteria andEducationnameIsNull() {
            addCriterion("educationname is null");
            return (Criteria) this;
        }

        public Criteria andEducationnameIsNotNull() {
            addCriterion("educationname is not null");
            return (Criteria) this;
        }

        public Criteria andEducationnameEqualTo(String value) {
            addCriterion("educationname =", value, "educationname");
            return (Criteria) this;
        }

        public Criteria andEducationnameNotEqualTo(String value) {
            addCriterion("educationname <>", value, "educationname");
            return (Criteria) this;
        }

        public Criteria andEducationnameGreaterThan(String value) {
            addCriterion("educationname >", value, "educationname");
            return (Criteria) this;
        }

        public Criteria andEducationnameGreaterThanOrEqualTo(String value) {
            addCriterion("educationname >=", value, "educationname");
            return (Criteria) this;
        }

        public Criteria andEducationnameLessThan(String value) {
            addCriterion("educationname <", value, "educationname");
            return (Criteria) this;
        }

        public Criteria andEducationnameLessThanOrEqualTo(String value) {
            addCriterion("educationname <=", value, "educationname");
            return (Criteria) this;
        }

        public Criteria andEducationnameLike(String value) {
            addCriterion("educationname like", value, "educationname");
            return (Criteria) this;
        }

        public Criteria andEducationnameNotLike(String value) {
            addCriterion("educationname not like", value, "educationname");
            return (Criteria) this;
        }

        public Criteria andEducationnameIn(List<String> values) {
            addCriterion("educationname in", values, "educationname");
            return (Criteria) this;
        }

        public Criteria andEducationnameNotIn(List<String> values) {
            addCriterion("educationname not in", values, "educationname");
            return (Criteria) this;
        }

        public Criteria andEducationnameBetween(String value1, String value2) {
            addCriterion("educationname between", value1, value2, "educationname");
            return (Criteria) this;
        }

        public Criteria andEducationnameNotBetween(String value1, String value2) {
            addCriterion("educationname not between", value1, value2, "educationname");
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