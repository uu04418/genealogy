package cn.com.gene.pojo;

import java.util.ArrayList;
import java.util.List;

public class DetailtypeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DetailtypeExample() {
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

        public Criteria andDetailtypeidIsNull() {
            addCriterion("detailtypeid is null");
            return (Criteria) this;
        }

        public Criteria andDetailtypeidIsNotNull() {
            addCriterion("detailtypeid is not null");
            return (Criteria) this;
        }

        public Criteria andDetailtypeidEqualTo(Integer value) {
            addCriterion("detailtypeid =", value, "detailtypeid");
            return (Criteria) this;
        }

        public Criteria andDetailtypeidNotEqualTo(Integer value) {
            addCriterion("detailtypeid <>", value, "detailtypeid");
            return (Criteria) this;
        }

        public Criteria andDetailtypeidGreaterThan(Integer value) {
            addCriterion("detailtypeid >", value, "detailtypeid");
            return (Criteria) this;
        }

        public Criteria andDetailtypeidGreaterThanOrEqualTo(Integer value) {
            addCriterion("detailtypeid >=", value, "detailtypeid");
            return (Criteria) this;
        }

        public Criteria andDetailtypeidLessThan(Integer value) {
            addCriterion("detailtypeid <", value, "detailtypeid");
            return (Criteria) this;
        }

        public Criteria andDetailtypeidLessThanOrEqualTo(Integer value) {
            addCriterion("detailtypeid <=", value, "detailtypeid");
            return (Criteria) this;
        }

        public Criteria andDetailtypeidIn(List<Integer> values) {
            addCriterion("detailtypeid in", values, "detailtypeid");
            return (Criteria) this;
        }

        public Criteria andDetailtypeidNotIn(List<Integer> values) {
            addCriterion("detailtypeid not in", values, "detailtypeid");
            return (Criteria) this;
        }

        public Criteria andDetailtypeidBetween(Integer value1, Integer value2) {
            addCriterion("detailtypeid between", value1, value2, "detailtypeid");
            return (Criteria) this;
        }

        public Criteria andDetailtypeidNotBetween(Integer value1, Integer value2) {
            addCriterion("detailtypeid not between", value1, value2, "detailtypeid");
            return (Criteria) this;
        }

        public Criteria andTypenameIsNull() {
            addCriterion("typename is null");
            return (Criteria) this;
        }

        public Criteria andTypenameIsNotNull() {
            addCriterion("typename is not null");
            return (Criteria) this;
        }

        public Criteria andTypenameEqualTo(String value) {
            addCriterion("typename =", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotEqualTo(String value) {
            addCriterion("typename <>", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThan(String value) {
            addCriterion("typename >", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameGreaterThanOrEqualTo(String value) {
            addCriterion("typename >=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThan(String value) {
            addCriterion("typename <", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLessThanOrEqualTo(String value) {
            addCriterion("typename <=", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameLike(String value) {
            addCriterion("typename like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotLike(String value) {
            addCriterion("typename not like", value, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameIn(List<String> values) {
            addCriterion("typename in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotIn(List<String> values) {
            addCriterion("typename not in", values, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameBetween(String value1, String value2) {
            addCriterion("typename between", value1, value2, "typename");
            return (Criteria) this;
        }

        public Criteria andTypenameNotBetween(String value1, String value2) {
            addCriterion("typename not between", value1, value2, "typename");
            return (Criteria) this;
        }

        public Criteria andTypeurlIsNull() {
            addCriterion("typeurl is null");
            return (Criteria) this;
        }

        public Criteria andTypeurlIsNotNull() {
            addCriterion("typeurl is not null");
            return (Criteria) this;
        }

        public Criteria andTypeurlEqualTo(String value) {
            addCriterion("typeurl =", value, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeurlNotEqualTo(String value) {
            addCriterion("typeurl <>", value, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeurlGreaterThan(String value) {
            addCriterion("typeurl >", value, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeurlGreaterThanOrEqualTo(String value) {
            addCriterion("typeurl >=", value, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeurlLessThan(String value) {
            addCriterion("typeurl <", value, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeurlLessThanOrEqualTo(String value) {
            addCriterion("typeurl <=", value, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeurlLike(String value) {
            addCriterion("typeurl like", value, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeurlNotLike(String value) {
            addCriterion("typeurl not like", value, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeurlIn(List<String> values) {
            addCriterion("typeurl in", values, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeurlNotIn(List<String> values) {
            addCriterion("typeurl not in", values, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeurlBetween(String value1, String value2) {
            addCriterion("typeurl between", value1, value2, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeurlNotBetween(String value1, String value2) {
            addCriterion("typeurl not between", value1, value2, "typeurl");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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