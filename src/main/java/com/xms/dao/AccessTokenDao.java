package com.xms.dao;

import com.xms.annotation.MyAnnotation;
import com.xms.entity.AccessToken;

@MyAnnotation
public interface AccessTokenDao {
	void save(AccessToken token);

	void delAll();
}
