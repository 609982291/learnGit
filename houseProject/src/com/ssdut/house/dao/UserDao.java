package com.ssdut.house.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssdut.house.entities.Case;
import com.ssdut.house.entities.UserInfo;
import com.ssdut.house.tools.createUUIDUtils;

public class UserDao extends BaseDao {
	public List<UserInfo> getAll() {
		utils.getConnection();
		String sql = "select * from userInfo";
		List<UserInfo> list = null;
		try {
			list = utils.findMoreRefResult(sql, null, UserInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			utils.releaseConn();
		}
		return list;
	}

	public List<UserInfo> getDeptAll(String dept) {
		utils.getConnection();
		String sql = "select * from userInfo where depId=?";
		List<Object> params = new ArrayList<Object>();
		params.add(dept);// 通过params为占位符赋值
		List<UserInfo> list = null;
		try {
			list = utils.findMoreRefResult(sql, params, UserInfo.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			utils.releaseConn();
		}
		return list;
	}

	public String loginCheck(String name, String pwd) {
		utils.getConnection();
		String sql = "select * from userInfo where loginName=?";
		List<Object> params = new ArrayList<Object>();
		params.add(name);// 通过params为占位符赋值
		UserInfo user = null;
		try {
			user = (UserInfo) utils.findSimpleRefResult(sql, params,
					UserInfo.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			utils.releaseConn();
		}
		if (user == null) {
			return createUUIDUtils.USERNOTEXIST;

		} else {
			if (user.getLoginPwd().equals(pwd)) {
				return createUUIDUtils.LOGINSUCCESS;
			} else {
				return createUUIDUtils.PWDWRONG;
			}
		}
	}
	public UserInfo getUser(String name, String pwd) {
		utils.getConnection();
		String sql = "select * from userInfo where loginName=?";
		List<Object> params = new ArrayList<Object>();
		params.add(name);// 通过params为占位符赋值
		UserInfo user = null;
		try {
			user = (UserInfo) utils.findSimpleRefResult(sql, params,
					UserInfo.class);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			utils.releaseConn();
		}
		return user;
	}
}
