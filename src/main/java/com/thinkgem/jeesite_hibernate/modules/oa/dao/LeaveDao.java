/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package com.thinkgem.jeesite_hibernate.modules.oa.dao;

import org.springframework.stereotype.Repository;

import com.thinkgem.jeesite_hibernate.common.persistence.BaseDao;
import com.thinkgem.jeesite_hibernate.common.persistence.Parameter;
import com.thinkgem.jeesite_hibernate.modules.oa.entity.Leave;

/**
 * 请假DAO接口
 * @author liuj
 * @version 2013-8-23
 */
@Repository
public class LeaveDao extends BaseDao<Leave> {
	
	public int updateProcessInstanceId(String id,String processInstanceId){
		return update("update Leave set processInstanceId=:p1 where id = :p2", new Parameter(processInstanceId, id));
	}
	
}
