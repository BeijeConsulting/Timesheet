package it.beije.mgmt.entity;

import java.sql.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Table(name = "attachment")
@Entity
public class Attachment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="description")
	private String description;
	
	@Column(name="doc_type")
	private String docType;
	
	@Column(name="path")
	private String path;
	
	@Column(name="upload_date")
	private Date uploadDate;
	
	@Column(name="user_id")
	private Long userId;
	
	
	
	public Attachment() {
		
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getDocType() {
		return docType;
	}


	public void setDocType(String docType) {
		this.docType = docType;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public Date getUploadDate() {
		return uploadDate;
	}


	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public String toString() {
		StringBuilder row = new StringBuilder();
		row.append("Id : ").append(id);
		row.append("description : ").append(description);
		row.append("doc_type : ").append(docType);
		row.append("path : ").append(path);
		row.append("upload_date : ").append(uploadDate);
		row.append("user_id : ").append(userId);
		return row.toString();
	}
	
	
	
}
