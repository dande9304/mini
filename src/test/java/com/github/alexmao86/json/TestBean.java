package com.github.alexmao86.json;

import java.util.Collection;
import java.util.Map;

public class TestBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private boolean bol;
	private byte by;
	private char ch;
	private short sho;
	private int in;
	private long lon;
	private float flo;
	private double dou;
	private byte[] byArray;
	private int styleNum;
	private boolean[] bolArray;
	private char[] charArray;
	private double[] doubleArray;
	private float[] floatArray;
	private int[] intArray;
	private long[] longArray;
	private short[] shortArray;
	private String[] strArray;
	private Map<String, String> map;
	private Collection<String> col;

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Collection getCol() {
		return col;
	}

	public void setCol(Collection col) {
		this.col = col;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public String[] getStrArray() {
		return strArray;
	}

	public void setStrArray(String[] strArray) {
		this.strArray = strArray;
	}

	public boolean[] getBolArray() {
		return bolArray;
	}

	public void setBolArray(boolean[] bolArray) {
		this.bolArray = bolArray;
	}

	public char[] getCharArray() {
		return charArray;
	}

	public void setCharArray(char[] charArray) {
		this.charArray = charArray;
	}

	public double[] getDoubleArray() {
		return doubleArray;
	}

	public void setDoubleArray(double[] doubleArray) {
		this.doubleArray = doubleArray;
	}

	public float[] getFloatArray() {
		return floatArray;
	}

	public void setFloatArray(float[] floatArray) {
		this.floatArray = floatArray;
	}

	public int[] getIntArray() {
		return intArray;
	}

	public void setIntArray(int[] intArray) {
		this.intArray = intArray;
	}

	public long[] getLongArray() {
		return longArray;
	}

	public void setLongArray(long[] longArray) {
		this.longArray = longArray;
	}

	public short[] getShortArray() {
		return shortArray;
	}

	public void setShortArray(short[] shortArray) {
		this.shortArray = shortArray;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getStyleNum() {
		return styleNum;
	}

	public void setStyleNum(int styleNum) {
		this.styleNum = styleNum;
	}

	public byte[] getByArray() {
		return byArray;
	}

	public void setByArray(byte[] byArray) {
		this.byArray = byArray;
	}

	// public boolean isBol() {
	// return bol;
	// }
	//
	//
	//
	//
	// public void setBol(boolean bol) {
	// this.bol = bol;
	// }

	public byte getBy() {
		return by;
	}

	public void setBy(byte by) {
		this.by = by;
	}

	public char getCh() {
		return ch;
	}

	public void setCh(char ch) {
		this.ch = ch;
	}

	public short getSho() {
		return sho;
	}

	public void setSho(short sho) {
		this.sho = sho;
	}

	public int getIn() {
		return in;
	}

	public void setIn(int in) {
		this.in = in;
	}

	public long getLon() {
		return lon;
	}

	public void setLon(long lon) {
		this.lon = lon;
	}

	public float getFlo() {
		return flo;
	}

	public void setFlo(float flo) {
		this.flo = flo;
	}

	public double getDou() {
		return dou;
	}

	public void setDou(double dou) {
		this.dou = dou;
	}

}
