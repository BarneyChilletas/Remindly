package org.remindly.dao;

import org.remindly.exceptions.DataAccessException;
import org.remindly.model.TaskModel;
import org.remindly.util.DBConnection;


import java.sql.*;
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

            query = "INSERT INTO tasks (title, description, due_date) VALUES (?,?,?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, taskName);
            st.setString(2, taskDescription);
            if (dueDate != null) {
                st.setDate(3,java.sql.Date.valueOf(dueDate));

            }else {
                st.setNull(3, java.sql.Types.DATE); /*in case the due_date is null*/
            }
            st.executeUpdate();

        } catch (Exception e) {

           throw new DataAccessException(e.getMessage());
        }

    }



    public List<TaskModel> showTasks() {
        List<TaskModel> tasks = new ArrayList<>();
        try {
            query = "SELECT * FROM tasks;";
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {



                LocalDate duedate = null; // in case the due date in null
                if (rs.getDate("due_date") != null) {

                    duedate = rs.getDate("due_date").toLocalDate(); // get the value(date) from the database
                }
               TaskModel task = new TaskModel(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        duedate,
                        rs.getBoolean("is_done")
                );

                tasks.add(task);
            }
        } catch (SQLException e) {
            throw new DataAccessException("Error showing tasks" + e.getMessage());
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
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage());
        }
    }


    public void delete(Integer id) {
        try{
            query = "DELETE FROM tasks " + "WHERE id=" + id + ";";
            st = conn.createStatement();
            st.executeUpdate(query);
        }catch (SQLException e){
          throw new DataAccessException("Error deleting task" + e.getMessage());
        }
    }



}
