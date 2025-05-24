package org.remindly.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import org.remindly.model.TaskModel;
import org.remindly.service.TaskService;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


import java.sql.SQLException;
import java.time.LocalDate;

import java.util.List;

public class TaskController {
    private final TaskService service = new TaskService();

    @FXML
    private VBox Form;
    @FXML
    private Button btnShowForm;
    @FXML
    private TextField taskNameInput;
    @FXML
    private TextArea taskDescriptionInput;
    @FXML
    private DatePicker dueDatePicker;
    @FXML
    private ListView<TaskModel> taskListView;


    private ObservableList<TaskModel> observableTasks;


    public TaskController() throws SQLException {}

    @FXML
    public void initialize() {
        List<TaskModel> tasks = service.callShow();
        observableTasks = FXCollections.observableArrayList(tasks);

        taskListView.setItems(observableTasks);
        taskListView.setCellFactory(ListView -> new  ListCell<>() {
           private HBox content;
            private final Label label;
          /*  private final Button editButton;*/
            private final Button deleteButton;
            {

                label = new Label();
             /*   editButton = new Button("Edit");*/
                deleteButton = new Button("Delete");
                deleteButton.setVisible(false);
                /*editButton.setVisible(false);*/
                /*editButton.setStyle("-fx-background-color:#4CAF50 ;");*/
                deleteButton.setStyle("-fx-background-color:#b11d1d;");


/*
                editButton.setOnAction(e -> {
                    TaskModel task = getItem();
                    if (task != null) {
                        System.out.println("Edit: " + task);

                    }
                });*/
                deleteButton.setOnAction(e -> {
                    TaskModel task = getItem();
                    if (task != null) { /*call the */
                        service.callDelete(task.getId());
                    }
                    initialize();
                });
                content = new HBox(label, deleteButton);


               /* content.setOnMouseEntered(e -> editButton.setVisible(true));
                content.setOnMouseExited(e -> editButton.setVisible(false));*/
                content.setSpacing(100);
                content.setAlignment(Pos.CENTER_LEFT);

                content.setOnMouseEntered(e -> deleteButton.setVisible(true));
                content.setOnMouseExited(e -> deleteButton.setVisible(false));


            }
            @Override
            protected void updateItem(TaskModel task,boolean empty){
                super.updateItem(task,empty);
                if (empty || task == null) {
                   setGraphic(null);
                }else{
                    label.setText(task.toString());
                    setGraphic(content);
                }

            }
        });



    }

    @FXML
    private void toggleTaskForm() {
        boolean showing = Form.isVisible();
        Form.setVisible(!showing);
        Form.setManaged(!showing);

        if (!showing) {
            btnShowForm.setText("Hide form");
        } else {
            btnShowForm.setText("Add task");
            clearForm();
        }
    }


    @FXML
    protected void OnSaveClick() {
        try{
            String taskName = taskNameInput.getText();
            String taskDescription = taskDescriptionInput.getText();
            LocalDate taskDueDate = dueDatePicker.getValue();
            TaskModel task = new TaskModel(0,taskName, taskDescription, taskDueDate, false);
            service.callAdd(task);
            observableTasks.add(task);
            clearForm();


        }catch(IllegalArgumentException e){
            showError("Task name and Date are required");
        }catch(Exception e){
            showError(e.getMessage());
        }


    }





    @FXML
    protected void cancelAddTask() {
        toggleTaskForm();
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        taskNameInput.clear();
        taskDescriptionInput.clear();
        dueDatePicker.setValue(null);

    }




}