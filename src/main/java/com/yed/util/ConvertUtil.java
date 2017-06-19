package com.yed.util;

/**
 * @description:
 * 对象类型转换工具类
 * @class name:com.netsky.base.flow.utils.NumUtil
 * @author wind Jan 16, 2010
 */
public class ConvertUtil {
	
    /**
     * 把目标对应转化为Integer
     * <br>如果目标对象可以转化者转化，不能转化者返回-1
     * @param arg
     * @return Integer
     */
    public static Integer toInteger(Object arg){
    	if (arg instanceof Integer) {
			return (Integer) arg;
		} else if (arg instanceof String) {
			String tmpStr = (String) arg;
			if (tmpStr != null && tmpStr.matches("^0|[+,-]?[1-9][0-9]{0,8}$")) {
				return new Integer(tmpStr);
			}
		} else if(arg instanceof Long){
			Long tmpLong = (Long)arg;
			if(tmpLong < Integer.MAX_VALUE&&tmpLong>Integer.MIN_VALUE){
				return tmpLong.intValue();
			}
		}
		return new Integer(-1);
    }
    
    /**
     * 把目标对应转化为Integer
     * <br>如果目标对象可以转化者转化，不能转化者返回 defaultValue
     * @param arg
     * @param defaultValue
     * @return Integer
     */
    public static Integer toInteger(Object arg,Integer defaultValue){
    	if (arg instanceof Integer) {
			return (Integer) arg;
		} else if (arg instanceof String) {
			String tmpStr = (String) arg;
			if (tmpStr != null && tmpStr.matches("^0|[+,-]?[1-9][0-9]{0,8}$")) {
				return new Integer(tmpStr);
			}
		} else if(arg instanceof Long){
			Long tmpLong = (Long)arg;
			if(tmpLong < Integer.MAX_VALUE&&tmpLong>Integer.MIN_VALUE){
				return tmpLong.intValue();
			}
		}
		return defaultValue;
    }
    
    /**
     *  把目标对应转化为Long
     * <br>如果目标对象可以转化者转化，不能转化者返回-1
     * @param arg
     * @return Long
     */
    public static Long toLong(Object arg){
    	if (arg instanceof Long) {
			return (Long) arg;
		} else if (arg instanceof String) {
			String tmpStr = (String) arg;
			if (tmpStr != null && tmpStr.matches("^0|[+,-]?[1-9][0-9]{0,17}$")) {
				return new Long(tmpStr);
			}
		} else if(arg instanceof Integer){
			Long tmpLong = (Long)arg;
			return tmpLong;
		}
		return new Long(-1);
    }
    
    /**
     * 把目标对应转化为Long
     * <br>如果目标对象可以转化者转化，不能转化者返回 defaultValue
     * @param arg
     * @param defaultValue
     * @return Long
     */
    public static Long toLong(Object arg,Long defaultValue){
    	if (arg instanceof Long) {
			return (Long) arg;
		} else if (arg instanceof String) {
			String tmpStr = (String) arg;
			if (tmpStr != null && tmpStr.matches("^0|[+,-]?[1-9][0-9]{0,17}$")) {
				return new Long(tmpStr);
			}
		} else if(arg instanceof Integer){
			Long tmpLong = (Long)arg;
			return tmpLong;
		}
		return defaultValue;
    }    
    
    /**
     * 把目标对象转化成String
     * @param arg
     * @return String
     */
    public static String toString(Object arg){
    	if(arg == null){
	    	return "";	
    	} else if(arg instanceof String){
    		return (String)arg;
    	} else {
    		return arg.toString();
    	}
    }
    
    /**
     * 把目标对象转化成String,如果为空返回　defaultValue
     * @param arg
     * @param defaultValue
     * @return String
     */
    public static String toString(Object arg,String defaultValue){
    	if(arg == null || arg.equals("")){
	    	return defaultValue;	
    	} else if(arg instanceof String){
    		return (String)arg;
    	} else {
    		return arg.toString();
    	}
    }
    
    /**
     *  把目标对应转化为Long
     * <br>如果目标对象可以转化者转化，不能转化者返回-1
     * @param arg
     * @return Long
     */
    public static Double toDouble(Object arg){
    	if (arg instanceof Double) {
			return (Double) arg;
		} else if (arg instanceof String) {
			String tmpStr = (String) arg;
			if (tmpStr != null && tmpStr.matches("^0|[+,-]?[1-9][0-9]{0,17}$")) {
				return new Double(tmpStr);
			}
		} else if(arg instanceof Integer){
			Double tmpDouble = (Double)arg;
			return tmpDouble;
		} else if(arg instanceof java.math.BigDecimal){
			return ((java.math.BigDecimal)arg).doubleValue();
		}
		return new Double(-1);
    }
    
    /**
     * 把目标对应转化为Long
     * <br>如果目标对象可以转化者转化，不能转化者返回 defaultValue
     * @param arg
     * @param defaultValue
     * @return Long
     */
    public static Double toDouble(Object arg,Double defaultValue){
    	if (arg instanceof Double) {
			return (Double) arg;
		} else if (arg instanceof String) {
			String tmpStr = (String) arg;
			if (tmpStr != null && tmpStr.matches("^0|[+,-]?[1-9][0-9]{0,17}(\\.)*\\d*$")) {
				return new Double(tmpStr);
			}
		} else if(arg instanceof Integer){
			Double tmpDouble = (Double)arg;
			return tmpDouble;
		} else if(arg instanceof java.math.BigDecimal){
			return ((java.math.BigDecimal)arg).doubleValue();
		}
		return defaultValue;
    }    
}
