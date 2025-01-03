package ie.atu.projectserviceapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class ProjectControllerIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @InjectMocks
    private ProjectController projectController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
    }

    @Test
    public void testCreateProject() throws Exception {
        ProjectDTO projectDTO = new ProjectDTO("1", "Test Project", "Test Description", "2025-01-01", "2025-12-31", List.of("Task 1"));
        when(projectService.createProject(any())).thenReturn(new Project("1", "Test Project", "Test Description", "2025-01-01", "2025-12-31", List.of("Task 1")));

        mockMvc.perform(post("/projects")
                        .contentType("application/json")
                        .content("{\"name\":\"Test Project\", \"description\":\"Test Description\", \"startDate\":\"2025-01-01\", \"endDate\":\"2025-12-31\", \"tasks\":[\"Task 1\"]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Test Project"));
    }

    @Test
    public void testGetProject() throws Exception {
        Project project = new Project("1", "Test Project", "Test Description", "2025-01-01", "2025-12-31", List.of("Task 1"));
        when(projectService.getProjectById("1")).thenReturn(project);

        mockMvc.perform(get("/projects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("Test Project"));
    }

    @Test
    public void testDeleteProject() throws Exception {
        when(projectService.deleteProject("1")).thenReturn(true);

        mockMvc.perform(delete("/projects/1"))
                .andExpect(status().isNoContent());
    }
}
