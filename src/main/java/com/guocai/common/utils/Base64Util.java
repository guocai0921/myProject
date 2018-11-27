package com.guocai.common.utils;
import org.apache.commons.codec.binary.Base64;

/**
 * 使用Apache commons实现的Base 64加密解密
 * @author Duanxj
 *
 */
public class Base64Util {
	 /**
	  * 加密
	  * @param plainText
	  * @return
	  */
	 public static String encodeStr(String plainText){  
	        byte[] b=plainText.getBytes();  
	        Base64 base64=new Base64();
	        b=base64.encode(b);  
	        String s=new String(b);  
	        return s;  
	    }  
	      
	    /** 
	     * 解密 
	     * @param encodeStr
	     * @return
	     */
	    public static String decodeStr(String encodeStr){  
	        byte[] b=encodeStr.getBytes();  
	        Base64 base64=new Base64();
	        b=base64.decode(b);  
	        String s=new String(b);  
	        return s;  
	    }  
}
