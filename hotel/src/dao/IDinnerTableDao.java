package dao;

import java.util.List;

import entity.DinnerTable;
import entity.TableStatus;

public interface IDinnerTableDao {
	
	//查询所有未预定的餐桌
		 List<DinnerTable> findByStatus(TableStatus status);
	//主键查询
		 
	DinnerTable findById(int id);
	
		 
		 
}
