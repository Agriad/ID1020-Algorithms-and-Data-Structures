import java.io.BufferedReader;
import java.io.FileReader;

import static java.lang.System.out;

/*
README
This is an implementation of a filter that detects if the parentheses in the input is balanced.
Since the implementation is done with a static array size there is a limit of 100 concurrent open {[( or 100 open {[(
that is not closed before adding another open {[(.
*/

public class Lab1Question6
{
    private char[] charArray = new char[100];
    private int counter1 = 0;
    private int counter2 = 0;
    private int counter3 = 0;
    private int arrPointer = 0;

    public String in(String s)
    {
        for (int x = 0; x < s.length(); x++)  //iterates through the string a char at a time
        {
            char c = s.charAt(x);

            if ((int)(c) == (int)('{'))  //checks if it is open {[(
            {
                counter1++;
                charArray[arrPointer] = c;
                arrPointer++;
                //out.println("1" + c);
            }
            else if ((int)(c) == (int)('['))
            {
                counter2++;
                charArray[arrPointer] = c;
                arrPointer++;
                //out.println("2" + c);
            }
            else if ((int)(c) == (int)('('))
            {
                counter3++;
                charArray[arrPointer] = c;
                arrPointer++;
                //out.println("3" + c);
            }

            if (0 <= (arrPointer - 1))  //if there is/are open {[( before check if they follow the convention
            {  //does so by checking the previous entry to make sure ([)] chain like parentheses don't count as balanced
                if (((int)(c) == (int)('}')) && ((int)('{') == (int)(charArray[arrPointer - 1])))
                {
                    arrPointer--;
                    counter1--;
                    //out.println("4" + c);
                }
                else if (((int)(c) == (int)(']')) && ((int)('[') == (int)(charArray[arrPointer - 1])))
                {
                    arrPointer--;
                    counter2--;
                    //out.println("5" + c);
                }
                else if (((int)(c) == (int)(')')) && ((int)('(') == (int)(charArray[arrPointer - 1])))
                {
                    arrPointer--;
                    counter3--;
                    //out.println("6" + c);
                }
            }
            else  //if only closed }])
            {
                if ((int)(c) == (int)('}'))
                {
                    counter1--;
                    //out.println("7" + c);
                }
                else if ((int)(c) == (int)(']'))
                {
                    counter2--;
                    //out.println("8" + c);
                }
                else if ((int)(c) == (int)(')'))
                {
                    counter3--;
                    //out.println("9" + c);
                }
            }
        }

        arrPointer = 0;

        if ((counter1 + counter2 + counter3) == 0)
        {
            counter1 = 0;
            counter2 = 0;
            counter3 = 0;
            return ("Perfectly balanced. As all things should be.");
        }
        else
        {
            counter1 = 0;
            counter2 = 0;
            counter3 = 0;
            return ("It is not balanced");
        }
    }

    public static void main(String [] args)
    {
        String test1 = "()";  //balanced
        String test2 = "{[]()(}";  //not balanced
        String test3 = "{(})";  //not balanced
        String test4 = "{[()]}()";  //balanced
        String test5 = "((";  //not balanced
        String test6 = "(";  //not balanced
        String test7 = "(){}[]";  //balanced
        String test8 = ")";  //not balanced

        Lab1Question6 balance = new Lab1Question6();

        String ans1 = balance.in(test1);
        out.println(ans1);

        String ans2 = balance.in(test2);
        out.println(ans2);

        String ans3 = balance.in(test3);
        out.println(ans3);

        String ans4 = balance.in(test4);
        out.println(ans4);

        String ans5 = balance.in(test5);
        out.println(ans5);

        String ans6 = balance.in(test6);
        out.println(ans6);

        String ans7 = balance.in(test7);
        out.println(ans7);

        String ans8 = balance.in(test8);
        out.println(ans8);

        /*
        out.println((int)(('{')));
        out.println((int)(('}')));
        out.println((int)(('[')));
        out.println((int)((']')));
        out.println((int)(('(')));
        out.println((int)((')')));
        */
    }
}

