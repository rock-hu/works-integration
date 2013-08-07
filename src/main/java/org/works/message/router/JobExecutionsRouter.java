package org.works.message.router;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.integration.annotation.Router;

public class JobExecutionsRouter {
	@Router
	public String routeJobExecution(JobExecution jobExecution) {
		if (jobExecution.getStatus().equals(BatchStatus.FAILED)) {
			return "jobRestarts";
		} else {
			return "notifiableExecutions";
		}
	}
}
