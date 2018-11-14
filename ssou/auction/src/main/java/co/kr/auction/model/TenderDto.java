package co.kr.auction.model;

public class TenderDto {
	private int t_no, t_price;
	private String t_memid, t_date, t_pbun;
	private String max, count, p_bun, p_name, p_memid, p_edate, tmax; // tmax : 마지막 입찰, 판매일자.
	private int p_sprice;
	
	
	
	public String getP_bun() {
		return p_bun;
	}

	public void setP_bun(String p_bun) {
		this.p_bun = p_bun;
	}

	public String getTmax() {
		return tmax;
	}

	public void setTmax(String tmax) {
		this.tmax = tmax;
	}
	
	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}

	public String getP_memid() {
		return p_memid;
	}

	public void setP_memid(String p_memid) {
		this.p_memid = p_memid;
	}

	public String getP_edate() {
		return p_edate;
	}

	public void setP_edate(String p_edate) {
		this.p_edate = p_edate;
	}

	public int getP_sprice() {
		return p_sprice;
	}

	public void setP_sprice(int p_sprice) {
		this.p_sprice = p_sprice;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		this.max = max;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	public int getT_no() {
		return t_no;
	}
	
	public void setT_no(int t_no) {
		this.t_no = t_no;
	}
	
	public String getT_pbun() {
		return t_pbun;
	}
	
	public void setT_pbun(String t_pbun) {
		this.t_pbun = t_pbun;
	}
	
	public int getT_price() {
		return t_price;
	}
	
	public void setT_price(int t_price) {
		this.t_price = t_price;
	}
	
	public String getT_memid() {
		return t_memid;
	}
	
	public void setT_memid(String t_memid) {
		this.t_memid = t_memid;
	}
	
	public String getT_date() {
		return t_date;
	}
	
	public void setT_date(String t_date) {
		this.t_date = t_date;
	}
	
	
}
