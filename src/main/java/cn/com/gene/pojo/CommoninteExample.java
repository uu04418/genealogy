package cn.com.gene.pojo;

import java.util.ArrayList;
import java.util.List;

public class CommoninteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommoninteExample() {
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

        public Criteria andCommonidIsNull() {
            addCriterion("commonid is null");
            return (Criteria) this;
        }

        public Criteria andCommonidIsNotNull() {
            addCriterion("commonid is not null");
            return (Criteria) this;
        }

        public Criteria andCommonidEqualTo(Integer value) {
            addCriterion("commonid =", value, "commonid");
            return (Criteria) this;
        }

        public Criteria andCommonidNotEqualTo(Integer value) {
            addCriterion("commonid <>", value, "commonid");
            return (Criteria) this;
        }

        public Criteria andCommonidGreaterThan(Integer value) {
            addCriterion("commonid >", value, "commonid");
            return (Criteria) this;
        }

        public Criteria andCommonidGreaterThanOrEqualTo(Integer value) {
            addCriterion("commonid >=", value, "commonid");
            return (Criteria) this;
        }

        public Criteria andCommonidLessThan(Integer value) {
            addCriterion("commonid <", value, "commonid");
            return (Criteria) this;
        }

        public Criteria andCommonidLessThanOrEqualTo(Integer value) {
            addCriterion("commonid <=", value, "commonid");
            return (Criteria) this;
        }

        public Criteria andCommonidIn(List<Integer> values) {
            addCriterion("commonid in", values, "commonid");
            return (Criteria) this;
        }

        public Criteria andCommonidNotIn(List<Integer> values) {
            addCriterion("commonid not in", values, "commonid");
            return (Criteria) this;
        }

        public Criteria andCommonidBetween(Integer value1, Integer value2) {
            addCriterion("commonid between", value1, value2, "commonid");
            return (Criteria) this;
        }

        public Criteria andCommonidNotBetween(Integer value1, Integer value2) {
            addCriterion("commonid not between", value1, value2, "commonid");
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

        public Criteria andTypecountIsNull() {
            addCriterion("typecount is null");
            return (Criteria) this;
        }

        public Criteria andTypecountIsNotNull() {
            addCriterion("typecount is not null");
            return (Criteria) this;
        }

        public Criteria andTypecountEqualTo(Long value) {
            addCriterion("typecount =", value, "typecount");
            return (Criteria) this;
        }

        public Criteria andTypecountNotEqualTo(Long value) {
            addCriterion("typecount <>", value, "typecount");
            return (Criteria) this;
        }

        public Criteria andTypecountGreaterThan(Long value) {
            addCriterion("typecount >", value, "typecount");
            return (Criteria) this;
        }

        public Criteria andTypecountGreaterThanOrEqualTo(Long value) {
            addCriterion("typecount >=", value, "typecount");
            return (Criteria) this;
        }

        public Criteria andTypecountLessThan(Long value) {
            addCriterion("typecount <", value, "typecount");
            return (Criteria) this;
        }

        public Criteria andTypecountLessThanOrEqualTo(Long value) {
            addCriterion("typecount <=", value, "typecount");
            return (Criteria) this;
        }

        public Criteria andTypecountIn(List<Long> values) {
            addCriterion("typecount in", values, "typecount");
            return (Criteria) this;
        }

        public Criteria andTypecountNotIn(List<Long> values) {
            addCriterion("typecount not in", values, "typecount");
            return (Criteria) this;
        }

        public Criteria andTypecountBetween(Long value1, Long value2) {
            addCriterion("typecount between", value1, value2, "typecount");
            return (Criteria) this;
        }

        public Criteria andTypecountNotBetween(Long value1, Long value2) {
            addCriterion("typecount not between", value1, value2, "typecount");
            return (Criteria) this;
        }

        public Criteria andTypewordIsNull() {
            addCriterion("typeword is null");
            return (Criteria) this;
        }

        public Criteria andTypewordIsNotNull() {
            addCriterion("typeword is not null");
            return (Criteria) this;
        }

        public Criteria andTypewordEqualTo(String value) {
            addCriterion("typeword =", value, "typeword");
            return (Criteria) this;
        }

        public Criteria andTypewordNotEqualTo(String value) {
            addCriterion("typeword <>", value, "typeword");
            return (Criteria) this;
        }

        public Criteria andTypewordGreaterThan(String value) {
            addCriterion("typeword >", value, "typeword");
            return (Criteria) this;
        }

        public Criteria andTypewordGreaterThanOrEqualTo(String value) {
            addCriterion("typeword >=", value, "typeword");
            return (Criteria) this;
        }

        public Criteria andTypewordLessThan(String value) {
            addCriterion("typeword <", value, "typeword");
            return (Criteria) this;
        }

        public Criteria andTypewordLessThanOrEqualTo(String value) {
            addCriterion("typeword <=", value, "typeword");
            return (Criteria) this;
        }

        public Criteria andTypewordLike(String value) {
            addCriterion("typeword like", value, "typeword");
            return (Criteria) this;
        }

        public Criteria andTypewordNotLike(String value) {
            addCriterion("typeword not like", value, "typeword");
            return (Criteria) this;
        }

        public Criteria andTypewordIn(List<String> values) {
            addCriterion("typeword in", values, "typeword");
            return (Criteria) this;
        }

        public Criteria andTypewordNotIn(List<String> values) {
            addCriterion("typeword not in", values, "typeword");
            return (Criteria) this;
        }

        public Criteria andTypewordBetween(String value1, String value2) {
            addCriterion("typeword between", value1, value2, "typeword");
            return (Criteria) this;
        }

        public Criteria andTypewordNotBetween(String value1, String value2) {
            addCriterion("typeword not between", value1, value2, "typeword");
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