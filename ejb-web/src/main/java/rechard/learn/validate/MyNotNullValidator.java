package rechard.learn.validate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class MyNotNullValidator implements ConstraintValidator<MyNotNull, Object> {

    @Override
    public void initialize(MyNotNull constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return value!=null;
    }

}
