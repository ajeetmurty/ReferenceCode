package ajeetmurty.reference.java.patterns.constants;

public final class Constants {
	public static final String COLOR_A = "BLACK";
	public static final String COLOR_B = "White";
	public static final String COLOR_C = "gRay";

	private Constants() {
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("cloning this class not allowed: " + this.getClass().getName());
	}
}