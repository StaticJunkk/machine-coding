package org.example.exception;

public class DataStoreException extends Exception {
    // Constructor that accepts a message and a cause (Throwable)
    public DataStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    // Optionally, you can also include other constructors for flexibility:

    // Constructor that accepts only a message
    public DataStoreException(String message) {
        super(message);
    }

    // Constructor that accepts only a cause (Throwable)
    public DataStoreException(Throwable cause) {
        super(cause);
    }

    // Default constructor
    public DataStoreException() {
        super();
    }
}
