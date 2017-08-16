package com.jk.service.book.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jk.dao.book.BookDao;
import com.jk.entity.book.Book;
import com.jk.entity.book.UserRequest;
import com.jk.entity.book.UserResponse;
import com.jk.entity.book.menu.MenuRequest;
import com.jk.entity.book.role.RoleRequest;
import com.jk.entity.book.role.RoleResponse;
import com.jk.service.book.BookService;

import common.interceptor.constant.Constant;

@Service
public class BookServiceImpl implements BookService {
	@Autowired
	private BookDao bookDao;

	@Override
	public List selectBookList(Book book) {
		 return bookDao.selectBookList(book);
	}

	@Override
	public void insertBook(Book book) {
		  this.bookDao.insertBook(book);
	}


	@Override
	public void deleteBook(Integer bookID) {
		this.bookDao.deleteBook(bookID);
	}

	@Override
	public Book querytoUpdateBook(Integer bookID) {
		Book book = bookDao.querytoUpdateBook(bookID);
		return book;
	}

	@Override
	public void updatebook(Book book) {
		bookDao.updatebook(book);
	}

	@Override
	public Map<String, Object> login(UserRequest userRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		//默认设置密码错误
		map.put("flag", Constant.LOGIN_PWD_ERROR);
		map.put("userInfo", null);
		
		//判断验证码
		if (null == userRequest.getUserImgCode() || "".equals(userRequest.getUserImgCode())) {
			//验证码为空
			map.put("flag", Constant.LOGIN_CODE_NULL);
		} else if (userRequest.getUserImgCode().equals(userRequest.getSysImgCode())) {
			
			
			
			 //连接数据库，查询用户信息
			UserResponse userResponse = bookDao.login(userRequest);
			
			//判断是否被锁定（小于连续3次失败并且距离最近一次失败大于5分钟）
			if (null == userResponse) {
				//用户不存在
				map.put("flag", Constant.LOGIN_ACCOUNT_ERROR);
			} else if (0 == userResponse.getLoginFailNum()
					|| 0 < (userResponse.getLoginFailNum() % 3)
					|| userResponse.getLoginFailDate() > 300000) {
				if (userResponse.getUserPwd().equals(userRequest.getUserPwd())) {
					//登陆成功
					map.put("flag", Constant.LOGIN_SUCCESS);
					//用户信息放入map中
					map.put("userInfo", userResponse);
					userRequest.setLoginFailNum(0);
				} else {
					//密码错误
					//连接数据库，修改登陆失败的次数
					int loginFailNum = 1;
						if (userResponse.getLoginFailDate() > 300000) {
							userRequest.setLoginFailNum(1);
					} else {
						userResponse.setLoginFailNum(userResponse.getLoginFailNum() + 1);
						loginFailNum = userResponse.getLoginFailNum() + 1;
					}
					map.put("loginfailnum", loginFailNum);
				}
				//修改登陆失败次数
				bookDao.updateLoginFailNum(userRequest);
			} else {
				//锁定
				map.put("flag", Constant.ACCOUNT_LOCKED);
			}
		} else {
			//验证码错误
			map.put("flag", Constant.LOGIN_CODE_ERROR);
		}
		return map;
	}

	@Override
	public List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest) {
		return bookDao.selectUserRoleListJson(roleRequest);
	}

	@Override
	public void insertUserRoleList(List<RoleRequest> roleRequestList) {
		// 1、删除用户之前的所有角色（mid）
		bookDao.deleteAllRolesByUserID(roleRequestList.get(0));
		// 2、添加用户勾选的所有角色（mid）
		bookDao.insertUserRoleList(roleRequestList);
	}

	@Override
	public int selectUserCount(UserRequest userRequest) {
		return bookDao.selectUserCount(userRequest);
	}
	
	
	@Override
	public List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest) {
		List<Map<String, Object>> treeList = bookDao.selectTreeListJson(menuRequest);
		if (null != treeList && 0 < treeList.size()) {
			//开始调用递归
			treeList = queryTreeListNodes(treeList, menuRequest);
		} else {
			treeList = new ArrayList<Map<String,Object>>();
		}
		return treeList;
	}
	
	//递归查询树菜单
	private List<Map<String, Object>> queryTreeListNodes(List<Map<String, Object>> treeList, MenuRequest menuRequest) {
		for (Map<String, Object> map : treeList) {
			if ("0".equals(map.get("pid").toString())) {
				//取出ID作为下次查询的pid
				int pid = Integer.valueOf(map.get("id").toString());
				menuRequest.setPid(pid);
				List<Map<String, Object>> queryTreeListNodes = 
						queryTreeListNodes(bookDao.selectTreeListJson(menuRequest), menuRequest);
				map.put("nodes", queryTreeListNodes);
			}
		}
		return treeList;
	}


	
	
}

