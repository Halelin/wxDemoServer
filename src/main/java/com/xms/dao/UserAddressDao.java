package com.xms.dao;

import java.util.List;

import com.xms.annotation.MyAnnotation;
import com.xms.entity.UserAddress;

@MyAnnotation
public interface UserAddressDao {
	void save(UserAddress useraddress);
	UserAddress selectAddressByAddressId(Integer id);
	List<UserAddress> selectAddressByOpenId(String openId);
	void update(UserAddress useraddress);
}
