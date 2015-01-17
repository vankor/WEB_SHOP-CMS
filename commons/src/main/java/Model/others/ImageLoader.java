package Model.others;

import java.io.File;
import java.io.IOException;

import Model.dto_beans.FileEntityBean;
import Model.dto_beans.SliderBean;
import Model.dto_beans.SliderImageBean;
import Model.entity.FileEntity;
import Model.entity.Image;
import Model.entity.SliderImage;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class ImageLoader extends Loader{
	
	private FileEntityBean bean;
	private FileEntity fileentity;

	private String keyfolder;
	
	public String loadimage(CommonsMultipartFile f, SliderImageBean imb){
		String databasepath = "";

		System.out.println("gerty    "+f!=null && !f.isEmpty());
		if(f!=null && !f.isEmpty()){
		System.out.println(f.getName() + "  " + f.getSize());
		String path = rootpath+keyfolder+"/"+fldrid+"/img"+filenumber+".jpg";
		System.out.println(path);
		databasepath = "/"+keyfolder+"/"+fldrid+"/img"+filenumber+".jpg";
		System.out.println(databasepath);
		try {
			File file = new File(path);
			if(file.exists() && file.isFile()) file.delete();
			System.out.println(path);

			f.transferTo(new File(path));
			SliderImage slim = new SliderImage();
			slim.fillfrombean(imb);
			slim.setUrl(databasepath);
			fileentity.addImage(slim);
			filenumber++;

		} catch (IOException e) {
			e.printStackTrace();
			
		}
		}
		return databasepath;
	}

	public static String loadthumb(CommonsMultipartFile thumb, Integer fldrid, String keyfolder){
		try {
			String path = "";
			
			System.out.println("Тип файла "+getFormat(thumb));
			File folder = new File(rootpath+keyfolder+"/"+fldrid);
			if(!folder.exists() || !folder.isDirectory()){ folder.mkdirs();}
			File file = new File(rootpath+keyfolder+"/"+fldrid+"/thumb.jpg");
			if(file.exists() && file.isFile()) {
				file.delete();
				
				System.out.println("File deleted!! "+rootpath+keyfolder+"/"+fldrid+"/thumb.jpg");};
			thumb.transferTo(new File(rootpath+keyfolder+"/"+fldrid+"/thumb.jpg"));
			path = "/"+keyfolder+"/"+fldrid+"/thumb.jpg";



			return path;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	private static String getFormat(CommonsMultipartFile thumb){
		String type = thumb.getContentType();
		String format = type.split("/")[1];
		System.out.println("frfr "+format);
		if(format.equals("jpeg"))format = format.replace("e", "");
		return type;
		
	}
	
	public ImageLoader(FileEntityBean bean, FileEntity fileentity,
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

		
		return true;
	}

	@Override
	public boolean moveFile() {
		CommonsMultipartFile thumb;

		fileentity.setThumb("/"+keyfolder+"/"+fldrid+"/thumb.jpg");
		
		if((thumb=bean.getNewthumb())!=null && bean.getNewthumb().getFileItem().getSize()>0){
			
		
		try {
			File folder = new File(rootpath+keyfolder+"/"+fldrid);

			if(!folder.exists() || !folder.isDirectory()){ folder.mkdirs();}
			
			File file = new File(rootpath+keyfolder+"/"+fldrid+"/thumb.jpg");
			if(file.exists() && file.isFile()) {
				file.delete();
				System.out.println("File deleted!! "+rootpath+keyfolder+"/"+fldrid+"/thumb.jpg");};
			thumb.transferTo(new File(rootpath+keyfolder+"/"+fldrid+"/thumb.jpg"));
	


			fileentity.setThumb("/"+keyfolder+"/"+fldrid+"/thumb.jpg");
			
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
		}
		
		if(bean instanceof SliderBean){
			SliderBean slbean = (SliderBean)bean;
			for(SliderImageBean imb: slbean.getImages()){
				if(imb.getId()==null){
				CommonsMultipartFile f = imb.getNewimage();
				System.out.println("gerty    "+f!=null && !f.isEmpty());
				if(f!=null && !f.isEmpty()){
				System.out.println(f.getName() + "  " + f.getSize());
				String path = rootpath+keyfolder+"/"+fldrid+"/img"+filenumber+".jpg";
				System.out.println(path);
				String databasepath = "/"+keyfolder+"/"+fldrid+"/img"+filenumber+".jpg";
				System.out.println(databasepath);
				try {
					File file = new File(path);
					if(file.exists() && file.isFile()) file.delete();
					System.out.println(path);

					f.transferTo(new File(path));
					SliderImage slim = new SliderImage();
					slim.fillfrombean(imb);
					slim.setUrl(databasepath);
					fileentity.addImage(slim);
					filenumber++;

				} catch (IOException e) {
					e.printStackTrace();
					return false;
				}
				}
				}
			}
		}
		else{
		if(bean.getNewimages()!=null){	
		for(CommonsMultipartFile f: bean.getNewimages()){
			if(f!=null){
			System.out.println(f.getName() + "  " + f.getSize());
			String path = rootpath+keyfolder+"/"+fldrid+"/img"+filenumber+".jpg";
			System.out.println(path);
			String databasepath = "/"+keyfolder+"/"+fldrid+"/img"+filenumber+".jpg";
			System.out.println(databasepath);
			try {
				File file = new File(path);
				if(file.exists() && file.isFile()) file.delete();
				f.transferTo(new File(path));
				fileentity.addImage(new Image(databasepath));
				filenumber++;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
			}
		}
		}
		}

		return true;
	}

}
