package Model.others;

import java.io.File;
import java.io.IOException;

import Model.dto_beans.FileEntityBean;
import Model.dto_beans.SliderImageBean;
import Model.entity.FileEntity;
import Model.entity.Video;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class VideoLoader extends Loader{
	
	private FileEntityBean bean;
	private FileEntity fileentity;

	private String keyfolder;
	
	
	public VideoLoader(FileEntityBean bean, FileEntity fileentity,
			Integer fldrid,
			Integer filenumber, String keyfolder) {
	
		this.bean = bean;
		this.fileentity = fileentity;
		this.fldrid = fldrid;
		this.filenumber = filenumber;
		this.keyfolder = keyfolder;
	}


	private Integer fldrid;
	private Integer filenumber;
	
	 

	public FileEntityBean getBean() {
		return bean;
	}

	public void setBean(FileEntityBean bean) {
		this.bean = bean;
	}

	public FileEntity getFileentity() {
		return fileentity;
	}

	public void setFileentity(FileEntity fileentity) {
		this.fileentity = fileentity;
	}


	public Integer getFldrid() {
		return fldrid;
	}

	public void setFldrid(Integer fldrid) {
		this.fldrid = fldrid;
	}

	public Integer getFilenumber() {
		return filenumber;
	}

	public void setFilenumber(Integer filenumber) {
		this.filenumber = filenumber;
	}

	@Override
	public boolean createFolder() {
		System.out.println(rootpath+keyfolder+"/"+fldrid);
		File folder = new File(rootpath+keyfolder+"/"+fldrid);
		if(!folder.exists() || !folder.isDirectory()){ folder.mkdirs();}
		String path;
		System.out.println("–––¿¿¿¿«««ÃÃÃ≈––– "+bean.getNewvideos().size());
		System.out.println(bean.getThumb());
		
		return false;
	}

	@Override
	public boolean moveFile() {
		CommonsMultipartFile thumb;
		if((thumb=bean.getNewthumb())!=null){
			
		
		try {
			thumb.transferTo(new File(rootpath+keyfolder+"/"+fldrid+"/thumb.jpg"));

			fileentity.setThumb("/"+keyfolder+"/"+fldrid+"/thumb.jpg");
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		}
		
		for(CommonsMultipartFile f: bean.getNewvideos()){
			if(f!=null){
			System.out.println(f.getName() + "  " + f.getSize());
			String path = rootpath+keyfolder+"/"+fldrid+"/img"+filenumber+".jpg";
			System.out.println(path);
			String databasepath = "/"+keyfolder+"/"+fldrid+"/img"+filenumber+".jpg";
			System.out.println(databasepath);
			try {
				f.transferTo(new File(path));

				fileentity.addVideo(new Video(databasepath));
				filenumber++;
			} catch (IOException e) {

				e.printStackTrace();
			}
			}
		}
		return false;
	}

	@Override
	public String loadimage(CommonsMultipartFile f, SliderImageBean imb) {
		return keyfolder;

		
	}

}
