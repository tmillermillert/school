/*
* MillerDataLoader.c
* Basic Description:
* Completion time: 4 hours
* author Marcus Miller
* version 12/01/19
*
*/

#include <stdio.h>
#include <stdlib.h>
#include "DataLoader.h"
#include <string.h>



/**
 * Loads a test_scenario strut from a textfile.
 *
 * @param fname The name of the file to load.
 * @return A struct containing the loaded file.
 */
struct test_scenario* load_test_data(char* fname){
       FILE *fptr;
       char filename[1000];
       char * destination = &filename[0];
       const char * source = fname;
       strcpy(destination, source);
       char ch;
    
       struct test_scenario *ts =  malloc(sizeof(struct test_scenario));
       /*  open the file for reading */
       fptr = fopen(filename, "r");
       if (fptr == NULL)
       {
           printf("Cannot open file \n");
           exit(0);
       }
       float number = 0;
       int line = 0;
       int is_float = 0;
       int power = 1;
    
       int current_page = 0;

       do
       {
           ch = fgetc(fptr);
           if (ch == '\n'){
               if (line == 0){
                   ts->page_count = number;

               }
               else if(line == 1){
                   ts->frame_count = number;
               }
               else if(line == 2){
                   ts->refstr_len = number;
               }
               else{
                   ts->refstr[current_page++] = number;

               }
               line++;
               number = 0;
               is_float = 0;
               power = 1;
               
               
           }
           else if (ch == '\r'){
               
           }
           else if (ch == '.'){
               is_float = 1;
           }
           else if (ch == EOF){
               break;
           }
           else{
               if(is_float == 0){
                   number  = atoi(&ch) + number * 10;
               }
               else{
                   number  = (float) atoi(&ch) / (10*power) + number;
                   power++;
               }
               printf ("%c\t%f\n", ch,number);
           }
           
           
       }while (ch != EOF);
       
       fclose(fptr);
       return ts;
}


