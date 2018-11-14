package co.kr.auction.controller;

import org.springframework.web.multipart.MultipartFile;

public class ProductBean {
	
	private int p_bun, p_price, p_sprice, p_count, p_like, p_state, p_tag;
	private String p_memid,p_name,p_title,p_sdate, p_edate, p_photo , p_content;
	
	private String year1, month1, date1, time1, year2, month2, date2, time2, min1, min2;
	
	private MultipartFile file1, file2, file3, file4;
	
	private String p_category, p_category1, p_category2;
	
	public String getP_category() {
		return p_category;
	}
	public void setP_category(String p_category) {
		this.p_category = p_category;
	}
	public String getP_category1() {
		return p_category1;
	}
	public void setP_category1(String p_category1) {
		this.p_category1 = p_category1;
	}
	public String getP_category2() {
		return p_category2;
	}
	public void set_Pcategory2(String p_category2) {
		this.p_category2 = p_category2;
	}
	public int getP_tag() {
		return p_tag;
	}
	public void setP_tag(int p_tag) {
		this.p_tag = p_tag;
	}
	public int getP_state() {
		return p_state;
	}
	public void setP_state(int p_state) {
		this.p_state = p_state;
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
	public String getMin1() {
		return min1;
	}
	public void setMin1(String min1) {
		this.min1 = min1;
	}
	public String getMin2() {
		return min2;
	}
	public void setMin2(String min2) {
		this.min2 = min2;
	}
	public String getYear1() {
		return year1;
	}
	public void setYear1(String year1) {
		this.year1 = year1;
	}
	public String getMonth1() {
		return month1;
	}
	public void setMonth1(String month1) {
		this.month1 = month1;
	}
	public String getDate1() {
		return date1;
	}
	public void setDate1(String date1) {
		this.date1 = date1;
	}
	public String getTime1() {
		return time1;
	}
	public void setTime1(String time1) {
		this.time1 = time1;
	}
	public String getYear2() {
		return year2;
	}
	public void setYear2(String year2) {
		this.year2 = year2;
	}
	public String getMonth2() {
		return month2;
	}
	public void setMonth2(String month2) {
		this.month2 = month2;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getTime2() {
		return time2;
	}
	public void setTime2(String time2) {
		this.time2 = time2;
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
