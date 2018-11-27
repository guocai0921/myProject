/**
 * 
 */
package com.guocai.common.utils;

/**
 * @author lvjian
 *
 */
public final class ORMappingUtils {
	
//	public static String TAB_PREFIX = "MES_";
	public static String VIEW_PREFIX = "V_";
	
	public static String toSequenceName(String tableName) {
		int index = tableName.indexOf("_");
		String prefix = tableName.substring(0, index);
		String	sequence = prefix + "_SEQ_" + tableName.substring(index+1);	
		return sequence;
	}
	/**
	 * eg: productionOrder -> MES_PRODUCTION_ORDERS
	 * @param ctType
	 * @return
	 */
	public static String toTabName(String prefix, String ctType) {
		StringBuffer sb = new StringBuffer(prefix);
		String tmp = toTabField(ctType);
		if (tmp.startsWith("_")) {
			tmp = tmp.substring(1);
		}
		sb.append(tmp).append("s");
		return sb.toString().toUpperCase();
	}
	
	
	public static String toViewName(String ctType) {
		StringBuffer sb = new StringBuffer(VIEW_PREFIX);
		String tmp = toTabField(ctType);
		if (tmp.startsWith("_")) {
			tmp = tmp.substring(1);
		}
		sb.append(tmp);//.append("s");
		return sb.toString().toUpperCase();
	}
	/**
	 * eg: poParList -> MES_PO_PAR_VALUES 
	 * @param parInstId
	 * @return
	 */
//	public static String toParTabName(String parInstId) {
//		StringBuffer sb = new StringBuffer(TAB_PREFIX);
//		String temp = parInstId.replaceFirst("List", "Values");
//		sb.append(toTabField(temp));
//		return sb.toString().toUpperCase();
//	}
	
	/**
	 * eg: poParList -> po_pv_no
	 * @param parInstId
	 * @return
	 */
	public static String toParPkName(String parInstId) {
		StringBuffer sb = new StringBuffer();
		String temp = parInstId.replaceFirst("ParList", "PvNo");
		sb.append(toTabField(temp));
		return sb.toString().toLowerCase();
	}
	
	/**
	 * eg: poParList -> po_no
	 * @param parInstId
	 * @return
	 */
	public static String toParFkName(String parInstId) {
		StringBuffer sb = new StringBuffer();
		String temp = parInstId.replaceFirst("ParList", "No");
		sb.append(toTabField(temp));
		return sb.toString().toLowerCase();
	}
	
	
	//eg: parId -> par_id
	public static  String toTabField(String parId) {
		char[] chs = parId.toCharArray();
		StringBuffer sb = new StringBuffer();
		int index = parId.indexOf('$');
		int begin = index == -1 ? -1 : index+1;
		for (int i=0; i<chs.length; i++) {
			//if (chs[i]
			char c = chs[i];			
			if (i>begin && Character.isUpperCase(c)) {
				sb.append('_');
				sb.append(Character.toLowerCase(c));
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static String fromTabField(String fieldId) {
		char[] chs = fieldId.toCharArray();
		StringBuffer sb = new StringBuffer();
		boolean flag = false;
		for (int i=0; i<chs.length; i++) {
			//if (chs[i]
			char c = chs[i];
			if (c == '_') {
				flag = true;
				continue;
			} 
				
			if (flag) {
				sb.append(Character.toUpperCase(c));
				flag = false;
			} else {
				sb.append(Character.toLowerCase(c));
			}
			
		}
		return sb.toString();
	}
	/**
	 * 将对象的父对象实例名称转换成外键，如：productionOrder.poJobList.poJob(*)
	 * 对于PoJob 对象而言，poJobList 就是 parenetObjId ，需转换成外键 po_no
	 * @param parenetObjId
	 * @return
	 */
	public static  String toForeignKeyName(String parenetObjId) {
		if (StringUtil.isBlank(parenetObjId)) {
			return null;
		}
		
		char[] chs = parenetObjId.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<chs.length; i++) {
			//if (chs[i]
			char c = chs[i];
			if (Character.isUpperCase(c)) {				
				break;
			} else {
				sb.append(c);
			}
		}
		return sb.append("_no").toString();
	}
	
	/**
	 * 解析引用对象的实例名称到 对象名缩写，如pomIn:PoMaterial -> pom (pom 为PoMaterial的类型缩写）
	 * @param refObjInstId
	 * @return
	 */
	public static  String toRefObjName(String refObjInstId) {
		if (StringUtil.isBlank(refObjInstId)) {
			return null;
		}
		
		char[] chs = refObjInstId.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<chs.length; i++) {
			//if (chs[i]
			char c = chs[i];
			if (Character.isUpperCase(c)) {				
				break;
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	
	public static  String toPrimaryKeyName(String objInstId, String ctType) {
		if (!StringUtil.isBlank(objInstId)) {
			return objInstId + "_no";
		}
		
		char[] chs = ctType.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<chs.length; i++) {
			//if (chs[i]
			char c = chs[i];
			if (Character.isUpperCase(c)) {				
				sb.append(Character.toLowerCase(c));
			} else {
				continue;
			}
		}
		return sb.append("_no").toString();
	}
	
	public static String genObjInstId(String ctType) {
		char[] chs = ctType.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<chs.length; i++) {
			//if (chs[i]
			char c = chs[i];
			if (Character.isUpperCase(c)) {				
				sb.append(Character.toLowerCase(c));
			} else {
				continue;
			}
		}
		return sb.toString();
	}
	
	public static boolean isTimeField(String attrId) {
		return (attrId.endsWith("Time") || attrId.endsWith("Timestamp"));
	}
	
	public static boolean isDateField(String attrId) {
		return (attrId.endsWith("Dt") || attrId.endsWith("Date"));
	}
	
	public static String  formateAsDateString(String attrv, String formateStr, String dbFormStr) {
		if (StringUtil.isBlank(attrv)) {
			return "null";
		} else {
			
			if ("sysdate".equalsIgnoreCase(attrv)) {
				return "sysdate";
			}
			
			java.util.Date d = DatetimeUtil.stringToDate(attrv, formateStr);
			String s1 = formateStr;
		    if (d == null) {
		    	return "to_date('" + attrv + "','" + s1 +"')";
		    }
		    String s = DatetimeUtil.dateToString(d, formateStr);		    
		    if (dbFormStr != null) {
		    	s1 = dbFormStr;
		    }
			return "to_date('" + s + "','" + s1 +"')";
		}		
	}
}
