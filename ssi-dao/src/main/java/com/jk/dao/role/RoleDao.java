package com.jk.dao.role;

import java.util.List;

import com.jk.entity.book.menu.MenuRequest;
import com.jk.entity.book.menu.MenuResponse;
import com.jk.entity.book.role.RoleRequest;
import com.jk.entity.book.role.RoleResponse;

public interface RoleDao {

	int selectRoleCount(RoleRequest roleRequest);

	List<RoleResponse> selectRoleList(RoleRequest roleRequest);

	List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest);

	void deleteAllRoleMenuByRoleID(MenuRequest menuRequest);

	void insertRoleMenuList(List<MenuRequest> menuRequestList);

}
