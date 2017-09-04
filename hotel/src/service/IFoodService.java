package service;

import utils.PageBean;
import entity.FoodDetail;

public interface IFoodService {
	//分页且按条件查询所有菜品
	void getAll(PageBean<FoodDetail> pb);
	
	//根据id查询菜品
	FoodDetail findById(int id);
}
