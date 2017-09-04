package dao;

import java.util.List;

import entity.FoodType;

/*
 * ��ϵģ�飬dao�ӿ����
 * 			�����
 */
public interface IFoodTypeDao {
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
