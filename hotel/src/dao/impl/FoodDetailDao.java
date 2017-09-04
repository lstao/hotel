package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import utils.JdbcUtils;
import dao.IFoodDetailDao;
import entity.FoodDetail;

public class FoodDetailDao implements IFoodDetailDao {

	public void save(FoodDetail foodDetail) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO food(foodName,foodType_id,price,mprice,remark,img) values(?,?,?,?,?,?)";
		try {
			//JdbcUtils.getQueryRunner().update("INSERT INTO food(foodName,foodType_id,price,mprice,remark,img) VALUES('python',2,99,88,'hello world..','cc')");
			JdbcUtils.getQueryRunner().update(sql,foodDetail.getFoodName(),foodDetail.getFoodType_id(),
				foodDetail.getPrice(),foodDetail.getMprice(),foodDetail.getRemark(),foodDetail.getImg());
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public void update(FoodDetail foodDetail) {
		String sql="update food SET foodName=?, foodType_id=?, price=?, mprice=?, remark=?, img=?  WHERE id = ? ";
		//String sql="UPDATE food SET foodName=?  WHERE id = ? ;";
		try{
			String foodName=foodDetail.getFoodName();
			int foodType_id=foodDetail.getFoodType_id();
			double price=foodDetail.getPrice();
			double mprice=foodDetail.getMprice();
			String remark=foodDetail.getRemark();
			String Img=foodDetail.getImg();//foodDetail.getImg();
			int id=foodDetail.getId();
			//JdbcUtils.getQueryRunner().update("UPDATE food SET foodName='Ruby',foodType_id=3,price=199,mprice=99,remark='1035',img='jghj' WHERE id=7");
			JdbcUtils.getQueryRunner().update(sql,foodName,foodType_id,price,mprice,remark,Img,id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from food where id=?";
		try {
			JdbcUtils.getQueryRunner().update(sql,id);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<FoodDetail> getAll() {
		String sql="select food.id,foodName,foodType_id,price,mprice,remark,img,foodType.typeName from food,foodType where food.foodType_id=foodType.id ";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<FoodDetail>(FoodDetail.class));
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public List<FoodDetail> getAll(String typeName) {
		String sql="select * from food where typeName like %?%";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanListHandler<FoodDetail>(FoodDetail.class),typeName);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	public FoodDetail findById(int id) {
		String sql="select *from food where id=?";
		try {
			return JdbcUtils.getQueryRunner().query(sql,new BeanHandler<FoodDetail>(FoodDetail.class),id);
			
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

	

}
