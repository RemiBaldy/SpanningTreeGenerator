package Graph;

public class Arc {
	public Edge support;
	public boolean reversed;
	
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
		return String.valueOf(getSource()) + " => " + getDest() + "  : " + support.weight+ "\n";
    }
	
}
