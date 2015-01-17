package Model.others;

import Model.entity.Category;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CategoryFiledValidator implements ConstraintValidator<CategoryFilled, Category>{

	@Override
	public void initialize(CategoryFilled constraintAnnotation) {

		
	}

	@Override
	public boolean isValid(Category value, ConstraintValidatorContext context) {
		if(value.getId()!=null && value.getId()>0)
		return true;
		else return false;
	}

}
