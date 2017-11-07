// This class use for collect everything with implement IRenderable.
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RenderableHolder {
	private List<IRenderable> entities;
	private Comparator<IRenderable> comparator;

	public static final RenderableHolder instance = new RenderableHolder();

	public RenderableHolder() {
		entities = new ArrayList<IRenderable>();
		comparator = (IRenderable o1, IRenderable o2) -> {
			if (o1.getZ() > o2.getZ())
				return 1;
			return -1;
		};
	}

	public synchronized void add(IRenderable entity) {
		entities.add(entity);
		Collections.sort(entities, comparator);
	}

	public synchronized void remove(int index) {
		entities.remove(index);
		Collections.sort(entities, comparator);
	}

	public synchronized static RenderableHolder getInstance() {
		return instance;
	}

	public synchronized List<IRenderable> getEntities() {
		return entities;
	}
}
