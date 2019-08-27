package com.example.ticket.ticketSRSLY;

import com.example.ticket.ticketSRSLY.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketSrslyApplication implements CommandLineRunner{
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(TicketSrslyApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... params) throws Exception {
		//creates basic admin and client users
//		User admin = new User();
//		admin.setUsername("admin");
//		admin.setPassword("admin");
//		admin.setEmail("admin@email.com");
//		admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));
//
//		userService.signup(admin);
//
//		User client = new User();
//		client.setUsername("client");
//		client.setPassword("client");
//		client.setEmail("client@email.com");
//		client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));
//
//		userService.signup(client);
	}

}
