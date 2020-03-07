/*(basic description of the program or class)
* Collatz Conjecture example for learning C.
* Completion time: 20 mins
* @author Marcus Miller
* @version 10/21/19
*
*/

#include <stdio.h>

void termination(int n){
    int iterations = 0;
    int initial_n = n;
    while(n != 1){
        iterations++;
        if (n % 2 == 0){
            n = n / 2;
        }
        else{
            n = 3 * n + 1;
        }
    }
    printf("Number of iterations for termination(%d) = %d\n", initial_n, iterations);
    
}

int main(int argc, const char * argv[]) {
    int n = 0;
    printf("Enter an integer for collatz conjecture: ");
    scanf("%d", &n);
    termination(n);
    return 0;
}

