package com.yed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yed.bean.User;
import com.yed.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/save")
	public String save(ModelMap map){
		User user = new User();
		user.setName("new");
		user.setPwd("new");
		userService.save(user);
		map.addAttribute("list", userService.list());
		return "/list";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(ModelMap map, @PathVariable Long id){
		userService.deleteByPrimaryKey(id);
		map.addAttribute("list", userService.list());
		return "/list";
	}
	
	@RequestMapping(value="/list")
	public String list(ModelMap map){
		map.addAttribute("list", userService.list());
		return "/list";
	}
	@RequestMapping("/Test")
	public String test(){
		return "/Test";
	}
}
