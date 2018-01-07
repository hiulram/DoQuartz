package com.lyd.job;

import java.net.InetAddress;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.lyd.dao.PersonDao;
import com.lyd.dao.ServerDao;
import com.lyd.pojo.Person;
import com.lyd.pojo.Server;

public class MyQuartzJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
			PersonDao personDao=new PersonDao();
			ServerDao serverDao=new ServerDao();
			Integer total=null;
			Server  server=null;
			try {
			 String ip = InetAddress.getLocalHost().getHostAddress();
			        total=serverDao.getTotalsOfServer();
			        server=serverDao.getServerByIp(ip);
			        
				} catch (Exception e) {
				e.printStackTrace();
			} 
			if(total==null||server==null) {
				  return ;
			}
			//拿编号
			int count=0;
			List<Person> listAll=personDao.getALL();
			for (int i = 0; i <listAll.size(); i++) {
				if(i%total==server.getNum()) {
					System.out.println(listAll.get(i));
					count++;
				}
			}
			System.out.println("执行了"+count+"条任务");
						  
	}

}
