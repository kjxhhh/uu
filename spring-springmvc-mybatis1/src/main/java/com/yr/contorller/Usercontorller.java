package com.yr.contorller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.yr.entity.User;
import com.yr.service.Userservice;

@Controller
public class Usercontorller {

	public Usercontorller() {
		System.out.println(111);
	}
	
	@Autowired
	private Userservice userService;

	@RequestMapping("/users")
	public String getUsers(Map<String, Object> map) {  //查询
		List<User> users = userService.getUsers();
		map.put("users", users);
		return "list";
	}
	
	@RequestMapping("/i18n")
	public String testI18n(Locale locale,Map<String, Object> map){
		map.put("locale", locale.toString());
		return "forward:/users";
	}

	@RequestMapping(value = { "/head/show" })
	@CrossOrigin(origins = "*")
	@ResponseBody
	public String execute(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, int id) {
		// img为图片的二进制流
		InputStream inputStream;
		try {
			User user = userService.getUserById(id);
			//inputStream = new FileInputStream("D:\\springmvc\\Springmvc_spring\\WebContent\\head\\20191111113156418.jpg");
			inputStream = new FileInputStream(user.getHeadUrl());
			byte[] img = new byte[inputStream.available()];
			inputStream.read(img);
			httpServletResponse.setContentType("image/png");
			OutputStream os = httpServletResponse.getOutputStream();
			os.write(img);
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "success";
	}

	@RequestMapping("/addUserShow") 
	public String addUserShow(Map<String, Object> map) {
		Map<Integer, String> mapSex = new HashMap<>();
		mapSex.put(0, "男");
		mapSex.put(1, "女");
		System.out.println("88888");
		map.put("sex", mapSex);
		map.put("user", new User());
		return "input";
	}

	
	@RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)//删除
	public String delete(@PathVariable("id") Integer id){
		User user = new User();
		user.setId(id);
		userService.deleteUser(user);
		return "redirect:/users";
	}
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)//修改回响
	public String updateShow(@PathVariable("id")int id,Map<String,Object> map)
	{
		User user = userService.getUserById(id);
		map.put("user", user);
		
		Map<Integer, String> mapSex = new HashMap<>();
		mapSex.put(0, "男");
		mapSex.put(1, "女");
		System.out.println("0000000");
		map.put("sex", mapSex);
		return "input";
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)//添加保存
	public String addUser(@Valid User user,Errors value, @RequestParam("head") MultipartFile uploadFile) {
		System.out.println(value);
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String fileName = simpleDateFormat.format(new Date());
			String name = uploadFile.getOriginalFilename();
			String names[] = name.split("\\.");

			File file = new File("C:\\Users\\Administrator\\Desktop\\hah" + File.separator + fileName + "."
					+ names[names.length - 1]);
			InputStream inputStream = uploadFile.getInputStream();// 获取到文件上传的流
				System.out.println("77777777777");
			OutputStream outputStream = new FileOutputStream(file);
			System.out.println("333");
			byte[] b = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, length);
			}
			System.out.println("2222222");
			outputStream.flush();
			outputStream.close();
			user.setHeadUrl(file.getPath());
				System.out.println("55555");
			userService.addUser(user);
			System.out.println("898989");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/users";
	}
	
	@ModelAttribute
	public void getEmployee(@RequestParam(value="id",required=false) Integer id,Map<String, Object> map){
		if(id != null && id != 0){
			map.put("user", userService.getUserById(id));
		}
	}
	
	
	
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public String updateUser(User user, @RequestParam("head") MultipartFile uploadFile) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			String fileName = simpleDateFormat.format(new Date());
			String name = uploadFile.getOriginalFilename();
			String names[] = name.split("\\.");
			System.out.println("5656");
			File file = new File("C:\\Users\\Administrator\\Desktop\\hah" + File.separator + fileName + "."
					+ names[names.length - 1]);
			InputStream inputStream = uploadFile.getInputStream();// 获取到文件上传的流

			OutputStream outputStream = new FileOutputStream(file);
			byte[] b = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, length);
			}

			outputStream.flush();
			outputStream.close();
			user.setHeadUrl(file.getPath());
			System.out.println("666666");
			userService.updateUser(user);
			System.out.println("55555");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/users";
	}

}