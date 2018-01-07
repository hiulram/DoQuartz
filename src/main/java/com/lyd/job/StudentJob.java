package com.lyd.job;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.lyd.pojo.Student;
import com.lyd.service.StudentService;
import com.lyd.utils.SendMessageUtils;

/**
 * 
 * @author Young
 * @description
 * @date 2018年1月4日 上午12:32:45
 *
 */
public class StudentJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<String> list=null;
		try {
			   list=getMessages();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		/* 从卡机下载数据 */
		StudentService service = new StudentService();
		Map<String, Student> map = service.selectAllStudents();
		 if(list!=null&&list.size()!=0) {
			 for (String string : list) {
				if(map.containsKey(string)) {
					map.remove(string);
				}
			}
		 }
		 Set<Entry<String, Student>> entrySet = map.entrySet();
		 for (Entry<String, Student> entry : entrySet) {
			Student student=entry.getValue();
			System.out.println(student);
			SendMessageUtils.send(student.getTelephone(),student.getPname(),createTime());
		}
	}

	public List<String> getMessages() throws Exception {
		InputStream is = new FileInputStream(new File("C:\\Users\\Young\\Desktop\\Java68指纹编号.xls"));
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		Sheet sheet = hssfWorkbook.getSheetAt(0);
		List<String> list = new LinkedList<>();
		for (Row row : sheet) {
			if (row.getRowNum() == 0 || row.getRowNum() == 1) {
				continue;
			}
			/* 将第四列设置为字符串类型 */
			row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
			String finger = row.getCell(3).getStringCellValue();
			list.add(finger);
		}
		return list;

	}
	public String createTime(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String time=df.format(date);
		return time;
	}
}
