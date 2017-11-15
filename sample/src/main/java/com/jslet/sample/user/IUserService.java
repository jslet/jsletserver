package com.jslet.sample.user;

import java.util.List;
import java.util.Map;

public interface IUserService {
	List<User> findUserList(Map<String, Object> criteria);

	List<User> save(List<User> users);

}
