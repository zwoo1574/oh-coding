package com.cherry.trade.vo;

public class TradeVo {


	private String boardNo;
	private String memberNo;
	private String areasCode;
	private String title;
	private String tradeAreas;
	private String content;
	private String completeYn;
	private String delYn;
	private String product;
	private String price;
	private String enrollDate;
	private String editDate;
	private String memberName;
	private String areasName;
	private String memberNick;
	
	
	
	public String getMemberNick() {
		return memberNick;
	}
	public void setMemberNick(String memberNick) {
		this.memberNick = memberNick;
	}
	public String getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(String boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getAreasCode() {
		return areasCode;
	}
	public void setAreasCode(String areasCode) {
		this.areasCode = areasCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTradeAreas() {
		return tradeAreas;
	}
	public void setTradeAreas(String tradeAreas) {
		this.tradeAreas = tradeAreas;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCompleteYn() {
		return completeYn;
	}
	public void setCompleteYn(String completeYn) {
		this.completeYn = completeYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}
	public String getEditDate() {
		return editDate;
	}
	public void setEditDate(String editDate) {
		this.editDate = editDate;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getAreasName() {
		return areasName;
	}
	public void setAreasName(String areasName) {
		this.areasName = areasName;
	}
	public TradeVo() {
		super();
	}
	public TradeVo(String boardNo, String memberNo, String areasCode, String title, String tradeAreas, String content,
			String completeYn, String delYn, String product, String price, String enrollDate, String editDate,
			String memberName, String areasName, String memberNick) {
		super();
		this.boardNo = boardNo;
		this.memberNo = memberNo;
		this.areasCode = areasCode;
		this.title = title;
		this.tradeAreas = tradeAreas;
		this.content = content;
		this.completeYn = completeYn;
		this.delYn = delYn;
		this.product = product;
		this.price = price;
		this.enrollDate = enrollDate;
		this.editDate = editDate;
		this.memberName = memberName;
		this.areasName = areasName;
		this.memberNick = memberNick;
	}
	@Override
	public String toString() {
		return "TradeVo [boardNo=" + boardNo + ", memberNo=" + memberNo + ", areasCode=" + areasCode + ", title="
				+ title + ", tradeAreas=" + tradeAreas + ", content=" + content + ", completeYn=" + completeYn
				+ ", delYn=" + delYn + ", product=" + product + ", price=" + price + ", enrollDate=" + enrollDate
				+ ", editDate=" + editDate + ", memberName=" + memberName + ", areasName=" + areasName + ", memberNick="
				+ memberNick + "]";
	}

	
	
}
