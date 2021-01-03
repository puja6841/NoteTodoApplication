package com.candidjava.spring.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="todo", catalog="mydb")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Todo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;

	//@Size(max=50)
	@Column(name="tasktitle")
	private String tasktitle;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTasktitle() {
		return tasktitle;
	}

	public void setTasktitle(String tasktitle) {
		this.tasktitle = tasktitle;
	}

	public Todo(long id, String tasktitle) {
		super();
		this.id = id;
		this.tasktitle = tasktitle;
	}

	public Todo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	
	
	

	

}
