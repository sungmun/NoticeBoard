package database.Comment;

import java.util.LinkedList;

public class CommentList extends LinkedList<Object> {

	private static final long serialVersionUID = -5859194930588296937L;

	private boolean addObject(int index, Object element) {
		if (!(element instanceof Comment)) {
			return false;
		}

		Comment comment = (Comment) element;

		if (this.getLast() instanceof LinkedList<?>) {
			return ((LinkedList<Comment>) this.get(index)).add(comment);
		}

		LinkedList<Comment> list = new LinkedList<>();
		list.add((Comment) this.removeLast());
		list.add(comment);

		return super.add(list);
	}

	@Override
	public boolean add(Object e) {
		return addObject(this.size(), e);
	}

	@Override
	public void add(int index, Object element) {
		addObject(index, element);
	}

	@Override
	public void addLast(Object e) {
		addObject(this.size(), e);
	}
	
	@Override
	public void addFirst(Object e) {
		addObject(0, e);
	}
}
