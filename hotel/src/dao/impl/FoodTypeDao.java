package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.IFoodTypeDao;
import entity.FoodType;

public class FoodTypeDao implements IFoodTypeDao{

	public void save(FoodType foodType) {
		String sql="insert into foodType(typeName) values(?)";
		try {
			JdbcUtils.getQueryRunner().update(sql,foodType.getTypeName());
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void update(FoodType foodType) {
		String sql="update foodType set typeName=? where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,foodType.getTypeName(),foodType.getId());
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void delete(int id) {
		String sql="delete from foodType where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,id);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		
	}

	public List<FoodType> getAll() {
		String sql="select *from foodType";
		try {
			System.out.println("dao²ã");
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class));
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<FoodType> getAll(String typeName) {
		String sql="select * from foodType where typeName like %?%";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<FoodType>(FoodType.class),typeName);
			
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public FoodType findById(int id) {
		String sql="select * from foodType where id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<FoodType>(FoodType.class),id);
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

}
