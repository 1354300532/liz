package com.jk.service.role.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jk.dao.role.RoleDao;
import com.jk.entity.book.menu.MenuRequest;
import com.jk.entity.book.menu.MenuResponse;
import com.jk.entity.book.role.RoleRequest;
import com.jk.entity.book.role.RoleResponse;
import com.jk.service.role.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	
	@Resource
	private RoleDao roleDao;

	@Override
	public int selectRoleCount(RoleRequest roleRequest) {
		return roleDao.selectRoleCount(roleRequest);
	}

	@Override
	public List<RoleResponse> selectRoleList(RoleRequest roleRequest) {
		return roleDao.selectRoleList(roleRequest);
	}

	@Override
	public List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest) {
		return roleDao.selectRoleMenuListJson(menuRequest);
	}

	@Override
	public void insertRole1MenuList(List<MenuRequest> menuRequestList) {
		roleDao.deleteAllRoleMenuByRoleID(menuRequestList.get(0));
		roleDao.insertRoleMenuList(menuRequestList);
	}
}
