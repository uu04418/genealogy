package cn.com.gene.comm;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;




/**
 * 各种id生成策略
 */
public class IDUtils {
	

	static String[] units = { "", "十", "百", "千", "万", "十万", "百万", "千万", "亿", "十亿", "百亿", "千亿", "万亿" };
	static char[] numArray = { '零', '一', '二', '三', '四', '五', '六', '七', '八', '九' };
	static char[] numArraybig = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' };
	static String[] unitsbig = { "", "拾", "佰", "仟", "萬", "拾萬", "佰萬", "仟萬", "亿", "拾亿", "佰亿", "仟亿", "萬亿" };
	
	/**
	 * 通过请求头部token 获取用户ID 如果没有用户ID 返回-1
	 * **/
	public static Long searchuseridbyrequest(HttpServletRequest request) {
		String userid = request.getAttribute("checkuserid").toString();
		if (userid == null || "".equals(userid) || "null".equals(userid)) {
			return -1l;
		} else {
			Long i = Long.valueOf(userid);
			return i.longValue();
		}
	}
	
	
	
	public static String datecompany(Date end, Date start) {
		String str = "";
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(end);
		c2.setTime(start);
		if (c1.getTimeInMillis() < c2.getTimeInMillis())
			return str;
		int year1 = c1.get(Calendar.YEAR);
		int year2 = c2.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH);
		int month2 = c2.get(Calendar.MONTH);
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		int day2 = c2.get(Calendar.DAY_OF_MONTH);
		// 获取年的差值 假设 d1 = 2015-8-16 d2 = 2011-9-30
		int yearInterval = year1 - year2;
		// 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数
		if (month1 < month2 || month1 == month2 && day1 < day2)
			yearInterval--;
		if (yearInterval > 0) {
			str = yearInterval + "年";
		}
		int monthInterval = 0;
		if (month1 < month2) {
			monthInterval = (month1 + 12) - month2;
		} else {
			monthInterval = month1 - month2;
		}
		if (day1 < day2)
			monthInterval--;
		if (monthInterval > 0) {
			str = str + monthInterval + "个月";
		}

		return str;
	}
	/**
	 * 商品id生成
	 */
	public static String genItemId() {
		long millis = System.currentTimeMillis();
		Random random = new Random();
		int end2 = random.nextInt(99);
		String str = millis + String.format("%02d", end2);
		String id = new String(str);
		return id;
	}
	public static String foematIntegerbigprices(int num) {
		if (num <= 0) {
			return "零";
		}
		char[] val = String.valueOf(num).toCharArray();
		int len = val.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			String m = val[i] + "";
			int n = Integer.valueOf(m);
			boolean isZero = n == 0;
			String unit = unitsbig[(len - 1) - i];
			if (isZero) {
				if ('0' == val[i - 1]) {
					// not need process if the last digital bits is 0
					continue;
				} else {
					// no unit for 0
					sb.append(numArraybig[n]);
				}
			} else {
				sb.append(numArraybig[n]);
				sb.append(unit);
			}
		}
		String returnstr = sb.toString();
		if (returnstr.endsWith("零")) {
			returnstr = returnstr.substring(0, returnstr.length() - 1);
		}
		return returnstr;
	}

	public static String foematInteger(int num) {
		if (num <= 0) {
			return "";
		}
		char[] val = String.valueOf(num).toCharArray();
		int len = val.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			String m = val[i] + "";
			int n = Integer.valueOf(m);
			boolean isZero = n == 0;
			String unit = units[(len - 1) - i];
			if (isZero) {
				if ('0' == val[i - 1]) {
					// not need process if the last digital bits is 0
					continue;
				} else {
					// no unit for 0
					sb.append(numArray[n]);
				}
			} else {
				sb.append(numArray[n]);
				sb.append(unit);
			}
		}
		String returnstr = sb.toString();
		if (returnstr.endsWith("零")) {
			returnstr = returnstr.substring(0, returnstr.length() - 1);
		}
		if (returnstr.startsWith("一十")) {
			if (returnstr.length() > 1) {
				returnstr = returnstr.substring(1, returnstr.length());
			}
		}
		return returnstr;
	}

	public static String formatDecimal(double decimal) {
		String decimals = String.valueOf(decimal);
		int decIndex = decimals.indexOf(".");
		int integ = Integer.valueOf(decimals.substring(0, decIndex));
		int dec = Integer.valueOf(decimals.substring(decIndex + 1));
		String result = foematInteger(integ) + "." + formatFractionalPart(dec);
		return result;
	}

	public static String formatbigDecimal(double decimal) {
		String decimals = String.valueOf(decimal);
		int decIndex = decimals.indexOf(".");
		int integ = Integer.valueOf(decimals.substring(0, decIndex));
		int dec = Integer.valueOf(decimals.substring(decIndex + 1));
		String result = foematInteger(integ) + "." + formatFractionalPart(dec);
		return result;
	}

	private static String formatFractionalPart(int decimal) {
		char[] val = String.valueOf(decimal).toCharArray();
		int len = val.length;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; i++) {
			int n = Integer.valueOf(val[i] + "");
			sb.append(numArray[n]);
		}
		return sb.toString();
	}

	public static String checkphone(String telephone) {
		String phone = "";
		if (telephone.length() != 11) {
			phone = "";
		}else {
			phone = telephone;
		}
		return phone;
	}

	// str 传入的字符串 。
	public static String appendzero(String str, int length) {
		// 第一步判断字符串的长度
		if (str == null) {
			return "";
		}
		if (str.length() >= length) {
			return str;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length - str.length(); i++) {
			sb.append("0");
		}
		sb.append(str);

		return sb.toString();
	}

	
	
	
	
	
	
	
	
	
}
