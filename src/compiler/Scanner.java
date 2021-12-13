package compiler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Scanner extends UI {

    ArrayList<ArrayList<String>> tokenTable = new ArrayList<>();
    ArrayList<String> errorList = new ArrayList<>();
    
    final private String[] _KEYWORDS_ = {
        "BEGIN", "CALL", "DECLARE", "DO", "ELSE", "END", "ENDIF", "ENDUNTIL",
        "ENDWHILE", "IF", "INTEGER", "PARAMETERS", "PROCEDURE", "PROGRAM",
        "READ", "REAL", "SET", "THEN", "UNTIL", "WHILE", "WRITE"
    };
    
    final private String[] _OPERATOR_ = {
        ".", ";", ",", "(", ")", "=",
        "<", ">", "!", "+", "-", "*"
    };

    public Scanner() {
        setActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                scan(getCode());
            }
        });
    }

    void scan(String code) {
        
        String words[] =code.replaceAll("\n", " ").replaceAll("\\s+", " ").split(" ");
       
        for (int i = 0; i < words.length; ++i) {
            
            if (!isKeyword(words[i]).equals("NOTDEFINED"))
            {
                addToken(words[i], isKeyword(words[i]));
            }
            else if (!isOperator(words[i]).equals("NOTDEFINED"))
            {
                addToken(words[i], isOperator(words[i]));
            }
            else if (!isConstant(words[i]).equals("NOTDEFINED"))
            {
                addToken(words[i], isConstant(words[i]));
            }
            else if (!isIdentifier(words[i]).equals("NOTDEFINED"))
            {
                addToken(words[i], "Identifier".toUpperCase());
            }
            else
            {
                errorList.add(words[i]);
            }
            
        }
        
        setErrorList(errorList);
        setLexemeTable(tokenTable);
        //************************
        reset();
    }

    /**
     * Method that check if the word is a keyword
     *
     * @param word
     * @return return The keyword if it's not a valid keyword return NOTDEFINED
     */
    String isKeyword(String word) {
        
        //TODO 4: Implement this method
        word = word.toUpperCase();
        for (int i = 0; i < _KEYWORDS_.length; ++i) {
            if (word.equals(_KEYWORDS_[i]))
                return _KEYWORDS_[i];
        }
        return "NOTDEFINED";
    }
   

    /**
     * Method that check if the word is a Identifier
     *
     * @param word
     * @return return The Identifier if it's not a valid Identifier return
     * NOTDEFINED
     */
    
    // regular expression  character(digit/character)*
    String isIdentifier(String word) {
        //TODO 5: Implement this method
        
        if(Character.isAlphabetic(word.charAt(0)))
        {
            
            for(int x = 1; x < word.length(); x++)
            {
                if(!Character.isAlphabetic(word.charAt(x))&& !Character.isAlphabetic(word.charAt(x)))
                {
                    return "NOTDEFINED";
                }
            }
            
           
        }
        else
        {
            return "NOTDEFINED";
        }

        return "Identifier".toUpperCase();
    }
    
    /**
     * Method that check if the word is a Operator
     *
     * @param word
     * @return return The Operator if it's not a valid OPerator return
     * NOTDEFINED
     */
    String isOperator(String word) {
        //TODO 6: Implement this method
        /* wor*/
        for (int i = 0; i < _OPERATOR_.length; ++i) {
            if (word.equals(_OPERATOR_[i]))
                return _OPERATOR_[i];
        }
        
        return "NOTDEFINED";
    }

    /**
     * Method that check if the word is a Constant
     *
     * @param word
     * @return return The Constant if it's not a valid Constant return
     * NOTDEFINED
     */
       // regular expression
      // digit(. digit+)
    String isConstant(String word) {
        //TODO 7: Implement this method
        boolean dotflag = false;
        for(int x = 0; x < word.length(); x++)
        {
            if(!Character.isDigit(word.charAt(x)))
            {
                if(word.charAt(x)=='.' && !dotflag)
                {
                    dotflag = true;
                }
                else
                {
                    return "NOTDEFINED";
                }
            }
        }
        return "Constant".toUpperCase();  
    }

    /**
     * Method add pair of word and it's Token to TokenTable
     *
     * @param word the original word
     * @param token it's Token
     */
    void addToken(String word, String token) {
        ArrayList<String> row = new ArrayList();
        row.add(word);
        row.add(token);
        tokenTable.add(row);
    }
    
    String getToken(String word)
    {   
        if(!"NOTDEFIEND".equals(isKeyword(word)))
        {
            return isKeyword(word);
        }
        else if(!"NOTDEFIEND".equals(isOperator(word)))
        {
            return isOperator(word);
        }
        else if(!"NOTDEFIEND".equals(isConstant(word)))
        {
            return isConstant(word);
        }
        else if(!"NOTDEFIEND".equals(isIdentifier(word)))
        {
            return isIdentifier(word);
        }
        return "NOTDEFIEND";
    }
    

    /**
     * Method to clear error list and table of tokens
     */
    void reset() {
        tokenTable.clear();
        errorList.clear();
    }

    
    
}