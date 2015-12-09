/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */

public class Parser {
    
    static BufferedWriter writer;
    static String token;
    static int counter;
    static Vector<String> tokens = new Vector();
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Scanner2.startRunning();
        BufferedReader file = new BufferedReader(new FileReader("scanner_output.txt"));
        writer = new BufferedWriter(new FileWriter("parser_output.txt"));
        String line = file.readLine();
        while(line!=null)
        {
            String words [] = line.split("\\s+");
            for(int i=0;i<words.length;i++)
            {
                tokens.add(words[i]);  
            }
            line = file.readLine();
        }

        
        counter =0;
        token = tokens.get(counter);
        program();
        writer.write("Program Found");
        writer.close();
        
    }
    
    public static void match(String x) 
    {
        if(token.equals(x))
        {
            System.out.println("x is correct");
           
                counter++;
                if(tokens.size()!=counter)
                token = tokens.get(counter);
        }else{
            printError();
        }
    }
    
    public static void program() throws IOException
    {
        statementSequence();
    }
    
    public static void statementSequence() throws IOException
    {
        statement();
        while(token.equals(";"))
        {
            match(token);
            statement();
        }
        writer.write("Statement Sequence Found");
        writer.newLine();
    }
    
    public static void statement() throws IOException
    {
        if(token.startsWith("if"))ifStatement();
        else if(token.equals("repeat"))repeatStatement();
        else if(token.equals("Identifier"))assignStatement();
        else if(token.equals("read"))readStatement();
        else if(token.equals("write"))writeStatement();
        else printError();
    }
    
    public static void ifStatement() throws IOException
    {
        match("if");
        exp();
        match("then");
        statementSequence();
        if(token.equals("else"))
        {
            match("else");
            statementSequence();
        }
        match("end");
        writer.write("If Statement Found");
        writer.newLine();
    }
    
    public static void repeatStatement() throws IOException
    {
        match("repeat");
        statementSequence();
        match("until");
        exp();
        writer.write("Repeat Statement Found");
        writer.newLine();
    }
    
    public static void assignStatement() throws IOException
    {
        match("Identifier");
        match(":=");
        exp();
        writer.write("Assignment Statement Found");
        writer.newLine();
    }
    
    public static void readStatement() throws IOException
    {
        match("read");
        match("Identifier");
        writer.write("Read Statement Found");
        writer.newLine();
    }
    
    public static void writeStatement() throws IOException
    {
        match("write");
        exp();
        writer.write("Write Statement Found");
        writer.newLine();
    }
    
    public static void exp() throws IOException
    {
        simpleExp();
        if(token.equals("<")||token.equals("="))
        {
            match(token);
            simpleExp();
            writer.write("Expression Found");
            writer.newLine();
        }
    }
    
    public static void simpleExp() throws IOException
    {
        term();
        while(token.equals("+")||token.equals("-"))
        {
            match(token);
            term();
        }
        writer.write("Simple Expression Found");
        writer.newLine(); 
    }
    
    public static void term() throws IOException
    {
        factor();
        while(token.equals("*")||token.equals("/"))
        {
            match(token);
            factor();
        }
        writer.write("Term Found");
        writer.newLine();
    }
    
    public static void factor() throws IOException
    {
        if(token.equals("("))
        {
            match("(");
            exp();
            match(")");
        }else if(token.equals("number"))match(token);
        else if(token.equals("Identifier"))match(token);
        else printError();
        writer.write("Factor Found");
        writer.newLine();
    }
    
    public static void printError()
    {
        System.out.println("Error");
        System.exit(0);
    }
    
    
    
    
}
