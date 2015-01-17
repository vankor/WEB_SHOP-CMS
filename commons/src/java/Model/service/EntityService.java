package Model.service;

import Model.entity.Entity;
import Model.repository.EntityDAO;
import Model.service.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EntityService extends ServiceImpl<Entity, Integer, EntityDAO> {

}
