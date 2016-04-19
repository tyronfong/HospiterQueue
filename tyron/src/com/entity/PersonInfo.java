package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PersonInfo")
public class PersonInfo {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "name")
	private String name;
	@Column(name = "post")
	private Integer post;
	@Column(name = "phone1")
	private String phoneNumber1;
	@Column(name = "phone2")
	private String phoneNumber2;
	@Column(name = "invite_code")
	private String inviteCode;
	@Column(name = "open_id")
	private String openId;
	@Column(name = "company")
	private Integer company;
	@Column(name = "platoon")
	private Integer platoon;
	@Column(name = "squad")
	private Integer squad;
	@Column(name = "status")
	private Integer status;
	public Long getId()
	{
		return id;
	}
	public void setId(Long id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public Integer getPost()
	{
		return post;
	}
	public void setPost(Integer post)
	{
		this.post = post;
	}
	public String getPhoneNumber1()
	{
		return phoneNumber1;
	}
	public void setPhoneNumber1(String phoneNumber1)
	{
		this.phoneNumber1 = phoneNumber1;
	}
	public String getPhoneNumber2()
	{
		return phoneNumber2;
	}
	public void setPhoneNumber2(String phoneNumber2)
	{
		this.phoneNumber2 = phoneNumber2;
	}
	public String getInviteCode()
	{
		return inviteCode;
	}
	public void setInviteCode(String inviteCode)
	{
		this.inviteCode = inviteCode;
	}
	public String getOpenId()
	{
		return openId;
	}
	public void setOpenId(String openId)
	{
		this.openId = openId;
	}
	public Integer getCompany()
	{
		return company;
	}
	public void setCompany(Integer company)
	{
		this.company = company;
	}
	public Integer getPlatoon()
	{
		return platoon;
	}
	public void setPlatoon(Integer platoon)
	{
		this.platoon = platoon;
	}
	public Integer getSquad()
	{
		return squad;
	}
	public void setSquad(Integer squad)
	{
		this.squad = squad;
	}
	public Integer getStatus()
	{
		return status;
	}
	public void setStatus(Integer status)
	{
		this.status = status;
	}

}
