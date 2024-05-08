package com.panel.LRapp.Service;


import com.panel.LRapp.Dto.TasksDto;
import com.panel.LRapp.Entity.Tasks;
import com.panel.LRapp.Repo.TasksRepo;
import com.panel.LRapp.response.TasksResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TasksRepo tasksRepo;

    @Override
    public TasksResponse findById(int id) {
        if (tasksRepo.findById(id).isEmpty()){
            return new TasksResponse("Task not found", null);
        }

        else{
            return new TasksResponse("Task found", tasksRepo.findById(id).get());
        }


    }

    @Override
    public TasksResponse save(TasksDto tDto) {
        Tasks t=new Tasks(tDto.getTitle(),tDto.getDate(),false);
        return new TasksResponse("Tasks Save Successfully ",tasksRepo.save(t));
    }

    @Override
    public TasksResponse update(TasksDto tDto) {
        Optional<Tasks> t=tasksRepo.findById(tDto.getId());
        if(t.isEmpty()){
            return new TasksResponse("Tasks not found",null);
        }
        t.get().setTitle(tDto.getTitle());
        t.get().setDate(tDto.getDate());
        t.get().setDone(tDto.isDone());
        return new TasksResponse("Tasks updated Successfully ",tasksRepo.save(t.get()));
    }

    @Override
    public String delete(int id) {

        Optional<Tasks> t=tasksRepo.findById(id);
        if(t.isEmpty()){
            return "Tasks already deleted";
        }
        tasksRepo.deleteById(id);
        return "Tasks deleted Successfully";
    }

    @Override
    public List<Tasks> findAll() {
        return tasksRepo.findAll();
    }

    @Override
    public String deleteTasks(int id, int indx) {
        Optional<Tasks> task = tasksRepo.findById(id);
        task.get().getTitle().set(indx,null);
        return "deleted";
    }

//    @Override
//    public List<Tasks> searchByName(String name) {
//        return tasksRepo.findByName(name);
//    }
}
