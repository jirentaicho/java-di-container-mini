package test;

import static org.junit.jupiter.api.Assertions.*;
import vol.*;
import vol.user.User;
import vol.user.dao.UserDao;

import org.junit.jupiter.api.Test;

class DITest {

	@Test
	void test() throws Exception {
		DI di = new DI();
		di.init("vol");
		
		User user = di.get(User.class);
		assertTrue(user instanceof User);
		
		UserDao dao = di.get(UserDao.class);
		assertTrue(dao instanceof UserDao);
		
		// アノテーションがついてないクラスはインスタンス化されない
		Sample sample = di.get(Sample.class);
		assertNull(sample);
		
	}

}
