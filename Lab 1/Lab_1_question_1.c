/*
README
This program takes in terminal inputs and reverses them.
As this is done by a stack implemented using a static array size the limit is 10 characters.
*/
#include <stdio.h>

int main()
{
    char characters[10];  //set up an array
    char input;
    int x = 0;  //counter
    printf("Enter your input: ");

    while((input = getchar()) != '\n')  //put characters into the array unless it is newline
    {
        characters[x] = input;
        //printf("%d", x);
        x++;  //increment counter
    }

    
    while(x >= 0)  //if there are characters in the array print it
    {
        x--;
        putchar(characters[x]);
    }
    printf("\n");  //print newline to make it easier to read

    return 0;
}