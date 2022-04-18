package vol;

import vol.user.User;
import vol.user.dao.UserDao;
import vol.web.controller.UserController;

public class Main {
	public static void main(String[] args) throws Exception {
		
		DI di = new DI();
		di.init("vol");
		
		// 対象のディレクトリにあるクラスを取得する
		Product p = di.get(Product.class);
		p.use();
		
		//　サブディレクトリに存在するクラスも取得する
		User user = di.get(User.class);
		user.login();
		
		UserDao userDao = di.get(UserDao.class);
		userDao.update();
		
		UserController controller = di.get(UserController.class);
		controller.getUser();
	}
}
