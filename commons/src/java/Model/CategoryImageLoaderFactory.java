package Model;

import javax.persistence.Transient;

public class CategoryImageLoaderFactory implements LoaderFactory{

	@Override
	public Loader createLoader(FileEntityBean bean, FileEntity fileentity,
			Integer fldrid, Integer filenumber) {
		Loader imgloader = new ImageLoader(bean, fileentity, fldrid, filenumber, "categories");
		return imgloader;
	}

}
