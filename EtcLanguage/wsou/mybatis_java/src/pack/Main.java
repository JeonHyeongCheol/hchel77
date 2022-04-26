package pack;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		ProcessData dao = ProcessData.getInstance();
		
		try {
			// 전체 자료 읽기
			List<DataDto> list = dao.readDataAll();
			for(DataDto s:list) {
				System.out.println(s.getCode() + " " + 
						s.getSang() + " " +
						s.getSu() + " " +
						s.getDan() + " ");
			}
			
			System.out.println();
			
			// 자료 하나 읽기
			DataDto dto = dao.readData("1");
			System.out.println(dto.getCode() + " " + dto.getSang() + " " +  dto.getSu() + " " + dto.getDan() + " ");
			
		} catch (Exception e) {
			System.out.println("main err : " + e);
		}
	}
}
