package vol;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public class DI {
	
	// 異種コンテナ
	private Map<Class<?>, Object> container = new HashMap<>();
	
	public <T> T get(Class<T> type) {
		return type.cast(container.get(type));
	}
	
	@SuppressWarnings("unused")
	private <T> void put(Class<T> type, T instance) {
		container.put(Objects.requireNonNull(type), type.cast(instance));
	}
	
	/**
	 * DIコンテナを初期化する
	 * @Componentアノテーションがついているクラスを全てDIコンテナに登録します。
	 * 
	 * @param packageName
	 * @throws Exception
	 */
	public void init(String packageName) throws Exception {
		List<String> classNameList = Finder.getClassList(packageName);
		for(String name : classNameList) {
			//　クラス文字列からクラスオブジェクトを取得する
			Class<?> clazz = Class.forName(name);
			// アノテーション一覧を取得する
			Annotation[] anos = clazz.getAnnotations();
			// @Componentアノテーションがついているか判定する
			if(haveComponent(anos)) {
				// コンストラクタオブジェクトを取得する
				Constructor<?> constructor = clazz.getConstructor();
				// インスタンスからgetClass()をしないとクラス情報は取得できない
				// clazz.getName().getClass()ではjava.lang.classになってしまう
				Object obj = constructor.newInstance();
				container.put(obj.getClass(), obj);
			}
		}
	}
	
	// Componentアノテーションが付いてるか確認する
	private boolean haveComponent(Annotation[] annotations) {
		for(Annotation annotation : annotations) {
			if(annotation.annotationType() == Component.class) {
				return true;
			}
		}
		return false;
	}
	
}
