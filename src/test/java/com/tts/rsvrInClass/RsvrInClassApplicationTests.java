package com.tts.rsvrInClass;


import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tts.rsvrInClass.model.User;
import com.tts.rsvrInClass.repository.UserRepository;
import com.tts.rsvrInClass.service.UserServiceImpl;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class RsvrInClassApplicationTests {
	 
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private UserRepository userRepository;
	
	@InjectMocks
	private UserServiceImpl userServiceImplWithMock;
	
	@Mock
	private UserRepository mockUserRepository;
	
	@After
	public void tearDown(){
		userRepository.deleteAll();
	}

	@Test
	public void integrationTestUsingService() {
		User testUser = new User("Miles Davis", "miles@davis.com");
		userServiceImpl.saveUser(testUser);
		List<User> testUsers = userServiceImpl.findAll();
		assertEquals(1, testUsers.size());
	}
	
	@Test
	public void unitTestUsingService() {
		User testUser = new User("Miles Davis", "miles@davis.com");
		Mockito.when(mockUserRepository.findUserById(1L)).thenReturn(testUser);
		User actual = userServiceImplWithMock.findUserById(1L);
		assertEquals(testUser, actual);
	}
	
	@Test
	public void validateUsersEndpoint() throws Exception {
		userServiceImpl.saveUser(new User("Miles Davis", "miles@davis.com"));
		userServiceImpl.saveUser(new User("Smashing Pumpkins", "smash@pumpkins.com"));
		mockMvc.perform(MockMvcRequestBuilders.get("/users"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void validateEventsEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/events"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	

}