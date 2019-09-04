package br.com.cers.notification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.cers.notification.entity.Notification;
import br.com.cers.notification.repository.NotificationRepository;

@SpringBootApplication
public class NotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationApplication.class, args);
	}

	@PostConstruct
	void started() {
	  TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}

	@Bean
	CommandLineRunner inicializar(NotificationRepository notificationRepository) {
		return args -> {
			initBase(notificationRepository);
		};
	}

	private void initBase(NotificationRepository notificationRepository) {
		List<Notification> notifications = new ArrayList<Notification>();
		Notification notification1 = createNotification1();
		Notification notification2 = createNotification2();

		Notification findByTitle1 = notificationRepository.findByTitle(notification1.getTitle());
		if (findByTitle1 == null) {
			notifications.add(notification1);
		}

		Notification findByTitle2 = notificationRepository.findByTitle(notification2.getTitle());
		if (findByTitle2 == null) {
			notifications.add(notification2);
		}

		notificationRepository.saveAll(notifications);

	}

	private Notification createNotification1() {
		Notification notification = new Notification();
		notification.setTitle("Titulo 1");
		notification.setDescription("Descricao 1");
		notification.setDate_publication(LocalDate.now());
		notification.setDate_visualization(LocalDate.now());
		return notification;
	}

	private Notification createNotification2() {
		Notification notification = new Notification();
		notification.setTitle("Titulo 2");
		notification.setDescription("Descricao 2");
		notification.setDate_publication(LocalDate.now());
		notification.setDate_visualization(LocalDate.now());
		return notification;
	}
}
