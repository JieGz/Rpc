package com.qiyue.server.service;

import com.qiyue.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public int addUser(User userInfo) {
		logger.debug("create user success, uid=" + userInfo.getUid());
		return 0;
	}
}
