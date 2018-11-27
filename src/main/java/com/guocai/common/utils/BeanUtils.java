package com.guocai.common.utils;

import org.apache.commons.beanutils.PropertyUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author cwtu
 */

/**
 * @author cwtu
 */
public class BeanUtils extends PropertyUtils {
    public static Object getProperty(Object bean, String property)
            throws RuntimeException {
        try {
            return PropertyUtils.getProperty(bean, property);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object cloneBean(Object bean) throws RuntimeException {
        try {
            return org.apache.commons.beanutils.BeanUtils.cloneBean(bean);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static void populate(Object bean, Map map) throws Exception {
        org.apache.commons.beanutils.BeanUtils.populate(bean, map);
    }

    public static void setPropertyAsNull(Object bean, String name)
            throws Exception {
        org.apache.commons.beanutils.BeanUtils.setProperty(bean, name, null);
    }

    /**
     * @param oldValue
     * @param newValue
     * @return
     */
    public static boolean compareValue(Object oldValue, Object newValue) {
        if ((oldValue == null) && (newValue == null)){
            return true;
        }else if ((oldValue == null) || (newValue == null)){
            return false;
        }else if (oldValue.getClass().isInstance(newValue)){
            return oldValue.equals(newValue);
        }else{
        	return newValue.equals(oldValue);
        }
    }

    public static boolean equals(Object bean1, Object bean2) {
        if (bean1 == null && bean2 == null) {
            return true;
        }else if(bean1 == null || bean2 == null){
        	return false;
        }

        try {
            if (bean1.getClass().equals(bean2.getClass())) {
                if (bean1 == bean2)
                    return true;
                Map map1 = BeanUtils.describe(bean1);
                Map map2 = BeanUtils.describe(bean2);
                return map1.equals(map2);
            }
        } catch (Exception e) {
            /*
             * catch bean1 or bean2 NullPointException catch
             * BeanUtils.describe() exception catch BeanUtils.describe()
             * returned null map exception
             */
            return false;
        }

        return false;
    }

    public static String toString(Object bean) {
        return toString(bean, null);
    }

    //	protected static String toString(Collection collection, List
    // travelledList)
    //	{
    //		String collectionString = "<collection>" + "\n";
    //					
    //		for(Iterator iterator = collection.iterator(); iterator.hasNext(); )
    //		{
    //			Object element = iterator.next();
    //			
    //			collectionString = collectionString + "<element>";
    //			collectionString = collectionString + BeanUtils.toString(element,
    // travelledList);
    //			collectionString = collectionString + "</element>" + "\n";
    //		}
    //					
    //		collectionString = collectionString + "</collection>" + "\n";
    //		
    //		return collectionString;
    //	}
    //	
    //	protected static String toString(Map map, boolean isOverall, List
    // travelledList)
    //	{
    //		String mapString = "<map>" + "\n";
    //						
    //		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();)
    //		{
    //			Map.Entry entry = (Map.Entry) iterator.next();
    //			Object key = entry.getKey();
    //			Object value = entry.getValue();
    //
    //			mapString = mapString + "<entry key=\"" + key + "\">";
    //			mapString = mapString + BeanUtils.toString(value, travelledList);
    //			mapString = mapString + "</entry>"+ "\n";
    //		}
    //						
    //		mapString = mapString + "</map>" + "\n";
    //		
    //		return mapString;
    //	}

    public static String toString(Object bean, List travelledList) {
        if (bean == null) {
            return "null";
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<javabean class=\"" + bean.getClass().getName() + "\">"
                + "\n");

        PropertyDescriptor[] propDescriptors = BeanUtils
                .getPropertyDescriptors(bean);

        for (int i = 0; i < propDescriptors.length; i++) {
            try {
                String propName = propDescriptors[i].getName();
                Class propClass = propDescriptors[i].getPropertyType();
                Object propValue = BeanUtils.getProperty(bean, propName);
                if (propValue.getClass().isPrimitive()
                        || (propValue.getClass().getName()
                                .startsWith("java.lang"))
                        || (propValue.getClass().getName()
                                .startsWith("java.util.Date"))) {

                    propValue = propValue.toString();

                    sb.append("<property name=\"" + propName + "\" class=\""
                            + propClass.getName() + "\">");
                    sb.append(propValue);
                    sb.append("</property>\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sb.append("</javabean>" + "\n");

        return sb.toString();
    }

    public static void copyProperties(Object desBean, Object srcBean,
            HashMap propMap) {
        try {
            Object tempBean = BeanUtils.cloneBean(desBean);

            Iterator iterator = propMap.keySet().iterator();
            while (iterator.hasNext()) {
                String srcPropName = (String) iterator.next();
                String desPropName = (String) propMap.get(srcPropName);
                PropertyUtils.setProperty(tempBean, desPropName, PropertyUtils
                        .getProperty(srcBean, srcPropName));
            }

            BeanUtils.copyProperties(desBean, tempBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param beanList
     * @param beanClass
     * @param propMaps
     *            id = {id1, id2, id3} name = {name1, name2, name3} desc =
     *            {desc1, desc2, desc3} populate 3 instances of beanClass :
     *            beanClass#id, beanClass#name, beanClass#desc
     */
    public static void populate(List beanList, Class beanClass, Map propMaps) {
        try {
            int beanCount = 0;
            Iterator iterator = propMaps.values().iterator();
            while (iterator.hasNext()) {
                Object[] value = (Object[]) iterator.next();
                if (value != null && value.length > beanCount) {
                    beanCount = value.length;
                }
            }

            for (int i = 0; i < beanCount; i++) {
                Map propMap = new HashMap();
                iterator = propMaps.keySet().iterator();
                while (iterator.hasNext()) {
                    Object propName = iterator.next();
                    Object propValue = null;

                    Object[] propValues = (Object[]) propMaps.get(propName);
                    if (propValues != null && (propValues.length - 1) >= i) {
                        propValue = propValues[i];
                    }

                    propMap.put(propName, propValue);
                }

                Object bean = beanClass.newInstance();
                populate(bean, propMap);

                beanList.add(bean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Collection mergeCollectionsByProperties(
            Collection collection1, Collection collection2, String[] propNames) {
        TreeMap objectMap = new TreeMap();

        mergeCollectionsByProperties(objectMap, collection1, propNames);
        mergeCollectionsByProperties(objectMap, collection2, propNames);

        if (objectMap.size() > 0) {
            return new Vector(objectMap.values());
        } else {
            return null;
        }
    }

    private static void mergeCollectionsByProperties(Map objectMap,
            Collection collection, String[] propNames) {
        if (objectMap != null && collection != null) {
            Iterator iterator = collection.iterator();
            while (iterator.hasNext()) {
                try {
                    Object object = iterator.next();
                    //					Object objectId = BeanUtils.getProperty(object, "id");
                    String objectId = "";
                    for (int i = 0; i < propNames.length; i++) {
                        objectId += BeanUtils.getProperty(object, propNames[i])
                                + "#";
                    }

                    if (!objectMap.containsKey(objectId)) {
                        objectMap.put(objectId, object);
                    }
                } catch (Exception e) {
                }
            }
        }
    }

    public static void setProperty(Collection beans, String propName,
            Object propValue) throws Exception {
        if (beans != null && beans.size() > 0) {
            Iterator iterator = beans.iterator();
            while (iterator.hasNext()) {
                try {
                    setProperty(iterator.next(), propName, propValue);
                } catch (Exception e) {
                }
            }
        }
    }

    public static void setProperty(Object bean, String propName,
            Object propValue) {
        if (bean != null) {
            try {
                PropertyUtils.setProperty(bean, propName, propValue);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
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
    
    /**
     * Get the property value of every bean in beanCollection, and return them
     * as a List of objects.
     * @param name the name of the property that want to get.
     * @author chenke
     */
    public static List getPropertyList(Collection beanCollection, String name) {
		if (CollectionUtil.isEmpty(beanCollection)) {
			return Collections.EMPTY_LIST;
		}
		List result = new ArrayList(beanCollection.size());
		for (Iterator it = beanCollection.iterator(); it.hasNext();) {
			Object obj = it.next();
			Object value = getProperty2(obj, name);
			result.add(value);
		}
		return result;
	}

    public static Object[] getProperty2(Object bean, String[] names) {
		Object[] values = new Object[names.length];
		for (int i = 0; i < names.length; i++) {
			values[i] = getProperty2(bean, names[i]);
		}
		return values;
	}
    
    public static Object getProperty2(Object bean, String name) {
		try {
			return PropertyUtils.getProperty(bean, name);
		} catch (IllegalAccessException e) {
			String errMsg = generateErrMsg(bean, name);
			throw new RuntimeException(errMsg, e);
		} catch (InvocationTargetException e) {
			String errMsg = generateErrMsg(bean, name);
			throw new RuntimeException(errMsg, e);
		} catch (NoSuchMethodException e) {
			String errMsg = generateErrMsg(bean, name);
			throw new RuntimeException(errMsg, e);
		}
	}
    
    private static String generateErrMsg(Object bean, String name) {
		StringBuffer result = new StringBuffer();
		result.append("Can not get the value of property <" + name
				+ "> in bean <" + bean.getClass().getName() + ">");
		return result.toString();
	}
    
}
