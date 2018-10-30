package pack.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {

	@Autowired
	private FileValidator fileValidator;
	
	@RequestMapping(value = "upload", method = RequestMethod.GET)
	public String getUploadform(@ModelAttribute("uploadFile") UploadFile uploadFile) {
		return "uploadform";
	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public ModelAndView fileUploadform(@ModelAttribute UploadFile uploadFile, BindingResult result) {
		System.out.println(uploadFile);

		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		// Error 검사
		MultipartFile file = uploadFile.getFile();
		fileValidator.validate(uploadFile, result); // 업로드할 파일과 result 값.
		
		String fileName = file.getOriginalFilename(); // 들어오는 파일 Name.
		
		if(result.hasErrors()) { // 에러가 있을 시.
			return new ModelAndView("uploadform"); // 에러시 uploadform으로 다시 감.
		}
		
		try {
			inputStream = file.getInputStream();
			
			// 파일은 입출력이 나뉘어져 있음.
			File newFile = new File("C:/work/upload/" + fileName);
			if(!newFile.exists()) { // 있는지 확인. 없으면 만듬.
				newFile.createNewFile(); // 새로 만듬.
			}
			
			outputStream = new FileOutputStream(newFile);
			int read = 0;
			byte[] bytes = new byte[1024]; // 1byte 단위로 설정
			
			while((read = inputStream.read(bytes)) != -1) { // 읽음. 끝이 아닌동안
				outputStream.write(bytes, 0, read); // bytes 단위로 0부터 시작하여 읽음.
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return new ModelAndView("uploadFile", "filename", fileName);
	}
}
