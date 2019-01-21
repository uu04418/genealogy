package cn.com.gene.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenemessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GenemessExample() {
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

        public Criteria andGenemessidIsNull() {
            addCriterion("genemessid is null");
            return (Criteria) this;
        }

        public Criteria andGenemessidIsNotNull() {
            addCriterion("genemessid is not null");
            return (Criteria) this;
        }

        public Criteria andGenemessidEqualTo(Long value) {
            addCriterion("genemessid =", value, "genemessid");
            return (Criteria) this;
        }

        public Criteria andGenemessidNotEqualTo(Long value) {
            addCriterion("genemessid <>", value, "genemessid");
            return (Criteria) this;
        }

        public Criteria andGenemessidGreaterThan(Long value) {
            addCriterion("genemessid >", value, "genemessid");
            return (Criteria) this;
        }

        public Criteria andGenemessidGreaterThanOrEqualTo(Long value) {
            addCriterion("genemessid >=", value, "genemessid");
            return (Criteria) this;
        }

        public Criteria andGenemessidLessThan(Long value) {
            addCriterion("genemessid <", value, "genemessid");
            return (Criteria) this;
        }

        public Criteria andGenemessidLessThanOrEqualTo(Long value) {
            addCriterion("genemessid <=", value, "genemessid");
            return (Criteria) this;
        }

        public Criteria andGenemessidIn(List<Long> values) {
            addCriterion("genemessid in", values, "genemessid");
            return (Criteria) this;
        }

        public Criteria andGenemessidNotIn(List<Long> values) {
            addCriterion("genemessid not in", values, "genemessid");
            return (Criteria) this;
        }

        public Criteria andGenemessidBetween(Long value1, Long value2) {
            addCriterion("genemessid between", value1, value2, "genemessid");
            return (Criteria) this;
        }

        public Criteria andGenemessidNotBetween(Long value1, Long value2) {
            addCriterion("genemessid not between", value1, value2, "genemessid");
            return (Criteria) this;
        }

        public Criteria andSurnameidIsNull() {
            addCriterion("surnameid is null");
            return (Criteria) this;
        }

        public Criteria andSurnameidIsNotNull() {
            addCriterion("surnameid is not null");
            return (Criteria) this;
        }

        public Criteria andSurnameidEqualTo(Long value) {
            addCriterion("surnameid =", value, "surnameid");
            return (Criteria) this;
        }

        public Criteria andSurnameidNotEqualTo(Long value) {
            addCriterion("surnameid <>", value, "surnameid");
            return (Criteria) this;
        }

        public Criteria andSurnameidGreaterThan(Long value) {
            addCriterion("surnameid >", value, "surnameid");
            return (Criteria) this;
        }

        public Criteria andSurnameidGreaterThanOrEqualTo(Long value) {
            addCriterion("surnameid >=", value, "surnameid");
            return (Criteria) this;
        }

        public Criteria andSurnameidLessThan(Long value) {
            addCriterion("surnameid <", value, "surnameid");
            return (Criteria) this;
        }

        public Criteria andSurnameidLessThanOrEqualTo(Long value) {
            addCriterion("surnameid <=", value, "surnameid");
            return (Criteria) this;
        }

        public Criteria andSurnameidIn(List<Long> values) {
            addCriterion("surnameid in", values, "surnameid");
            return (Criteria) this;
        }

        public Criteria andSurnameidNotIn(List<Long> values) {
            addCriterion("surnameid not in", values, "surnameid");
            return (Criteria) this;
        }

        public Criteria andSurnameidBetween(Long value1, Long value2) {
            addCriterion("surnameid between", value1, value2, "surnameid");
            return (Criteria) this;
        }

        public Criteria andSurnameidNotBetween(Long value1, Long value2) {
            addCriterion("surnameid not between", value1, value2, "surnameid");
            return (Criteria) this;
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

        public Criteria andGenenameIsNull() {
            addCriterion("genename is null");
            return (Criteria) this;
        }

        public Criteria andGenenameIsNotNull() {
            addCriterion("genename is not null");
            return (Criteria) this;
        }

        public Criteria andGenenameEqualTo(String value) {
            addCriterion("genename =", value, "genename");
            return (Criteria) this;
        }

        public Criteria andGenenameNotEqualTo(String value) {
            addCriterion("genename <>", value, "genename");
            return (Criteria) this;
        }

        public Criteria andGenenameGreaterThan(String value) {
            addCriterion("genename >", value, "genename");
            return (Criteria) this;
        }

        public Criteria andGenenameGreaterThanOrEqualTo(String value) {
            addCriterion("genename >=", value, "genename");
            return (Criteria) this;
        }

        public Criteria andGenenameLessThan(String value) {
            addCriterion("genename <", value, "genename");
            return (Criteria) this;
        }

        public Criteria andGenenameLessThanOrEqualTo(String value) {
            addCriterion("genename <=", value, "genename");
            return (Criteria) this;
        }

        public Criteria andGenenameLike(String value) {
            addCriterion("genename like", value, "genename");
            return (Criteria) this;
        }

        public Criteria andGenenameNotLike(String value) {
            addCriterion("genename not like", value, "genename");
            return (Criteria) this;
        }

        public Criteria andGenenameIn(List<String> values) {
            addCriterion("genename in", values, "genename");
            return (Criteria) this;
        }

        public Criteria andGenenameNotIn(List<String> values) {
            addCriterion("genename not in", values, "genename");
            return (Criteria) this;
        }

        public Criteria andGenenameBetween(String value1, String value2) {
            addCriterion("genename between", value1, value2, "genename");
            return (Criteria) this;
        }

        public Criteria andGenenameNotBetween(String value1, String value2) {
            addCriterion("genename not between", value1, value2, "genename");
            return (Criteria) this;
        }

        public Criteria andPictureIsNull() {
            addCriterion("picture is null");
            return (Criteria) this;
        }

        public Criteria andPictureIsNotNull() {
            addCriterion("picture is not null");
            return (Criteria) this;
        }

        public Criteria andPictureEqualTo(String value) {
            addCriterion("picture =", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotEqualTo(String value) {
            addCriterion("picture <>", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThan(String value) {
            addCriterion("picture >", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureGreaterThanOrEqualTo(String value) {
            addCriterion("picture >=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThan(String value) {
            addCriterion("picture <", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLessThanOrEqualTo(String value) {
            addCriterion("picture <=", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureLike(String value) {
            addCriterion("picture like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotLike(String value) {
            addCriterion("picture not like", value, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureIn(List<String> values) {
            addCriterion("picture in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotIn(List<String> values) {
            addCriterion("picture not in", values, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureBetween(String value1, String value2) {
            addCriterion("picture between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andPictureNotBetween(String value1, String value2) {
            addCriterion("picture not between", value1, value2, "picture");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(Long value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(Long value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(Long value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(Long value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(Long value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(Long value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<Long> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<Long> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(Long value1, Long value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(Long value1, Long value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andDescnameIsNull() {
            addCriterion("descname is null");
            return (Criteria) this;
        }

        public Criteria andDescnameIsNotNull() {
            addCriterion("descname is not null");
            return (Criteria) this;
        }

        public Criteria andDescnameEqualTo(String value) {
            addCriterion("descname =", value, "descname");
            return (Criteria) this;
        }

        public Criteria andDescnameNotEqualTo(String value) {
            addCriterion("descname <>", value, "descname");
            return (Criteria) this;
        }

        public Criteria andDescnameGreaterThan(String value) {
            addCriterion("descname >", value, "descname");
            return (Criteria) this;
        }

        public Criteria andDescnameGreaterThanOrEqualTo(String value) {
            addCriterion("descname >=", value, "descname");
            return (Criteria) this;
        }

        public Criteria andDescnameLessThan(String value) {
            addCriterion("descname <", value, "descname");
            return (Criteria) this;
        }

        public Criteria andDescnameLessThanOrEqualTo(String value) {
            addCriterion("descname <=", value, "descname");
            return (Criteria) this;
        }

        public Criteria andDescnameLike(String value) {
            addCriterion("descname like", value, "descname");
            return (Criteria) this;
        }

        public Criteria andDescnameNotLike(String value) {
            addCriterion("descname not like", value, "descname");
            return (Criteria) this;
        }

        public Criteria andDescnameIn(List<String> values) {
            addCriterion("descname in", values, "descname");
            return (Criteria) this;
        }

        public Criteria andDescnameNotIn(List<String> values) {
            addCriterion("descname not in", values, "descname");
            return (Criteria) this;
        }

        public Criteria andDescnameBetween(String value1, String value2) {
            addCriterion("descname between", value1, value2, "descname");
            return (Criteria) this;
        }

        public Criteria andDescnameNotBetween(String value1, String value2) {
            addCriterion("descname not between", value1, value2, "descname");
            return (Criteria) this;
        }

        public Criteria andDetailidIsNull() {
            addCriterion("detailid is null");
            return (Criteria) this;
        }

        public Criteria andDetailidIsNotNull() {
            addCriterion("detailid is not null");
            return (Criteria) this;
        }

        public Criteria andDetailidEqualTo(Long value) {
            addCriterion("detailid =", value, "detailid");
            return (Criteria) this;
        }

        public Criteria andDetailidNotEqualTo(Long value) {
            addCriterion("detailid <>", value, "detailid");
            return (Criteria) this;
        }

        public Criteria andDetailidGreaterThan(Long value) {
            addCriterion("detailid >", value, "detailid");
            return (Criteria) this;
        }

        public Criteria andDetailidGreaterThanOrEqualTo(Long value) {
            addCriterion("detailid >=", value, "detailid");
            return (Criteria) this;
        }

        public Criteria andDetailidLessThan(Long value) {
            addCriterion("detailid <", value, "detailid");
            return (Criteria) this;
        }

        public Criteria andDetailidLessThanOrEqualTo(Long value) {
            addCriterion("detailid <=", value, "detailid");
            return (Criteria) this;
        }

        public Criteria andDetailidIn(List<Long> values) {
            addCriterion("detailid in", values, "detailid");
            return (Criteria) this;
        }

        public Criteria andDetailidNotIn(List<Long> values) {
            addCriterion("detailid not in", values, "detailid");
            return (Criteria) this;
        }

        public Criteria andDetailidBetween(Long value1, Long value2) {
            addCriterion("detailid between", value1, value2, "detailid");
            return (Criteria) this;
        }

        public Criteria andDetailidNotBetween(Long value1, Long value2) {
            addCriterion("detailid not between", value1, value2, "detailid");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNull() {
            addCriterion("updatetime is null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIsNotNull() {
            addCriterion("updatetime is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeEqualTo(Date value) {
            addCriterion("updatetime =", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotEqualTo(Date value) {
            addCriterion("updatetime <>", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThan(Date value) {
            addCriterion("updatetime >", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updatetime >=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThan(Date value) {
            addCriterion("updatetime <", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeLessThanOrEqualTo(Date value) {
            addCriterion("updatetime <=", value, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeIn(List<Date> values) {
            addCriterion("updatetime in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotIn(List<Date> values) {
            addCriterion("updatetime not in", values, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeBetween(Date value1, Date value2) {
            addCriterion("updatetime between", value1, value2, "updatetime");
            return (Criteria) this;
        }

        public Criteria andUpdatetimeNotBetween(Date value1, Date value2) {
            addCriterion("updatetime not between", value1, value2, "updatetime");
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