package com.jk.dao.book.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.dao.book.BookDao;
import com.jk.entity.book.Book;
import com.jk.entity.book.UserRequest;
import com.jk.entity.book.UserResponse;
import com.jk.entity.book.menu.MenuRequest;
import com.jk.entity.book.role.RoleRequest;
import com.jk.entity.book.role.RoleResponse;
@Repository
public class BookDaoImpl extends SqlMapClientDaoSupport implements BookDao {
	
	@Override
	public List selectBookList(Book book) {
		 return getSqlMapClientTemplate().queryForList("book.selectBookList");
	}

	@Override
	public void insertBook(Book book) {
		  getSqlMapClientTemplate().insert("book.insertBook", book);
	}

	@Override
	public void deleteBook(Integer bookID) {
		getSqlMapClientTemplate().delete("book.deleteBook",bookID);
	}

	@Override
	public Book querytoUpdateBook(Integer bookID) {
		List<Book> queryForList = this.getSqlMapClientTemplate().queryForList("book.querytoUpdateBook", bookID);
		if(!queryForList.isEmpty()){
			Book usersInfo = queryForList.get(0);
			return usersInfo;
		}
		return null;
	}

	@Override
	public void updatebook(Book book) {
		int update = this.getSqlMapClientTemplate().update("book.updatebook", book);
		System.out.println(update);
	}

	@Override
	public UserResponse login(UserRequest userRequest) {
		return  (UserResponse) this.getSqlMapClientTemplate().queryForObject("user.login", userRequest);
	}

	@Override
	public void updateLoginFailNum(UserRequest userRequest) {
		this.getSqlMapClientTemplate().update("user.updateLoginFailNum", userRequest);
	}

	@Override
	public List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest) {
		return this.getSqlMapClientTemplate().queryForList("user.selectUserRoleListJson", roleRequest);
	}

	@Override
	public void deleteAllRolesByUserID(RoleRequest roleRequest) {
		this.getSqlMapClientTemplate().delete("user.deleteAllRolesByUserID", roleRequest);
	}

	@Override
	public void insertUserRoleList(final List<RoleRequest> roleRequestList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			/* (non-Javadoc)    
			 * @see org.springframework.orm.ibatis.SqlMapClientCallback#doInSqlMapClient(com.ibatis.sqlmap.client.SqlMapExecutor)    
			 */
			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				//开启批量
				sqlMap.startBatch();
				//添加批量操作语句
				for (RoleRequest roleRequest : roleRequestList) {
					sqlMap.insert("user.insertUserRole", roleRequest);
				}
				//执行批量操作
				sqlMap.executeBatch();
				return null;
			}
		});
	}

	@Override
	public int selectUserCount(UserRequest userRequest) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("user.selectUserCount", userRequest);
	}

	@Override
	public List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest) {
		return this.getSqlMapClientTemplate().queryForList("user.selectTreeListJson", menuRequest);
	}


}
