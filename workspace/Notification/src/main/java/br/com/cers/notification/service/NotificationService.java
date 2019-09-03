package br.com.cers.notification.service;

import org.springframework.data.domain.Page;

import br.com.cers.notification.entity.Notification;

public interface NotificationService {

	Notification findByTitle(String title);

	Notification createOrUpdate(Notification notification);

	Notification findByIdNotification(Integer idNotification);

	void delete(Integer idNotification);

	Page<Notification> findAll(int page, int count);

}
