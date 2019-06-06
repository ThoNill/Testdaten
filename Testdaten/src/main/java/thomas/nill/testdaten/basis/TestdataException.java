package thomas.nill.testdaten.basis;

import lombok.NonNull;

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

	public TestdataException(@NonNull String message, @NonNull Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TestdataException(@NonNull String message, @NonNull Throwable cause) {
		super(message, cause);
	}

	public TestdataException(@NonNull String message) {
		super(message);
	}

	public TestdataException(@NonNull Throwable cause) {
		super(cause);
	}


}
