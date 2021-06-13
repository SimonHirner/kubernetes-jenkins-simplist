package edu.hm.simplist;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import edu.hm.simplist.model.Task;
import edu.hm.simplist.model.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerMockTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);

    @Autowired
    private MockMvc mvc;

    @Autowired
    TaskRepository taskRepository;

    @Test
    public void shouldGetTasks()
            throws Exception {
        int size = taskRepository.findAll().size();
        mvc.perform(MockMvcRequestBuilders
                .get("/api/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(size))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Einkaufen"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("Seminararbeit erstellen"));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        Task task = new Task("Titel", "Beschreibung", false);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(task);

        mvc.perform(MockMvcRequestBuilders
                .post("/api/tasks").contentType(APPLICATION_JSON_UTF8)
                .content(requestJson))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        Task task = new Task("Test Titel", "Test Beschreibung", false);
        String taskId = taskRepository.save(task).getId();

        mvc.perform(MockMvcRequestBuilders
                .delete("/api/tasks/" + taskId).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

}
