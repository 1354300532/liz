package com.jk.service.book;

import java.util.List;
import java.util.Map;

import com.jk.entity.book.Book;
import com.jk.entity.book.UserRequest;
import com.jk.entity.book.UserResponse;
import com.jk.entity.book.menu.MenuRequest;
import com.jk.entity.book.role.RoleRequest;
import com.jk.entity.book.role.RoleResponse;

public interface BookService {

	List selectBookList(Book book);

	void insertBook(Book book);

	void deleteBook(Integer bookID);

	Book querytoUpdateBook(Integer bookID);

	void updatebook(Book book);

	Map<String, Object> login(UserRequest userRequest);

	List<RoleResponse> selectUserRoleListJson(RoleRequest roleRequest);

	void insertUserRoleList(List<RoleRequest> roleRequestList);

	int selectUserCount(UserRequest userRequest);

	List<Map<String, Object>> selectTreeListJson(MenuRequest menuRequest);


}
