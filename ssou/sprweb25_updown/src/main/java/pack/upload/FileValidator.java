package pack.upload;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class FileValidator implements Validator{
	
	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}
	
	@Override
	public void validate(Object uploadFile, Errors errors) {
		UploadFile file = (UploadFile)uploadFile; // 업로드할 파일을 가져옴.
		
		if(file.getFile().getSize() == 0) { // size 값이 0이면
			System.out.println(uploadFile);
			
			errors.rejectValue("file", "업로드할 파일을 선택하시오");
		}

	}
	
}
