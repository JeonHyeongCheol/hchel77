package pack;

import org.springframework.stereotype.Component;

public class LoginCommand { // formBean : 폼에서 받아오는 값을 맞춰줘야 함. 그래야지 보낼 수 있음.
	private String userid, passwd;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
}
