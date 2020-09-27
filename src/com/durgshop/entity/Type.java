package com.durgshop.entity;

/**
 * @author 刘志文
 * @version 创建时间：2020年7月11日 上午10:42:09
 * @ClassName 类名称
 * @Description 类描述
 */
public class Type {
	private Integer typeNo;
	private String typeName;
	private Integer superiorNo;
	
	public Type() {
		super();
	}

	public Type(Integer typeNo, String typeName, Integer superiorNo) {
		super();
		this.typeNo = typeNo;
		this.typeName = typeName;
		this.superiorNo = superiorNo;
	}

	public Integer getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(Integer typeNo) {
		this.typeNo = typeNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Integer getSuperiorNo() {
		return superiorNo;
	}

	public void setSuperiorNo(Integer superiorNo) {
		this.superiorNo = superiorNo;
	}

	@Override
	public String toString() {
		return "Type [typeNo=" + typeNo + ", typeName=" + typeName + ", superiorNo=" + superiorNo + "]";
	}

	
	
	

	
	
	
	

}
