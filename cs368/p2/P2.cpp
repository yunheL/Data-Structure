///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            368_p2
// Files:            Student.cpp Database.cpp p2.cpp
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
 * Main class of the project. Reads in txt file to construct database
 * and take in user command and call functions to process query.
 *
 * <p>Bugs: (a list of bugs and other problems)
 *
 * @author Yunhe Liu
 */

#include "stdlib.h"
#include "stdio.h"
#include "Student.h"
#include "Database.h"
#include <iostream>
#include <fstream>

using namespace std;


/**
 * main method. Read in file, construct data and call functions to
 * process query.
 *
 * @param argc number of command line arg
 * @param argv an array of command line arg
 * @return (description of the return value)
 */
 
int main(int argc, char* argv[])
{
  //variable used to quit the infinite loop
  bool quit = false;
  //true if input command is valid
  bool valid = true;
  //number of students in the database
  int numStudents = 0;
  //student ID number
  int studentId = 0;
  //number of courses of a student
  int numCourses = 0;
  //course number
  int cNumber = 0;
  //number of credits of a course
  int numCredit = 0;
  //letter grade, initialized to z
  string lgrade = "z";
  //couter loop counter
  int i = 0;
  //inner loop counter
  int j = 0;
  //user command
  char command;
  //additional param for query
  int val;
  //database to store student info
  Database* StudentDB;
  //student instance
  Student* st;

  //input validation
  if(argc < 2){
    std::cerr << "invalid input" << std::endl;
    return -1;
  }

  //readin file
  ifstream myReadFile;
  myReadFile.open(argv[1]);
  char output[1000];

  //construct database
 if(myReadFile.is_open()) {
    while(true) {
      myReadFile >> numStudents;

      if( myReadFile.eof() )
        break;
 
      StudentDB = new Database(numStudents);
      i = 0;
      while(i < numStudents) {
        myReadFile >> studentId;
        myReadFile >> numCourses;
        st = new Student(studentId, numCourses);

        j = 0;
        while(j < numCourses) {
          myReadFile >> cNumber;
          myReadFile >> numCredit;
          myReadFile >> lgrade;

          char *lg = new char[lgrade.length() + 1];
          strcpy(lg, lgrade.c_str());

          st->addStudentCourseInfo(cNumber, numCredit, lg);
          delete [] lg;

          j++;
        }

        StudentDB->addStudent(*st);
        i++;
      }
    }
  }
  
  //process query
  while(!quit)
  {
    //prompt
    valid = true;
    cout << ">";
    cin >> command;

    //take in user command
    if(command == 'c' || command == 'C' || command == 'g' || command == 'G' || command == 'p' || command == 'P') {
      cin >> val;
    } else {
      val = -1;
    }

    //quit command
    if(command == 'q' || command == 'Q') {
      quit = true;
    }
    //error handling
    else if(command == 'g' || command == 'G') {
      if(val < 10000 || val > 99999) {
        cout << "Invalid Input" << endl;
        valid = false;
      }
    }
    else if(command == 'c' || command == 'C') {
      if(val < 0 || val > 1000) {
        cout << "Invalid Input" << endl;
        valid = false;
      }
    }
    else if(command == 'p' || command == 'P') {
      if( val > 4) {
        cout << "Invalid Input" << endl;
        valid = false;
      }
    }
    else if(command == 'h' || command == 'H') {

    }
    else if(command == 'n' || command == 'N') {

    } 
    else {
      cout << "Invalid Command. Need help?" << endl;
      command = 'h';
      val = -1;
    }

    //call function to process query
    if(valid) {
      StudentDB->dataProcess(command, val);
    }
  }

  //close file
  myReadFile.close();
  return 0;
}
