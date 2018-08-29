package pack;

public class Session_DTO {
	private String name;
	private int jumsu;
	
	public Session_DTO(String name, int jumsu) {
		this.name = name;
		this.jumsu = jumsu;
	}
	
	public String getName() {
		return name;
	}
	
	public int getJumSu() {
		return jumsu;
	}
}
