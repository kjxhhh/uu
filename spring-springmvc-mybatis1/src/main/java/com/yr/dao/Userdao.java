package com.yr.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yr.entity.User;
import com.yr.mapper.UserMapper;

@Repository
@Transactional

public class Userdao {

	@Autowired
	private UserMapper userMapper;

	/**
	 * ��ѯ����
	 * 
	 * @return
	 */

	public List<User> getUsers() {
		List<User> user = userMapper.getUsers();
		return user;
	}

	/**
	 * �������
	 * 
	 * @param user
	 */
	public void addUser(User user) {

		userMapper.addUser(user);

	}

	/**
	 * �޸�����
	 * 
	 * @param user
	 */
	public void updateUser(User user) {
		userMapper.updateUser(user);

	}

	/**
	 * �޸Ļ�������
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(Integer id) {
		User user = userMapper.getUserById(id);
		return user;
	}

	/**
	 * ɾ��
	 * 
	 * @param user
	 */
	public void deleteUser(User user) {
		userMapper.deleteUser(user);
	}
}
