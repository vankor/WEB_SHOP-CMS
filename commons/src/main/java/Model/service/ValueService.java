package Model.service;

import Model.repository.ValueDAO;
import Model.entity.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValueService extends ServiceImpl<Value, Integer, ValueDAO> {
	@Autowired
	private ValueDAO propDAO;
	

}
