package com.houarizegai.gestioncommercial.java.database.dao;

import com.houarizegai.gestioncommercial.java.database.DBConnection;
import com.houarizegai.gestioncommercial.java.utils.UsefulMethods;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class FamilleDao {
    public static List<String[]> getFamilles() { // get all Famile [code + libelle]
        String sql = "SELECT * FROM Famille;";

        List<String[]> familles = new LinkedList<>();
        try {
            Statement st = DBConnection.con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String[] famille = {rs.getString("CodeFamille"), rs.getString("Libelle")};
                familles.add(famille);
            }

        } catch (SQLException se) {
            System.out.println("Get All Famille Error SQL");
            se.printStackTrace();
            return null;
        }

        return familles;
    }

    public static int setFamille(String[] famille) { // Edit Libelle of Famille
        StringBuilder sql = new StringBuilder("UPDATE `Famille` SET `Libelle` = ? WHERE `CodeFamille` = ?;");

        try {
            if(DBConnection.con == null) {
                return -1; // connection failed !
            }
            PreparedStatement prest = DBConnection.con.prepareStatement(sql.toString());
            prest.setString(1, famille[1]);
            prest.setString(2, famille[0]);

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Set Famille Error SQL");
            se.printStackTrace();
            return 0;
        }
    }

    public static int deleteFamille(String codeFamille) { // Remove Famille
        String sql = "DELETE FROM `Famille` WHERE CodeFamille = ?;";
        try {
            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, codeFamille);
            return prest.executeUpdate();
        } catch (SQLException se) {
            System.out.println("Delete Famille Error SQL");
            se.printStackTrace();
            return -1;
        }
    }

    public static int addFamille(String[] famille) { // Add new famille

        String sql = "INSERT INTO `famille`(`CodeFamille`, `Libelle`) VALUES (?, ?);";

        try {
            if(DBConnection.con == null)
                return -1;

            PreparedStatement prest = DBConnection.con.prepareStatement(sql);
            prest.setString(1, famille[0]);
            prest.setString(2, famille[1]);

            return prest.executeUpdate();

        } catch (SQLException se) {
            System.out.println("Add Famille Error SQL");
            se.printStackTrace();
            return 0;
        }
    }
}
