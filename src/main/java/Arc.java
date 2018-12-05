
public class Arc {
	Edge support;
	boolean reversed;
	
	public Arc(Edge e, boolean reversed) {
		this.support = e;
		this.reversed = reversed;
	}
	
	public int getSource() {
		return (reversed ? support.getDest() : support.getSource());
	}
	
	public int getDest() {
		return (reversed ? support.getSource() : support.getDest());
	}

	public String toString (){
        StringBuilder result = new StringBuilder();
        result.append(getSource()).append(" => ").append(getDest()).append("\n");
        return result.toString();
    }
	
}
