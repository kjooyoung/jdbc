package com.douzone.jdbc.bookshop.vo;

public class BookVo {
	private long no;
	private String title;
	private String status;
	private long authorNo;
	private String authorName;
	
	public BookVo() {}

	public BookVo(long no, String title, String status, long authorNo, String authorName) {
		super();
		this.no = no;
		this.title = title;
		this.status = status;
		this.authorNo = authorNo;
		this.authorName = authorName;
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getAuthorNo() {
		return authorNo;
	}

	public void setAuthorNo(long authorNo) {
		this.authorNo = authorNo;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "BookVo [no=" + no + ", title=" + title + ", status=" + status + ", authorNo=" + authorNo
				+ ", authorName=" + authorName + "]";
	}
	
}
