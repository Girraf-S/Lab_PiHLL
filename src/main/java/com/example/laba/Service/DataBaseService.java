package com.example.laba.Service;

import com.example.laba.controller.ActionController;
import com.example.laba.models.ParamsModel;
import com.example.laba.models.ResultModel;
import com.example.laba.models.ShowModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseService {


    private final String url = "jdbc:h2:D:\\laba\\database\\Equations";
    private final String user = "girrafe-s";
    private final String password = "sHem03+v";
    private Connection connection;
    private Statement statement;
    private ResultSet rs;

    private static Logger logger = LoggerFactory.getLogger(ActionController.class);

    private String toDote(Double num) {
        Integer numInt = num.intValue();
        return String.format("%d.%d",
                numInt, (int) ((num - numInt.doubleValue()) * 10));
    }

    public void put(ParamsModel paramsModel, ResultModel resultModel) {//
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            String sqlRequest = String.
                    format("INSERT INTO EQUATIONS (FIRST_ARG, MODE, SECOND_ARG, RESULT) VALUES (%s, '%c', %s, %s)",
                            toDote(paramsModel.getX()),
                            paramsModel.getMode(),
                            toDote(paramsModel.getY()),
                            toDote(resultModel.getResult()));
            logger.info(sqlRequest);
            statement.executeUpdate(sqlRequest);
            logger.info("succes");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void select() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT * FROM EQUATIONS");
            logger.info("succes");
            while (rs.next()) {
                System.out.printf("%d %.3f %s %.3f = %.3f\n",
                        rs.getInt("ID"),
                        rs.getDouble("FIRST_ARG"),
                        rs.getString("MODE"),
                        rs.getDouble("SECOND_ARG"),
                        rs.getDouble("RESULT")
                );
            }
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

    public void truncate() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.executeUpdate("TRUNCATE TABLE EQUATIONS");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    public void drop() {
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE EQUATIONS");
            statement.executeUpdate("CREATE TABLE EQUATIONS(ID INT PRIMARY KEY AUTO_INCREMENT, FIRST_ARG FLOAT, MODE CHAR, SECOND_ARG FLOAT, RESULT FLOAT)");

        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
    public List<ShowModel> uploadToCache() {

        List<ShowModel> list = new ArrayList<>();
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();

            rs = statement.executeQuery("SELECT * FROM EQUATIONS");
            logger.info("succes");
            while (rs.next()) {
                ShowModel showModel = new ShowModel(rs.getDouble("FIRST_ARG"),
                        rs.getDouble("SECOND_ARG"),
                        rs.getString("MODE").charAt(0),
                        rs.getDouble("RESULT"));
                list.add(showModel);
            }
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return list;
    }
    public int async(ParamsModel paramsModel){
        int id=0;
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            String sqlRequest = String.
                    format("INSERT INTO EQUATIONS (FIRST_ARG, MODE, SECOND_ARG) VALUES (%s, '%c', %s)",
                            toDote(paramsModel.getX()),
                            paramsModel.getMode(),
                            toDote(paramsModel.getY()));
            logger.info(sqlRequest);
            statement.executeUpdate(sqlRequest);
            rs = statement.executeQuery("SELECT ID FROM EQUATIONS");

            while (rs.next()){
                if(id<rs.getInt("ID"))id =rs.getInt("ID");
            }
            logger.info("succes");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return id;
    }
    public void asyncResult(ResultModel result, int id){
        try {
            Class.forName("org.h2.Driver");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            String sqlRequest = String.
                    format("UPDATE EQUATIONS SET RESULT = %s WHERE ID = %d",
                            toDote(result.getResult()), id);

            statement.executeUpdate(sqlRequest);
            logger.info("succes");
        } catch (ClassNotFoundException e) {
            logger.error(e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }
}
