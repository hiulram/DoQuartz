package com.lyd.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TempJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		                 System.out.println("Call Me Young  !!");		
		                 
	}

}
