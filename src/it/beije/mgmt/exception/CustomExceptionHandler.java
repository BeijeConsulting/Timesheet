package it.beije.mgmt.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value = {NoContentException.class})
	public ResponseEntity<ErrorMessage> ControllerExceptionHandler(NoContentException ex, WebRequest request) {
		int errorCode = 222;
		ErrorMessage re = new ErrorMessage();
		re.setMessage(ex.getLocalizedMessage());
		re.setStatus(errorCode+" Invalid Id");
		re.setTime(LocalDateTime.now());
		log.error(re.getMessage());
		return ResponseEntity.status(errorCode).body(re);
	}
	
	@ExceptionHandler(value = {InvalidJSONException.class})
	public ResponseEntity<ErrorMessage> ControllerExceptionHandler(InvalidJSONException ex, WebRequest request) {
		int errorCode = 223;
		ErrorMessage re = new ErrorMessage();
		re.setMessage(ex.getLocalizedMessage());
		re.setStatus(errorCode+" Error in JSON");
		re.setTime(LocalDateTime.now());
		log.error(re.getMessage());
		return ResponseEntity.status(errorCode).body(re);
	}
	
	@ExceptionHandler(value = {DBException.class})
	public ResponseEntity<ErrorMessage> ControllerExceptionHandler(DBException ex, WebRequest request) {
		int errorCode = 221;
		ErrorMessage re = new ErrorMessage();
		re.setMessage(ex.getLocalizedMessage());
		re.setStatus(errorCode+" Error in DB");
		re.setTime(LocalDateTime.now());
		log.error(re.getMessage());
		return ResponseEntity.status(errorCode).body(re);
	}
	
	@ExceptionHandler(value = {ServiceException.class})
	public ResponseEntity<ErrorMessage> ControllerExceptionHandler(ServiceException ex, WebRequest request) {
		int errorCode = 224;
		ErrorMessage re = new ErrorMessage();
		re.setMessage(ex.getLocalizedMessage());
		re.setStatus(errorCode+" Error in Service");
		re.setTime(LocalDateTime.now());
		log.error(re.getMessage());
		return ResponseEntity.status(errorCode).body(re);
	}
	
	@ExceptionHandler(value = {IllegalDateException.class})
	public ResponseEntity<ErrorMessage> ControllerExceptionHandler(IllegalDateException ex, WebRequest request) {
		int errorCode = 225;
		ErrorMessage re = new ErrorMessage();
		re.setMessage(ex.getLocalizedMessage());
		re.setStatus(errorCode+" Error in date");
		re.setTime(LocalDateTime.now());
		log.error(re.getMessage());
		return ResponseEntity.status(errorCode).body(re);
	}
	
	@ExceptionHandler(value = {NotExistPcException.class})
	public ResponseEntity<ErrorMessage> ControllerExceptionHandler(NotExistPcException ex, WebRequest request) {
		int errorCode = 226;
		ErrorMessage re = new ErrorMessage();
		re.setMessage(ex.getLocalizedMessage());
		re.setStatus(errorCode+" Error in pc");
		re.setTime(LocalDateTime.now());
		log.error(re.getMessage());
		return ResponseEntity.status(errorCode).body(re);
	}
	
	@ExceptionHandler(value = {UpdateException.class})
	public ResponseEntity<ErrorMessage> ControllerExceptionHandler(UpdateException ex, WebRequest request) {
		int errorCode = 227;
		ErrorMessage re = new ErrorMessage();
		re.setMessage(ex.getLocalizedMessage());
		re.setStatus(errorCode+" Error in update");
		re.setTime(LocalDateTime.now());
		log.error(re.getMessage());
		return ResponseEntity.status(errorCode).body(re);
	}
}




