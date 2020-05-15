package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import it.beije.mgmt.service.AttachmentService;
import it.beije.mgmt.service.JPAService;
import it.beije.mgmt.service.TimesheetService;

@RestController
@RequestMapping("api")
public class AttachmentController {
	@Autowired
	private AttachmentService attachmentService;
	
	@RequestMapping(value = "/attachments/user/{user_id}", method = RequestMethod.GET)
	public @ResponseBody List<Attachment> getAttachmentUser(@PathVariable Long id){
		
		return attachmentService.getAttachmentByUser(id);   
		
		
		
	}
	@RequestMapping(value = "/attachment/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Attachment> getAttachment(@PathVariable Long id){
		
	
		return attachmentService.getAttachmentByUser(id);   
		
		
	}
	@RequestMapping(value = "/attachment/user/{user_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Attachment getAttachmentsPost(@RequestBody Attachment attachment, Model model,
			HttpServletResponse response) throws IOException {
		
	
	
		return attachmentService.create(attachment.getUserId(),attachment);   //da controllare l'id che si passa
		
		
		
	}
	
	@RequestMapping(value = "/attachment/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Attachment getAttachmentsPost(@PathVariable Long id, @RequestBody Attachment attachment, Model model,
			HttpServletResponse response) throws IOException {
		
		
		return attachmentService.update(id,attachment);  
		
		
	}
}
