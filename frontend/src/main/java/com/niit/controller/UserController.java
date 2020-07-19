package com.niit.controller;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.niit.dao.UserDAO;
import com.niit.model.Category;
import com.niit.model.User;

@Controller
public class UserController {

	@Autowired
	UserDAO userDAO;
	
	@RequestMapping("/login_success")
	public String loginProcess(HttpSession session,Model m) {
		
		String page=null;
		boolean loggedIn=true;
		
		SecurityContext securityContext = SecurityContextHolder.getContext();
		
		Authentication authentication = securityContext.getAuthentication();
		
		String username = authentication.getName();
		
		Collection<GrantedAuthority> roles = (Collection<GrantedAuthority>)authentication.getAuthorities();
		
		for(GrantedAuthority role:roles) {
			
			String roleName = role.getAuthority();
			session.setAttribute("loggedIn", loggedIn);
			session.setAttribute("username", username);
			session.setAttribute("role", roleName);
			if(roleName.equals("ROLE_USER")) 
			{
				page="UserHome";
			}
			else 
			{
				page="AdminHome";
			}
		}
		return page;
	}
	
	@RequestMapping("/perform_logout")
	public String loggingOut(HttpSession session,Model m) {
		
		session.invalidate();
		m.addAttribute("errorInfo", "Logout successfully!");
		
		return "Home";
	}

	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public String addUser(@RequestParam("userName")String userName,@RequestParam("password")String password,
			@RequestParam("customerName")String customerName,@RequestParam("emailID")String emailID,
			@RequestParam("mobileNo")String mobileNo,Model m) {
		
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setCustomerName(customerName);
		user.setEmailID(emailID);
		user.setMobileNo(mobileNo);
		user.setRole("ROLE_USER");
		user.setEnabled("true");
		
		userDAO.registerUser(user);
		
		List<User> listUser = userDAO.listUser();
		m.addAttribute("userList", listUser);
		
		m.addAttribute("Login Info","Thank you! Registered Successfully");
		
		return "Login";
		
	}
	
}
