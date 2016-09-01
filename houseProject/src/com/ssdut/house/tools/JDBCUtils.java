package com.ssdut.house.tools;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;



public class JDBCUtils<T> {

	/**
	 * @param args
	 *            锟斤拷锟捷匡拷锟斤拷锟斤拷啵拷锟斤拷拥锟斤拷锟斤拷菘锟轿猰ySQL
	 * 
	 */
	private   String USERNAME = "";//数据连接的用户名
	private   String PASSWORD = "";//密码
	private   String DRIVER = "";//数据库驱动类
	private   String URL = "";//连接URL
	private  Connection connection;
	private  PreparedStatement preparedStatement;// 
	private ResultSet resultSet;// 返回结果集

	public JDBCUtils() {
		try {
			InputStream fis=this.getClass().getResourceAsStream("/db.properties");  //读取db.properties文件
            Properties p=new Properties();  
            p.load(fis);  
              
            DRIVER=p.getProperty("driver_class");      //从db.properties中获得连接数据库的信息
            URL=p.getProperty("driver_url");  
            USERNAME=p.getProperty("database_user");  
            PASSWORD=p.getProperty("database_password");  
      
			Class.forName(DRIVER);//获得DriverClass
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 建立连接
	public  Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			///System.out.println("connect success");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return connection;
	}

	/**
	 * 
	 * @param sql  执行delete ，update，inser
	 * @param params  sql中的占位符所代表的实际数据
	 * @return 成功返回true
	 */
	public   boolean updateByPreparedStatement(String sql, List<Object> params) {
		boolean flag = false;
		try {
			int result = -1;
			preparedStatement = connection.prepareStatement(sql);
			int index = 1;
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					preparedStatement.setObject(index++, params.get(i));
				}
			}
			result = preparedStatement.executeUpdate();
			flag = result > 0 ? true : false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return flag;
	}
	//不建议使用，建议使用后面的findSimpleRefResult
	public Map<String, Object> findSimpleResult(String sql, List<Object> params)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		int index = 1;
		preparedStatement = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				preparedStatement.setObject(index++, params.get(i));
			}

		}
		resultSet = preparedStatement.executeQuery();
		ResultSetMetaData metadata = resultSet.getMetaData();
		int col_len = metadata.getColumnCount();
		while (resultSet.next()) {
			for (int i = 0; i < col_len; i++) {
				String cols_name = metadata.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
		}
		return map;
	}
	//不建议使用，建议使用后面的findMoreRefResult
	public List<Map<String, Object>> findMoreResult(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		preparedStatement = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				preparedStatement.setObject(index++, params.get(i));
			}

		}
		resultSet = preparedStatement.executeQuery();
		ResultSetMetaData metadata = resultSet.getMetaData();
		int col_len = metadata.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();

			for (int i = 0; i < col_len; i++) {
				String cols_name = metadata.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}

	/**
	 * 查询获得一个对象
	 * @param sql  查询语句，只能获得一个查询对象，对象的属性必须和数据库表的字段名完全一样
	 * @param params sql中占位符所对应的实际数据
	 * @param cls  查询对象的类
	 * @return 类对象
	 * @throws Exception
	 */
	public <T> T findSimpleRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		T resultObject = null;
		int index = 1;
		preparedStatement = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				preparedStatement.setObject(index++, params.get(i));
			}

		}
		resultSet = preparedStatement.executeQuery();
		ResultSetMetaData metadata = resultSet.getMetaData();
		int col_len = metadata.getColumnCount();
		while (resultSet.next()) {
			resultObject = cls.newInstance();
			for (int i = 0; i < col_len; i++) {
				String cols_name = metadata.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);
				field.set(resultObject, cols_value);
			}
		}
		return resultObject;
	}
	/**
	 * 查询获得多个对象，放在list结合中
	 * @param sql 查询语句，对象的属性必须和数据库表的字段名完全一样
	 * @param params sql中占位符所对应的实际数据
	 * @param cls 查询对象的类
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findMoreRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		List<T> list = new ArrayList<T>();
		int index = 1;
		preparedStatement = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				preparedStatement.setObject(index++, params.get(i));
			}

		}
		resultSet = preparedStatement.executeQuery();
		ResultSetMetaData metadata = resultSet.getMetaData();
		int col_len = metadata.getColumnCount();
		while (resultSet.next()) {
			T resultObject = cls.newInstance();
			for (int i = 0; i < col_len; i++) {
				String cols_name = metadata.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true);
				field.set(resultObject, cols_value);
			}
			list.add(resultObject);
		}
		return list;
	}
	/**
	 * 关闭连接
	 */
	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
