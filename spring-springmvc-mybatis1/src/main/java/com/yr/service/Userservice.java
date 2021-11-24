package com.yr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yr.dao.Userdao;
import com.yr.entity.User;

@Service
public class Userservice {

	@Autowired
	private Userdao userdao;

	public List<User> getUsers() {
		return userdao.getUsers();
	}

	public void addUser(User user) {
		userdao.addUser(user);
	}

	public void deleteUser(User user) {
		userdao.deleteUser(user);
	}

	public void updateUser(User user) {
		userdao.updateUser(user);
	}

	public User getUserById(int id) {
		return userdao.getUserById(id);
	}
}