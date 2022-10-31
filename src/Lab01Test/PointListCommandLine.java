package Lab01Test;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Objects;

public class PointListCommandLine {

    public static void main(String[] args) throws IOException{
        InputStreamReader reader = new InputStreamReader(System.in);
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        ArrayPointList apl = new ArrayPointList();

        tokenizer.nextToken();
        while(!tokenizer.sval.equals("quit")){
            switch(tokenizer.sval){

                case "add":
                    tokenizer.nextToken();
                    int x = (int)tokenizer.nval;
                    tokenizer.nextToken();
                    int y =(int)tokenizer.nval;
                    apl.append(new Point(x,y));
                    break;

                case "curr":
                    Point vertex = apl.getCursor();
                    System.out.println("(" + vertex.x + ", " + vertex.y + ")");
                    break;

                case "next":
                    System.out.println(apl.goToNext());
                    break;

                case "prev":
                    System.out.println(apl.goToPrior());
                    break;

                case "start":
                    System.out.println(apl.goToBeginning());
                    break;

                case "end":
                    System.out.println(apl.goToEnd());
                    break;

                case "empty":
                    if (apl.isEmpty()) System.out.println("true");
                    else System.out.println("false");
                    break;

                case "full":
                    if (apl.isFull()) System.out.println("true");
                    else System.out.println("false");
                    break;

                case "clear":
                    apl.clear();
                    break;

                case "quit":
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n");
            }
            tokenizer.nextToken();
        }
    }

}
