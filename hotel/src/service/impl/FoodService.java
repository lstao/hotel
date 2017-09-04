package service.impl;

import dao.IFoodDao;
import entity.FoodDetail;
import factory.BeanFactory;
import service.IFoodService;
import utils.PageBean;

public class FoodService implements IFoodService {
	//´´½¨Dao
	IFoodDao foodDao=BeanFactory.getInstance("foodDao", IFoodDao.class);
	
	public void getAll(PageBean<FoodDetail> pb) {
		try {
			foodDao.getAll(pb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}


	public FoodDetail findById(int id) {
		try {
			return foodDao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new RuntimeException();
		}
	}

}
