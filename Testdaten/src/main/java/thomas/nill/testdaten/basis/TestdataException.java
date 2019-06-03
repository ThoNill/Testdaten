package thomas.nill.testdaten.basis;
/**
 * Runtime Exception for this Testdata creation library.
 * 
 * @author tnill
 *
 */
public class TestdataException extends RuntimeException{
	private static final long serialVersionUID = -9195601128824267758L;

	public TestdataException() {
		super();
	}

	public TestdataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TestdataException(String message, Throwable cause) {
		super(message, cause);
	}

	public TestdataException(String message) {
		super(message);
	}

	public TestdataException(Throwable cause) {
		super(cause);
	}


}
