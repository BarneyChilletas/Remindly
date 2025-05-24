package org.remindly.dao;

import org.remindly.model.TaskModel;
import org.remindly.util.DBConnection;
import org.remindly.service.TaskService.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskDAO {
    private Connection conn;
    private Statement st;
    private String query;

    public TaskDAO() throws SQLException {
        this.conn = DBConnection.connect();

    }

    public void add(String taskName, String taskDescription, LocalDate dueDate) {

        try {
            query = "INSERT INTO tasks (title,description,due_date)" + "VALUES ('" + taskName + "', '" + taskDescription + "', '" + dueDate + "');";
            st = conn.createStatement();
            st.executeUpdate(query);
            System.out.println("TASK ADDED");
            st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }



    public void searchTask(String taskName) {
        try {

            query = "SELECT * FROM tasks WHERE title = '" + taskName + "';";
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
              rs.getInt("id");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<TaskModel> showTasks() {
        List<TaskModel> tasks = new ArrayList<>();
        try {
            query = "SELECT * FROM tasks;";
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                TaskModel task = new TaskModel(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getDate("due_date").toLocalDate(),
                        rs.getBoolean("is_done"));

                tasks.add(task);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }



    /*
    *  The old input will appear, It's the user who change the inputs (if they want)
    * */
    public void update(String taskName, String taskDescription, LocalDate dueDate) {
        try {
            query = "UPDATE tasks " + "SET title='" + taskName + "', description='" + taskDescription + "' WHERE due_date='" + dueDate + "';";
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void delete(Integer id) {
        try{
            query = "DELETE FROM tasks " + "WHERE id=" + id + ";";
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }



}
