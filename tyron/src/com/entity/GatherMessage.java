package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GatherMessage")
public class GatherMessage {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "creator_id")
	private Long creatorId;
	@Column(name = "start_time")
	private Date startTime;
	@Column(name = "end_time")
	private Date endTime;
	@Column(name = "content")
	private String content;
	@Column(name = "location_content")
	private String locationContent;
	@Column(name = "location_x")
	private String locationX;
	@Column(name = "location_y")
	private String locationY;
	@Column(name = "state")
	private Integer state;
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
	public Long getCreatorId()
	{
		return creatorId;
	}
	public void setCreatorId(Long creatorId)
	{
		this.creatorId = creatorId;
	}
	public Date getStartTime()
	{
		return startTime;
	}
	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}
	public Date getEndTime()
	{
		return endTime;
	}
	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public String getLocationContent()
	{
		return locationContent;
	}
	public void setLocationContent(String locationContent)
	{
		this.locationContent = locationContent;
	}
	public String getLocationX()
	{
		return locationX;
	}
	public void setLocationX(String locationX)
	{
		this.locationX = locationX;
	}
	public String getLocationY()
	{
		return locationY;
	}
	public void setLocationY(String locationY)
	{
		this.locationY = locationY;
	}
	public Integer getState()
	{
		return state;
	}
	public void setState(Integer state)
	{
		this.state = state;
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
