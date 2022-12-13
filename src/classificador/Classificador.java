/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author SISTRAM4
 */
public class Classificador extends BaseDAO {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        List<List<String>> records = new ArrayList<>();
        connection = conexao.conectar();
        Statement stmt = null;
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\bradesco\\bl6.csv"))) {
            String line;
            int u = 0;
            while ((line = br.readLine()) != null) {
                //System.out.println(line);
                String[] values = line.split("#");
                /*for(int i=0;i<values.length;i++)
                 {                 
                    System.out.println(i+" - "+ values[i]);
                 }
                 
                 System.out.println("================");*/
                records.add(Arrays.asList(values));
                try {
                    System.out.println(records.get(u).get(0) + " - " + records.get(u).get(1) + " - " + records.get(u).get(4) + " - " + records.get(u).get(14) + " - " + records.get(u).get(16));
                    String id = records.get(u).get(0);
                    String descricao = records.get(u).get(1);
                    String vertical = records.get(u).get(4);
                    String status = records.get(u).get(14);
                    String analista = records.get(u).get(16);
                    String devinfra = records.get(u).get(18);
                    
                    if(status.equals("concluida"))
                    {
                        String qryInsereConcluidas = "insert into concluidas (id,descricao,vertical,status,analista,devinfra) values ('"+ id +"', '"+ descricao +"', '"+ vertical +"', '"+ status +"', '"+ analista +"','"+ devinfra +"')";
                        System.out.println(qryInsereConcluidas);
                        stmt = connection.createStatement();
                        stmt.execute(qryInsereConcluidas);
                        connection.commit();
                    }
                    

                } catch (Exception e) {
                }
                u++;
            }
        }

    }

}
