package ie.atu.projectserviceapplication;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.List;

/*@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "projects")
public class Project {

    @Id
    private String id;

    @NotEmpty
    private String name;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private List<String> tasks;
}*/

@Document(collection = "projects")
public class Project {
    @Id
    private String id;
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private List<String> tasks;

    public Project() {
    }

    public Project(String id, String name, String description, String startDate, String endDate, List<String> tasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.tasks = tasks;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public List<String> getTasks() {
        return tasks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setTasks(List<String> tasks) {
        this.tasks = tasks;
    }
}