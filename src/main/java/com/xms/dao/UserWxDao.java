package com.xms.dao;

import com.xms.annotation.MyAnnotation;
import com.xms.entity.UserWx;

@MyAnnotation
public interface UserWxDao {
	UserWx findUserWxByid(String id);
	void save(UserWx userwx);
}
