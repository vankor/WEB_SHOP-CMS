package Model.others;

import Model.dto_beans.FileEntityBean;
import Model.entity.FileEntity;

public interface LoaderFactory {
	

	public Loader createLoader(FileEntityBean bean, FileEntity fileentity, Integer fldrid, Integer filenumber);

}
