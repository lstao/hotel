package service.impl;

import java.util.List;

import service.IDinnerTableService;
import dao.IDinnerTableDao;
import entity.DinnerTable;
import entity.TableStatus;
import factory.BeanFactory;

public class DinnerTableService implements IDinnerTableService {

	//µ÷ÓÃDao
	private IDinnerTableDao dinnerDao=BeanFactory.getInstance("dinnerTableDao", IDinnerTableDao.class);
	
	public List<DinnerTable> findNoUseTable() {
		try {
			return dinnerDao.findByStatus(TableStatus.Free);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	public DinnerTable findByid(int id) {
		try {
			return dinnerDao.findById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
