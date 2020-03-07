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
#include "PageTable.h"

//forward declarations for structs

struct page_table_entry{
    int data;
    struct page_table_entry* next; // Pointer to next node
    unsigned int is_valid;
};
struct page_table{
    int max_number_of_pages;
    struct page_table_entry *head;
    enum replacement_algorithm algorithm;
    int frame_count;
    int number_of_faults;
};

/**
 * Creates a new page table object. Returns a pointer to created page table.
 *
 * @param page_count Number of pages.
 * @param frame_count Numbers of frames.
 * @param algorithm Page replacement algorithm
 * @param verbose Enables showing verbose table contents.
 * @return A page table object.
 */
struct page_table* page_table_create(int page_count, int frame_count, enum replacement_algorithm algorithm, int verbose){
    struct page_table* pt = malloc(sizeof(struct page_table));
    pt->head = NULL;
    pt->max_number_of_pages = page_count;
    pt->frame_count = frame_count;
    pt->algorithm = algorithm;
    pt->number_of_faults = 0;
    return pt;
}

/**
 * Destorys an existing page table object. Sets outside variable to NULL.
 *
 * @param pt A page table object.
 */
void page_table_destroy(struct page_table** pt){
    struct page_table * page_table_ptr = *pt;
    while(page_table_ptr->head != NULL){
        struct page_table_entry *tmp = page_table_ptr->head;
        page_table_ptr->head = page_table_ptr->head->next;
        //free(tmp);

    }
    pt = NULL;
    free(page_table_ptr);
    page_table_ptr = NULL;
    
}

/**
 * Simulates an instruction accessing a particular page in the page table.
 *
 * @param pt A page table object.
 * @param page The page being accessed.
 */
void page_table_access_page(struct page_table *pt, int page){
    int is_in_table = 0;
    struct page_table_entry * pte = pt->head;
    struct page_table_entry * prev = NULL;
    int current = 0;
    while(is_in_table == 0 && pte != NULL){
        if(pte->data == page && pte->is_valid%2 == 1){
            is_in_table = 1;
            if(pt->algorithm == MFU || pt->algorithm == LRU){
                if (prev != NULL && pte->next != NULL){
                    prev->next = pte->next;
                }
                if (pte == pt->head){
                    pt->head = pte->next;
                }
                struct page_table_entry * tmp = pte;
                while(tmp->next != NULL){
                    tmp = tmp->next;
                }
                if(tmp != pte){
                    tmp->next = pte;
                    pte->next = NULL;
                }
            }
            return;
        }
        prev = pte;
        pte = pte->next;
        current++;
    }
    if (current < pt->frame_count){
        struct page_table_entry * tmp = pt->head;
        if(pt->head == NULL){
            pt->head = malloc(sizeof(struct page_table_entry));
            pt->head->data = page;
            pt->head->is_valid = 1;
            pt->head->next = NULL;
            return;
        }
        while(tmp->next != NULL){
            tmp = tmp->next;
        }
        tmp->next = malloc(sizeof(struct page_table_entry));
        tmp->next->data = page;
        tmp->next->is_valid = 1;
        tmp->next->next = NULL;
        return;
    }
    
    pt->number_of_faults++;
    if(pt->algorithm == FIFO || pt->algorithm == LRU){
        struct page_table_entry * tmp = pt->head;
        while(tmp->next != NULL){
            tmp = tmp->next;
        }
        tmp->next = malloc(sizeof(struct page_table_entry));
        tmp->next->data = page;
        tmp->next->is_valid = 1;
        tmp->next->next = NULL;
        
        tmp = pt->head;
        pt->head = pt->head->next;
        free(tmp);
        return;
    }
    else{//MFU
        struct page_table_entry * tmp = pt->head;
        struct page_table_entry * tmp2 = pt->head;
        
        while(tmp->next != NULL){
            tmp = tmp->next;
        }
        tmp2 = tmp;
        tmp = malloc(sizeof(struct page_table_entry));
        free(tmp2);
        tmp->data = page;
        tmp->is_valid = 1;
        tmp->next = NULL;
    }
}

/**
 * Displays page table replacement algorithm, number of page faults, and the
 * current contents of the page table.
 *
 * @param pt A page table object.
 */
void page_table_display(struct page_table* pt){
    printf("==== Page Table ====\n");
    if(pt->algorithm == FIFO){
       printf("Mode : FIFO\n");
    }
    else if(pt->algorithm == MFU){
        printf("Mode : MFU\n");
    }
    else if (pt->algorithm == LRU){
        printf("Mode : LRU\n");
    }
    printf("Page Faults : %d\n",pt->number_of_faults);
    printf("page frame | dirty valid\n");
    struct page_table_entry * tmp = pt->head;
    int page = 0;
    while(tmp != NULL){
        printf("%d %d | %d %d\n",page++, tmp->data, tmp->is_valid/2 % 2, tmp->is_valid % 2);
        tmp = tmp->next;
    }
}

/**
 * Displays the current contents of the page table.
 *
 * @param pt A page table object.
 */
void page_table_display_contents(struct page_table *pt){
    struct page_table_entry * tmp = pt->head;
    int page = 0;
    printf("page frame | dirty valid\n");
    while(tmp != NULL){
        printf("%d %d | %d %d\n",page++, tmp->data, tmp->is_valid/2 % 2, tmp->is_valid % 2);
        tmp = tmp->next;
    }
}



