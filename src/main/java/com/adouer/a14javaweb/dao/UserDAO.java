package com.adouer.a14javaweb.dao;

import com.adouer.a14javaweb.entity.User;

import java.util.List;


public interface UserDAO {

	//插入新用户
	boolean insertUser(User user);

	//根据用户编号删除用户
	boolean deleteUser(int userid);

	//根据用户编号批量删除用户
	boolean deleteUserBatch(String[] userids);


	//更新用户
	boolean updateUser(User user);

	//根据用户编号查找用户
	User findUser(int userid);

	//查找全部用户列表
	List<User> findUser();




	//根据用户名和密码查找用户
	User findUser(String username, String password);

	//更新用户积分
	boolean updateScore(User user, int score);

	//查询指定用户名是否存在
	boolean findUser(String username);

	//查询用户列表
	//List<User> findUserList(String username);
	//List<User> findUserList(String username, String gender);
	//List<User> findUserList(String username, String gender, String job);
	List<User> findUserList(String username, String gender, String job, String begintime, String endtime);

	//查询分页的数据列表
	List<User> findUserList(int currentPage, int pageSize);

	//查询总记录数
	int findTotalCount();


}
