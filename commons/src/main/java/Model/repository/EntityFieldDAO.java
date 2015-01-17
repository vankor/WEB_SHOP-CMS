package Model.repository;

import Model.EntityField;

import java.util.List;

public interface EntityFieldDAO extends GenericDAO<EntityField, Integer> {
		public List<EntityField> listByClassname(String classname);
		public EntityField getByClassAndField(String classname, String fieldname);
}
