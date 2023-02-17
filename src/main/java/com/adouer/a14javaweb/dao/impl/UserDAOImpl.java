package com.adouer.a14javaweb.dao.impl;

import com.adouer.a14javaweb.dao.UserDAO;
import com.adouer.a14javaweb.dbutil.DBManager;
import com.adouer.a14javaweb.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserDAOImpl implements UserDAO {

	private DBManager dbManager = DBManager.getInstance();  //组合

	@Override
	public boolean insertUser(User user) {

		String sql = "insert into user values(null, ?, ?, ?, ?, ?, ?, ?, ?)";

		return dbManager.execUpdate(sql, user.getUsername(), user.getPassword(), user.getScore(),
				user.getPhoto(), user.getGender(), user.getJob(), user.getInterest(), new Date());
	}

	@Override
	public boolean deleteUser(int userid) {

		String sql = "delete from user where userid = ?";

		return dbManager.execUpdate(sql, userid);
	}

	@Override
	public boolean updateUser(User user) {

		String sql = "update user set username = ?, password = ?, score = ? where userid = ?";

		return dbManager.execUpdate(sql,  user.getUsername(), user.getPassword(), user.getScore(), user.getUserid());
	}

	@Override
	public User findUser(int userid) {

		String sql = "select * from user where userid = ?";

		ResultSet rs = dbManager.execQuery(sql, userid);

		if(rs!=null){
			try {
				if(rs.next()){  //有数据

					//创建并填充实体类
					User user = new User();

					user.setUserid(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setScore(rs.getInt(4));

					return user;

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{

				//关闭数据库连接
				dbManager.closeConnection();
			}
		}

		return null;
	}

	@Override
	public List<User> findUser() {

		String sql = "select * from user";

		ResultSet rs = dbManager.execQuery(sql);

		if(rs!=null){
			try {

				List<User> list = new ArrayList<>();

				while(rs.next()){  //有数据

					//创建并填充实体类
					User user = new User();

					user.setUserid(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setScore(rs.getInt(4));

					//添加到列表中
					list.add(user);
				}

				return list;

			} catch (SQLException e) {
				e.printStackTrace();
			} finally{

				//关闭数据库连接
				dbManager.closeConnection();
			}
		}

		return null;
	}

	@Override
	public User findUser(String username, String password) {

		String sql = "select * from user where username = ? and password = ?";

		ResultSet rs = dbManager.execQuery(sql, username, password);

		if(rs!=null){
			try {
				if(rs.next()){  //有数据

					//创建并填充实体类
					User user = new User();

					user.setUserid(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setScore(rs.getInt(4));

					user.setPhoto(rs.getString(5));
					user.setGender(rs.getString(6));
					user.setJob(rs.getString(7));
					user.setRegtime(rs.getTimestamp(9));

					return user;

				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{

				//关闭数据库连接
				dbManager.closeConnection();
			}
		}

		return null;
	}

	@Override
	public boolean updateScore(User user, int score) {

		String sql = "update user set score = score + ? where userid = ?";

		return dbManager.execUpdate(sql, score, user.getUserid());
	}

	@Override
	public boolean findUser(String username) {

		String sql = "select * from user where username = ?";

		ResultSet rs = dbManager.execQuery(sql, username);

		if(rs!=null){

			try {
				return rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{

				//关闭数据库连接
				dbManager.closeConnection();
			}
		}

		return false;
	}

	@Override
	public List<User> findUserList(String username, String gender, String job, String begintime, String endtime) {

		String sql = null;
		ResultSet rs = null;

		if("".equals(begintime) && "".equals(endtime)){  //全部时间
			sql = "select * from user where username like ? and gender like ? and job like ?";
			rs = dbManager.execQuery(sql, "%" + username +"%", "%" + gender +"%", "%" + job +"%");
		}else{
			sql = "select * from user where username like ? and gender like ? and job like ? and regtime between ? and ?";
			rs = dbManager.execQuery(sql, "%" + username +"%", "%" + gender +"%", "%" + job +"%", begintime + " 00:00:00", endtime + " 23:59:59");
		}

		if(rs!=null){
			try {

				List<User> list = new ArrayList<>();

				while(rs.next()){  //有数据

					//创建并填充实体类
					User user = new User();

					user.setUserid(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setScore(rs.getInt(4));

					user.setPhoto(rs.getString(5));
					user.setGender(rs.getString(6));
					user.setJob(rs.getString(7));
					user.setRegtime(rs.getTimestamp(9));


					//添加到列表中
					list.add(user);
				}

				return list;

			} catch (SQLException e) {
				e.printStackTrace();
			} finally{

				//关闭数据库连接
				dbManager.closeConnection();
			}
		}

		return null;
	}

	@Override
	public List<User> findUserList(int currentPage, int pageSize) {

		String sql = "select * from user limit ?, ?";

		ResultSet rs = dbManager.execQuery(sql, (currentPage-1) * pageSize, pageSize);

		if(rs!=null){
			try {

				List<User> list = new ArrayList<>();

				while(rs.next()){  //有数据

					//创建并填充实体类
					User user = new User();

					user.setUserid(rs.getInt(1));
					user.setUsername(rs.getString(2));
					user.setPassword(rs.getString(3));
					user.setScore(rs.getInt(4));

					//添加到列表中
					list.add(user);
				}

				return list;

			} catch (SQLException e) {
				e.printStackTrace();
			} finally{

				//关闭数据库连接
				dbManager.closeConnection();
			}
		}

		return null;
	}



	@Override
	public int findTotalCount() {

		String sql = "select count(*) from user";
		ResultSet rs = dbManager.execQuery(sql);

		try {
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbManager.closeConnection();
		}

		return -1;
	}

	public static void main(String[] args) {

		UserDAO userDAO = new UserDAOImpl();

		//User user = new User("user13", "13", 130);
		//userDAO.insertUser(user);

		//userDAO.deleteUser(16);

		/*User user = userDAO.findUser(15);
		user.setScore(122);
		userDAO.updateUser(user);*/

		//List<User> list = userDAO.findUserList(4, 5);
		//System.out.println(list);

		System.out.println(userDAO.findTotalCount());
	}

	@Override
	public boolean deleteUserBatch(String[] userids) {

		//delete from user where userid in(2,3,5)

		String sql = "delete from user where userid in(";

		/* String拼接
		for (int i = 0; i < userids.length; i++) {
			sql += "?,";
		}

		sql = sql.substring(0, sql.length()-1) + ")";
		*/

		//StringBuilder方式

		StringBuilder sb = new StringBuilder(sql);
		for (String userid : userids) {
			sb.append("?,");
		}

		sql = sb.deleteCharAt(sb.length()-1).append(")").toString();

		return dbManager.execUpdate(sql, userids);
	}
}
