package Model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveValidator implements ConstraintValidator<Positive, Double>{

	@Override
	public void initialize(Positive constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
/*		if(value.getClass().equals(Integer.class)){
			return ((Integer)value)>=0;
		}*/
		
		if(value!=null){
			return (value>=0);
		}
		
		
	/*	if(value.getClass().equals(Long.class)){
			return ((Long)value)>=0;
		}*/
		
	/*	if(value.getClass().equals(Float.class)){
			return ((Float)value)>=0;
		}*/
		
/*		if(value.getClass().equals(Short.class)){
			return ((Short)value)>=0;
		}*/
		return false;
	}

}
