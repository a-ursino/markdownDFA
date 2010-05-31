import java.io.*;

public class Main {
    static public void main(String argv[]) {
        try {
            /* Instance scanner with input file argv[0] */
           Yylex l = new Yylex(new FileReader(argv[0]));

            /* Instance parser */
            parser p = new parser(l);

            /* Start parser */
            Object result = p.parse();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
