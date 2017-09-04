package service;

import java.util.List;

import entity.FoodDetail;

/*
 * 3.菜系，逻辑层设计
 */
public interface IFoodDetailService {
	
	
	//更新
	void update(FoodDetail foodDetail);
	//删除
	void delete(int id);
	//添加
	void save(FoodDetail foodDetail);
	//查询全部
	List<FoodDetail> getAll();
	//根据主键查询
	FoodDetail findById(int id);
	//根据名字模糊查询
	List<FoodDetail> getAll(String typeName);
}
