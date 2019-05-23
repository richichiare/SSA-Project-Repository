# SSA-Project-Repository

In this repository there are all the homeworks done during the course of SSA held the first semester of the accademic year 2018/2019.
Moreover, there is also the final project which has been presented to all the class.

In particular:
- *Homework_1* folder contains the first homework. The goal of the assignment, is to use static analysis tools like 
  [Splint](https://splint.org/) and [Flawfinder](https://dwheeler.com/flawfinder/) to find vulnerabilies and programming 
  mistakes on C programs. There are a total of 6 *.ccd* files inside the folder:
  - *A.c*, *B.c* and *C.c* contain the three original C programs. The task is to apply static analysis on these three 
    small programs;
  - *A_solution.c*, *B_solution.c* and *C_solution.c* are the respective solutions.
    In particular, they contain Splint and Flawfinder annotations which express the intent of the programmer. 
- *Homework_2* folder is about the second homework of the course. The requirements are written into 
  the file *NewBag_specs.txt* 
  but basically, this homework is about adding JML specs, to stop ESC/Java2 from complaining and to verify 
  that the code performs as requested.
  - *NewBagRiccardoChiaretti_annotations.java* is the file containing JML annotations applied to the Java code.
    Notice that the code is still **bugged**. The purpose of this file is to stop ESC/Java2 from complaining;
  - *NewBagRiccardoChiaretti_code.java* is the file with bug fixes. This is the file to be referred to.
- *Final_project* folder contains everything about the final project of the course. 
  - *Assignment.pdf* contains a brief introduction about Application Security Verification Standard (ASVS) as well 
    as the specifications of the project;
  - *Application of OWASP principles for FluxBB.pptx* is a Power Point presentation about some of the OWASP
    principles which were assigned to our group;
  - *tools_results* is a folder containing the output of serveral tools which were used in the project;
  - *report.pdf* is the report of the whole project. For all information refer to it.


