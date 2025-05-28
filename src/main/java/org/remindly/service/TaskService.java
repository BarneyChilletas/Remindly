package org.remindly.service;

import org.remindly.dao.TaskDAO;
import org.remindly.exceptions.DataAccessException;
import org.remindly.model.TaskModel;
import org.remindly.exceptions.InvalidTaskException;
import java.sql.SQLException;

import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private final TaskDAO dao = new TaskDAO();

    public TaskService() throws SQLException {}


    public void callAdd(TaskModel task) throws RuntimeException {
            if ( task.getTaskName() == null || task.getTaskName().isEmpty() ) {
                    throw new InvalidTaskException("Task name cannot be empty");
            } else if ( task.getDueDate() != null && task.getDueDate().isBefore(LocalDate.now())) {
                    throw new InvalidTaskException("Due Date cannot be in the past");
            } else{
                dao.add(task.getTaskName(), task.getTaskDescription(),  task.getDueDate());
            }
    }


    public List<TaskModel> callShow() {
        try {
            return dao.showTasks();
        } catch (Exception e) {
            throw new DataAccessException("Error in obtaining tasks " ,  e);
        }


    }

    public void callDelete(Integer id){
        try{
            if (null == id || callShow().isEmpty()) {
                throw new DataAccessException("Error in obtaining tasks id");
            }else{
                dao.delete(id);
            }
        }catch (DataAccessException e){
            throw new DataAccessException("Error in deleting task ", e);

        }

    }




}
