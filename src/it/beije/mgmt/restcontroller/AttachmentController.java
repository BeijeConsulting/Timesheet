package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.Address;
import it.beije.mgmt.entity.Attachment;
import it.beije.mgmt.entity.Computer;
import it.beije.mgmt.entity.Contract;
import it.beije.mgmt.entity.User;
import it.beije.mgmt.service.JPAService;

@RestController
@RequestMapping("api")
public class AttachmentController {
	
	@RequestMapping(value = "/attachments/user/{user_id}", method = RequestMethod.GET)
	public @ResponseBody List<Attachment> getAttachmentUser(@PathVariable Long id){
		
		//return AttachmentService.getAttachmentsByUser(id);   chiamata al service
		
		
		return null;
	}
	@RequestMapping(value = "/attachment/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Attachment> getAttachment(@PathVariable Long id){
		
	
		//return AttachmentService.getAttachmentsById(id);   chiamata al service
		return null;
		
	}
	@RequestMapping(value = "/attachment/user/{user_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Attachment getAttachmentsPost(@RequestBody Attachment attachment, Model model,
			HttpServletResponse response) throws IOException {
		
	
	
		//return AttachmentService.insertAttachment(id);   chiamata al service
		
		
		
		return attachment;
	}
	
	@RequestMapping(value = "/attachment/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Attachment getAttachmentsPost(@PathVariable Long id, @RequestBody Attachment attachment, Model model,
			HttpServletResponse response) throws IOException {
		
		
		//return AttachmentService.update(attachment,id);   chiamata al service
		
		return null;
	}
}
