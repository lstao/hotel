package dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import utils.Condition;
import utils.JdbcUtils;
import utils.PageBean;
import dao.IFoodDao;
import entity.FoodDetail;

public class FoodDao implements IFoodDao {

	public void getAll(PageBean<FoodDetail> pb) {
		Condition condition=pb.getCondition();
		//ƾ������
		List<Object> list=new ArrayList<Object>();
		//����֮���id
		int typeId=condition.getFoodTypeId();
		//����֮����
		String foodName=condition.getFoodName();
		String sql="select f.id,f.foodName,f.foodType_id,f.price,f.mprice,f.remark,"
				+ "f.img,t.typeName from food f,"
				+ "foodType t where f.foodType_id=t.id";
		if(typeId>0)
		{
			sql+=" and f.foodType_id=?";
			list.add(typeId);
		}
		if(foodName!=null&&"".equals(foodName))
		{
			sql+=" and f.foodName like ?";
			list.add(foodName);
		}
		/*****��ҳ����*****/
		sql+=" limit ?,?";
		/*****ҳ������***********/
		int totalCount=getTotalCount(pb);
		if(totalCount==0)
		{
			pb.setPageData(null);
			return;
			}
		//�ܼ�¼��
		pb.setTotalCount(totalCount);
		if(pb.getCurrentPage()<1)
		{
			pb.setCurrentPage(1);
		}else if(pb.getCurrentPage()>pb.getTotalPage())
		{
			pb.setCurrentPage(pb.getTotalPage());
		}
		int index=(pb.getCurrentPage()-1)*pb.getPageCount();
		list.add(index);
		list.add(pb.getPageCount());
		//�������Լ���ҳ��ѯ
		try {
			List<FoodDetail> pageData=JdbcUtils.getQueryRunner().query(sql, new BeanListHandler<FoodDetail>(FoodDetail.class),list.toArray());
		
			//��ѯ�������õ���ҳ��
			pb.setPageData(pageData);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
		
	}

	public int getTotalCount(PageBean<FoodDetail> pb) {
		Condition condition=pb.getCondition();
		String sql="select count(*) from food f,"
				+ "foodType t where f.foodType_id=t.id";
		List<Object> list=new ArrayList<Object>();
		//����֮���id
		int typeId=condition.getFoodTypeId();
		//����֮����
		String foodName=condition.getFoodName();
		if(typeId>0)
		{
			sql+=" and f.foodType_id=?";
			list.add(typeId);
		}
		if(foodName!=null&&"".equals(foodName))
		{
			sql+=" and f.foodName like ?";
			list.add(foodName);
		}
		
		try {
			Long num= JdbcUtils.getQueryRunner().query(sql, new ScalarHandler<Long>(),list.toArray());
			return num.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public FoodDetail findById(int id) {
		
		String sql="select f.id,f.foodName,f.foodType_id,f.price,f.mprice,f.remark,"
				+ "f.img,t.typeName from food f,"
				+ "foodType t where f.foodType_id=t.id and f.id=?";
		
				try {
					return JdbcUtils.getQueryRunner().query(
					sql, new BeanHandler<FoodDetail>(FoodDetail.class),id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					throw new RuntimeException();
				}
				
	}

}
