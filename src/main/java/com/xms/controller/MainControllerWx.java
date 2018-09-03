package com.xms.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xms.entity.Car;
import com.xms.entity.Course;
import com.xms.entity.CourseContent;
import com.xms.entity.CourseDirection;
import com.xms.entity.Item;
import com.xms.entity.Order;
import com.xms.entity.User;
import com.xms.service.MainService;

@Controller
@RequestMapping("/mainwx")
public class MainControllerWx {
	
	@Resource
	private MainService mainService;
	
	@RequestMapping("/toIndexJson")
	@ResponseBody
	public Map<String,Object> toIndexJson(Model model){
		
		//譟･隸｢蜈ｨ驛ｨ隸ｾ遞区婿蜷�
		List<CourseDirection> courseDirections 
			= mainService.findAllCourseDirection();
		
		//譟･隸｢螳樊�俶耳闕占ｯｾ遞具ｼ�5荳ｪ�ｼ�
		List<Course> demoCourses = 
			mainService.findDemoCourse();
		
		//譟･隸｢譁ｰ謇句�･髣ｨ隸ｾ遞具ｼ�10荳ｪ�ｼ�
		List<Course> newCourses = 
			mainService.findNewCourse();
		Map map =new HashMap<String,Object>();
		map.put("coursedirections", courseDirections);
		map.put("democourses", demoCourses);
		map.put("newcourses", newCourses);
		return map;		
	}
	
	@RequestMapping("/toCourseJson")
	@ResponseBody
	public Map<String,Object> toCourseJson(
		@ModelAttribute("courseDirectionId") int courseDirectionId,
			@ModelAttribute("courseContentId") int courseContentId,Model model){		
		//譟･隸｢蜈ｨ驛ｨ隸ｾ遞区婿蜷�
		List<CourseDirection> courseDirections 
			= mainService.findAllCourseDirection();		
		
		//譬ｹ謐ｮ隸ｾ遞区婿蜷選D譟･隸｢蟇ｹ蠎皮噪隸ｾ遞句��螳ｹ
		List<CourseContent> courseContents = mainService
			.findCourseContentByCourseDirectionId(courseDirectionId);	
			
		//譬ｹ謐ｮ隸ｾ遞区婿蜷選D蜥瑚ｯｾ遞句��螳ｹID譟･隸｢蟇ｹ蠎皮噪隸ｾ遞�
		List<Course> courses = mainService
			.findCourseByCourseDirectionIdAndCourseContentId(
				courseDirectionId,courseContentId);
		Map map =new HashMap<String,Object>();
		map.put("courseDirections", courseDirections);
		map.put("courseContents", courseContents);
		map.put("courses", courses);
		return map;		
	}
	

	
	//隗�鬚第眺謾ｾ
	@RequestMapping("/toVideoJson")
	@ResponseBody
	public Course toVideoJson(int courseId,Model model){
		
		//譬ｹ謐ｮ隸ｾ遞紀D譟･謇ｾ隸ｾ遞倶ｿ｡諱ｯ
		Course course = mainService.findCourseByCourseId(courseId);		
		return course;
		
	}
	
	//雍ｭ荵ｰ
	@RequestMapping("/buy")
	@ResponseBody
	public boolean buy(int id,HttpServletRequest request){
		
		User user = (User) request.getSession().getAttribute("user");
		
		//譬ｹ謐ｮ逕ｨ謌ｷID譟･謇ｾ蜈ｶ雍ｭ迚ｩ霓ｦ�ｼ悟ｦよ棡蟄伜惠�ｼ檎峩謗･逕ｨ�ｼ悟ｦよ棡荳榊ｭ伜惠
		//蛻呎眠蛻帛ｻｺ
		Car car = mainService.findCarByUserId(user.getId());
		
		if(car == null){
			car = new Car();
			car.setU_id(user.getId());
			mainService.saveCar(car);
		}
		
		//譬ｹ謐ｮ蠖灘燕雍ｭ迚ｩ霓ｦID譟･隸｢雍ｭ迚ｩ霓ｦ荳ｭ蜈ｨ驛ｨ蝠�蜩∫噪ID
		List<Integer> ids = mainService
			.findCourseIdByCarId(car.getId());
		
		//蛻､譁ｭ雍ｭ迚ｩ霓ｦ荳ｭ譏ｯ蜷ｦ蛹�蜷ｫ豁､蝠�蜩�
		if(ids.contains(id)){
			//蟾ｲ雍ｭ荵ｰ霑�豁､蝠�蜩�
			return false;
		}else{
			//豐｡譛芽ｴｭ荵ｰ霑�豁､蝠�蜩�
			Course course = mainService.findCourseByCourseId(id);
			
			//逕滓�仙膚蜩∵擅逶ｮ
			Item item = new Item();
			item.setC_id(course.getId());
			item.setC_name(course.getName());
			item.setC_picture_url(course.getPicture_url());
			item.setC_price(course.getPrice());
			item.setAdd_time(new Timestamp(System.currentTimeMillis()));
			item.setRemove_time(null);
			
			mainService.saveItem(item);
			
			//蟆�蝠�蜩∵擅逶ｮ豺ｻ蜉�蛻ｰ雍ｭ迚ｩ霓ｦ
			Map<String,Object> map = 
				new HashMap<String, Object>();
			map.put("c_id",car.getId());
			map.put("i_id",item.getId());
			
			mainService.saveCarItem(map);
			
			//雍ｭ荵ｰ謌仙粥
			return true;
		}
		
	}	
	
