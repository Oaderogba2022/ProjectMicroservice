package ie.atu.projectserviceapplication;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ProjectMessageConsumer {

    @RabbitListener(queues = "notificationQueues")
    public void handleProject(Project project) {
        System.out.println("Received Project: " + project);
    }
}
