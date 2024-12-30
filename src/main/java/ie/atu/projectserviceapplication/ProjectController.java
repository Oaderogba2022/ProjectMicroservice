package ie.atu.projectserviceapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    /*@PostMapping
    public ResponseEntity<Project> createProject(@RequestBody Project project) {
        Project createdProject = projectService.createProject(project);
        return new ResponseEntity<>(createdProject, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProject(@PathVariable String projectId) {
        Project project = projectService.getProjectById(projectId);
        return project != null ? new ResponseEntity<>(project, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(@PathVariable String projectId, @RequestBody Project project) {
        Project updatedProject = projectService.updateProject(projectId, project);
        return updatedProject != null ? new ResponseEntity<>(updatedProject, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable String projectId) {
        boolean isDeleted = projectService.deleteProject(projectId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectDTO projectDTO) {
        Project project = ProjectMapper.toEntity(projectDTO);
        Project createdProject = projectService.createProject(project);
        ProjectDTO createdProjectDTO = ProjectMapper.toDTO(createdProject);
        return new ResponseEntity<>(createdProjectDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> getProject(@PathVariable String projectId) {
        Project project = projectService.getProjectById(projectId);
        if (project != null) {
            ProjectDTO projectDTO = ProjectMapper.toDTO(project);
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectDTO> updateProject(@PathVariable String projectId, @RequestBody ProjectDTO projectDTO) {
        Project updatedProject = ProjectMapper.toEntity(projectDTO);
        Project project = projectService.updateProject(projectId, updatedProject);
        if (project != null) {
            ProjectDTO updatedProjectDTO = ProjectMapper.toDTO(project);
            return new ResponseEntity<>(updatedProjectDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable String projectId) {
        boolean isDeleted = projectService.deleteProject(projectId);
        return isDeleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDTO>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        List<ProjectDTO> projectDTOs = projects.stream()
                .map(ProjectMapper::toDTO)
                .toList();
        return new ResponseEntity<>(projectDTOs, HttpStatus.OK);
    }
}
