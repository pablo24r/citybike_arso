package repositorio;

import java.lang.reflect.Constructor;

import org.bson.Document;

public interface Identificable {

	String getId();

	void setId(String id);

	static <T extends Identificable> T fromDocument(Document document, Class<T> clazz) {
		try {
			Constructor<T> constructor = clazz.getDeclaredConstructor();
			T entity = constructor.newInstance();
			entity.setId(document.getObjectId("_id").toString());
			return entity;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
