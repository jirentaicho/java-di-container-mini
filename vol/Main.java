package vol;

import vol.user.User;
import vol.user.dao.UserDao;
import vol.web.controller.UserController;

public class Main {
	public static void main(String[] args) throws Exception {
		
		DI di = new DI();
		di.init("vol");
		
		// �Ώۂ̃f�B���N�g���ɂ���N���X���擾����
		Product p = di.get(Product.class);
		p.use();
		
		//�@�T�u�f�B���N�g���ɑ��݂���N���X���擾����
		User user = di.get(User.class);
		user.login();
		
		UserDao userDao = di.get(UserDao.class);
		userDao.update();
		
		UserController controller = di.get(UserController.class);
		controller.getUser();
	}
}