	//譏ｾ遉ｺ雍ｭ迚ｩ霓ｦ
	@RequestMapping("/toCar")
	public String toCar(HttpServletRequest request,Model model){
		
		User user = (User) request.getSession().getAttribute("user");
		
		List<Item> items = mainService
			.findItemByUserId(user.getId());
		
		model.addAttribute("items",items);
		
		return "car";
		
	}
	
	//蛻�髯､蝠�蜩∵擅逶ｮ
	@RequestMapping("/delete")
	public String delete(@RequestParam("item_id") int id){
		
		//莉支c_car_item蛻�髯､謨ｰ謐ｮ
		mainService.deleteCarItem(id);
		
		//莉支c_item陦ｨ荳ｭ蛻�髯､謨ｰ謐ｮ
		mainService.deleteItem(id);
		
		//驥榊ｮ壼髄蛻ｰ雍ｭ迚ｩ霓ｦ鬘ｵ髱｢
		return "redirect:/main/toCar";
	}
	
	//謇ｹ驥丞唖髯､
	@RequestMapping("/batchDelete")
	public String batchDelete(Integer [] itemIds){
		
		for(int i=0;i<itemIds.length;i++){
			delete(itemIds[i]);
		}
		
		//驥榊ｮ壼髄蛻ｰ雍ｭ迚ｩ霓ｦ鬘ｵ髱｢
		return "redirect:/main/toCar";
		
	}
	
	//霍ｳ霓ｬ閾ｳ隶｢蜊暮｡ｵ髱｢
	@RequestMapping("/toOrder")
	public String toOrder(Integer [] itemIds,Model model){
		
		//隶｡邂苓ｴｭ荵ｰ蝠�蜩∵�ｻ莉ｷ
		double totalPrice = 0.0;
		
		//譬ｹ謐ｮ蝠�蜩∵擅逶ｮID譟･隸｢蝠�蜩∵擅逶ｮ
		List<Item> items = new ArrayList<Item>();
		for(Integer itemId : itemIds){
			Item item = mainService.findItemByItemId(itemId);
			
			totalPrice += item.getC_price();
			
			items.add(item);
		}
		
		model.addAttribute("items",items);
		model.addAttribute("totalPrice",totalPrice);
		
		return "order";
	}
	
	//謠蝉ｺ､隶｢蜊�
	@RequestMapping("/order")
	public String order(Integer [] itemIds,
		HttpServletRequest request,Model model){
		
		double totalPrice = 0.0;
		List<Item> items = new ArrayList<Item>();
		
		//譬ｹ謐ｮ蝠�蜩∵擅逶ｮID蟆�雍ｭ荵ｰ逧�蝠�蜩∵擅逶ｮ菫｡諱ｯ莉手ｴｭ迚ｩ霓ｦ荳ｭ蛻�髯､
		for(int i=0;i<itemIds.length;i++){
			Item item = mainService.findItemByItemId(itemIds[i]);
			//隶｡邂苓ｮ｢蜊穂ｸｭ蝠�蜩∵�ｻ莉ｷ
			totalPrice += item.getC_price();
			
			mainService.deleteCarItem(item.getId());
			
			items.add(item);
		}
		
		model.addAttribute("totalPrice",totalPrice);
		
		//逕滓�占ｮ｢蜊�
		Order order = new Order();
		User user = (User) request.getSession().getAttribute("user");
		order.setU_id(user.getId());
		order.setOrder_time(new Timestamp(System.currentTimeMillis()));
		order.setTotal_price(totalPrice);
		order.setPay_type("Y");
		
		//菫晏ｭ倩ｮ｢蜊�
		mainService.saveOrder(order);
		
		//蟆�隶｢蜊穂ｸ主膚蜩∵擅逶ｮ蜈ｳ閨�
		for(Item item : items){
			Map<String,Object> maps = 
				new HashMap<String, Object>();
			maps.put("orderId",order.getId());
			maps.put("itemId",item.getId());
			
			mainService.addOrderItem(maps);
		}
		
		return "success";
	}
	
}
