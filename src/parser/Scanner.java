/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author khaledba5
 */
public class Scanner {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static boolean isNumber(char x){
        try
        {
            double d = Double.parseDouble(Character.toString(x));
        }catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }
    public static boolean isLetter(char x){
        return Character.toString(x).matches("[a-zA-Z]+");
    }
 
    
    public static void startRunning() throws FileNotFoundException, IOException {
        // Reading From File
        BufferedReader file = new BufferedReader(new FileReader("tiny_sample_code.txt"));
        String line = file.readLine();
        
        //Writing To File
        BufferedWriter output = new BufferedWriter(new FileWriter("scanner_output.txt"));
        

        
        //Array Containing Reserved Words
        String reserved []={"if","then","else","end","repeat","until","read","write"};
        char symbols []={'+','-','*','/','=','<','>',';'};
        
        while(line !=null){
            char characters[] = line.toCharArray();
            for(int i=0;i<characters.length;i++)
            {
                List state = new ArrayList();       //List Store Recognized Tokens
                char Num = 'n';
                char Letter = 'n';
                if(isNumber(characters[i]))
                {
                    Num = 'y';      //indicates this characters is digit
                }else if(isLetter(characters[i]))
                {
                    Letter ='y';    //indicates this character is letter
                }
                switch(characters[i])
                {
                    
                    //*************Start of White Spcaes State******************
                    case ' ':
                        continue;
                    //*************End of White Spaces State********************
                        
                      
                        
                        
                        
                    //*************Start of Comment State***********************
                    case '{':
                        //State Of Comment
                        while(characters[i]!='}')i++;
                        break;
                    //**************End of Comment State************************
                        
                        
                        
                        
                        
                        
                    //*************Start of INASSIGN State**********************
                    case ':':
                        i++;
                        switch(characters[i])
                        {
                            case '=':
                                output.write(":= :Special Symbol");
                                output.newLine();
                                break;
                            default:
                                i--;
                        }
                    break;
                    //*************End of INASSIGN State************************
                        
                        
                        
                        
                    default:
                    //****************Start of INNUM State**********************
                        if(Num=='y')
                        {
                            while(isNumber(characters[i]))
                            {
                                state.add(characters[i]);
                                i++;
                                if(characters.length==i)break;
                            }
                            i--;
                            for(int j=0;j<state.size();j++)
                            {
                                output.write(state.get(j).toString());
                            }
                            output.write(" :number");
                            output.newLine();
                            continue;
                        }
                    //****************End of INNUM State************************
                    
                    
                    
                    
                        
                     //**************Start of INID State************************
                        else if(Letter=='y')
                        {
                            
                            while(isLetter(characters[i]))
                            {
                                state.add(characters[i]);
                                i++;
                                if(characters.length==i)break;
                            }
                            i--;
                            String word[] = new String[state.size()];
                            StringBuilder builder = new StringBuilder();
                            for(int j=0;j<state.size();j++)
                            {
                                word[j] = state.get(j).toString();
                                output.write(state.get(j).toString());
                                builder.append(word[j]);
                            }
                            String Final = builder.toString();
                            int flag =0;
                            for(int j=0;j<reserved.length;j++)
                            {
                                if(Final.equals(reserved[j]))
                                {
                                    flag =1;
                                    break;
                                }
                            }
                            if(flag==1)output.write(" :Reserved Word");
                            else output.write(" :Identifier");
                            output.newLine();
                            continue;
                        }
                    //****************End of INID State*************************
                    
                    
                    
                    
                    //****************Special Symbols***************************
                        else
                        {
                            for(int j=0;j<symbols.length;j++)
                            {
                                if(characters[i]==symbols[j])
                                {
                                    output.write(characters[i]);
                                    output.write(" :Special Symbol");
                                    output.newLine();
                                    break;
                                }else if(characters[i]=='(')
                                {
                                    int count =0;
                                    while(characters[i]!=')')
                                    {
                                        i++;
                                        count++;
                                    }
                                    output.write("() :Special Symbols");
                                    output.newLine();
                                    i = i-count;
                                    break;
                                }
                            }
                            
                        }
                        
                        
                        
                }
            }
            line = file.readLine();
        }
        output.close();
    }
    
}
