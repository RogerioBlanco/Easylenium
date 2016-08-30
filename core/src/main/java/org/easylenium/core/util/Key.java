package org.easylenium.core.util;

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
        
        Key<?, ?> key = (Key<?, ?>) o;
        
        if((x == null && key.x != null) || (x != null && key.x == null)) return false;

        if((y == null && key.y != null) || (y != null && key.y == null)) return false;

        return x.equals(key.x) && y.equals(key.y);
    }

    @Override
    public int hashCode() {
        return 31 * x.hashCode() + y.hashCode();
    }
}
