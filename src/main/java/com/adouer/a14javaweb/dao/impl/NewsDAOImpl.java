package com.adouer.a14javaweb.dao.impl;

import com.adouer.a14javaweb.dao.NewsDAO;
import com.adouer.a14javaweb.dbutil.DBManager;
import com.adouer.a14javaweb.entity.Comment;
import com.adouer.a14javaweb.entity.News;
import com.adouer.a14javaweb.entity.NewsType;
import com.adouer.a14javaweb.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class NewsDAOImpl implements NewsDAO {

	private DBManager dbManager = DBManager.getInstance();

	@Override
	public boolean insertNews(News news) {

		String sql = "insert into news values(null, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		return dbManager.execUpdate(sql,
				news.getTitle(), news.getContent(), news.getPubtime(),
				news.getNewsType().getTypeid(), news.getUser().getUserid(),
				news.getAttachment(), news.getTruename(), news.getDownloadscore(),news.getDownloadcount());
	}

	@Override
	public List<News> findNewsList(int currentPage, int pageSize) {

		//三表连接
		String sql = "select newsid, title, content, pubtime, typename, username, photo, attachment, truename, downloadscore, downloadcount";
		sql+=" from news join newstype as nt on news.typeid = nt.typeid";
		sql+=" join user on news.userid = user.userid";
		//sql+=" order by pubtime desc";
		sql+=" limit ?, ?";

		ResultSet rs = dbManager.execQuery(sql, (currentPage-1) * pageSize, pageSize);

		List<News> list = new ArrayList<>();

		try {

			while(rs.next()){

				News news = new News();

				news.setNewsid(rs.getInt(1));
				news.setTitle(rs.getString(2));
				news.setContent(rs.getString(3));
				news.setPubtime(rs.getTimestamp(4));

				//news.setTypeid(rs.getInt(5));

				//news对象中组合了newstype对象
				NewsType newsType = new NewsType();
				newsType.setTypename(rs.getString(5));
				news.setNewsType(newsType);

				//news.setUserid(rs.getInt(6));
				User user = new User();
				user.setUsername(rs.getString(6));
				user.setPhoto(rs.getString(7));
				news.setUser(user);

				news.setAttachment(rs.getString(8));
				news.setTruename(rs.getString(9));
				news.setDownloadscore(rs.getInt(10));
				news.setDownloadcount(rs.getInt(11));

				list.add(news);
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbManager.closeConnection();
		}

		return null;
	}

	@Override
	public boolean updateDownloadcount(int newsid) {

		String sql = "update news set downloadcount = downloadcount + 1 where newsid = ?";

		return dbManager.execUpdate(sql, newsid);
	}


	@Override
	public int findTotalCount() {

		String sql = "select count(*) from news";
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

	@Override
	public List<Comment> findCommentList(int newsid) {

		String sql = "select commentid, content, comment.score, pubtime, username, photo";
		sql += " from comment join user on comment.userid = user.userid";
		sql += " where newsid = ?";
		sql += " order by pubtime desc";

		ResultSet rs = dbManager.execQuery(sql, newsid);

		try {

			List<Comment> list = new ArrayList<Comment>();

			while(rs.next()){

				//创建并填充实体类
				Comment comment = new Comment();

				comment.setCommentid(rs.getInt(1));

				comment.setContent(rs.getString(2));
				comment.setScore(rs.getInt(3));
				comment.setPubtime(rs.getTimestamp(4));

				User user = new User();
				user.setUsername(rs.getString(5));
				user.setPhoto(rs.getString(6));
				comment.setUser(user);

				list.add(comment);
			}

			return list;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			dbManager.closeConnection();
		}

		return null;
	}

	@Override
	public boolean insertComment(Comment comment) {

		String sql = "insert into comment values(null, ?, ?, ?, ?, ?)";

		return dbManager.execUpdate(sql, comment.getContent(), comment.getScore(), comment.getPubtime(), comment.getNews().getNewsid(), comment.getUser().getUserid());
	}
}
