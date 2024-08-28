package org.example.exception;

public class ServiceException extends Exception {
    // Constructor that accepts a message and a cause (Throwable)
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    // Optionally, you can also include other constructors for flexibility:

    // Constructor that accepts only a message
    public ServiceException(String message) {
        super(message);
    }

    // Constructor that accepts only a cause (Throwable)
    public ServiceException(Throwable cause) {
        super(cause);
    }

    // Default constructor
    public ServiceException() {
        super();
    }
}
