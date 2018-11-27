package com.guocai.common.utils;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Map;

public class BeanUtilsEx extends BeanUtils
{

	private static final  Logger logger = LoggerFactory.getLogger(BeanUtilsEx.class);
    static
    {
        DateConverter d = new DateConverter(null);
        String[] datePattern = { "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd","yyyyMMdd","yyyy-MM-dd HH:mm:ss" };
        d.setPatterns(datePattern);
        ConvertUtils.register(d, java.util.Date.class);

        // 以下几行解决当值为空时,用 null 设置对应字段的值, (但 String 类型仍然非null, 为空字符串)
        ConvertUtils.register(new IntegerConverter(null), Integer.class);
        ConvertUtils.register(new FloatConverter(null), Float.class);
        ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
        ConvertUtils.register(new DoubleConverter(null), Double.class);
    }

    /**
     * 从 request 传递的参数集合,组装到 JavaBean 中
     * 
     * @param request
     * @param obj
     */
    @SuppressWarnings("unchecked")
    public static void populate(HttpServletRequest request, Object obj) {
        Map map = request.getParameterMap();
        	try {
        		org.apache.commons.beanutils.BeanUtils.populate(obj, map);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    public static void populateFromMap(Map map, Object obj) {
        try
        {
        	org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
            logger.error("error",e);
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
            logger.error("error",e);
        }
    }
    public static void copyProperties(Object dest, Object src) {
        try {
        	org.apache.commons.beanutils.BeanUtils.copyProperties(dest, src);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}

