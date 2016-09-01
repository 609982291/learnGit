package com.ssdut.house.dao;
import com.ssdut.house.entities.*;

import java.util.ArrayList;
import java.util.List;
public class DictionaryDao extends BaseDao {
	
	/**
	 * 根据类型选择字典表里的数据
	 * @param type
	 * @return
	 */
	public List<Dictionary> getDataByType(String type){
		utils.getConnection();
		String sql="select * from dataDictionary where itemType = ?";
		List<Object> params = new ArrayList<Object>();
		params.add(type);//通过params为占位符赋值
		List<Dictionary> list=null ;
			try {
				list=utils.findMoreRefResult(sql, params, Dictionary.class);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				utils.releaseConn();
			}			
		return list;
	}
}
