package com.saif.codefellowship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class CodefellowshipApplicationTests {
	@Autowired
	MockMvc mockMvc;
	@Test
	public void testHomePage() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(containsString("<h1>Welcome to Codefellowship, the best home for coders on the internet!</h1>"
)));
	}
	@Test
	public void testSignUp() throws Exception{
		mockMvc.perform(
				post("/signup")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
						.param("firstName", "saif")
						.param("lastName", "saif")
						.param("dateOfBirth", String.valueOf(LocalDate.now()))
						.param("bio", "engineer")
						.param("username", "saif1")
						.param("password", "saif123")
		)
				.andExpect(redirectedUrl("/"))
				.andExpect(status().isFound());
	}
	@Test
	public void testLogIn() throws Exception{
		mockMvc.perform(
						post("/login")
								.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
								.param("username", "saif1")
								.param("password", "saif123")
				)
				.andExpect(redirectedUrl("/myprofile"))
				.andExpect(status().isFound());
	}
}
