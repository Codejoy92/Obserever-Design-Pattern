Assuming you are in the directory containing this README:

## To clean:
ant -buildfile src/build.xml clean

-----------------------------------------------------------------------
## To compile: 
ant -buildfile src/build.xml all

-----------------------------------------------------------------------
## To run by specifying arguments from command line 
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=input.txt -Darg1=delete.txt -Darg2=output1.txt -Darg3=output2.txt -Darg4=output3.txt

-----------------------------------------------------------------------

## To create tarball for submission
ant -buildfile src/build.xml tarzip

-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating I will have to sign an
official form that I have cheated and that this form will be stored in
my official university record. I also understand that I will receive a
grade of 0 for the involved assignment for my first offense and that I
will receive a grade of F for the course for any additional
offense."

[Date: 02/12/2018]

-----------------------------------------------------------------------

Provide justification for Data Structures used in this assignment in
term of Big O complexity (time and/or space)
I have implemented Binary Search Tree (BST) in this project because insertion is the dominant process and for insertion average time complexity is
O(log n) where n is total number of nodes in BST.

Time complexity:
worst case for searching node, inserting node, deleting node is O(n).

I have used Arraylist to store courses because it can shrink or grow dynamically.

-----------------------------------------------------------------------

Provide list of citations (urls, etc.) from where you have taken code
(if any).

reference for BST data structure/ tree creation
http://algorithms.tutorialhorizon.com/binary-search-tree-complete-implementation/
