package cn.com.gene.comm;

import java.io.Serializable;

public class ResultMap implements Serializable {
	

	private static final long serialVersionUID = -7434603105681424078L;

	// 响应业务状态
	private Integer code;

	// 响应消息
	private String msg;

	// 响应中的数据
	private Object obj;

	public static ResultMap build(Integer code, String msg, Object obj) {
		return new ResultMap(code, msg, obj);
	}
	public static ResultMap IS_200(Object data) {
		return new ResultMap(data);
	}

	public static ResultMap IS_200() {
		return new ResultMap(null);
	}
	public ResultMap() {
		super();
	}
	public static ResultMap build(Integer code, String msg) {
		return new ResultMap(code, msg, null);
	}
	public ResultMap(Integer code, String msg, Object obj) {
		this.code = code;
		this.msg = msg;
		this.obj = obj;
	}
	public ResultMap(Object obj) {
		this.code = 200;
		this.msg = "OK";
		this.obj = obj;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
