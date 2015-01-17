package Model.others;

import Model.dto_beans.FileEntityBean;
import Model.entity.FileEntity;

public class ActionImageLoaderFactory implements LoaderFactory {
	

	@Override
	public Loader createLoader(FileEntityBean bean, FileEntity fileentity, Integer fldrid, Integer filenumber) {
		Loader imgloader = new ImageLoader(bean, fileentity, fldrid, filenumber, "actions");
		return imgloader;
	}
}
