package ie.atu.projectserviceapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProjectControllerTest {

    @Mock
    private ProjectService projectService;

    private ProjectController projectController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this) ;
        projectController = new ProjectController(projectService);
    }

    @Test
    void testCreateProject() {
        ProjectDTO projectDTO = new ProjectDTO("1", "Test Project", "Test Description", "2024-01-01", "2024-12-31", List.of("Task1"));
        Project project = new Project("1", "Test Project", "Test Description", "2024-01-01", "2024-12-31", List.of("Task1"));
        when(projectService.createProject(any(Project.class))).thenReturn(project);

        ResponseEntity<ProjectDTO> response = projectController.createProject(projectDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(projectDTO.getId(), response.getBody().getId());
    }

    @Test
    void testGetProject() {
        Project project = new Project("1", "Test Project", "Test Description", "2024-01-01", "2024-12-31", List.of("Task1"));
        when(projectService.getProjectById("1")).thenReturn(project);

        ResponseEntity<ProjectDTO> response = projectController.getProject("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Project", response.getBody().getName());
    }

    @Test
    void testUpdateProject() {
        ProjectDTO projectDTO = new ProjectDTO("1", "Updated Project", "Updated Description", "2024-02-01", "2024-12-31", List.of("Task1"));
        Project project = new Project("1", "Updated Project", "Updated Description", "2024-02-01", "2024-12-31", List.of("Task1"));
        when(projectService.updateProject(anyString(), any(Project.class))).thenReturn(project);

        ResponseEntity<ProjectDTO> response = projectController.updateProject("1", projectDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Project", response.getBody().getName());
    }

    @Test
    void testDeleteProject() {
        when(projectService.deleteProject("1")).thenReturn(true);

        ResponseEntity<Void> response = projectController.deleteProject("1");

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    void testGetAllProjects() {
        List<Project> projects = List.of(
                new Project("1", "Test Project 1", "Description", "2024-01-01", "2024-12-31", List.of("Task1")),
                new Project("2", "Test Project 2", "Description", "2024-01-01", "2024-12-31", List.of("Task2"))
        );
        when(projectService.getAllProjects()).thenReturn(projects);

        ResponseEntity<List<ProjectDTO>> response = projectController.getAllProjects();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
    }
}

