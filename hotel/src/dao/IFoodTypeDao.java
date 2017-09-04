package dao;

import java.util.List;

import entity.FoodType;

/*
 * 菜系模块，dao接口设计
 * 			解耦和
 */
public interface IFoodTypeDao {
	/*
	 * 添加
	 */
	void save(FoodType foodType);
	//更新
		void update(FoodType foodType);
	/*
	 * 删除,根据编号
	 */
	void delete(int id);
	
	//查询全部
	List<FoodType> getAll();
	
	//根据菜系名称查询
	List<FoodType> getAll(String typeName);
	//根据主键查询
	FoodType findById(int id);
	
	
}
