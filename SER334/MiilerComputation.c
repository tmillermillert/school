/* (basic description of the program or class)
 * Completion time: 20 mins
 * @author Marcus Miller
 * @version 10/21/19
 */

#include <stdio.h>

int main(void) {
    //Add a prompt for the number of cylinders to sum. Make sure to use the control//symbol for integers. [2 points]
    //Create a loop based on the number of cylinders the user enters. [2 points]
    //    Within the loop, prompt for height and radius parameters (allow floating
    //    point numbers), and calculate the volume for that particular cylinder.
    //    [4 points]
    //Display the total volume sum back to the user. Make sure to use the right control
    //symbol. [2 points]
    int number_of_cylinders = 0;
    float height = 0;
    float radius = 0;
    float pi = 3.14159265358979323846;
    float sum_of_cylinder_volumes = 0;
    printf("Enter the number of cylinders volumes to sum: ");
    scanf("%d", &number_of_cylinders);
    for(int i = 1; i <= number_of_cylinders; i++){
        printf("Enter cylinder %d's height: ", i);
        scanf("%f", &height);
        printf("Enter cylinder %d's radius: ", i);
        scanf("%f", &radius);
        sum_of_cylinder_volumes = sum_of_cylinder_volumes + pi * radius * radius * height;
    }
    printf("The sum of cylinder volumes = %f.\n", sum_of_cylinder_volumes);
    
    
    return 0;
}
