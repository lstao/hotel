package service;

import java.util.List;

import entity.FoodDetail;

/*
 * 3.��ϵ���߼������
 */
public interface IFoodDetailService {
	
	
	//����
	void update(FoodDetail foodDetail);
	//ɾ��
	void delete(int id);
	//���
	void save(FoodDetail foodDetail);
	//��ѯȫ��
	List<FoodDetail> getAll();
	//����������ѯ
	FoodDetail findById(int id);
	//��������ģ����ѯ
	List<FoodDetail> getAll(String typeName);
}
