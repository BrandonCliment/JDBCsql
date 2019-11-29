/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiondesvols;

import java.sql.* ;

/**
 *
 * @author pedago
 */
public class GestionDesVols {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("org.postgresql.Driver");
        } catch (java.lang.ClassNotFoundException e){
            System.out.println("org.postgresql.Driver" + e.getMessage());
        }
        
        Connection conn = null ;
        
        try{
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user ="postgres";
            String passwd="postgres";
            conn = DriverManager.getConnection(url,user,passwd);
            
            afficheTableAvion(conn);
            listePilote(conn);
            salaireMoy(conn);
            okBoomer(conn);
            
            
            
            
            
            if (conn !=null) {
                conn.close();
                System.out.println("Deconnection");
            } /*else {
                System.out.println("Connection");
            }*/
        } catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
        
    }
    
    public static void afficheTableAvion(Connection conn) throws SQLException {
        String sql = "SELECT * FROM AVION";
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                System.out.println("\t Numero : " + rs.getInt("AVNUM") + "\t");
                System.out.println("\t Nom : " + rs.getString("AVNOM") + "\t");
                System.out.println("\t Capacité : " + rs.getInt("CAPACITE") + "\t");
                System.out.println("\t Localisation : " + rs.getString("LOCALISATION") + "\t");
                System.out.println("\n------------------------------------------------");
            }
            System.out.println();
            
            rs.close();
            stmt.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
        public static void listePilote(Connection conn) throws SQLException {
        String sql = "SELECT * FROM PILOTE";
        
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                System.out.println("\t Numero : " + rs.getInt("PLNUM") + "\t");
                System.out.println("\t Nom : " + rs.getString("PLNOM") + "\t");
                System.out.println("\t Prenom : " + rs.getString("PLPRENOM") + "\t");
                System.out.println("\t Ville : " + rs.getString("VILLE") + "\t");
                System.out.println("\t Salaire : " + rs.getInt("SALAIRE") + "\t");
                System.out.println("\t Date de naissance : " + rs.getString("DATENAISS") + "\t");
                System.out.println("\n------------------------------------------------");
            }
            System.out.println();
            
            rs.close();
            stmt.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
        
    public static void salaireMoy(Connection conn){
        String sql = "SELECT SUM(SALAIRE)/COUNT(*) AS moyenne FROM PILOTE";
        
            try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                System.out.println("\t Salaire moyen : " + rs.getFloat("moyenne"));
                System.out.println("\n------------------------------------------------");
            }
            System.out.println();
            
            rs.close();
            stmt.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void okBoomer(Connection conn){
        String sql ="SELECT SUM(CAPACITE) as okboomer FROM AVION";
        
            try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()){
                System.out.println("\t Capacité moyenne : " + rs.getInt("okboomer"));
                System.out.println("\n------------------------------------------------");
            }
            System.out.println();
            
            rs.close();
            stmt.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    public static void Maj_Localisation(Connection conn){
        
        String sql = " UPDATE AVION SET Localisation = ? WHERE AVNOM =A300";
        
            try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            

            System.out.println();
            
            stmt.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        
    }
}
