### Quality Policy
> To ensure we can produce an efficient and detailed product, we will maintain our backlogs on Taiga including our product and sprint backlogs.  A designated scrum master will ensure that Taiga is organized and comprehensible. Our Customer/Product Owner will designate what they want out of the software and we will develop sprint plan to achieve those goals.  We will create relevant User Stories with achievable Sprint tasks that can be completed during a single Sprint.    Each User Story will include who the user is, what they want, and how it benefits them.   Our team will maintain an organized GitHub repository with a main development branch, one branch for each User Story, and sub-branches from User Story branch for different tasks associated.  Commits will include detailed messages to avoid any confusion about what each commit has done, it will include messages regarding any necessary unit tests, and do pull request for review and approval by at least one team member.  The designated git master will then merge final branch into master before submitting a working product each sprint.  We will ensure commits are sensible, and we will commit often!

**GitHub Workflow** (due Oct 27)
>In GitHub, we will ensure that we maintain a clean, working master file that is functional.  Additionally, a developmental branch will be the main development branch for the team that includes new enhancements, fully tested and ready to be merged with the master file.  The workflow structure will include one branch for each User Story and sub-branches from this branch for different tasks associated. Commits will include detailed messages to avoid any confusion about what each commit has done, it will include messages regarding any necessary unit tests, and do pull request for review and approval by at least one team member.  The designated git master will then merge final branch into master before submitting a working product each sprint.  

**Unit Tests Blackbox** (due Nov 4th)
 >For Blackbox unit testing, our team will ensure that we test major system functionality by providing the system with inputs and observing the outputs.  We will then compare the outputs with expected outputs to  confirm the functionality is operating correctly.   Additionally,  any major enhancements or components that our team writes will be tested using this method, again, to ensure that our enhancement are accurate and functional.  

 **Unit Tests Whitebox** (due Nov 11th)
  > For Whitebox testing, as we write our enhancement or change the given codebase, the developer creating it will include Whitebox testing for functionality and error.  These tests, combined with Blackbox test, will seek to achieve 90% node coverage on the methods or classes created.  For given code, our goal is to reach 60% node coverage to adhere to programming best practices. 

**Code Review** (due Nov 11th)
  > The overall goal of the code review will be to find errors, and improve the code for the software.  When a member of our team submits a pull request, an additional member that did not work on the code will conduct a code review.  A series of checklists are established below for both the developer and reviewer.  All code will need to be reviewed before being accepted and merged with the master branch.  Our team will provide constructive criticism of each other's work in a respectful manner.     

  > As a developer, the below checklist will need to be completed in full before a reviewer will determine if the code is acceptable to merge with the master branch:
   - [ ]  My code compiles.
   - [ ] My code has been developer-tested and includes unit tests.
   - [ ] My code is properly formatted (indentation, line length, no commented-out code, no spelling mistakes, etc.).
   - [ ] I have considered proper use of exceptions.
   - [ ] I have made appropriate use of logging.
   - [ ] I have eliminated unused imports.
   - [ ] I have made efforts to resolved all IDE warnings.
   - [ ] My code follows the Coding Standards given in week.
   - [ ] My code is properly documented.


  > As a reviewer, the below checklist will need to be completed in full before the code is accepted and merged with master.  
   - [ ] Comments make sense and add something to the maintainability of the code.
   - [ ] Comments are neither too numerous nor verbose.
   - [ ] Types have been generalized where possible.
   - [ ] Parameterized types have been used appropriately.
   - [ ] Exceptions have been used appropriately.
   - [ ] Repetitive code has been removed.
   - [ ] Frameworks (APIs) are used appropriately – methods have all been defined appropriately.
   - [ ] Command classes have been designed to undertake one task only.
   - [ ] JSPs do not contain business logic.
   - [ ] Unit tests are present and correct.
   - [ ] Common errors have been checked.
   - [ ] Potential threading issues have been eliminated where possible.


**Static Analysis**  (due Nov 18th)
  > For static analysis, we will make use of SpotBugs and Checkstyle tools as we implement our new additions and code, making corrections as we go. We will also be sure to run reports for both after we finish a task and ensure our code does not contains any bugs or stylistic errors. If SpotBugs is preventing a build because of a false positive we can disable it for the build.

**Continuous Integration**  (due Nov 18th)
  > For Continuous Integration, we will make use of Travis CI, through Github and ensure before we merge anything into development or master, our CI checks pass.  If it does, we must correct whatever error we introduced.  
