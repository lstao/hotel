package dao;

import java.util.List;

import entity.FoodDetail;

/*
 * 1.��ϵdao�����ݽӿڲ����
 */
public interface IFoodDetailDao {
	/*
	 * ���
	 */
	void save(FoodDetail foodDetail);
	//����
		void update(FoodDetail foodDetail);
	/*
	 * ɾ��,���ݱ��
	 */
	void delete(int id);
	
	//��ѯȫ��
	List<FoodDetail> getAll();
	
	//���ݲ�ϵ���Ʋ�ѯ
	List<FoodDetail> getAll(String typeName);
	//����������ѯ
	FoodDetail findById(int id);
	
}
