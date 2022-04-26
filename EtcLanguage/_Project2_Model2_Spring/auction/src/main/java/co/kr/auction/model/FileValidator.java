package co.kr.auction.model;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class FileValidator implements Validator{

	@Override
	public boolean supports(Class<?> arg0) {
		return false;
	}

	@Override
	public void validate(Object upload, Errors err) {
		// TODO Auto-generated method stub
		FileDto file = (FileDto)upload;
		
		if(file.getFile().getSize() == 0) {
			err.rejectValue("filenick","", "파일을 선택하세요");
		}
	}

	
	
	
	
}
