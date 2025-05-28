package org.remindly.controller;
import org.remindly.exceptions.DataAccessException;
import org.remindly.model.TaskModel;
import org.remindly.service.TaskService;
import org.remindly.exceptions.InvalidTaskException;
import org.remindly.exceptions.DataAccessException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TaskController {
    private final TaskService service = new TaskService(); // call TaskService for crud operations

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
        try{
            // if showing tasks have an error catch
            List<TaskModel> tasks = service.callShow();
            observableTasks = FXCollections.observableArrayList(tasks);
        } catch (DataAccessException e) {
            e.printStackTrace(); // only in console
            showError("Error showing tasks ");

        }


        taskListView.setItems(observableTasks);
        taskListView.setCellFactory(ListView -> new  ListCell<>() {
            private HBox content;
            private final Label label;
            private final Button editButton;
            private final Button deleteButton;
            {

                label = new Label();
                editButton = new Button("Edit");
                deleteButton = new Button("Delete");


                /*default: no visible*/
                deleteButton.setVisible(false);
                editButton.setVisible(false);


                /*color of the buttons*/
                editButton.setStyle("-fx-background-color:#e1d94a ; -fx-text-fill:white;");
                deleteButton.setStyle("-fx-background-color:#e03737; -fx-text-fill:white;");


                /*if click in edit -> will do*/
                editButton.setOnAction(e -> {
                    TaskModel task = getItem();
                    if (task != null) {
                        System.out.println("Edit: " + task.getId());

                    }
                });

                /*if click in delete -> it will call the service then the dao (database logic) for delete*/
                deleteButton.setOnAction(e -> {
                    TaskModel task = getItem();
                    try {
                        if (task != null) { /*call the */
                            service.callDelete(task.getId());
                        }
                    }catch (DataAccessException exc){

                        showError(exc.getMessage());
                    }

                    initialize();
                });

                content = new HBox(label, deleteButton,editButton);
                content.setSpacing(50); // space between button and title tasks
                content.setAlignment(Pos.CENTER_LEFT);

                /*When the mouse is in the tasks*/
                content.setOnMouseEntered(e -> {
                    editButton.setVisible(true);
                    deleteButton.setVisible(true);
                });
                /*no mouse*/
                content.setOnMouseExited(e -> {
                    editButton.setVisible(false);
                    deleteButton.setVisible(false);
                });


            }
            /*update list of tasks*/
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
        /*header button add*/
        boolean showing = Form.isVisible();
        Form.setVisible(!showing); /*if is false -> true || if is true -> false */
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
        // add task
        try{
            String taskName = taskNameInput.getText();
            String taskDescription = taskDescriptionInput.getText();
            LocalDate taskDueDate = dueDatePicker.getValue();
            TaskModel task = new TaskModel(0,taskName, taskDescription, taskDueDate, false);
            service.callAdd(task);
            observableTasks.add(task);
            clearForm();


        }catch(InvalidTaskException e){
            showError(e.getMessage());
        }catch(Exception e){
            showError(e.getMessage());
        }


    }





    @FXML
    protected void cancelAddTask() {
        toggleTaskForm(); // hide form
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearForm() {
        /*Clear all the inputs from a form*/
        taskNameInput.clear();
        taskDescriptionInput.clear();
        dueDatePicker.setValue(null);

    }




}