package com.example.test.component;

import com.example.test.mapper.EmployeeMapper;
import com.example.test.mapper.ProjectMapper;
import com.example.test.mapper.SubTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

public class OIDGenerator {
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private SubTaskMapper subTaskMapper;
    private volatile static OIDGenerator instance=new OIDGenerator();
    private OIDGenerator() { }
    public static OIDGenerator getInstance()
    {
        if (null == instance) {
            synchronized(OIDGenerator.class) {
                if (null == instance) {
                    instance = new OIDGenerator();
                }
            }
        }
        return instance;
    }
    public synchronized String createEmployeeID(){
        boolean flag = true;
        String empID;
//        do {
//            empID="EMP"+getRandomString(10);
//            flag = (employeeMapper.getEmpInfoByEmpId(empID)!=null);
//        }while (flag);
        empID="EMP"+getRandomString(10);
        return empID;
    }

    public synchronized String createProjectID(){
        boolean flag = true;
        String projectID;
//        do {
//            projectID="PRO"+getRandomString(10);
//            if(projectMapper==null){
//                System.out.println("bbb");
//            }
//            flag = (projectMapper.getProInfoByProId(projectID)!=null);
//        }while (flag);
        projectID="PRO"+getRandomString(10);
        return projectID;
    }

    public synchronized String createSubTaskID(){
        boolean flag = true;
        String subTaskID;
//        do {
//            subTaskID="TASK"+getRandomString(9);
//            flag = (subTaskMapper.getTaskInfoByProId(subTaskID)!=null);
//        }while (flag);
        subTaskID="TASK"+getRandomString(9);
        return subTaskID;
    }

    private String getRandomString(int length){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(36);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}

