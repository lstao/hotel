package utils;


import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/*
 * ��װ���ò���
 */
public class JdbcUtils {

	//��ʼ�����ӳ�
	private static DataSource dataSource;
	static{
		dataSource=new ComboPooledDataSource();
		
	}
	public static DataSource getDataSource() {
		return dataSource;
	}
	/*
	 * ����DBUtils���ù��������
	 */
	public static QueryRunner getQueryRunner()
	{
		return new QueryRunner(dataSource);
		
	}
	
}
