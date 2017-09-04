package service.impl;
/*
 * 3.业务逻辑层接口实现
 */
import java.util.List;

import javax.management.RuntimeErrorException;

import service.IFoodTypeService;
import dao.IFoodTypeDao;
import dao.impl.FoodTypeDao;
import entity.FoodType;
import factory.BeanFactory;

public class FoodTypeService implements IFoodTypeService {

	//调用dao
	//工厂创建对象
	private IFoodTypeDao foodTypeDao=BeanFactory.getInstance("foodType", IFoodTypeDao.class);
	
	//private IFoodTypeDao foodTypeDao=new FoodTypeDao();
	public void save(FoodType foodType) {
		try {
			foodTypeDao.save(foodType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	public void update(FoodType foodType) {
		try {
			foodTypeDao.update(foodType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	public void delete(int id) {
		try {
			foodTypeDao.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	public List<FoodType> getAll() {
		try {
			return foodTypeDao.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	public List<FoodType> getAll(String typeName) {
		try {
			return foodTypeDao.getAll(typeName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	public FoodType findById(int id) {
		try {
			return foodTypeDao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

}
