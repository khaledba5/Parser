/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author root
 */

public class Parser {
    
    static BufferedWriter writer;
    static String token;
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        //Scanner scanner = new Scanner();
        //scanner.startRunning();
        writer = new BufferedWriter(new FileWriter("parser_output.txt"));
        program();
        
    }
    
    public void match(String x) throws IOException
    {
        if(token.equals(x))
        {
            System.out.println("x is correct");
            writer.write(x);
        }else{
            System.out.println(x+" don't belong to the grammer rules");
            System.exit(0);
        }
    }
    
    public static void program()
    {
        statementSequence();
    }
    
    public static void statementSequence()
    {
        while(token!=null)
        {
            statement();
        }
    }
    
    public static void statement()
    {
        
    }
    
}
