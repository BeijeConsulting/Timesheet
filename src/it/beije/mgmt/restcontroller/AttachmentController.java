package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.entity.Attachment;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.service.AttachmentService;

@RestController
@RequestMapping("api")
public class AttachmentController {
	
	@Autowired
	private AttachmentService attachmentService;
	
	@RequestMapping(value = "/attachments/user/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Attachment> getAttachmentUser(@PathVariable Long id){
		
		try {
			return attachmentService.getAttachmentByUser(id);   
		}catch(MasterException e) {
			throw e;
		}	
	}
	
	@RequestMapping(value = "/attachment/{id}", method = RequestMethod.GET)
	public @ResponseBody Attachment getAttachment(@PathVariable Long id){
	
		return attachmentService.find(id);    	
	}
	
	
	@RequestMapping(value = "/attachment/user/{user_id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Attachment getAttachmentsPost(@RequestBody Attachment attachment, Model model,
			HttpServletResponse response) throws IOException {
		
		return attachmentService.create(attachment.getUserId(),attachment);   //da controllare l'id che si passa	
	}
	
	
	@RequestMapping(value = "/attachment/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Attachment getAttachmentsPost(@PathVariable Long id, @RequestBody Attachment attachment, Model model,
			HttpServletResponse response) {
		
		return attachmentService.update(id,attachment); 	
	}
}
