package br.com.cers.notification.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cers.notification.controller.response.Response;
import br.com.cers.notification.entity.Notification;
import br.com.cers.notification.service.NotificationService;

@RestController
@RequestMapping("/api/notification")
@CrossOrigin(origins = "*")
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@PostMapping
	public ResponseEntity<Response<Notification>> create(HttpServletRequest request, @RequestBody Notification notification,
			BindingResult result) {
		Response<Notification> response = new Response<Notification>();

		try {
			validateComun(notification, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			Notification newNotification = notificationService.createOrUpdate(notification);
			response.setData(newNotification);

		} catch (Exception e) {
			response.getErros().add(e.getMessage());
		}
		return ResponseEntity.ok(response);
	}

	private void validateComun(Notification notification, BindingResult result) {

		if (notification.getTitle() == null || "".equals(notification.getTitle())) {
			result.addError(new ObjectError(Notification.class.getSimpleName(), "Title is mandatory."));
		}
		
		if (notification.getDescription() == null || "".equals(notification.getDescription())) {
			result.addError(new ObjectError(Notification.class.getSimpleName(), "Descriptionn is mandatory."));
		}
	}
	
	@PutMapping
	public ResponseEntity<Response<Notification>> update(HttpServletRequest request, @RequestBody Notification notification,
			BindingResult result) {
		Response<Notification> response = new Response<Notification>();

		try {
			validateUpdate(notification, result);
			validateComun(notification, result);
			if (result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErros().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			
			Notification notificationUpdated = notificationService.createOrUpdate(notification);
			response.setData(notificationUpdated);

		} catch (Exception e) {
			response.getErros().add(e.getMessage());
			ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}

	private void validateUpdate(Notification notification, BindingResult result) {
		
		if (notification.getIdnotification() == null) {
			result.addError(new ObjectError(Notification.class.getSimpleName(), "Id notification is mandatory."));
		}		
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Response<Notification>> findById(@PathVariable("id") String idNotification) {
		Response<Notification> response = new Response<Notification>();

		Notification notification = notificationService.findByIdNotification(new Integer(idNotification));

		if (notification == null) {
			response.getErros().add("Notification not found. Idnotification = " + idNotification);
			ResponseEntity.badRequest().body(response);
		}
		response.setData(notification);
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Response<String>> delete(@PathVariable("id") String idNotification) {
		Response<String> response = new Response<String>();

		Notification notification = notificationService.findByIdNotification(new Integer(idNotification));
		if (notification == null) {
			response.getErros().add("Notification not found. Idnotification = " + idNotification);
			ResponseEntity.badRequest().body(response);
		}

		notificationService.delete(new Integer(idNotification));

		return ResponseEntity.ok(new Response<String>());
	}
	
	@GetMapping(value = "{page}/{count}")
	public ResponseEntity<Response<Page<Notification>>> findAll(@PathVariable("page") int page,
			@PathVariable("count") int count) {
		Response<Page<Notification>> response = new Response<Page<Notification>>();
		Page<Notification> notification = notificationService.findAll(page, count);
		response.setData(notification);
		return ResponseEntity.ok().body(response);
	}
}
