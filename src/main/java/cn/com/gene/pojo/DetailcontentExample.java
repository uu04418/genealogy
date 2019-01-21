package cn.com.gene.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DetailcontentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DetailcontentExample() {
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

        public Criteria andDetailcontentidIsNull() {
            addCriterion("detailcontentid is null");
            return (Criteria) this;
        }

        public Criteria andDetailcontentidIsNotNull() {
            addCriterion("detailcontentid is not null");
            return (Criteria) this;
        }

        public Criteria andDetailcontentidEqualTo(Long value) {
            addCriterion("detailcontentid =", value, "detailcontentid");
            return (Criteria) this;
        }

        public Criteria andDetailcontentidNotEqualTo(Long value) {
            addCriterion("detailcontentid <>", value, "detailcontentid");
            return (Criteria) this;
        }

        public Criteria andDetailcontentidGreaterThan(Long value) {
            addCriterion("detailcontentid >", value, "detailcontentid");
            return (Criteria) this;
        }

        public Criteria andDetailcontentidGreaterThanOrEqualTo(Long value) {
            addCriterion("detailcontentid >=", value, "detailcontentid");
            return (Criteria) this;
        }

        public Criteria andDetailcontentidLessThan(Long value) {
            addCriterion("detailcontentid <", value, "detailcontentid");
            return (Criteria) this;
        }

        public Criteria andDetailcontentidLessThanOrEqualTo(Long value) {
            addCriterion("detailcontentid <=", value, "detailcontentid");
            return (Criteria) this;
        }

        public Criteria andDetailcontentidIn(List<Long> values) {
            addCriterion("detailcontentid in", values, "detailcontentid");
            return (Criteria) this;
        }

        public Criteria andDetailcontentidNotIn(List<Long> values) {
            addCriterion("detailcontentid not in", values, "detailcontentid");
            return (Criteria) this;
        }

        public Criteria andDetailcontentidBetween(Long value1, Long value2) {
            addCriterion("detailcontentid between", value1, value2, "detailcontentid");
            return (Criteria) this;
        }

        public Criteria andDetailcontentidNotBetween(Long value1, Long value2) {
            addCriterion("detailcontentid not between", value1, value2, "detailcontentid");
            return (Criteria) this;
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

        public Criteria andUseridIsNull() {
            addCriterion("userid is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userid is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(Long value) {
            addCriterion("userid =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(Long value) {
            addCriterion("userid <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(Long value) {
            addCriterion("userid >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(Long value) {
            addCriterion("userid >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(Long value) {
            addCriterion("userid <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(Long value) {
            addCriterion("userid <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<Long> values) {
            addCriterion("userid in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<Long> values) {
            addCriterion("userid not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(Long value1, Long value2) {
            addCriterion("userid between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(Long value1, Long value2) {
            addCriterion("userid not between", value1, value2, "userid");
            return (Criteria) this;
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

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andDetailcontentIsNull() {
            addCriterion("detailcontent is null");
            return (Criteria) this;
        }

        public Criteria andDetailcontentIsNotNull() {
            addCriterion("detailcontent is not null");
            return (Criteria) this;
        }

        public Criteria andDetailcontentEqualTo(String value) {
            addCriterion("detailcontent =", value, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andDetailcontentNotEqualTo(String value) {
            addCriterion("detailcontent <>", value, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andDetailcontentGreaterThan(String value) {
            addCriterion("detailcontent >", value, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andDetailcontentGreaterThanOrEqualTo(String value) {
            addCriterion("detailcontent >=", value, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andDetailcontentLessThan(String value) {
            addCriterion("detailcontent <", value, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andDetailcontentLessThanOrEqualTo(String value) {
            addCriterion("detailcontent <=", value, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andDetailcontentLike(String value) {
            addCriterion("detailcontent like", value, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andDetailcontentNotLike(String value) {
            addCriterion("detailcontent not like", value, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andDetailcontentIn(List<String> values) {
            addCriterion("detailcontent in", values, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andDetailcontentNotIn(List<String> values) {
            addCriterion("detailcontent not in", values, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andDetailcontentBetween(String value1, String value2) {
            addCriterion("detailcontent between", value1, value2, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andDetailcontentNotBetween(String value1, String value2) {
            addCriterion("detailcontent not between", value1, value2, "detailcontent");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
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