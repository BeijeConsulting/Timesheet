package it.beije.mgmt.restcontroller.exception;

import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = {NoContentException.class})
	public ResponseEntity<ErrorMessage> ControllerExceptionHandler(NoContentException ex, WebRequest request) {
		int errorCode = 222;
		ErrorMessage re = new ErrorMessage();
		re.setMessage(ex.getLocalizedMessage());
		re.setStatus(errorCode+" Invalid Id");
		re.setTime(LocalDateTime.now());
		return ResponseEntity.status(errorCode).body(re);
	}
	
	@ExceptionHandler(value = {InvalidJSONException.class})
	public ResponseEntity<ErrorMessage> ControllerExceptionHandler(InvalidJSONException ex, WebRequest request) {
		int errorCode = 223;
		ErrorMessage re = new ErrorMessage();
		re.setMessage(ex.getLocalizedMessage());
		re.setStatus(errorCode+" Error in JSON");
		re.setTime(LocalDateTime.now());
		return ResponseEntity.status(errorCode).body(re);
	}
	
}




