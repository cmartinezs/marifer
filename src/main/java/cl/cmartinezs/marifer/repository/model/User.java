package cl.cmartinezs.marifer.repository.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

import cl.cmartinezs.marifer.repository.model.enums.UserStatus;

@Entity
@Table(name = "users")
public class User extends ModelBase {

	private static final long serialVersionUID = -8719426128540064357L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Expose
	private Long id;

	@Column(name = "username", nullable = false, unique = true)
	@Expose
	private String username;

	@Column(name = "password", nullable = false)
	@Expose
	private String password;

	@Column(name = "email", unique = true)
	@Expose
	private String email;

	@Column(name = "salt", nullable = false)
	@Expose
	private String salt;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", nullable = false, columnDefinition = "ENUM('ACTIVE', 'BANNED', 'DELETED', 'DOWN', 'INACTIVE', 'SUSPENDED')")
	@Expose
	private UserStatus status;

	@Column(name = "created_at", nullable = false, columnDefinition = "DATETIME")
	@Expose
	private Date createdAt;

	@Column(name = "updated_at", nullable = false, columnDefinition = "DATETIME")
	@Expose
	private Date updatedAt;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User createdBy;

	@OneToMany
	@JoinTable(name = "users", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "id") })
	private List<User> createdUsers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public List<User> getCreatedUsers() {
		return createdUsers;
	}

	public void setCreatedUsers(List<User> createdUsers) {
		this.createdUsers = createdUsers;
	}

}
