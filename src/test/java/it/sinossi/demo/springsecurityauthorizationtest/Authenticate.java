/*
 * Sinossi Software srl - http://www.sinossi.it - http://sinossisoftware.blogspot.com/
 */
package it.sinossi.demo.springsecurityauthorizationtest;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Annotation for secured method testing. Use with {@link AuthenticationListener}.
 * 
 * @author marco.caboni
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Authenticate {

	String username();

	String[] roles() default {};
}
