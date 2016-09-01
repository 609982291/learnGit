package com.ssdut.house.entities;

public class Dictionary {
	private String id;
	private String itemName;
	private String itemType;
	public Dictionary(){}
	public Dictionary(String id,String itemName,String itemType){
		this.id = id;
		this.itemName = itemName;
		this.itemType = itemType;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", itemName=" + itemName
				+ ", itemType=" + itemType + "]";
	}
	
}
