package com.jk.controller.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jk.entity.book.menu.MenuRequest;
import com.jk.entity.book.menu.MenuResponse;
import com.jk.entity.book.role.RoleRequest;
import com.jk.entity.book.role.RoleResponse;
import com.jk.service.role.RoleService;

@Controller
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("toRoleListPage")
	String toRoleListPage() {
		return "role/role_list";
	}
	
	/**
	 * 带分页的查询
	 */
	@RequestMapping("selectRoleListJson")
	@ResponseBody
	Map<String, Object> selectRoleListJson(String pageNumber, RoleRequest roleRequest) {
		//查询总条数
		int totalCount = roleService.selectRoleCount(roleRequest);
		roleRequest.setTotalCount(totalCount);
		if (null == pageNumber || "".equals(pageNumber.trim())) {
			pageNumber = "1";
		}
		roleRequest.setPageIndex(Integer.valueOf(pageNumber));
		//计算分页信息
		roleRequest.calculate();
		//查询列表
		List<RoleResponse> roleList = roleService.selectRoleList(roleRequest);
		//封装结果
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", totalCount);
		map.put("rows", roleList);
		return map;
	}
	
	@RequestMapping("toRoleMenuPage")
	String toRoleMenuPage(Model m, RoleRequest roleRequest) {
		m.addAttribute("roleID", roleRequest.getRoleID());
		return "role/role_menu";
	}
	
	@RequestMapping("selectRoleMenuListJson")
	@ResponseBody
	List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest) {
		List<MenuResponse> menuList = roleService.selectRoleMenuListJson(menuRequest);
		return menuList;
	}
	
	@RequestMapping("insertRoleMenuList")
	@ResponseBody
	String insertRoleMenuList(@RequestBody List<MenuRequest> menuRequestList) {
		roleService.insertRole1MenuList(menuRequestList);
		return "{}";
	}
}
