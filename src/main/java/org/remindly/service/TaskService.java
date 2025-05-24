package org.remindly.service;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.remindly.controller.TaskController;
import org.remindly.dao.TaskDAO;
import org.remindly.model.TaskModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private final TaskDAO dao = new TaskDAO();

    public TaskService() throws SQLException {
    }


    public void callAdd(TaskModel task) throws  IllegalArgumentException {
            if ( task.getTaskName() == null || task.getTaskName().isEmpty() || task.getDueDate() == null ) {
                    throw new IllegalArgumentException();
            }else{
                dao.add(task.getTaskName(), task.getTaskDescription(),  task.getDueDate());

            }
    }
    public List<TaskModel> callShow() throws  IllegalArgumentException {
        try {

            return dao.showTasks();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public void callDelete(Integer id) throws  IllegalArgumentException {
            if (null == id || callShow().isEmpty()) {
                  throw new IllegalArgumentException();
            }else{
                dao.delete(id);
            }
    }




}
