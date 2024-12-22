package ie.atu.projectserviceapplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public Project getProjectById(String projectId) {
        Optional<Project> project = projectRepository.findById(projectId);
        return project.orElse(null);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project updateProject(String projectId, Project projectDetails) {
        Project project = getProjectById(projectId);
        if (project != null) {
            project.setName(projectDetails.getName());
            project.setDescription(projectDetails.getDescription());
            project.setStartDate(projectDetails.getStartDate());
            project.setEndDate(projectDetails.getEndDate());
            project.setTasks(projectDetails.getTasks());
            return projectRepository.save(project);
        }
        return null;
    }

    public boolean deleteProject(String projectId) {
        Project project = getProjectById(projectId);
        if (project != null) {
            projectRepository.delete(project);
            return true;
        }
        return false;
    }
}