package com.tms;

import java.util.Arrays;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.tms.model.Role;
import com.tms.model.User;
import com.tms.repository.RoleRepository;
import com.tms.repository.UserRepository;



@Component
public class SeedCommandLineRunner implements CommandLineRunner{
	

	private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

	public SeedCommandLineRunner(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
    public void run(String... strings) throws Exception {
		
		Role roleUser = roleRepository.findByRole("USER");
		Role roleAdmin = roleRepository.findByRole("ADMIN");
		
		System.out.println("Checking Roles.");
		if(roleAdmin == null)  {
			roleAdmin = new Role();
			roleAdmin.setRole("ADMIN");
			roleRepository.save(roleAdmin);
			System.out.println("Seeding ADMIN role");
		}
		else {
			System.out.println("ADMIN role OK.");
		}
		if(roleUser == null)  {
			roleUser = new Role();
			roleUser.setRole("USER");
			roleRepository.save(roleUser);
			System.out.println("Seeding USER role.");
		}
		else {
			System.out.println("USER role OK.");
		}
		
		
		System.out.println("Checking Admin account.");
		User user = userRepository.findByEmail("admin@admin.com");
		if(user == null) {
			user = new User();
			user.setName("Admin");
			user.setLastName("Adminstrator");
			user.setPassword(bCryptPasswordEncoder.encode("test1"));
			user.setEmail("admin@admin.com");
			user.setSalaryPerHour(0);
			user.setHasStartedTime(false);
			user.setActive(1);
			userRepository.save(user);
			user.setRoles(new HashSet<Role>(Arrays.asList(roleAdmin)));
			userRepository.save(user);
			System.out.println("Seeding Admin account.");
		}
		else {
			System.out.println("ADMIN account OK.");
		}
		
		System.out.println("Checking User Account.");
		User user1 = userRepository.findByEmail("user@user.com");
		if(user1 == null) {
			user1 = new User();
			user1.setName("User");
			user1.setLastName("UserAccount");
			user1.setPassword(bCryptPasswordEncoder.encode("test1"));
			user1.setEmail("user@user.com");
			user1.setSalaryPerHour(99);
			user1.setHasStartedTime(false);
			user1.setActive(1);
			userRepository.save(user1);
			user1.setRoles(new HashSet<Role>(Arrays.asList(roleUser)));
			userRepository.save(user1);
			System.out.println("Seeding USER account.");
		}
		else {
			System.out.println("ADMIN user OK.");
		}
		System.out.println("Seed done.");
	}
}

