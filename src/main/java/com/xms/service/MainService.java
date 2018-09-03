package com.xms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xms.dao.MainDao;
import com.xms.entity.Car;
import com.xms.entity.Course;
import com.xms.entity.CourseContent;
import com.xms.entity.CourseDirection;
import com.xms.entity.Item;
import com.xms.entity.Order;

@Service
public class MainService {
	public MainService(){
		System.out.println("MainService Init");
	}
	
	@Resource
	private MainDao mainDao;
	
	//譟･隸｢蜈ｨ驛ｨ隸ｾ遞区婿蜷�
	public List<CourseDirection> findAllCourseDirection(){
		
		List<CourseDirection> courseDirections 
			= mainDao.findAllCourseDirection();
		
		return courseDirections;
		
	}
	
	//譟･隸｢螳樊�俶耳闕占ｯｾ遞具ｼ�5荳ｪ�ｼ�
	public List<Course> findDemoCourse(){
		
		List<Course> demoCourses = 
			mainDao.findAllDemoCourse();
		
		List<Course> listCourses = new ArrayList<Course>();
		Random random = new Random();	
		
		if(demoCourses.size() <= 5){
			return demoCourses;
		}else{
			for(int i=0;i<5;i++){
				int index = random.nextInt(demoCourses.size());
				Course course = demoCourses.remove(index);
				listCourses.add(course);
			}
		}
		
		return listCourses;
		
	}
	
	//譟･隸｢譁ｰ謇句�･髣ｨ隸ｾ遞具ｼ�10荳ｪ�ｼ�
	public List<Course> findNewCourse(){
		
		List<Course> newCourses = 
			mainDao.findAllNewCourse();
		
		List<Course> listCourses = new ArrayList<Course>();
		Random random = new Random();
		
		if(newCourses.size() <= 10){
			return newCourses;
		}else{
			for(int i=0;i<10;i++){
				int index = random.nextInt(newCourses.size());
				Course course = newCourses.remove(index);
				listCourses.add(course);
			}
		}
		
		return listCourses;
	}
	
	//譬ｹ謐ｮ隸ｾ遞区婿蜷選D譟･隸｢蟇ｹ蠎碑ｯｾ遞句��螳ｹ
	public List<CourseContent> findCourseContentByCourseDirectionId(int courseDirectionId){
		return mainDao.findCourseContentByCourseDirectionId(courseDirectionId);
	}
	
	//譬ｹ謐ｮ隸ｾ遞区婿蜷選D蜥瑚ｯｾ遞句��螳ｹID譟･隸｢蟇ｹ蠎皮噪隸ｾ遞�
	public List<Course> findCourseByCourseDirectionIdAndCourseContentId(
		int courseDirectionId,int courseContentId){
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("courseDirectionId",courseDirectionId);
		map.put("courseContentId",courseContentId);		
		return mainDao.findCourseByCourseDirectionIdAndCourseContentId(map);		
	}
	
	//譬ｹ謐ｮ隸ｾ遞紀D譟･謇ｾ隸ｾ遞倶ｿ｡諱ｯ
	public Course findCourseByCourseId(int courseId){		
		return mainDao.findCourseByCourseId(courseId);
		
	}
	
	//譬ｹ謐ｮ逕ｨ謌ｷID譟･謇ｾ雍ｭ迚ｩ霓ｦ
	public Car findCarByUserId(int userId){
		return mainDao.findCarByUserId(userId);
	}
	
	//蛻帛ｻｺ雍ｭ迚ｩ霓ｦ
	public void saveCar(Car car){
		mainDao.saveCar(car);
	}
	
	//譬ｹ謐ｮ蠖灘燕雍ｭ迚ｩ霓ｦID譟･隸｢豁､雍ｭ迚ｩ霓ｦ荳ｭ蜈ｨ驛ｨ蝠�蜩！D
	public List<Integer> findCourseIdByCarId(int carId){
		return mainDao.findCourseIdByCarId(carId);
	}
	
	//逕滓�仙膚蜩∵擅逶ｮ
	public void saveItem(Item item){
		mainDao.saveItem(item);
	}
	
	//蟆�蝠�蜩∵擅逶ｮ豺ｻ蜉�蛻ｰ雍ｭ迚ｩ霓ｦ
	public void saveCarItem(Map<String,Object> map){
		mainDao.saveCarItem(map);
	}
	
	//譬ｹ謐ｮ逕ｨ謌ｷID譟･隸｢蟇ｹ蠎碑ｴｭ迚ｩ霓ｦ荳ｭ逧�蝠�蜩∵擅逶ｮ
	public List<Item> findItemByUserId(int userId){
		return mainDao.findItemByUserId(userId);
	}
	
	//譬ｹ謐ｮ蝠�蜩∵擅逶ｮID蛻�髯､Car_Item蟇ｹ蠎泌�ｳ邉ｻ
	public void deleteCarItem(int itemId){
		mainDao.deleteCarItem(itemId);
	}
	
	//譬ｹ謐ｮ蝠�蜩∵擅逶ｮID蛻�髯､蝠�蜩∵擅逶ｮ
	public void deleteItem(int id){
		mainDao.deleteItem(id);
	}
	
	//譬ｹ謐ｮ蝠�蜩∵擅逶ｮID譟･隸｢蝠�蜩∵擅逶ｮ菫｡諱ｯ
	public Item findItemByItemId(int itemId){
		return mainDao.findItemByItemId(itemId);
	}
	
	//菫晏ｭ倩ｮ｢蜊�
	public void saveOrder(Order order){
		mainDao.saveOrder(order);
	}
	
	//蟆�隶｢蜊穂ｸ主膚蜩∵擅逶ｮ蜈ｳ閨�
	public void addOrderItem(Map<String,Object> map){
		mainDao.addOrderItem(map);
	}
	
}
