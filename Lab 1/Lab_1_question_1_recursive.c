/*
README
This program takes in terminal inputs and reverses them.
As this is done in a recursive manner the limit of the input is quite large.
*/
#include <stdio.h>

void recursive(char c)
{
    if(c != '\n')  //if not newline call the function again (base case)
    {
        recursive(getchar());
    }

    putchar(c);  //print out the char
}

int main()
{
    char c;
    printf("Enter your input: ");
    c = getchar();  //first input
    recursive(c);  //cal function to handle the input and output

    printf("\n");

    return 0;
}

/*
inst 1
      inst 2
            inst 3
c1    
      c2
            c3

inst 3 done
print c3
            inst 2 done
            print c2 
                       inst 1 done
                       print c1*/