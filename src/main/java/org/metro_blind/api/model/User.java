package org.metro_blind.api.model;

import java.security.Principal;
import java.util.Set;

public class User implements Principal {
    private Integer id;
    private String name;
    private String password;

    private final Set<String> roles = null;

    public User() {}

    public User(String name, String password) {
        this.name = name;
	this.password = password;
    }

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
	this.password = password;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public Integer getId() {
        return id;
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
}
