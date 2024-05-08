package com.panel.LRapp.UserController;

import com.panel.LRapp.Dto.TasksDto;
import com.panel.LRapp.Entity.Tasks;
import com.panel.LRapp.Service.TaskService;
import com.panel.LRapp.response.TasksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/tasks")
public class TasksController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/getAllTasks")
    public List<Tasks> findAll(){
        return taskService.findAll();
    }

    @PostMapping("/save")
    public TasksResponse save(@RequestBody TasksDto tasks){
        return taskService.save(tasks);
    }

    @PutMapping("/update")
    public TasksResponse update(@RequestBody TasksDto toDoItem){
        return taskService.update(toDoItem);
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") int id){
        return taskService.delete(id);
    }

    @GetMapping("/getTasks")
    public TasksResponse findById(@RequestParam("id") int id){
        return taskService.findById(id);
    }

    @DeleteMapping("/deleteTask")
    public String deleteTask(@RequestParam("id") int id,@RequestParam("index") int indx){
        return taskService.deleteTasks(id,indx);}
//    @GetMapping("search")
//    public List<Tasks> searchByName(@RequestParam("name") String name){
//        return taskService.searchByName(name);
//    }

}
