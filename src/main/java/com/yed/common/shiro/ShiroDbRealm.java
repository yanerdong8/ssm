package com.yed.common.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.yed.system.model.Resources;
import com.yed.system.model.User;
import com.yed.system.service.ResourcesService;
import com.yed.system.service.UserService;



public class ShiroDbRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	@Autowired
	private ResourcesService resourcesService;
	
	public ShiroDbRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
		// TODO Auto-generated constructor stub
		super(cacheManager, matcher);
	}
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		User shiroUser = (User) principals.getPrimaryPrincipal();
		Resources resources = new Resources();
		resources.setId(shiroUser.getId());
		resources.setrType("2");
		List<Resources> resourcesList = resourcesService.findTByT(resources);
		Set<String> urlSet = new HashSet<String>();
		for (Resources resource : resourcesList) {
			urlSet.add(resource.getrUrl());
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(urlSet);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken userToken = (UsernamePasswordToken)token;
		User user = new User();
		user.setLoginName(userToken.getUsername());
		user = userService.findTByTOne(user);
		//认证缓存信息
		return new SimpleAuthenticationInfo(user, user.getPassword().toCharArray(), getName());
	}

	@Override
	public void onLogout(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
		User shiroUser = (User) principals.getPrimaryPrincipal();
		removeUserCache(shiroUser);
	}

	/**
	 * 清除用户缓存
	 * @param shiroUser
	 */
	public void removeUserCache(User shiroUser){
		removeUserCache(shiroUser.getLoginName());
	}

	/**
	 * 清除用户缓存
	 * @param loginName
	 */
	public void removeUserCache(String loginName){
		SimplePrincipalCollection principals = new SimplePrincipalCollection();
		principals.add(loginName, super.getName());
		super.clearCachedAuthenticationInfo(principals);
	}
}
