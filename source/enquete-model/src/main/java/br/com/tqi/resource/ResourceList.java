package br.com.tqi.resource;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class ResourceList<R extends Resource, T> extends Resource {

	private List<R> resourceList;

	protected ResourceList(List<T> modelList) {
		this.resourceList = new ArrayList<R>(modelList.size());
		populateResourceList(modelList);
	}

	protected List<R> resourceList() {
		return this.resourceList;
	}

	@SuppressWarnings("unchecked")
	private Class<R> resourceType() {
		return (Class<R>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	private void populateResourceList(List<T> modelList) {
		try {
			if (!modelList.isEmpty()) {
				Constructor<R> constructor = resourceType().getConstructor(
						modelList.get(0).getClass());
				for (T model : modelList) {
					this.resourceList.add(constructor.newInstance(model));
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
