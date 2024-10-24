package com.SimpleProject.School_Management.controller;

import com.SimpleProject.School_Management.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/student/api")

public class StudentController {
      //let us save the student details in hashmap , we are using hashmap as database here
    HashMap<Integer, Student> database = new HashMap<>();

   // saving the student  data
    @PostMapping("/savingStudent")  // POST http method is used to save the input
    public  String saveStudent(@RequestBody Student studentrequest){   // here data type is Student class
         // here @RequestBody will take the input and assign to the object
        //adding input to hashmap
        database.put(studentrequest.getStudentId(), studentrequest);
        return "student added successfully";
    }

    //getting student details
    @GetMapping("/studentDetails")
    public HashMap<Integer, Student> fullStudentDetails(){
        return  database;
    }

    //getting student details by studentId
    @GetMapping("/studentById/{studentid}")
    public Student detailsByStudentId(@PathVariable("studentid") int studentId ){
        Student student1 = database.get(studentId);
        return student1;
    }

    //getting student by name
    //@RequestParam - it take the input as request parameter as query
    @GetMapping("/studentByName")
    public Student studentByName( @RequestParam("name") String name){
         //here we use for-each loop to traverse in the hashmap(our database) and check name is equal to given input
        for(Student student2 : database.values()){
            if(student2.getName().equals(name)) {
                return student2;
            }
        }
        return null;
    }

    //let us search student by grade - here their may be multiple students in the same grade
    // so we maintain a list to store all the students
    @GetMapping("/studentByGrade")
    public List<Student> studentByGrade( @RequestParam("grade") String grade){
        //here we use for-each loop to traverse in the hashmap(our database) and check name is equal to given input
        List<Student> mylist = new ArrayList<>();
        for(Student student2 : database.values()){
            if(student2.getGrade().equals(grade)) {
                mylist.add(student2);
            }
        }
        return mylist;
    }

    // here we can have combination request like name and grade

    @GetMapping("/studentByNameAndGrade")
    public List<Student> studentByNameAnsGrade( @RequestParam("name") String name,  @RequestParam("grade") String grade){
        //here we use for-each loop to traverse in the hashmap(our database) and check name is equal to given input
        List<Student> mylist = new ArrayList<>();
        for(Student student2 : database.values()){
            if(student2.getGrade().equals(grade) && student2.getName().equals(name)) {
                mylist.add(student2);
            }
        }
        return mylist;
    }

    // to delete the student from the database, here we use studentId because it will be unique
    @DeleteMapping("/deleteStudent/{studentid}")   //we are using delete http method here
    public String deleteStudentById( @PathVariable("studentid") int studentId){
        database.remove(studentId);
        return "student with the id "+studentId+" is deleted";
    }

    //let us update the student
    @PutMapping("/updatingStudent/{studentid}")
    public  String updateStudent(@PathVariable("studentid") int studentId, @RequestBody Student studentUpdate){
  // here we are first finding the student by student id then taking whole student class and updating whatever field we want
        Student student3 = database.get(studentId);
        //check student is present r not
        if(student3 != null){
            database.put(studentId,studentUpdate);
            return "student updated successfully";
        }else{
            return "studentId not found";
        }
    }
}
