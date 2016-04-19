package com.tyron.personmanage.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.entity.PersonInfo;
import com.tyron.common.HibernateDao;

public class PersonService
{
	@Autowired
	HibernateDao dao;

	private static char[] seed =
	{ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
			'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
			's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public void savePersonInfo(PersonInfo personInfo)
	{
		dao.save(personInfo);
	}

	public void updatePersonInfo(PersonInfo personInfo)
	{
		dao.update(personInfo);
	}

	public void saveOrUpdatePersonInfo(PersonInfo personInfo)
	{
		dao.saveOrUpdate(personInfo);
	}
	
	private static String getInviteCode()
	{
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < 8; i++)
		{
			sb.append(seed[(int)(Math.random()*53)]);
		}
		return sb.toString();
	}
	public static void main(String[] args)
	{
		System.out.println(getInviteCode());
	}
}
