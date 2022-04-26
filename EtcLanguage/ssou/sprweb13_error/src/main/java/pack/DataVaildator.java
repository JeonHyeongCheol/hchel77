package pack;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class DataVaildator implements Validator{
	
	@Override
	public boolean supports(Class<?> sup) { // 넘어오는 command를 반환 해줘야 함.
		// 검정 후 다시 command 반환
		return LoginCommand.class.isAssignableFrom(sup); 
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		// target에 대한 검증 지원. 에러 발생 시 Errors 객체에 저장.
		LoginCommand command = (LoginCommand)target; // 검정 자료는 target을 통해 들어옴
		System.out.println(command.getUserid() + " " + command.getPasswd());
		
		// 검증 방법 1
		if(command.getUserid() == null || command.getUserid().trim().isEmpty()) { // ID 값이 없거나, 앞뒤공백을 잘랐는데도 없으면
			errors.rejectValue("userid", "err.userid"); 
			// Err객체에 있는 rejectValue를 사용함 . 
			// userid는 loginform에 sform에 있는 error path를 뜻함. err.userid는 properties에 있음.
			
		}
		
		// 검증 방법 2 - 위에 여러줄을 한줄로 처리 가능.
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwd", "err.passwd");
	}
}
