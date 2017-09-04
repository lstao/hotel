package service.impl;

import java.util.List;

import javax.management.RuntimeErrorException;

import service.IFoodDetailService;
import dao.IFoodDetailDao;
import entity.FoodDetail;
import factory.BeanFactory;

public class FoodDetailService implements IFoodDetailService {
	private IFoodDetailDao foodDetailDao=BeanFactory.getInstance("foodDetailDao", IFoodDetailDao.class);
	
	public void update(FoodDetail foodDetail) {
		try {
			foodDetailDao.update(foodDetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();throw new RuntimeException();
		}
	}

	public void delete(int id) {
		try {
			foodDetailDao.delete(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void save(FoodDetail foodDetail) {
		try {
			foodDetailDao.save(foodDetail);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();throw new RuntimeException();
		}
	}

	public List<FoodDetail> getAll() {
		
		try {
			return foodDetailDao.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	public FoodDetail findById(int id) {
		try {
			return foodDetailDao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

	public List<FoodDetail> getAll(String typeName) {
		try {
			return foodDetailDao.getAll(typeName);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
