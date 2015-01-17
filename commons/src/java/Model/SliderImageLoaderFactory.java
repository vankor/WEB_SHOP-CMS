package Model;

public class SliderImageLoaderFactory implements LoaderFactory {
	

	@Override
	public Loader createLoader(FileEntityBean bean, FileEntity fileentity, Integer fldrid, Integer filenumber) {
		Loader imgloader = new ImageLoader(bean, fileentity, fldrid, filenumber, "sliders");
		return imgloader;
	}
}

