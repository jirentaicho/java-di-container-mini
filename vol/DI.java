package vol;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class DI {
	
	// �َ�R���e�i
	private Map<Class<?>, Object> container = new HashMap<>();
	
	public <T> T get(Class<T> type) {
		return type.cast(container.get(type));
	}
	
	@SuppressWarnings("unused")
	private <T> void put(Class<T> type, T instance) {
		container.put(Objects.requireNonNull(type), type.cast(instance));
	}
	
	/**
	 * DI�R���e�i������������
	 * @Component�A�m�e�[�V���������Ă���N���X��S��DI�R���e�i�ɓo�^���܂��B
	 * 
	 * @param packageName
	 * @throws Exception
	 */
	public void init(String packageName) throws Exception {
		List<String> classNameList = Finder.getClassList(packageName);
		for(String name : classNameList) {
			//�@�N���X�����񂩂�N���X�I�u�W�F�N�g���擾����
			Class<?> clazz = Class.forName(name);
			// �A�m�e�[�V�����ꗗ���擾����
			Annotation[] anos = clazz.getAnnotations();
			// @Component�A�m�e�[�V���������Ă��邩���肷��
			if(haveComponent(anos)) {
				// �R���X�g���N�^�I�u�W�F�N�g���擾����
				Constructor<?> constructor = clazz.getConstructor();
				// �C���X�^���X����getClass()�����Ȃ��ƃN���X���͎擾�ł��Ȃ�
				// clazz.getName().getClass()�ł�java.lang.class�ɂȂ��Ă��܂�
				Object obj = constructor.newInstance();
				container.put(obj.getClass(), obj);
			}
		}
	}
	
	// Component�A�m�e�[�V�������t���Ă邩�m�F����
	private boolean haveComponent(Annotation[] annotations) {
		for(Annotation annotation : annotations) {
			if(annotation.annotationType() == Component.class) {
				return true;
			}
		}
		return false;
	}
	
}
