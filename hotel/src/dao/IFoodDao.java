package dao;

import java.util.List;

import utils.PageBean;
import entity.FoodDetail;

/*
 * 菜品管理
 */
public interface IFoodDao {
	//分页且按条件查询所有菜品
	void getAll(PageBean<FoodDetail> pb);
	
	//按条件统计菜品总记录数
	int getTotalCount(PageBean<FoodDetail> pb);
	//根据id查询菜品
	FoodDetail findById(int id);
	
}
