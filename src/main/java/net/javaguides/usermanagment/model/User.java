package net.javaguides.usermanagment.model;
import java.util.Date;

public class User {
	private int id;
	private String name;
	private Date dob;
	private Date doj;
     
	
	
	public User(int id, String name, Date dob, Date doj) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.doj = doj;
	}
	
	
	public User(String name, Date dob, Date doj) {
		super();
		this.name = name;
		this.dob = dob;
		this.doj = doj;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}
	
	}
