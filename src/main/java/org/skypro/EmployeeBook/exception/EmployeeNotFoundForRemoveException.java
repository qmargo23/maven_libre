package org.skypro.EmployeeBook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class EmployeeNotFoundForRemoveException extends HttpStatusCodeException {

    public EmployeeNotFoundForRemoveException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
