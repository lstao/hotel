package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.IDinnerTableDao;
import entity.DinnerTable;
import entity.TableStatus;

public class DinnerTableDao implements IDinnerTableDao {

	public DinnerTable findById(int id) {
		String sql="select * from dinnerTable where id=?"; 
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanHandler<DinnerTable>(DinnerTable.class),id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public List<DinnerTable> findByStatus(TableStatus status) {
		String sql="select * from dinnerTable where tableStatus=?";
		
		
		try {
			return JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<DinnerTable>(DinnerTable.class),status.ordinal());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
}
