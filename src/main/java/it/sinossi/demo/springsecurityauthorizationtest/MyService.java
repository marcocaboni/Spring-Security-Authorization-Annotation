/*
 * Sinossi Software srl - http://www.sinossi.it - http://sinossisoftware.blogspot.com/
 */
package it.sinossi.demo.springsecurityauthorizationtest;

import org.springframework.security.access.prepost.PreAuthorize;


/**
 * @author marco.caboni
 */
public interface MyService
{

	@PreAuthorize("hasRole('USER') OR hasRole('ADMIN')")
	void hello();

	@PreAuthorize("hasRole('ADMIN')")
	void bye();

}
