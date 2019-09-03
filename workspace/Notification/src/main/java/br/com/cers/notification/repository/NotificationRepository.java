package br.com.cers.notification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import br.com.cers.notification.entity.Notification;

@Component
public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	
	Notification findByTitle(String title);

}
