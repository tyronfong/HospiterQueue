package com.tyron.signon.service;

import java.util.HashSet;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.transaction.annotation.Transactional;

import com.entity.User;
import com.tyron.common.HibernateDao;

public class AuthRealm extends AuthorizingRealm {

	private HibernateDao dao;

	@Transactional
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		return new SimpleAuthorizationInfo(new HashSet<String>());
	}

	@Transactional
	@SuppressWarnings("unchecked")
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) {
		UsernamePasswordToken userToken = (UsernamePasswordToken) arg0;
		SimpleAuthenticationInfo saInfo = null;

//		Session session = dao.getSessionFactory().openSession();
//		Query query = session.createQuery("from User where userName = ?");
//		query.setParameter(0, userToken.getUsername());
		List<User> userList = (List<User>) dao.find("from User where userName = ?", userToken.getUsername());
//		@SuppressWarnings("unchecked")
//		List<User> userList = query.list();
		if (userList.size() == 0) {
			throw (new AuthenticationException("login error"));
		}
		if (userList.get(0).getPassword().equals(String.valueOf(userToken.getPassword()))) {
			saInfo = new SimpleAuthenticationInfo(userToken.getPrincipal(), userToken.getPassword(), getName());
			return saInfo;
		} else {
			throw (new AuthenticationException("login error"));
		}
	}

	public HibernateDao getDao()
	{
		return dao;
	}

	public void setDao(HibernateDao dao)
	{
		this.dao = dao;
	}

}
