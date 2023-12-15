package org.jsp.springuserapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.springuserapp.UserConfig;
import org.jsp.springuserapp.dao.UserDao;
import org.jsp.springuserapp.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SuppressWarnings("all")
public class UserController {
	static Scanner s = new Scanner(System.in);
	static UserDao dao;
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(UserConfig.class);
		dao = context.getBean(UserDao.class);
	}

	public static void main(String[] args) {
		System.out.println("1.Save user");
		System.out.println("2.Update User");
		System.out.println("3.Find User By Id");
		System.out.println("4.Verify user By Phone and password");
		System.out.println("5.Verify user By Email and password");
		System.out.println("6.Verify user By Id and password");
		System.out.println("7.Delete user");
		System.out.println("8.Fetch All Names");
		switch (s.nextInt()) {
		case 1: {
			save();
			break;
		}
		case 2: {
			update();
			break;
		}
		case 3: {
			findById();
			break;
		}
		case 4: {
			verifyByPhone();
			break;
		}
		case 5: {
			verifyByEmail();
			break;
		}
		case 6: {
			verifyById();
			break;
		}
		case 7: {
			delete();
			break;
		}
		case 8: {
			printNames();
			break;
		}
		default: {
			System.err.println("Invalid Choice");
		}
		}
	}

	public static void save() {
		System.out.println("Enter the name,phone, email and password to save user");
		User user = new User();
		user.setName(s.next());
		user.setPhone(s.nextLong());
		user.setEmail(s.next());
		user.setPassword(s.next());
		user = dao.saveUser(user);
		System.out.println("User registered with Id:" + user.getId());
	}

	public static void update() {
		System.out.println("Enter the User Id to update");
		int id = s.nextInt();
		System.out.println("Enter the name,phone, email and password to save user");
		User user = new User();
		user.setId(id);
		user.setName(s.next());
		user.setPhone(s.nextLong());
		user.setEmail(s.next());
		user.setPassword(s.next());
		user = dao.updateUser(user);
		if (user != null)
			System.out.println("User updated");
		else
			System.err.println("cannot update user as the entered id is Invalid");
	}

	public static void findById() {
		System.out.println("Enter the User id to print details");
		int id = s.nextInt();
		User u = dao.findById(id);
		if (u != null) {
			System.out.println("User Id:" + u.getId());
			System.out.println("User Name:" + u.getId());
			System.out.println("Phone Number:" + u.getPhone());
			System.out.println("Email Id:" + u.getEmail());
		} else {
			System.err.println("You have entered an Invalid Id");
		}
	}

	public static void verifyByPhone() {
		System.out.println("Enter the Phone Number and password to verify User");
		long phone = s.nextLong();
		String password = s.next();
		User u = dao.verifyUser(phone, password);
		if (u != null) {
			System.out.println("User Verified Succesfully");
			System.out.println("User Id:" + u.getId());
			System.out.println("User Name:" + u.getId());
			System.out.println("Phone Number:" + u.getPhone());
			System.out.println("Email Id:" + u.getEmail());
		} else {
			System.err.println("Invalid Phone Number or Password");
		}
	}

	public static void verifyByEmail() {
		System.out.println("Enter the Email Id and password to verify User");
		String email = s.next();
		String password = s.next();
		User u = dao.verifyUser(email, password);
		if (u != null) {
			System.out.println("User Verified Succesfully");
			System.out.println("User Id:" + u.getId());
			System.out.println("User Name:" + u.getId());
			System.out.println("Phone Number:" + u.getPhone());
			System.out.println("Email Id:" + u.getEmail());
		} else {
			System.err.println("Invalid Email Id or Password");
		}
	}

	public static void verifyById() {
		System.out.println("Enter the User Id and password to verify User");
		int id = s.nextInt();
		String password = s.next();
		User u = dao.verifyUser(id, password);
		if (u != null) {
			System.out.println("User Verified Succesfully");
			System.out.println("User Id:" + u.getId());
			System.out.println("User Name:" + u.getId());
			System.out.println("Phone Number:" + u.getPhone());
			System.out.println("Email Id:" + u.getEmail());
		} else {
			System.err.println("Invalid User Id or Password");
		}
	}

	public static void delete() {
		System.out.println("Enter the User Id to delete");
		int id = s.nextInt();
		boolean deleted = dao.delete(id);
		if (deleted)
			System.out.println("user deleted");
		else
			System.err.println("Cannot delete user as Entered Id is Invalid");
	}

	public static void printNames() {
		List<String> names = dao.getNames();
		for (String name : names) {
			System.out.println(name);
		}
	}
}
