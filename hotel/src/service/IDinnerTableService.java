package service;

import java.util.List;

import entity.DinnerTable;

public interface IDinnerTableService {

	/*��ѯ����δԤ���Ĳ���
	 * 
	 */
	List<DinnerTable> findNoUseTable();
	//����������ѯ
	DinnerTable findByid(int id);
}
