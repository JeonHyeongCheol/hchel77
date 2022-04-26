package co.kr.auction.model;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


public class FileList {
	
	private List<MultipartFile> filelist;
	
	public void addList(MultipartFile file) {
		filelist.add(file);
	}
	
	public int listSize() {
		return filelist.size();
	}

	public List<MultipartFile> getFilelist() {
		return filelist;
	}


	public void listname() {
		for (int i = 0; i < filelist.size(); i++) {
			System.out.println(filelist.get(i));
		}
	}
	
	

}
