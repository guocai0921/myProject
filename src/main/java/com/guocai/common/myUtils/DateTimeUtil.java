package com.guocai.common.myUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *   实际开发中比较常用时间日期的各种转换 
 * 
 * @author 桑伟东    
 */
public class DateTimeUtil {
	/**
	 * 默认的时间日期样式
	 */
	private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public final static String DEFAULT_DATE_FORMAT="yyyyMMddHHmmss";
	/** * 系统默认的时区 */
	// private static final ZoneId ZONE_ID= ZoneId.systemDefault();
	/** * 中国时区 */
	private static final ZoneId ZONE_ID = ZoneId.of("Asia/Shanghai");

	public static void main(String[] args) {
		String dateTime = formatEpochSecond("yyyy-MM-dd HH:mm:ss", 1525767228);
		System.out.println(dateTime);
		String parseStrToNewStr = parseStrToNewStr("yyyy-MM-dd HH:mm:ss", "2018-05-08 16:13:48", "MM-dd");
		System.out.println(parseStrToNewStr);
		long parseDateTime = parseDateTime("yyyy-MM-dd HH:mm:ss", "2018-05-08 16:13:48");
		System.out.println(parseDateTime);
		long parseDateTime2 = DefaultParseDateTime("2018-05-08 16:13:48");
		System.out.println(parseDateTime2);
		String yesterdayStr1 = yesterdayStr(DateTimeFormatter.ofPattern(DateTimeUtil.DEFAULT_PATTERN));
		System.out.println(yesterdayStr1);
		String yesterdayStr2 = yesterdayStr(DateTimeFormatter.ofPattern(DateTimeUtil.DEFAULT_DATE_FORMAT));
		System.out.println(yesterdayStr2);
		System.out.println(ZoneId.getAvailableZoneIds());
	}

	/**
	 * * 按照默认的模板将时间戳转换为时间日期的字符串形式 *  * @param epochSecond *            时间戳
	 * 1525767228 * @return 返回时间日期的字符串形式 2018-05-08 16:13:48
	 */
	public static String DefaultFormatEpochSecond(long epochSecond) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond),
				ZoneId.systemDefault());
		return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
	}

	/**
	 * * 按照给定的时间日期模版，将时间戳转换成字符串形式 *  * @param pattern *            模版，例如"yyyy-MM-dd
	 * HH:mm:ss" * @param epochSecond *            时间戳 1525767228 * @return 转换后的字符串
	 * 2018-05-08 16:13:48
	 */
	public static String formatEpochSecond(String pattern, long epochSecond) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond),
				ZoneId.systemDefault());
		return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * * 按照给定的时间日期模版，将时间戳转换成字符串形式 *  * @param dateTimeFormatter *            模版
	 * * @param epochSecond *            时间戳 * @return 转换后的字符串
	 */
	public static String formatEpochSecond(DateTimeFormatter dateTimeFormatter, long epochSecond) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(epochSecond),
				ZoneId.systemDefault());
		return localDateTime.format(dateTimeFormatter);
	}

	/**
	 * * 将一种时间日期字符串转换成另外一种形式 *  * @param oldPattern *            旧的时间日期字符串样式
	 * "yyyy-MM-dd HH:mm:ss" * @param oldDateTime *            旧的时间日期字符串 2018-05-08
	 * 16:13:48 * @param newPattern *            新的时间日期字符串样式 "MM-dd" * @return
	 * 转换后的字符串 05-08
	 */
	public static String parseStrToNewStr(String oldPattern, String oldDateTime, String newPattern) {
		LocalDateTime localDateTime = LocalDateTime.parse(oldDateTime, DateTimeFormatter.ofPattern(oldPattern));
		return localDateTime.format(DateTimeFormatter.ofPattern(newPattern));
	}

	/**
	 * * 将一种时间日期字符串转换成另外一种形式 *  * @param oldDateTimeFormatter *           
	 * 旧的时间日期字符串样式 * @param oldDateTime *            旧的时间日期字符串 * @param
	 * newDateTimeFormatter *            新的时间日期字符串样 * @return 转换后的字符串
	 */
	public static String parseStrToNewStr(DateTimeFormatter oldDateTimeFormatter, String oldDateTime,
			DateTimeFormatter newDateTimeFormatter) {
		LocalDateTime localDateTime = LocalDateTime.parse(oldDateTime, oldDateTimeFormatter);
		return localDateTime.format(newDateTimeFormatter);
	}

	/**
	 * * 将给定的时间日期字符串按照指定的模版解析成时间戳 *  * @param pattern *            模版，例如"yyyy-MM-dd
	 * HH:mm:ss" * @param dateTime *            时间日期字符串 2018-05-08 16:13:48
	 * * @return 时间戳 1525767228000
	 */
	public static long parseDateTime(String pattern, String dateTime) {
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
		Instant instant = localDateTime.atZone(ZONE_ID).toInstant();
		return instant.toEpochMilli();
	}

	/**
	 * * 将给定的时间日期字符串按照指定的模版解析成时间戳 *  * @param dateTimeFormatter *            模版
	 * * @param dateTime *            时间日期字符串 * @return 时间戳
	 */
	public static long parseDateTime(DateTimeFormatter dateTimeFormatter, String dateTime) {
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, dateTimeFormatter);
		Instant instant = localDateTime.atZone(ZONE_ID).toInstant();
		return instant.toEpochMilli();
	}

	/**
	 * * 将给定的时间日期字符串按照默认的模版解析成时间戳 "yyyy-MM-dd HH:mm:ss" *  * @param dateTime *     
	 *       2018-05-08 16:13:48 * @return 时间戳 1525767228000
	 */
	public static long DefaultParseDateTime(String dateTime) {
		LocalDateTime localDateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(DEFAULT_PATTERN));
		Instant instant = localDateTime.atZone(ZONE_ID).toInstant();
		return instant.toEpochMilli();
	}

	/**
	 * * 按照给定的格式获取昨天这个时候的时间日期字符串 *  * @param dateTimeFormatter *            时间日期格式
	 * * @return
	 */
	public static String yesterdayStr(DateTimeFormatter dateTimeFormatter) {
		LocalDateTime localDateTime = LocalDateTime.now().plusDays(-1);
		return localDateTime.format(dateTimeFormatter);
	}

	/**
	 * * 按照给定的格式获取昨天这个时候的时间日期字符串 *  * @param pattern *            时间日期格式 * @return
	 */
	public static String yesterdayStr(String pattern) {
		LocalDateTime localDateTime = LocalDateTime.now().plusDays(-1);
		return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
	}

	/** * 按照给定的格式获取当前时间日期字符串 *  * @param pattern *            时间日期格式 * @return */
	public static String todayStr(String pattern) {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * * 按照给定的格式获取当前时间日期字符串 *  * @param dateTimeFormatter *            时间日期格式
	 * * @return
	 */
	public static String todayStr(DateTimeFormatter dateTimeFormatter) {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.format(dateTimeFormatter);
	}

	/** * 获取昨天这个时间的时间戳 *  * @return 时间戳 */
	public static long yesterday() {
		LocalDateTime localDateTime = LocalDateTime.now().plusDays(-1);
		return localDateTime.atZone(ZONE_ID).toInstant().toEpochMilli();
	}
}