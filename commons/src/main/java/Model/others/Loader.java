package Model.others;

import Model.dto_beans.SliderImageBean;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public abstract class Loader {
	public static final String rootpath = "D:/Ваня/проги/web-shop/src/main/webapp/resources/images/";
	
	public boolean load(){
		if(createFolder()){
			return moveFile();
		}
		return false;
	}
	
	public abstract boolean moveFile();
	abstract boolean createFolder();
	
	public abstract String loadimage(CommonsMultipartFile f, SliderImageBean imb);
	

}
