package demo4j;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.andlinks.demo4j.Application;
import com.andlinks.demo4j.model.UserDO;
import com.andlinks.demo4j.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Before
	public void init() {
		UserDO entity = new UserDO();
		entity.setUserName("test");
		entity.setPassword("test");
		userService.save(entity);
	}

	@Test
	public void getTest() {
		UserDO result = userService.getByUserName("test");
		assertEquals("test", result.getUserName());

		UserDO result2 = userService.getByUuid("UserDO-e1ef993c-7e9e-4cad-95ed-e8548e0c2f71");
		assertEquals(result, result2);

		UserDO result3 = new UserDO();
		result3.setUuid("UserDO-e1ef993c-7e9e-4cad-95ed-e8548e0c2f71");

		assertEquals(true, result.equals(result3));

		System.out.println(result);

	}
}
