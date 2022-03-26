package telran.annotation.calidation.constraints;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
/*
 * constraint for nested object validation
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface Valid {

}
