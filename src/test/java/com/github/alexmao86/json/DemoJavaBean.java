package com.github.alexmao86.json;

import java.util.List;

public class DemoJavaBean {
	private transient String feeling;
	private int age;
	private String name;
	private String address;

	private DemoJavaBean father;
	private DemoJavaBean mother;
	private List<DemoJavaBean> children;

	private int[] months = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

	private Boolean[] array = { true, false, true };

	public DemoJavaBean(int age, String name, String address) {
		super();
		this.age = age;
		this.name = name;
		this.address = address;
	}

	public DemoJavaBean() {
		super();
	}

	public final int getAge() {
		return age;
	}

	public final void setAge(int age) {
		this.age = age;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getAddress() {
		return address;
	}

	public final void setAddress(String address) {
		this.address = address;
	}

	public final String getFeeling() {
		return feeling;
	}

	public final void setFeeling(String feeling) {
		this.feeling = feeling;
	}

	public final DemoJavaBean getFather() {
		return father;
	}

	public final void setFather(DemoJavaBean father) {
		this.father = father;
	}

	public final DemoJavaBean getMother() {
		return mother;
	}

	public final void setMother(DemoJavaBean mother) {
		this.mother = mother;
	}

	public final List<DemoJavaBean> getChildren() {
		return children;
	}

	public final void setChildren(List<DemoJavaBean> children) {
		this.children = children;
	}

	public final int[] getMonths() {
		return months;
	}

	public final void setMonths(int[] months) {
		this.months = months;
	}

	public final Boolean[] getArray() {
		return array;
	}

	public final void setArray(Boolean[] array) {
		this.array = array;
	}
}
