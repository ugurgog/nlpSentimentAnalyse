package com.uren.extranet.api.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class Users {

	@Transient
    public static final String SEQUENCE_NAME = "users_sequence";
	
    @Id
    private Integer id;
    private String name;
    private String teamName;
    private Long salary;

    @PersistenceConstructor
    public Users() {
    }
    
    @PersistenceConstructor
    public Users(Integer id, String name, String teamName, Long salary) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.salary = salary;
    }
    
    @PersistenceConstructor
    public Users(String name, String teamName, Long salary) {
        this.name = name;
        this.teamName = teamName;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}