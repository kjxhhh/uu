package com.yr.mapper;

import java.util.List;

import com.yr.entity.User;

public interface UserMapper {

	// 查询所有数据
	public List<User> getUsers();

	// 添加数据
	public void addUser(User user);

	// 修图数据 updateUser
	public void updateUser(User user);

	// 修改回显数据 getUserById
	public User getUserById(Integer id);

	// 删除数据 deleteUser
	public void deleteUser(User user);
}
