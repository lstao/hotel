package service;

import utils.PageBean;
import entity.FoodDetail;

public interface IFoodService {
	//��ҳ�Ұ�������ѯ���в�Ʒ
	void getAll(PageBean<FoodDetail> pb);
	
	//����id��ѯ��Ʒ
	FoodDetail findById(int id);
}
