package cl.cmartinezs.marifer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import cl.cmartinezs.marifer.services.UserService;

public class MariferAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication res = isValid(authentication);
        if (!res.isAuthenticated()) {
            throw new BadCredentialsException("Bad credentials");
        }
        return res;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	private Authentication isValid(final Authentication authentication) {
        Authentication res = authentication;
        System.out.println("Selected method: "+((MariferAuthenticationDetails)authentication.getDetails()).getMethod());
        if (userService.isValid(authentication)) {
            res = createSuccessAuthentication(authentication);
        }
        return res;
    }
	
	private Authentication createSuccessAuthentication(final Authentication authentication) {
        final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
        result.setDetails(authentication.getDetails());

        return result;
    }
}
