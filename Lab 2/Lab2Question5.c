#include <stdio.h>

int negative_check(int input[], int pos_1, int pos_2)
{
    int x = input[pos_1];
    int y = input[pos_2];

    if (y < 0)
    {
        if (x >= 0)
        {
            return (1);
        }
        else
        {
            return (0);
        }
    }

    return (0);
}

void negative_sort(int input[], int size)
{
    int x = 0;
    int y = 0;
    int temp = 0;
    int pointer;
    /*int size = sizeof(input) / sizeof(int);  size of the pointer not array*/

    /*
    Loop invariant:
    for loop x if any negative numbers are not at index 0 they are shifted left.
    for loop x for any negative number not at index 0, it is shifted -1 in position.

    5 7 6 -2 4 -10
    5 7 -2 6 -10 4
    5 -2 7 -10 6 4
    -2 5 -10 7 6 4
    -2 -10 5 7 6 4
    */
    for (x = 1; x < size; x++)
    {
        for (y = 1; y < size; y++)
        {
            if (negative_check(input, y - 1, y))
            {
            /*printf("test %d", size);*/
            /*printf("%d, %d, %d\n", input[y - 1], input[y], negative_check(input, y - 1, y));*/
            temp = input[y - 1];
            input[y - 1] = input[y];
            input[y] = temp;
            }
        }
    }
}

int main()
{
    /*int test[4] = {-2, 5, -10, 2};*/
    int test[6] = {5, 7, 6, -2, 4 ,-10};
    int size = sizeof(test) / sizeof(int);
    negative_sort(test, size);

    int x = 0;
    for (x = 0; x < size; x++)
    {
        printf("[%d]", test[x]);
    }
    int t = -5 * -7;
}
