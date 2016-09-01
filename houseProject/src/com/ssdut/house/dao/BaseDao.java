package com.ssdut.house.dao;

import java.util.ArrayList;
import java.util.List;

import com.ssdut.house.entities.Case;
import com.ssdut.house.tools.JDBCUtils;

public class BaseDao {
	public static JDBCUtils utils=new JDBCUtils();
	/**
	 * 根据id获取案件对象
	 * @param id
	 * @return
	 */
	public Case getModelById(String id){
		utils.getConnection();
		String sql="select * from allcase where id=?";
		Case c = null;
		List<Object> params=new ArrayList<Object>();
		params.add(id);
		try{
			c=(Case) utils.findSimpleRefResult(sql, params, Case.class);
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
			utils.releaseConn();
		}	
		return c;
	}
}
