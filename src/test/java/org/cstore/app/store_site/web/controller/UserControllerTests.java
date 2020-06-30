//package org.cstore.app.store_site.web.controller;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//import org.cstore.app.store_site.entity.RoleType;
//import org.cstore.app.store_site.service.UserService;
//import org.cstore.app.store_site.utils.TestHelper;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.cstore.app.store_site.entity.AUser;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(controllers = UserController.class)
//public class UserControllerTests {
//
//    @MockBean
//    UserService userService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    Optional<AUser> existingUser, newUser, updateUser;
//
//    @Before
//    public void setUp() {
//        newUser = TestHelper.buildUserWithId();
//        existingUser = TestHelper.buildUserWithId();
//        updateUser = TestHelper.buildUserWithId();
//    }
//
//    @Test
//    public void should_get_all_users() throws Exception {
//        given(userService.getAllUsers()).willReturn(Arrays.asList(existingUser.get(), updateUser.get()));
//
//        this.mockMvc
//                .perform(get("/api/users"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)));
//    }
//
//    @Test
//    public void should_get_user_by_id() throws Exception {
//        given(userService.getUserById(existingUser.get().getUserId())).willReturn(existingUser);
//
//        this.mockMvc
//                .perform(get("/api/users/"+existingUser.get().getUserId()))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(existingUser.get().getUserId())))
//                .andExpect(jsonPath("$.name", is(existingUser.get().getUsername())))
//                .andExpect(jsonPath("$.email", is(existingUser.get().getEmail())));
//    }
//
//    @Test
//    public void should_create_user() throws Exception {
//        given(userService.createUser(newUser,RoleType.StoreAdmin)).willReturn(newUser);
//
//        this.mockMvc
//                .perform(post("/api/users/")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(newUser))
//                )
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id", notNullValue()))
//                .andExpect(jsonPath("$.name", is(newUser.get().getUsername())))
//                .andExpect(jsonPath("$.email", is(newUser.get().getEmail())));
//    }
//
//    @Test
//    public void should_update_user() throws Exception {
//        given(userService.updateUser(existingUser)).willReturn(existingUser);
//
//        this.mockMvc
//                .perform(put("/api/users/"+existingUser.get().getUserId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(existingUser))
//                )
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(existingUser.get().getUserId())))
//                .andExpect(jsonPath("$.name", is(existingUser.get().getUsername())))
//                .andExpect(jsonPath("$.email", is( existingUser.get().getEmail())));
//    }
//
//    @Test
//    public void should_delete_user() throws Exception {
//        doNothing().when(userService).deleteUser(existingUser.get().getUserId());
//
//        this.mockMvc
//                .perform(delete("/api/users/"+existingUser.get().getUserId()))
//                .andExpect(status().isOk());
//    }
//
//}
