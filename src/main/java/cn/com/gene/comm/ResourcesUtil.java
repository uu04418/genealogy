package cn.com.gene.comm;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public class ResourcesUtil implements Serializable {

	private static final long serialVersionUID = -7657898714983901418L;

	/**
	 * 系统语言环境，默认为中文zh
	 */
	public static final String LANGUAGE = "zh";

	/**
	 * 系统国家环境，默认为中国CN
	 */
	public static final String COUNTRY = "CN";

	private static Locale getLocale() {
		Locale locale = new Locale(LANGUAGE, COUNTRY);
		return locale;
	}

	/**
	 * 根据语言、国家、资源文件名和key名字获取资源文件值
	 * @param languag 语言
	 * @param country 国家
	 * @param baseName  资源文件名
	 * @param section key名字
	 * @return 值
	 */
	private static String getProperties(String baseName, String section) {
		String retValue = "";
		try {
			Locale locale = getLocale();
			ResourceBundle rb = ResourceBundle.getBundle(baseName, locale);
			retValue = (String) rb.getObject(section);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO 添加处理
		}
		return retValue;
	}

	/**
	 * 通过key从资源文件读取内容
	 * @param fileName  资源文件名
	 * @param key  索引
	 * @return 索引对应的内容
	 */
	public static String getValue(String fileName, String key) {
		String value = getProperties(fileName, key);
		return value;
	}
	
	public static String getValue(String fileName, Integer key) {
		String value = getProperties(fileName, key+"");
		return value;
	}

	public static List<String> gekeyList(String baseName) {
		Locale locale = getLocale();
		ResourceBundle rb = ResourceBundle.getBundle(baseName, locale);
		List<String> reslist = new ArrayList<String>();
		Set<String> keyset = rb.keySet();
		for (Iterator<String> it = keyset.iterator(); it.hasNext();) {
			String lkey = (String) it.next();
			reslist.add(lkey);
		}
		return reslist;

	}

	/**
	 * 通过key从资源文件读取内容，并格式化
	 * @param fileName 资源文件名
	 * @param key  索引
	 * @param objs  格式化参数
	 * @return 格式化后的内容
	 */
	public static String getValue(String fileName, String key, Object[] objs) {
		String pattern = getValue(fileName, key);
		String value = MessageFormat.format(pattern, objs);
		return value;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		Integer totalpage = 14;
		Integer  currentpage = 14;
		Integer seven = 7;
		Integer seven1 = currentpage / seven; 
		Integer seven2 = currentpage % seven; 
		for (int i=currentpage-seven2+1;i<=currentpage-seven2+7 &&i<=totalpage;i++) {
			list.add(i);
		}
	}

}
