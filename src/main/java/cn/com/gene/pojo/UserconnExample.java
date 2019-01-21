package cn.com.gene.pojo;

import java.util.ArrayList;
import java.util.List;

public class UserconnExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserconnExample() {
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

        public Criteria andProfileidIsNull() {
            addCriterion("profileid is null");
            return (Criteria) this;
        }

        public Criteria andProfileidIsNotNull() {
            addCriterion("profileid is not null");
            return (Criteria) this;
        }

        public Criteria andProfileidEqualTo(Long value) {
            addCriterion("profileid =", value, "profileid");
            return (Criteria) this;
        }

        public Criteria andProfileidNotEqualTo(Long value) {
            addCriterion("profileid <>", value, "profileid");
            return (Criteria) this;
        }

        public Criteria andProfileidGreaterThan(Long value) {
            addCriterion("profileid >", value, "profileid");
            return (Criteria) this;
        }

        public Criteria andProfileidGreaterThanOrEqualTo(Long value) {
            addCriterion("profileid >=", value, "profileid");
            return (Criteria) this;
        }

        public Criteria andProfileidLessThan(Long value) {
            addCriterion("profileid <", value, "profileid");
            return (Criteria) this;
        }

        public Criteria andProfileidLessThanOrEqualTo(Long value) {
            addCriterion("profileid <=", value, "profileid");
            return (Criteria) this;
        }

        public Criteria andProfileidIn(List<Long> values) {
            addCriterion("profileid in", values, "profileid");
            return (Criteria) this;
        }

        public Criteria andProfileidNotIn(List<Long> values) {
            addCriterion("profileid not in", values, "profileid");
            return (Criteria) this;
        }

        public Criteria andProfileidBetween(Long value1, Long value2) {
            addCriterion("profileid between", value1, value2, "profileid");
            return (Criteria) this;
        }

        public Criteria andProfileidNotBetween(Long value1, Long value2) {
            addCriterion("profileid not between", value1, value2, "profileid");
            return (Criteria) this;
        }

        public Criteria andFatheridIsNull() {
            addCriterion("fatherid is null");
            return (Criteria) this;
        }

        public Criteria andFatheridIsNotNull() {
            addCriterion("fatherid is not null");
            return (Criteria) this;
        }

        public Criteria andFatheridEqualTo(Long value) {
            addCriterion("fatherid =", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridNotEqualTo(Long value) {
            addCriterion("fatherid <>", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridGreaterThan(Long value) {
            addCriterion("fatherid >", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridGreaterThanOrEqualTo(Long value) {
            addCriterion("fatherid >=", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridLessThan(Long value) {
            addCriterion("fatherid <", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridLessThanOrEqualTo(Long value) {
            addCriterion("fatherid <=", value, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridIn(List<Long> values) {
            addCriterion("fatherid in", values, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridNotIn(List<Long> values) {
            addCriterion("fatherid not in", values, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridBetween(Long value1, Long value2) {
            addCriterion("fatherid between", value1, value2, "fatherid");
            return (Criteria) this;
        }

        public Criteria andFatheridNotBetween(Long value1, Long value2) {
            addCriterion("fatherid not between", value1, value2, "fatherid");
            return (Criteria) this;
        }

        public Criteria andSpouseidIsNull() {
            addCriterion("spouseid is null");
            return (Criteria) this;
        }

        public Criteria andSpouseidIsNotNull() {
            addCriterion("spouseid is not null");
            return (Criteria) this;
        }

        public Criteria andSpouseidEqualTo(Long value) {
            addCriterion("spouseid =", value, "spouseid");
            return (Criteria) this;
        }

        public Criteria andSpouseidNotEqualTo(Long value) {
            addCriterion("spouseid <>", value, "spouseid");
            return (Criteria) this;
        }

        public Criteria andSpouseidGreaterThan(Long value) {
            addCriterion("spouseid >", value, "spouseid");
            return (Criteria) this;
        }

        public Criteria andSpouseidGreaterThanOrEqualTo(Long value) {
            addCriterion("spouseid >=", value, "spouseid");
            return (Criteria) this;
        }

        public Criteria andSpouseidLessThan(Long value) {
            addCriterion("spouseid <", value, "spouseid");
            return (Criteria) this;
        }

        public Criteria andSpouseidLessThanOrEqualTo(Long value) {
            addCriterion("spouseid <=", value, "spouseid");
            return (Criteria) this;
        }

        public Criteria andSpouseidIn(List<Long> values) {
            addCriterion("spouseid in", values, "spouseid");
            return (Criteria) this;
        }

        public Criteria andSpouseidNotIn(List<Long> values) {
            addCriterion("spouseid not in", values, "spouseid");
            return (Criteria) this;
        }

        public Criteria andSpouseidBetween(Long value1, Long value2) {
            addCriterion("spouseid between", value1, value2, "spouseid");
            return (Criteria) this;
        }

        public Criteria andSpouseidNotBetween(Long value1, Long value2) {
            addCriterion("spouseid not between", value1, value2, "spouseid");
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