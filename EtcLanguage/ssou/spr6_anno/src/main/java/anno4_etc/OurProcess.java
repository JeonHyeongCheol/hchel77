package anno4_etc;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("our")
public class OurProcess {
	@Value("#{dataInfo.name}") // 외부자료와 맵핑. EL과 기능 거의 비슷
	private String name;
	private String part;
	
	// 생성자가 두 개 일 때 어떤 것을 호출하나? 첫번째것을 호출.
	public OurProcess() {
		
	}
	
	@Autowired // Autowired를 사용했기 때문에 이것을 생성자로 사용하게 됨. 객체변수에만 할 수 있는 것이 아니라 생성자에서도 가능.
	public OurProcess(@Value("#{dataInfo.part}") String part) { // 외부 값을 읽어 part에게 넘겨줌. 외부 값을 인정해준다고 생각하면 됨. get메소드를 통해 읽어옴 
		this.part= part;
	}
	
	@Value("#{dataInfo.job}") // public 이기 때문에 가져올 수 있음.
	private String job;
	
	@PostConstruct // 생성자 이후에 초기화 작업 수행.
	public void hi() {
		System.out.println("생성자 이후에 초기화 작업 수행");
	}
	
	@PreDestroy // Destroy 이전에 수행.
	public void bye() {
		System.out.println("마무리 작업 수행");
	}
	
	public void showResult() {
		System.out.println("name : " + name);
		System.out.println("part : " + part);
		System.out.println("job : " + job);
	}
}
