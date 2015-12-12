/*package com.example;

import java.util.List;

import jersey.repackaged.com.google.common.collect.Lists;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import rh.DemoApplication;
import rh.security.AppUserDetails;
import rh.security.AppUserDetailsService;
import rh.security.Role;
import rh.security.User;
import rh.security.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
	}
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AppUserDetailsService appUserDetailsService;
	@Test
	public void findAllUsersWithTheirRoles() {
		 Iterable<User> users = userRepository.findAll();
		 for (User user : users) {
		 System.out.println(user);
		 display("Roles :", userRepository.getRoles(user.getId()));
		 }
	 }
	@SuppressWarnings("deprecation")
	@Test
	public void findUserByLogin() {
		 // on récupère l'utilisateur [admin]
		 User user = userRepository.findUserByLogin("admin");
		 // on vérifie que son mot de passe est [admin]
		 Assert.assertEquals("hiphop", user.getPassword());
		 // on vérifie le rôle de admin / admin
		List<Role> roles = Lists.newArrayList(userRepository.getRoles("admin", user.getPassword()));
		Assert.assertEquals(1L, roles.size());
		Assert.assertEquals("ROLE_ADMIN", roles.get(0).getName());
		}
	
	
	@SuppressWarnings("deprecation")
	@Test
	public void loadUserByUsername() {
	// on récupère l'utilisateur [admin]
	AppUserDetails userDetails = (AppUserDetails) appUserDetailsService.loadUserByUsername("admin");
	// on vérifie que son mot de passe est [admin]
	Assert.assertEquals("hiphop", userDetails.getPassword());
	 // on vérifie le rôle de admin / admin
	@SuppressWarnings("unchecked")
    List<SimpleGrantedAuthority> authorities = (List<SimpleGrantedAuthority>)
	userDetails.getAuthorities();
	 Assert.assertEquals(1L, authorities.size());
	Assert.assertEquals("ROLE_ADMIN", authorities.get(0).getAuthority());
	}
	
	
	
	// méthode utilitaire - affiche les éléments d'une collection
	 private void display(String message, Iterable<?> elements) {
	System.out.println(message);
	for (Object element : elements) {
		 System.out.println(element);
	 }

	 }
}
*/