package dao;

import java.util.List;

import entity.FoodDetail;

/*
 * 1.菜系dao，数据接口层设计
 */
public interface IFoodDetailDao {
	/*
	 * 添加
	 */
	void save(FoodDetail foodDetail);
	//更新
		void update(FoodDetail foodDetail);
	/*
	 * 删除,根据编号
	 */
	void delete(int id);
	
	//查询全部
	List<FoodDetail> getAll();
	
	//根据菜系名称查询
	List<FoodDetail> getAll(String typeName);
	//根据主键查询
	FoodDetail findById(int id);
	
}
