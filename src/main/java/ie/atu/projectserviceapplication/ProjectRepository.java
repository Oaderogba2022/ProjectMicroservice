package ie.atu.projectserviceapplication;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProjectRepository extends MongoRepository<Project, String> {
}
