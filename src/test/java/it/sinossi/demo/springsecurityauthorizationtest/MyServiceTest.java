/*
 * Sinossi Software srl - http://www.sinossi.it - http://sinossisoftware.blogspot.com/
 */
package it.sinossi.demo.springsecurityauthorizationtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@ContextConfiguration("classpath:META-INF/spring/applicationContext*.xml")
@Listeners(AuthenticationListener.class)
public class MyServiceTest extends AbstractTestNGSpringContextTests
{

	@Autowired
	private MyService myService;

	@Test
	@Authenticate(username = "pippo", roles = {"ADMIN" })
	public void testCallHelloAsAdmin()
	{
		myService.hello();
	}

	@Test
	@Authenticate(username = "pippo", roles = {"USER" })
	public void testCallHelloAsUser()
	{
		myService.hello();
	}

	@Test
	@Authenticate(username = "pippo", roles = {"ADMIN" })
	public void testCallByeAsAdmin()
	{
		myService.bye();
	}

	@Test(expectedExceptions = AccessDeniedException.class)
	@Authenticate(username = "pippo", roles = {"USER" })
	public void testCallByeAsUser()
	{
		myService.bye();
	}
}
