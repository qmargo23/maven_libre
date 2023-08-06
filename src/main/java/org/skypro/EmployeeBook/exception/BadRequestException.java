package org.skypro.EmployeeBook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

public class BadRequestException extends HttpStatusCodeException {

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}