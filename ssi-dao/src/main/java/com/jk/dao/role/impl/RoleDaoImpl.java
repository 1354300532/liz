package com.jk.dao.role.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapExecutor;
import com.jk.dao.role.RoleDao;
import com.jk.entity.book.menu.MenuRequest;
import com.jk.entity.book.menu.MenuResponse;
import com.jk.entity.book.role.RoleRequest;
import com.jk.entity.book.role.RoleResponse;

@Repository
public class RoleDaoImpl extends SqlMapClientDaoSupport implements RoleDao {

	@Override
	public int selectRoleCount(RoleRequest roleRequest) {
		return (Integer) this.getSqlMapClientTemplate().queryForObject("role.selectRoleCount", roleRequest);
	}

	@Override
	public List<RoleResponse> selectRoleList(RoleRequest roleRequest) {
		return this.getSqlMapClientTemplate().queryForList("role.selectRoleList", roleRequest);
	}

	@Override
	public List<MenuResponse> selectRoleMenuListJson(MenuRequest menuRequest) {
		return this.getSqlMapClientTemplate().queryForList("role.selectRoleMenuListJson", menuRequest);
	}

	@Override
	public void deleteAllRoleMenuByRoleID(MenuRequest menuRequest) {
		this.getSqlMapClientTemplate().delete("role.deleteAllRoleMenuByRoleID", menuRequest);
		
	}

	@Override
	public void insertRoleMenuList(final List<MenuRequest> menuRequestList) {
		this.getSqlMapClientTemplate().execute(new SqlMapClientCallback<Object>() {
			/* (non-Javadoc)    
			 * @see org.springframework.orm.ibatis.SqlMapClientCallback#doInSqlMapClient(com.ibatis.sqlmap.client.SqlMapExecutor)    
			 */
			@Override
			public Object doInSqlMapClient(SqlMapExecutor sqlMap) throws SQLException {
				sqlMap.startBatch();
				for (MenuRequest menuRequest : menuRequestList) {
					sqlMap.insert("role.insertRoleMenu", menuRequest);
				}
				sqlMap.executeBatch();
				return null;
			}
		});
	}

}
