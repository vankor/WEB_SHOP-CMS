package Model.others;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy =PositiveValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Positive {

	String message() default "{Значение должно быть позитивным}";
    
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
	
}
