package com.ssdut.house.tools;

import java.util.UUID;

/**
 * 产生UUID的类
 * @author admin
 *
 */
public class createUUIDUtils {
	public String uuid = UUID.randomUUID().toString();
	public int size5=1;
	public static String USERNOTEXIST="user_not_exist";
	public static String PWDWRONG="password_wrong";
	public static String LOGINSUCCESS="login_success";
}
