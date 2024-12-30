package ie.atu.projectserviceapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    private ProjectService projectService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        projectService = new ProjectService(projectRepository);
    }

    @Test
    void testCreateProject() {
        Project project = new Project("1", "Test Project", "Test Description", "2024-01-01", "2024-12-31", List.of("Task1"));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        Project createdProject = projectService.createProject(project);

        assertNotNull(createdProject);
        assertEquals("Test Project", createdProject.getName());
    }

    @Test
    void testGetProjectById() {
        Project project = new Project("1", "Test Project", "Test Description", "2024-01-01", "2024-12-31", List.of("Task1"));
        when(projectRepository.findById("1")).thenReturn(Optional.of(project));

        Project foundProject = projectService.getProjectById("1");

        assertNotNull(foundProject);
        assertEquals("Test Project", foundProject.getName());
    }

    @Test
    void testUpdateProject() {
        Project project = new Project("1", "Test Project", "Test Description", "2024-01-01", "2024-12-31", List.of("Task1"));
        when(projectRepository.findById("1")).thenReturn(Optional.of(project));
        when(projectRepository.save(any(Project.class))).thenReturn(project);

        project.setName("Updated Project");
        Project updatedProject = projectService.updateProject("1", project);

        assertEquals("Updated Project", updatedProject.getName());
    }

    @Test
    void testDeleteProject() {
        Project project = new Project("1", "Test Project", "Test Description", "2024-01-01", "2024-12-31", List.of("Task1"));
        when(projectRepository.findById("1")).thenReturn(Optional.of(project));

        boolean isDeleted = projectService.deleteProject("1");

        assertTrue(isDeleted);
    }

    @Test
    void testGetAllProjects() {
        List<Project> projects = List.of(
                new Project("1", "Test Project 1", "Description", "2024-01-01", "2024-12-31", List.of("Task1")),
                new Project("2", "Test Project 2", "Description", "2024-01-01", "2024-12-31", List.of("Task2"))
        );
        when(projectRepository.findAll()).thenReturn(projects);

        List<Project> allProjects = projectService.getAllProjects();

        assertEquals(2, allProjects.size());
    }
}