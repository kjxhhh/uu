package com.yr.mapper;

import java.util.List;

import com.yr.entity.User;

public interface UserMapper {

	// ��ѯ��������
	public List<User> getUsers();

	// �������
	public void addUser(User user);

	// ��ͼ���� updateUser
	public void updateUser(User user);

	// �޸Ļ������� getUserById
	public User getUserById(Integer id);

	// ɾ������ deleteUser
	public void deleteUser(User user);
}
