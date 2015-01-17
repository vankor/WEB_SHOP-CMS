package Model.others;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PositiveValidator implements ConstraintValidator<Positive, Double>{

	@Override
	public void initialize(Positive constraintAnnotation) {

		
	}

	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {


		
		if(value!=null){
			return (value>=0);
		}
		
		

		

		

		return false;
	}

}
