package jbr.springmvc;

import jbr.springmvc.model.Login;
import jbr.springmvc.model.User;
import jbr.springmvc.service.UserService;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;


//TEST CLASS


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:jbr/config/user-beans.xml" })
public class UserServiceTest {
  
// Autowired is used to inject the object dependency implicitly
  @Autowired
  private UserService userService;

  @Test
  public void testRegister() //create a new dummy user
  {
    User user = new User();
    user.setUsername("sakshi89");
    user.setPassword("sakshi@pune");
    user.setFirstname("sakshi");
    user.setLastname("sane");
    user.setAddress("Mumbai");
    user.setEmail("sakshisane55@gmail.com");
    user.setPhone(89690);    
    
    //change the user parameters every time before running or u will get a duplicate key error
    int result = userService.register(user);
    Assert.assertEquals(1, result);
  }

  @Test
  public void testValidateUser() //validating the correct password/username
  {
    Login login = new Login();
    // the user is already in the database
    login.setUsername("aarya");
    login.setPassword("aaryahello");

    User user = userService.validateUser(login);
    Assert.assertEquals("aarya", user.getFirstname()); 
  }

}
