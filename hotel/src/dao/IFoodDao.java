package dao;

import java.util.List;

import utils.PageBean;
import entity.FoodDetail;

/*
 * ��Ʒ����
 */
public interface IFoodDao {
	//��ҳ�Ұ�������ѯ���в�Ʒ
	void getAll(PageBean<FoodDetail> pb);
	
	//������ͳ�Ʋ�Ʒ�ܼ�¼��
	int getTotalCount(PageBean<FoodDetail> pb);
	//����id��ѯ��Ʒ
	FoodDetail findById(int id);
	
}
