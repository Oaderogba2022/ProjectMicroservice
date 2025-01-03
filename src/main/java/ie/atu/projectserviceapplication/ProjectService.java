package ie.atu.projectserviceapplication;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, RabbitTemplate rabbitTemplate) {
        this.projectRepository = projectRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Project createProject(Project project) {
        Project createdProject = projectRepository.save(project);

        // Send the created project to RabbitMQ
        rabbitTemplate.convertAndSend("projectQueue", createdProject);

        return createdProject;
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

            // Send the updated project to RabbitMQ
            rabbitTemplate.convertAndSend("projectQueue", project);

            return projectRepository.save(project);
        }
        return null;
    }

    public boolean deleteProject(String projectId) {
        Project project = getProjectById(projectId);
        if (project != null) {
            projectRepository.delete(project);

            rabbitTemplate.convertAndSend("projectQueue", "Project Deleted: " + projectId);

            return true;
        }
        return false;
    }
}