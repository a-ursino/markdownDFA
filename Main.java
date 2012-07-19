import java.io.*;

public class Main {
    
    
    static public void main(String argv[]) {
        //System.out.printf("%s",argv[0]);
        
        try {
            
           lexer l = new lexer(new FileReader(argv[0]));

            
            parser p = new parser(l);

            
            Object result = p.parse();
//            Object result = p.debug_parse();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
