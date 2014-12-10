package Model;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface MediaService<D> {

	@Transactional
	public void addMedia(D f);

	@Transactional
	public List<D> getAllMedias();
	
	@Transactional
	public void deleteMediaById(Integer f);
	
	@Transactional
	public D getMediaById(Integer id);
	
	@Transactional
	public void update(D video);
	
	@Transactional
	public Integer getLastId();
	
}
