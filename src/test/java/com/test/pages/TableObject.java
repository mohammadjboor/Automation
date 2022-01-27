package com.test.pages;

public class TableObject {
	private String title;
	private String year;
	
	public TableObject() {
		// TODO Auto-generated constructor stub
	}
	TableObject(String title, String year){
		this.title = title;
		this.year = year;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	

}
