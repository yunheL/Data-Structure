///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  p2.cpp
// File:             Database.cpp
// Semester:         cs368 Spring 2016
//
// Author:           Yunhe Liu
// Email:            liu348@wisc.edu
// CS Login:         Yunhe
// Lecturer's Name:  Deb
// Lab Section:      (your lab section number)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
//                   CHECK ASSIGNMENT PAGE TO see IF PAIR-PROGRAMMING IS ALLOWED
//                   If pair programming is allowed:
//                   1. Read PAIR-PROGRAMMING policy (in cs302 policy) 
//                   2. choose a partner wisely
//                   3. REGISTER THE TEAM BEFORE YOU WORK TOGETHER 
//                      a. one partner creates the team
//                      b. the other partner must join the team
//                   4. complete this section for each program file.
//
// Pair Partner:     (name of your pair programming partner)
// Email:            (email address of your programming partner)
// CS Login:         (partner's login name)
// Lecturer's Name:  (name of your partner's lecturer)
// Lab Section:      (your partner's lab section number)
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   Google
//                   Piazza
//                   StackOverflow
//                   Linux Man page
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Implement the Database.h header file.
 *
 * <p>Bugs: (a list of bugs and other problems)
 *
 * @author Yunhe Liu
 */

#include "Database.h"
#include <iostream>

using namespace std;

/**
 * Constructor.
 *
 * @param numStudents number of students in the database
 *
 */
Database::Database(int numStudents) :
numStudents(numStudents) {
//  cout << "one arg constructor" << endl;
}

/**
 * Add student to the database
 *
 * @param aStudent student to be added
 *
 */
void Database::addStudent(Student aStudent) {
  students.push_back(aStudent);
}

int Database::getNumStudents(void) {
  return numStudents;
}

/**
 * Process query according to the given command and val
 *
 * @param command type of query need to be processed
 * @param val additional param needed to process the query
 */
void Database::dataProcess(char command, int val) {
  //h - displays list of available commands to the user
  if(command == 'H' || command == 'h') {
    cout << "You can enter the following valid commands: " << endl;
    cout << "n: Get number of student in the database" << endl;
    cout << "c num: Get number of students who took course 'num'. Also prints student IDs" << endl;
    cout << "g student_ID: Get GPA of student student_ID" << endl;
    cout << "p num: Get number of students with GPA>num. Also print student IDs" << endl;
    cout << "q: Quit" << endl;
  }

  //n - displays the number of students currently in the database
  else if(command == 'n' || command == 'N')
    cout << numStudents << endl;

  //c - num:displays the number of students who took course 'num'. 
  //It also prints the student id numbers of those students.
  else if(command == 'c' || command == 'C') {
    int i = 0;
//    int j = 0;
    int numStudents = 0;
    while(i < students.size()) {
//      while(j < students[i].getNumCourses()){
        if(students[i].tookCourse(val))
        {
          //cout << "here";
          numStudents++;
        }
//        j++;
//      }
     i++;
    }
    cout << numStudents << endl;

    i = 0;
//    j = 0;
    while(i < students.size()) {
//      while(j < students[i].getNumCourses()){
        if(students[i].tookCourse(val))
        {
          cout << students[i].getId() << " ";
          //TODO - Take care of last element no space here
        }
//        j++;
//      }
     i++;
    }
    if(numStudents > 0)
      cout << endl;
  }

  //g - student_id: displays the GPA of student with student id number given
  else if(command == 'g' || command == 'G') {
    int i = 0;
    bool exist = false; 
    while(i < students.size()){
      if(students[i].getId() == val){
        cout << students[i].getGPA() << endl;
        exist = true;
      }
      i++;
    }
    if(!exist)
	cout << endl; //TODO think about this
  }

  //p - num: displays the number of students with a GPA greater than 'num'. 
  //It also prints the student id numbers of those students.
  else if(command == 'p' || command == 'P') {
    int i = 0;
    int numStudents = 0;
    
    while(i < students.size()){
      if(students[i].getGPA() >  val){
        numStudents++;
      }
      i++;
    }
    cout << numStudents << endl;
 
    i = 0;
    while(i < students.size()){
      if(students[i].getGPA() >  val){
        cout << students[i].getId() << " ";
      }
      i++;
    }
    cout << endl;

  }

  //q -  will exit the program
  //Taken care of in the main program

}
