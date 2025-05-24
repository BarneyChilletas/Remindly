package org.remindly.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class TaskModel {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty taskName = new SimpleStringProperty();
    private final StringProperty taskDescription = new SimpleStringProperty();
    private final ObjectProperty<LocalDate> dueDate = new SimpleObjectProperty<>();
    private final BooleanProperty taskStatus = new SimpleBooleanProperty();

   public TaskModel(Integer id, String taskName,
                    String taskDescription,
                    LocalDate dueDate,
                    Boolean taskStatus) {
        this.id.set(id);
        this.taskName.set(taskName);
        this.taskDescription.set(taskDescription);
        this.dueDate.set(dueDate);
        this.taskStatus.set(taskStatus);

   }


    public Integer getId(){
       return id.get();
    }



    public String getTaskName() {
        return taskName.get();
    }

    public StringProperty taskNameProperty() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription.get();
    }

    public StringProperty taskDescriptionProperty() {
        return taskDescription;
    }

    public LocalDate getDueDate() {
        return dueDate.get();
    }

    public BooleanProperty getTaskStatus() {
        return taskStatus;
    }

    @Override
    public String toString() {
        return String.format(getTaskName());


    }
}
