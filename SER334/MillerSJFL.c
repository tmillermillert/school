/*
* MillerSJFL.c
* Basic Description: This progarm is used to simulate sjf and sjfl cpu scheduling
* Completion time: 7 hours
* author Marcus Miller
* version 12/01/19
*
*/

#include <stdio.h>
#include <stdlib.h>

int compare( const void* a, const void* b)
{
     int int_a = * ( (int*) a );
     int int_b = * ( (int*) b );

     if ( int_a == int_b ) return 0;
     else if ( int_a < int_b ) return -1;
     else return 1;
}

void shortest_job_first(int** tick, int ticks, int process_count){
    printf("== Shortest -Job - First ==\n");
    int time  = 0;
    int wait_time = 0;
    int turn_around_time = 0;
    int total_wait_time = 0;
    int total_turn_around_time = 0;
    int *jobs = malloc(sizeof(int) * process_count);
    for (int t = 0; t < ticks; t++){
        printf("Simulating %dth tick of processes @ time %d:\n", t, time);
        for(int i = 0; i < process_count; i++){
            jobs[i] = tick[i][t];
        }
        qsort(jobs, process_count, sizeof(int), compare);
        int current_wait_time = 0;
        for(int i = 0; i < process_count; i++){
            printf("Process %d took %d.\n",i, jobs[i]);
            wait_time += jobs[i] + current_wait_time;
            current_wait_time += jobs[i];
            turn_around_time += jobs[i] + turn_around_time;
            time += jobs[i];
        }
        wait_time -= current_wait_time;
        
        total_wait_time += wait_time;
        wait_time = 0;
        total_turn_around_time += turn_around_time;
        turn_around_time = 0;
        
    }
    printf("Turnaround time : %d\n",total_turn_around_time);
    printf("Waiting time : %d\n\n", total_wait_time);
    free(jobs);
    jobs = NULL;
}
void swap(int *xp, int *yp)
{
    int temp = *xp;
    *xp = *yp;
    *yp = temp;
}

void bubbleSort(int * arr,int * arr2, int n)
{
    int i, j;
    for (i = 0; i < n-1; i++)
      
    // Last i elements are already in place
    for (j = 0; j < n-i-1; j++)
        if (arr[j] > arr[j+1]){
            swap(&arr[j], &arr[j+1]);
            swap(&arr2[j], &arr2[j+1]);
        }
}

void shortest_job_first_live(int** tick, int ticks, float tau, float alpha, int process_count){
    printf("== Shortest -Job - First Live ==\n");
    int time  = 0;
    int wait_time = 0;
    int turn_around_time = 0;
    int total_wait_time = 0;
    int total_turn_around_time = 0;
    int *jobs = malloc(sizeof(int) * process_count);
    int *actual_jobs = malloc(sizeof(int) * process_count);
    int **taus = malloc(sizeof(int*) * process_count);
    
    int estimation_error =0;
    for(int i = 0; i < process_count; i++){
        taus[i] = malloc(sizeof(int) * ticks);
    }
    
    for (int t = 0; t < ticks; t++){
        printf("Simulating %dth tick of processes @ time %d:\n", t, time);
        for(int i = 0; i < process_count; i++){
            if(t != 0){
                taus[i][t] = alpha * tick[i][t-1] + (1 - alpha) * taus[i][t-1];
            }
            else{
                taus[i][t] = tau;
            }
            jobs[i] = taus[i][t];
            actual_jobs[i] = tick[i][t];
        }
        bubbleSort(jobs, actual_jobs, process_count);
        int current_wait_time = 0;
        for(int i = 0; i < process_count; i++){
            printf("Process %d was estimated for %d and took %d.\n",i, jobs[i], actual_jobs[i]);
            wait_time += actual_jobs[i] + current_wait_time;
            current_wait_time += actual_jobs[i];
            turn_around_time += actual_jobs[i] + turn_around_time;
            time += actual_jobs[i];
            estimation_error += abs(jobs[i] - actual_jobs[i]);
        }
        wait_time -= current_wait_time;
        
        total_wait_time += wait_time;
        wait_time = 0;
        total_turn_around_time += turn_around_time;
        turn_around_time = 0;
        
    }
    printf("Turnaround time : %d\n",total_turn_around_time);
    printf("Waiting time : %d\n", total_wait_time);
    printf("Estimation Error : %d\n", estimation_error);
    for (int i = 0; i < process_count; i++){
        free(taus[i]);
    }
    free(jobs);
    free(taus);
    free(actual_jobs);
    actual_jobs = NULL;
    
    taus = NULL;
    jobs = NULL;
}

int main(int argc, const char * argv[]) {
       FILE *fptr;
       char filename[15];
       char ch;
    
       printf("Enter the filename to be opened \n");
       scanf("%s", filename);
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
    int current_tick = 0;
    
    int ticks = 0;
    int process_count = 0;
    int start_of_process = 0;
    float tau = 0;
    float alpha = 0;
    int **tick = NULL;
    
       
       do
       {
           ch = fgetc(fptr);
           if (ch == '\n'){
               if (line == 0){
                   ticks = number;

               }
               else if(line == 1){
                   process_count = number;
                   tick = malloc(sizeof(int*)*process_count);
                   for(int i = 0; i < process_count; i++){
                       tick[i] = malloc(sizeof(int)*ticks);
                   }
               }
               else if((line - 2) % (ticks + 3) == 0){
                   start_of_process = number;
                   current_tick = 0;
                   
               }
               else if((line - 2) % (ticks + 3) == 1){
                   tau = number;
               }
               else if((line - 2) % (ticks + 3) == 2){
                   alpha = number;
               }
               else{
                   tick[start_of_process][current_tick++] = number;

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
               //printf ("%c\t%f\n", ch,number);
           }
           
           
       }while (ch != EOF);
       shortest_job_first(tick, ticks, process_count);
       shortest_job_first_live(tick, ticks, tau, alpha,process_count);
       for(int i = 0; i < process_count; i++){
           free(tick[i]);
           tick[i] = NULL;
       }
       free(tick);
       tick = NULL;
       fclose(fptr);
    return 0;
}


