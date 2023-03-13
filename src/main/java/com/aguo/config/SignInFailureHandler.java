package com.aguo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@Component
public class SignInFailureHandler implements AuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
										AuthenticationException exception) throws IOException {
		HashMap<String, Integer> map = new HashMap<>();
		if (exception instanceof LockedException){
			map.put("state",1);
		}else if (exception instanceof DisabledException){
			map.put("state",2);
		}else map.put("state",-1);
		request.setAttribute("stateMap",map);
		try {
			request.getRequestDispatcher("/loginError").forward(request,response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}
}
