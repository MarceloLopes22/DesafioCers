package br.com.cers.notification.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.cers.notification.entity.Notification;
import br.com.cers.notification.repository.NotificationRepository;
import br.com.cers.notification.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {
	
	@Autowired
	private NotificationRepository repo;

	@Override
	public Notification findByTitle(String titulo) {
		return this.repo.findByTitle(titulo);
	}

	@Override
	public Notification createOrUpdate(Notification aviso) {
		return this.repo.save(aviso);
	}

	@Override
	public Notification findByIdNotification(Integer idAviso) {
		return this.repo.findById(idAviso).get();
	}

	@Override
	public void delete(Integer idAviso) {
		this.repo.deleteById(idAviso);
	}

	@SuppressWarnings("deprecation")
	@Override
	public Page<Notification> findAll(int page, int count) {
		Pageable pages = new PageRequest(page, count);
		Page<Notification> lista = this.repo.findAll(pages);
		return lista;
	}

}
