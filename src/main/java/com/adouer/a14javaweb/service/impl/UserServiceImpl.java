package com.adouer.a14javaweb.service.impl;

import com.adouer.a14javaweb.dao.UserDAO;
import com.adouer.a14javaweb.dao.impl.UserDAOImpl;
import com.adouer.a14javaweb.entity.User;
import com.adouer.a14javaweb.service.UserService;
import com.adouer.a14javaweb.vo.UserPage;

import java.util.List;


public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAOImpl();   //组合

	@Override
	public User login(String username, String password, int score) {
		//return userDAO.findUser(username, password);

		//查询用户
		User user = userDAO.findUser(username, password);
		if(user!=null){

			if(userDAO.updateScore(user, score)){  //更新数据库的积分

				//更新当前对象的积分
				user.setScore(user.getScore() + score);

				return user;
			}
		}

		return null;

	}

	@Override
	public User register(User user) {

		if(userDAO.insertUser(user)){  //插入成功

			return userDAO.findUser(user.getUsername(), user.getPassword());

		}

		return null;
	}


	@Override
	public boolean checkUsername(String username) {

		return !userDAO.findUser(username);
	}

	@Override
	public List<User> getUserList(String username, String gender, String job, String begintime, String endtime) {
		return userDAO.findUserList(username, gender, job, begintime, endtime);
	}

	//@Override
	//public List<User> getUserList(int currentPage, int pageSize) {
	//	return userDAO.findUserList(currentPage, pageSize);
	//}

	@Override
	public UserPage getUserPage(int currentPage, int pageSize) {

		UserPage userPage = new UserPage();

		userPage.setPageSize(pageSize);

		userPage.setCurrentPage(currentPage);

		int totalCount = userDAO.findTotalCount();
		userPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize ==0 ?  totalCount / pageSize : totalCount / pageSize + 1;
		userPage.setTotalPage(totalPage);

		userPage.setDataList(userDAO.findUserList(currentPage, pageSize));

		return userPage;
	}

	public static void main(String[] args) {

		UserService userService = new UserServiceImpl();

		User user = userService.login("user1", "1", 2);

		if(user!=null){
			System.out.println("登录成功，您的当前积分为" + user.getScore());
		}else{
			System.out.println("登录失败，请检查用户名呵呵密码是否输入正确");
		}

	}

	@Override
	public boolean deleteUser(int userid) {
		return userDAO.deleteUser(userid);
	}

	@Override
	public boolean deleteUserBatch(String[] userids) {

		/*for(String userid : userids){

			if(!userDAO.deleteUser(Integer.parseInt(userid))){
				return false;
			}
		}

		return true;*/

		return userDAO.deleteUserBatch(userids);
	}
}
