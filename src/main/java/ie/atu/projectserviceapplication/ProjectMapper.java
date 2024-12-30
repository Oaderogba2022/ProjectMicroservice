package ie.atu.projectserviceapplication;

public class ProjectMapper {
    public static ProjectDTO toDTO(Project project) {
        return new ProjectDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getStartDate(),
                project.getEndDate(),
                project.getTasks()
        );
    }

    public static Project toEntity(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setId(projectDTO.getId());
        project.setName(projectDTO.getName());
        project.setDescription(projectDTO.getDescription());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setTasks(projectDTO.getTasks());
        return project;
    }
}
