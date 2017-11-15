package com.jslet.sample.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jslet.sample.auditlog.AuditLogService;

@Service
public class UserServiceImpl implements IUserService {
	private static List<User> allUsers = new ArrayList<User>();

	static {
		User u = new User();
		u.setUserId(1);
		u.setUserCode("admin");
		u.setUserName("Admin");
		u.setUserType(0);
		allUsers.add(u);

		u = new User();
		u.setUserId(2);
		u.setUserCode("001");
		u.setUserName("Tom");
		u.setUserType(1);
		allUsers.add(u);

		u = new User();
		u.setUserId(3);
		u.setUserCode("003");
		u.setUserName("Jerry");
		u.setUserType(1);
		allUsers.add(u);
	}

	@Autowired
	private AuditLogService auditLogService;

	@Override
	public List<User> findUserList(Map<String, Object> criteria) {
		if (criteria == null || criteria.isEmpty()) {
			return allUsers;
		}
		Integer userType = (Integer) criteria.get("userType");
		if (userType == null) {
			return allUsers;
		}
		List<User> result = new ArrayList<User>();
		for (User u : allUsers) {
			if (u.getUserType().equals(userType)) {
				result.add(u);
			}
		}
		return result;
	}

	@Override
	public List<User> save(List<User> users) {
		for (User u : users) {
			if (u.updated()) {
				// 记录修改日志
				this.auditLogService.saveAuditLog("User", u.getUserId().toString(), u.auditLog(), "Tom");
				User oldUser;
				for (int i = 0, len = allUsers.size(); i < len; i++) {
					oldUser = allUsers.get(i);
					if (oldUser.getUserId().equals(u.getUserId())) {
						allUsers.set(i, u);
					}
				}
			}
			if (u.inserted()) {
				allUsers.add(u);
			}
			if (u.deleted()) {
				for (User oldUser : allUsers) {
					if (oldUser.getUserId().equals(u.getUserId())) {
						allUsers.remove(oldUser);
						break;
					}
				}
			}
		}
		return users;
	}
}
