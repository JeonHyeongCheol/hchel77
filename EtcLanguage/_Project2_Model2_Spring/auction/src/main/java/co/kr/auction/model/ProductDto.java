package co.kr.auction.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductDto {
	
	private int p_bun, p_price, p_sprice, p_count, p_like, p_state, p_category, p_tag;
	private String p_memid,p_name,p_title,p_sdate, p_edate, p_photo , p_content, picture;
	private String nowprice, count;
	private MultipartFile file1, file2, file3, file4;
	
	public int getP_category() {
		return p_category;
	}
	public void setP_category(int p_category) {
		this.p_category = p_category;
	}
	public int getP_tag() {
		return p_tag;
	}
	public void setP_tag(int p_tag) {
		this.p_tag = p_tag;
	}
	public String getNowprice() {
		return nowprice;
	}
	public void setNowprice(String nowprice) {
		this.nowprice = nowprice;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getP_state() {
		return p_state;
	}
	public void setP_state(int p_state) {
		this.p_state = p_state;
	}
	public MultipartFile getFile1() {
		return file1;
	}
	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}
	public MultipartFile getFile2() {
		return file2;
	}
	public void setFile2(MultipartFile file2) {
		this.file2 = file2;
	}
	public MultipartFile getFile3() {
		return file3;
	}
	public void setFile3(MultipartFile file3) {
		this.file3 = file3;
	}
	public MultipartFile getFile4() {
		return file4;
	}
	public void setFile4(MultipartFile file4) {
		this.file4 = file4;
	}
	public int getP_bun() {
		return p_bun;
	}
	public void setP_bun(int p_bun) {
		this.p_bun = p_bun;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_sprice() {
		return p_sprice;
	}
	public void setP_sprice(int p_sprice) {
		this.p_sprice = p_sprice;
	}
	public int getP_count() {
		return p_count;
	}
	public void setP_count(int p_count) {
		this.p_count = p_count;
	}
	public int getP_like() {
		return p_like;
	}
	public void setP_like(int p_like) {
		this.p_like = p_like;
	}
	public String getP_memid() {
		return p_memid;
	}
	public void setP_memid(String p_memid) {
		this.p_memid = p_memid;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
	public String getP_sdate() {
		return p_sdate;
	}
	public void setP_sdate(String p_sdate) {
		this.p_sdate = p_sdate;
	}
	public String getP_edate() {
		return p_edate;
	}
	public void setP_edate(String p_edate) {
		this.p_edate = p_edate;
	}
	public String getP_photo() {
		return p_photo;
	}
	public void setP_photo(String p_photo) {
		this.p_photo = p_photo;
	}
	public String getP_content() {
		return p_content;
	}
	public void setP_content(String p_content) {
		this.p_content = p_content;
	}

}