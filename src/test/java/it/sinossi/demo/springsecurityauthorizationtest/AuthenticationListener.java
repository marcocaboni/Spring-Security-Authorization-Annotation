/*
 * Sinossi Software srl - http://www.sinossi.it - http://sinossisoftware.blogspot.com/
 */
package it.sinossi.demo.springsecurityauthorizationtest;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;


/**
 * TestNG Listener for secured method testing. Use with {@link Authenticate} annotation.
 * 
 * @author marco.caboni
 */
public class AuthenticationListener implements IInvokedMethodListener
{

	public static final String DEFAULT_USERNAME = "default";

	private static final String FAKE_PASSWORD = "fakePassword";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult)
	{
		if (method.isTestMethod())
		{
			ITestNGMethod testMethod = method.getTestMethod();
			Method javaMethod = testMethod.getMethod();
			Authenticate userAnnotation = javaMethod.getAnnotation(Authenticate.class);
			if (userAnnotation != null)
			{
				String username = userAnnotation.username();
				if (username == null || username.isEmpty())
				{
					username = DEFAULT_USERNAME;
				}
				String[] roles = userAnnotation.roles(); // role may be null/empty
				authenticateUser(username, roles);
			}
		}
	}

	private void authenticateUser(String username, String... roles)
	{
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		if (roles != null)
		{
			for (String role : roles)
			{
				grantedAuthorities.add(new GrantedAuthorityImpl(role));
			}
		}
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, FAKE_PASSWORD,
			grantedAuthorities);
		SecurityContextHolder.getContext().setAuthentication(token);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult)
	{
		SecurityContextHolder.clearContext();
	}

}
