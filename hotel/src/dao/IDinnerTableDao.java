package dao;

import java.util.List;

import entity.DinnerTable;
import entity.TableStatus;

public interface IDinnerTableDao {
	
	//��ѯ����δԤ���Ĳ���
		 List<DinnerTable> findByStatus(TableStatus status);
	//������ѯ
		 
	DinnerTable findById(int id);
	
		 
		 
}
