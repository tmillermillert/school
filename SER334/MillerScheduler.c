/**
* Basic course scheduling program for practice in C
*
* Completion time: 2 hours
*
* @author Marcus Miller
* @version 10/24/19
*/

////////////////////////////////////////////////////////////////////////////////
//INCLUDES
#include <stdio.h>
#include <stdlib.h>

////////////////////////////////////////////////////////////////////////////////
//MACROS: CONSTANTS



////////////////////////////////////////////////////////////////////////////////
//DATA STRUCTURES

enum Subject {SER=0, EGR=1, CSE=2, EEE=3};

struct CourseNode{
    enum Subject subject;
    int number;
    char teacher[1024];
    int credit_hours;
    struct CourseNode *next;
    
};



////////////////////////////////////////////////////////////////////////////////
//GLOBAL VARIABLES

//place to store course information
struct CourseNode* course_collection = NULL;

////////////////////////////////////////////////////////////////////////////////
//FORWARD DECLARATIONS
void branching(char option);
void course_insert(void);
void schedule_print(void);
void course_drop(void);

//main entry point. Starts the program by displaying a welcome and beginning an
//input loop that displays a menu and processes user input. Pressing q quits.
int main() {
    char input_buffer;

    printf("\n\nWelcome to ASU Class Schedule\n");

    //menu and input loop
    do {
        printf("\nMenu Options\n");
        printf("------------------------------------------------------\n");
        printf("a: Add a class\n");
        printf("d: Drop a class\n");
        printf("s: Show your classes\n");
        printf("q: Quit\n");
        //printf("\nTotal Credits: %d\n\n", TODO);
        int total_credits = 0;
        struct CourseNode *ptr = course_collection;
        while (ptr != NULL){
            total_credits += ptr->credit_hours;
            ptr = ptr->next;
        }
        printf("\nTotal Credits: %d\n\n", total_credits);
        printf("Please enter a choice ---> ");

        scanf(" %c", &input_buffer);




        branching(input_buffer);
    } while (input_buffer != 'q');

    return 0;
}

//takes a character representing an inputs menu choice and calls the appropriate
//function to fulfill that choice. display an error message if the character is
//not recognized.
void branching(char option) {
    switch (option) {
    case 'a':
        //TODO
        course_insert();
        break;

    case 'd':
        //TODO
            course_drop();
        break;

    case 's':
        //TODO
            schedule_print();
        break;

    case 'q':
        // main loop will take care of this.
        break;

    default:
        printf("\nError: Invalid Input.  Please try again...");
        break;
    }
}

void course_insert(){
    struct CourseNode *course = NULL;
    course = malloc(sizeof(struct CourseNode));
    course->subject = -1;
    course->number = -1;
    course->credit_hours = -1;
    
    while (course->subject < 0 || course->subject > 3){
        int c;
        while ((c = getchar()) != '\n' && c != EOF) { }
        printf("What is the subject (SER=0, EGR=1, CSE=2, EEE=3)?\n");
        scanf("%d", &course->subject);
    }
    while(course->number < 0 || course->number > 999){
        int c;
        while ((c = getchar()) != '\n' && c != EOF) { }
        printf("What is the number (e.g. 240)?\n");
        scanf("%d", &course->number);
    }
    while(course->credit_hours < 0 || course->credit_hours > 15){
        int c;
        while ((c = getchar()) != '\n' && c != EOF) { }
        printf("How many credits is the class (e.g. 3)?\n");
        scanf("%d", &course->credit_hours);
    }
    printf("What is the name of the teacher?\n");
    scanf("%1023s", &course->teacher[0]);
    
    course->next = NULL;
    
    if (course_collection == NULL){
        course_collection = course;
        return;
    }
    struct CourseNode *ptr = course_collection;
    struct CourseNode *prev = NULL;
    while(ptr != NULL){
        if (course->number < ptr->number){
            if (prev != NULL){ // not first in list
                prev->next = course;
                course->next = ptr;
            }
            else{
                course->next = ptr;
                course_collection = course;
                return;
            }
        }
        else if (ptr->next == NULL){ //last course
            ptr->next = course;
            return;
        }
        else{
            prev = ptr;
            ptr = ptr->next;
        }
    }
}

void schedule_print(){
    struct CourseNode *ptr = course_collection;
    printf("Class Schedule:\n");
    while(ptr != NULL){
        switch (ptr->subject) {
        case 0:
            printf("SER%d\t%d\t%s\n", ptr->number, ptr->credit_hours, ptr->teacher);
            break;

        case 1:
            printf("EGR%d\t%d\t%s\n", ptr->number, ptr->credit_hours, ptr->teacher);
            break;

        case 2:
            printf("CSE%d\t%d\t%s\n", ptr->number, ptr->credit_hours, ptr->teacher);
            break;

        case 3:
            printf("EEE%d\t%d\t%s\n", ptr->number, ptr->credit_hours, ptr->teacher);
            break;

        default:
            printf("\nError: Invalid Input.  Please try again...");
            break;
        }
        ptr = ptr->next;
    }
}

void course_drop(){
    int course_number = -1;
    while(course_number < 0 || course_number > 999){
          int c;
          while ((c = getchar()) != '\n' && c != EOF) { }
          printf("Enter number:\n");
          scanf("%d", &course_number);
    }
    struct CourseNode *ptr = course_collection;
    struct CourseNode *prev = NULL;
    while(ptr != NULL){
        if (ptr->number == course_number){
            if (prev != NULL){ // not first in list
                prev->next = ptr->next;
                free(ptr);
                return;
            }
            else{ // first in list
                course_collection = ptr->next;
                free(ptr);
                return;
            }
        }
        else{
            prev = ptr;
            ptr = ptr->next;
        }
    }
    printf("No course matches that course number.\n");
}
