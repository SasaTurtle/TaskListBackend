package cz.spsjecna.TaskListBackend.controller;

import cz.spsjecna.TaskListBackend.model.ERole;
import cz.spsjecna.TaskListBackend.model.User;
import cz.spsjecna.TaskListBackend.payload.request.LoginRequest;
import cz.spsjecna.TaskListBackend.payload.request.SignupRequest;
import cz.spsjecna.TaskListBackend.payload.response.JwtResponse;
import cz.spsjecna.TaskListBackend.payload.response.MessageResponse;
import cz.spsjecna.TaskListBackend.repository.RoleRepository;
import cz.spsjecna.TaskListBackend.repository.UserRepository;
import cz.spsjecna.TaskListBackend.security.jwt.JwtUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for login and register API
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = {"ADMIN"})
    public void testRegisterUserWithValidDetails() {
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setUsername("testuser");
        signupRequest.setEmail("testuse1r@test.com");
        signupRequest.setPassword("testpassword");
        Set<String> strRoles = new HashSet<String>();
        strRoles.add("ROLE_USER");
        signupRequest.setRole(strRoles);

        ResponseEntity<?> response = authController.registerUser(signupRequest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(MessageResponse.class);
        assertThat(((MessageResponse) response.getBody()).getMessage()).isEqualTo("User registered successfully!");
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = {"USER"})
    public void testAuthenticateUserWithValidCredentials() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpassword");

        ResponseEntity<?> response = authController.authenticateUser(loginRequest);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(JwtResponse.class);
    }



}
