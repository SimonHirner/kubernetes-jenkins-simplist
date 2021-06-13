package edu.hm.simplist;

import edu.hm.simplist.controller.TaskController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskControllerSmokeTest {

    @Autowired
    private TaskController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    public void shouldReturnTasks() {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/tasks",
                String.class)).contains("\"title\":\"Einkaufen\"", "\"title\":\"Seminararbeit erstellen\"");
    }
}
