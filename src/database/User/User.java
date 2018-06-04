package database.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User {
	private String id;
	private String password;
	private String firstname;
	private String secondname;
	private String phone;
	private String email;
	private String image;
	private Date joindate;

	public User(String id, String password, String firstname, String secondname, String phone, String email,
			String image, Date joindate) {
		this.id = id;
		this.password = password;
		this.firstname = firstname;
		this.secondname = secondname;
		this.phone = phone;
		this.email = email;
		this.image = image;
		this.joindate = joindate;
	}

	public User(String id, String password, String firstname, String secondname, String phone, String email,
			String image) {
		this(id, password, firstname, secondname, phone, email, image, null);
	}

	public User(String id, String password, String firstname, String secondname, String phone, String email,
			Date joindate) {
		this(id, password, firstname, secondname, phone, email, null, joindate);
	}

	public User(String id, String password, String firstname, String secondname, String phone, String email) {
		this(id, password, firstname, secondname, phone, email, null,null);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSecondname() {
		return secondname;
	}

	public void setSecondname(String secondname) {
		this.secondname = secondname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone.replace("-", "");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Date getJoindate() {
		return joindate;
	}

	public void setJoindate(Date joindate) {
		this.joindate = joindate;
	}
}