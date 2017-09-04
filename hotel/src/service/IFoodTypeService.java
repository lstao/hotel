package service;

import java.util.List;

import entity.FoodType;

/*
 * 3.��ϵҵ���߼��㣬�ӿ����
 */
public interface IFoodTypeService {
	
	/*
	 * ���
	 */
	void save(FoodType foodType);
	//����
		void update(FoodType foodType);
	/*
	 * ɾ��,���ݱ��
	 */
	void delete(int id);
	
	//��ѯȫ��
	List<FoodType> getAll();
	
	//���ݲ�ϵ���Ʋ�ѯ
	List<FoodType> getAll(String typeName);
	//����������ѯ
	FoodType findById(int id);
}
