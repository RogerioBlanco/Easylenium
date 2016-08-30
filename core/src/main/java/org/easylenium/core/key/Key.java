package org.easylenium.core.key;

public class Key<X, Y> {

	private X x;
	
	private Y y;
	
	public Key(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Key)) return false;
        Key key = (Key) o;
        return x == key.x && y == key.y;
    }

    @Override
    public int hashCode() {
        return 31 * x.hashCode() + y.hashCode();
    }
}
