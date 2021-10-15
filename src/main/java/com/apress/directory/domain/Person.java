package com.apress.directory.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name="Person")
@Data
public class Person {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column(unique = true)
    private String email;
    private String name;
    private String password;
    private String role = "USER";
    private boolean enabled = true;
    private LocalDate birthday;
    @Column(insertable = true, updatable = false)
    private LocalDateTime created;
    private LocalDateTime modified;
    public Person() {
    }
    public Person(String email, String name, String password, String birthday) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birthday = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public Person(String email, String name, String password, LocalDate birthday) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.birthday = birthday;
    }

    public Person(String email, String name, String password, String birthday, String role, boolean enabled) {
        this(email, name, password, birthday);
        this.role = role;
        this.enabled = enabled;
    }

    @PrePersist
    void onCreate() {
        this.setCreated(LocalDateTime.now());
        this.setModified(LocalDateTime.now());
    }
 
    @PreUpdate
    void onUpdate() {
        this.setModified(LocalDateTime.now());
    }
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getModified() {
		return modified;
	}
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}
    
}