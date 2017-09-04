package entity;

import java.util.Date;

public class DinnerTable {
	private int id;//²Í×ÀÖð½¥
	private String tableName;
	private int tableStatus;
	private Date tableDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(int tableStatus) {
		this.tableStatus = tableStatus;
	}
	public Date getTableDate() {
		return tableDate;
	}
	public void setTableDate(Date tableDate) {
		this.tableDate = tableDate;
	}
	
	
}
