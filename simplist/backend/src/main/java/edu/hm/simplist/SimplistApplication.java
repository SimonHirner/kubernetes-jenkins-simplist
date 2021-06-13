package edu.hm.simplist;

import edu.hm.simplist.model.Task;
import edu.hm.simplist.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimplistApplication implements CommandLineRunner {

	private final TaskRepository taskRepository;

	public SimplistApplication(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SimplistApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		taskRepository.deleteAll();
		taskRepository.save(new Task("Einkaufen", "Waschmittel, Brot, Erdbeeren, ...", false));
		taskRepository.save(new Task("Seminararbeit erstellen", "Konfiguration einer CI/CD-Pipeline mit Kubernetes", false));
	}
}

