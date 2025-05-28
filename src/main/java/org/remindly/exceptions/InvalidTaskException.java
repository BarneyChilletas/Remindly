package org.remindly.exceptions;



public class InvalidTaskException extends RuntimeException {

        public InvalidTaskException(String message) {
            super(message);
        }

        public InvalidTaskException(String message, Throwable cause) {
            super(message, cause);
        }

}
