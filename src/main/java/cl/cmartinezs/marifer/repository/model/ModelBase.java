package cl.cmartinezs.marifer.repository.model;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class ModelBase implements Serializable {

	private static final long serialVersionUID = -1624910073464736923L;

	@Override
	public String toString() {
		Gson gson=  new GsonBuilder()
				.excludeFieldsWithoutExposeAnnotation()
				.setDateFormat("yyyy-MM-dd HH:mm:ss Z")
				.create();
		
		return gson.toJson(this);
	}
}
