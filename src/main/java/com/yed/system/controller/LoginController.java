package com.yed.system.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yed.common.controller.BaseController;

@Controller
public class LoginController extends BaseController{
	
	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "system/login";
    }
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object loginPost(String loginName, String password) {
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
        Map<String, Object> map = new HashMap<>();
        try {
            user.login(token);
            map.put("result", "success");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            map.put("result", "error");
            map.put("msg", "账号不存在");
        } catch (DisabledAccountException e) {
            e.printStackTrace();
            map.put("result", "error");
            map.put("msg", "账号未启用");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            map.put("result", "error");
            map.put("msg", "密码错误");
        } catch (RuntimeException e) {
            e.printStackTrace();
            map.put("result", "error");
            map.put("msg", "未知错误,请联系管理员");
        }
        return map;
    }
}
