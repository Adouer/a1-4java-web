package com.adouer.a14javaweb.service.impl;

import com.adouer.a14javaweb.dao.NewsDAO;
import com.adouer.a14javaweb.dao.UserDAO;
import com.adouer.a14javaweb.dao.impl.NewsDAOImpl;
import com.adouer.a14javaweb.dao.impl.UserDAOImpl;
import com.adouer.a14javaweb.entity.Comment;
import com.adouer.a14javaweb.entity.News;
import com.adouer.a14javaweb.entity.User;
import com.adouer.a14javaweb.service.NewsService;
import com.adouer.a14javaweb.vo.NewsPage;

import java.util.List;

public class NewsServiceImpl implements NewsService {

	private NewsDAO newsDAO = new NewsDAOImpl();
	private UserDAO userDAO = new UserDAOImpl();


	@Override
	public boolean addNews(News news, User user, int addNewsScore) {

		if(newsDAO.insertNews(news)){   //插入新闻成功

			if(userDAO.updateScore(user, addNewsScore)){  //更新数据库的积分

				//更新当前对象的积分
				user.setScore(user.getScore() + addNewsScore);

				return true;
			}
		}

		return false;
	}


	/*@Override
	public List<News> getNewsList() {
		return newsDAO.findNewsList();
	}*/

	@Override
	public NewsPage getNewsPage(int currentPage, int pageSize) {

		NewsPage newsPage = new NewsPage();

		newsPage.setPageSize(pageSize);

		newsPage.setCurrentPage(currentPage);

		int totalCount = newsDAO.findTotalCount();
		newsPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize ==0 ?  totalCount / pageSize : totalCount / pageSize + 1;
		newsPage.setTotalPage(totalPage);

		newsPage.setDataList(newsDAO.findNewsList(currentPage, pageSize));

		int pageNumer = 5;

		//计算页码
		int begin = currentPage - pageNumer / 2;
		int end = currentPage + pageNumer / 2;

		//调整开始页码
		if(begin<1){
			begin = 1;
			end = pageNumer;
		}

		//调整结束页码
		if(end>totalPage){
			end = totalPage;
			begin = totalPage - pageNumer + 1;
		}

		//调整页码
		if(totalPage<pageNumer){
			begin = 1;
			end = totalPage;
		}

		newsPage.setBegin(begin);
		newsPage.setEnd(end);

		return newsPage;
	}

	@Override
	public void downloadAttachment(User user, int newsid, int downloadscore) {

		if(newsDAO.updateDownloadcount(newsid)){

			if(userDAO.updateScore(user, -1 * downloadscore)){
				user.setScore(user.getScore() - downloadscore);
			}

		}

	}


	@Override
	public List<Comment> getCommentList(int newsid) {
		return newsDAO.findCommentList(newsid);
	}

	@Override
	public boolean addComment(Comment comment, User user, int addCommentScore) {

		if(newsDAO.insertComment(comment)){   //插入评论成功

			if(userDAO.updateScore(user, addCommentScore)){  //给用户加分成功

				//给当前用户对象加分
				user.setScore(user.getScore() + addCommentScore);

				return true;
			}
		}

		return false;
	}


}
