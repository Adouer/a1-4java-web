package com.adouer.a14javaweb.service;

import com.adouer.a14javaweb.entity.User;
import com.adouer.a14javaweb.vo.UserPage;

import java.util.List;


public interface UserService {

	//登录
	User login(String username, String password, int score);

	//注册
	User register(User user);

	//检测用户名是否可用   true---可用  false---已占用
	boolean checkUsername(String username);

	//按用户名模糊查询用户列表
	List<User> getUserList(String username, String gender, String job, String begintime, String endtime);

	//根据用户编号删除用户
	boolean deleteUser(int userid);

	//根据用户编号批量删除用户
	boolean deleteUserBatch(String[] userids);

	//查询用户列表---分页
	//List<User> getUserList(int currentPage, int pageSize);

	//获取分页统计信息VO
	UserPage getUserPage(int currentPage, int pageSize);
}
