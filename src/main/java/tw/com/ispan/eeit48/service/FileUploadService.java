package tw.com.ispan.eeit48.service;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {
	
	public void uploadFile(MultipartFile file)throws IllegalStateException,IOException {
		file.transferTo(new File("C:\\Users\\user\\Documents\\GitHub\\Final-Project\\back_End\\iboss\\src\\main\\resources\\static\\upload\\"+file.getOriginalFilename()));
		
	}
}
