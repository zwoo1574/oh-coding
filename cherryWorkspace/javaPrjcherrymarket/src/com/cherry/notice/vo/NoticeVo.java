package com.cherry.notice.vo;

public class NoticeVo {
	private String no;
	private String title;
	private String content;
	private String managerNo;
	private String writerNick;	
	private String hit;
	private String enrolldate;
	private String editDate;
	private String secretYn;
	
	public NoticeVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeVo(String no, String title, String content, String managerNo, String writerNick, String hit,
			String enrolldate, String editDate, String secretYn) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.managerNo = managerNo;
		this.writerNick = writerNick;
		this.hit = hit;
		this.enrolldate = enrolldate;
		this.editDate = editDate;
		this.secretYn = secretYn;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getManagerNo() {
		return managerNo;
	}

	public void setManagerNo(String managerNo) {
		this.managerNo = managerNo;
	}

	public String getWriterNick() {
		return writerNick;
	}

	public void setWriterNick(String writerNick) {
		this.writerNick = writerNick;
	}

	public String getHit() {
		return hit;
	}

	public void setHit(String hit) {
		this.hit = hit;
	}

	public String getEnrolldate() {
		return enrolldate;
	}

	public void setEnrolldate(String enrolldate) {
		this.enrolldate = enrolldate;
	}

	public String getEditDate() {
		return editDate;
	}

	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}

	public String getSecretYn() {
		return secretYn;
	}

	public void setSecretYn(String secretYn) {
		this.secretYn = secretYn;
	}

	@Override
	public String toString() {
		return "NoticeVo [no=" + no + ", title=" + title + ", content=" + content + ", managerNo=" + managerNo
				+ ", writerNick=" + writerNick + ", hit=" + hit + ", enrolldate=" + enrolldate + ", editDate="
				+ editDate + ", secretYn=" + secretYn + "]";
	}
	
	
	

}
