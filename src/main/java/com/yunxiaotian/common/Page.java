package com.yunxiaotian.common;

/**
 * 分页类
 * @author zhou
 */
import java.util.List;

public class Page<T> {

	private int page;
	private int size;
	private int total;
	private List<T> data;

	public Page() {
		super();
	}

	public Page(int page, int size, int total, List<T> data) {
		super();
		this.page = page;
		this.size = size;
		this.total = total;
		this.data = data;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}