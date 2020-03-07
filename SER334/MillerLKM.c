/*
 * MillerLKM.c
 * Basic Description: This progarm is used to print out processes and their
 * children processes currently running. 
 * Completion time: 16 hours
 * author Marcus Miller
 * version 11/08/19
 *
 */

#include <linux/module.h> /* Needed by all modules */ 
#include <linux/kernel.h> /* Needed for KERN_INFO */
#include <linux/sched.h>
#include <asm/processor.h>
#include <linux/sched/signal.h>
#include <linux/init.h>
#include <linux/moduleparam.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Marcus Miller");

static int inp_pid = 0;

module_param(inp_pid, int, S_IRUSR | S_IWUSR | S_IRGRP | S_IROTH); 
MODULE_PARM_DESC(inp, "Processes with pids greater than this will be displayed");

int init_module(void) {
  printk(KERN_INFO "Start MillerLKM.\n");
  printk(KERN_INFO "inp_pid is an integer: %d\n", inp_pid);
  /*
  * A non 0 return means init_module failed; module can't be loaded. */
  struct task_struct *ptr;
  char process_name[16];
  char parent_process_name[16];
  char child_process_name[16];
  for_each_process(ptr){
    if(ptr->pid >= inp_pid){
      printk(KERN_INFO "PROCESS         \tPID    \tSTATE   \tPRIO    \tST_PRIO \tNORM_PRIO\n");
      get_task_comm(process_name, ptr);
      printk(KERN_INFO "%-16s\t%-7d\t%-8ld\t%-8d\t%-8d\t%-9d\n",process_name,ptr->pid,
		      ptr->state,ptr->prio, ptr->static_prio, ptr->normal_prio);
      struct task_struct *parent_ptr = ptr->parent;
      if (parent_ptr){
        printk(KERN_INFO "PARENT OF %s\n", process_name);
        get_task_comm(parent_process_name, parent_ptr);
        printk(KERN_INFO "%-16s\t%-7d\t%-8ld\t%-8d\t%-8d\t%-9d\n",parent_process_name,
			parent_ptr->pid, parent_ptr->state,parent_ptr->prio,
		       	parent_ptr->static_prio, parent_ptr->normal_prio);
      }
      struct task_struct *child_ptr;
      list_for_each_entry(child_ptr, &ptr->children, sibling){
        printk(KERN_INFO "CHILD OF %s\n", process_name);
        get_task_comm(child_process_name, child_ptr);
        printk(KERN_INFO "%-16s\t%-7d\t%-8ld\t%-8d\t%-8d\t%-9d\n",child_process_name,
			child_ptr->pid, child_ptr->state,child_ptr->prio,
		       	child_ptr->static_prio, child_ptr->normal_prio);
      }
      printk(KERN_INFO "\n");

    }
  }

  return 0;
}
void cleanup_module(void) {
  printk(KERN_INFO "End MillerLKM\n");
}
