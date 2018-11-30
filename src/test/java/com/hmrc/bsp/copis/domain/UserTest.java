package com.hmrc.bsp.copis.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class UserTest {

    private static final String USERNAME = "username123";
    private static final String PASSWORD = "Password1";
    private static final String PID = "Pid12345";
    private static final String USER_GROUP = "Group123";
    private static final String ROLE = "Role123";

    @Autowired
    private Validator validator;

    private User user = null;


    @Before
    public void setUp() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.user = createUser();
    }

    @After
    public void tearDown() throws Exception {
        this.user = null;
    }

    @Test
    public void testConstructor() {
        assertTrue(this.user.equals(createUserViaConstructor()));
        assertTrue(this.user.hashCode() == createUserViaConstructor().hashCode());
        Map<String, User> map = new HashMap<>();
        map.put(this.user.getUsername(), this.user);
        User e = map.get(this.user.getUsername());
        assertNotNull(e);
        e = new User();
        assertNotNull(e);
    }

    @Test
    public void testUsernameOk() {
        assertEquals(USERNAME, this.user.getUsername());
    }

    @Test
    public void testNullUsernameFail() {
        this.user.setUsername(null);
        assertViolations();
    }

    @Test
    public void testEmptyUsernameFail() {
        this.user.setUsername("");
        assertViolations();
    }

    @Test
    public void testUsernameTooLongFail() {
        this.user.setUsername("ThisUserNameIsMoreThanTwentyCharacters");
        assertViolations();
    }

    @Test
    public void testInvalidUsernameCharacters() {
        this.user.setUsername("InvalidCharacters2.");
        assertViolations();
    }

    @Test
    public void testPidOk() {
        assertEquals(PID, this.user.getPid());
    }

    @Test
    public void testNullPidFail() {
        this.user.setPid(null);
        assertViolations();
    }

    @Test
    public void testPidTooShortFail() {
        this.user.setPid("123");
        assertViolations();
    }

    @Test
    public void testPidTooLongFail() {
        this.user.setPid("ThisPidIsMoreThanTwentyCharacters");
        assertViolations();
    }

    @Test
    public void testPasswordOk() {
        assertEquals(PASSWORD, this.user.getPassword());
    }

    @Test
    public void testPasswordNullFail() {
        this.user.setPassword(null);
        assertViolations();
    }

    @Test
    public void testPasswordTooShortFail() {
        this.user.setPassword("1234567");
        assertViolations();
    }

    @Test
    public void testPasswordTooLongFail() {
        this.user.setPassword("ThisPasswordIsMoreThanTwentyCharacters");
        assertViolations();
    }

    @Test
    public void testPasswordConfirmOk() {
        assertEquals(PASSWORD, this.user.getPasswordConfirm());
    }

    @Test
    public void testPasswordConfirmNullFail() {
        this.user.setPasswordConfirm(null);
        assertViolations();
    }

    @Test
    public void testPasswordConfirmTooShortFail() {
        this.user.setPasswordConfirm("1234567");
        assertViolations();
    }

    @Test
    public void testPasswordConfirmTooLongFail() {
        this.user.setPasswordConfirm("ThisPasswordIsMoreThanTwentyCharacters");
        assertViolations();
    }

    @Test
    public void testUserGroupOk() {
        assertEquals(USER_GROUP, this.user.getUserGroup());
    }

    @Test
    public void testUserGroupNullFail() {
        this.user.setUserGroup(null);
        assertViolations();
    }

    @Test
    public void testUserGroupEmptyFail() {
        this.user.setUserGroup("");
        assertViolations();
     }

    @Test
    public void testRoleOk() {
        assertEquals(ROLE, this.user.getRole());
    }

    @Test
    public void testRoleNullFail() {
        this.user.setRole(null);
        assertViolations();
    }

    @Test
    public void testRoleEmptyFail() {
        this.user.setRole("");
        assertViolations();
    }

    @Test
    public void tesAddPasswordToHistory() {
        User u = new User();
        assertTrue(u.getPasswordHistory().isEmpty());
        u.addPasswordToHistory(PASSWORD);
        assertTrue(u.getPasswordHistory().size() == 1);
        u.addPasswordToHistory(PASSWORD);
        assertTrue(u.getPasswordHistory().size() == 1);
        u.addPasswordToHistory(PASSWORD + 1);
        assertTrue(u.getPasswordHistory().size() == 2);
        assertFalse(u.toString().contains(PASSWORD));
    }

    @Test
    public void testEqualsAndHashcode() {
        assertEquals(this.user, createUserViaConstructor());
        assertEquals(User.builder().username("123").build(),
                User.builder().username("123").password("password").build());
        assertEquals(User.builder().username("123").build().hashCode(),
                User.builder().username("123").password("password").build().hashCode());
    }

    @Test
    public void testToStringNotContainSensitiveData() {
        assertFalse(this.user.toString().contains(PASSWORD));
    }

    private User createUser() {
        return User.builder()
                .username(USERNAME)
                .pid(PID)
                .password(PASSWORD)
                .passwordConfirm(PASSWORD)
                .userGroup(USER_GROUP)
                .role(ROLE)
                .build();
    }

    private User createUserViaConstructor() {
        return new User(USERNAME, PID, PASSWORD, PASSWORD, USER_GROUP, ROLE);
    }

    private void assertViolations() {
        Set<ConstraintViolation<User>> violations = validator.validate(this.user);
        assertThat(violations.size()).isGreaterThan(0);
    }
}
