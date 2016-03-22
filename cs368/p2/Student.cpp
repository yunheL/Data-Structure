///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  p2.cpp
// File:             Student.cpp
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
 * Implement the Student.h header file.
 *
 * <p>Bugs: (a list of bugs and other problems)
 *
 * @author Yunhe Liu
 */

#include <iostream>
#include "Student.h"

using namespace std;


/**
 * Constructor.
 *
 * @param sid student id
 * @param nCourses number of courses the student is taking
 * 
 */
Student::Student(int sid, int nCourses) :
studentID(sid), nCourses(nCourses) {
//  cout << "two arg constructor" << endl;
}

/**
 * Access GPA
 * @ param None.
 */
//Accessor - get GPA
float Student::getGPA() {
  int i = 0;
  float gradePointTotal = 0;
  float numericalGrade = 0;
  int creditTotal = 0;

  while(i < Courses.size()) {
    if(Courses[i].grade == "A" || Courses[i].grade == "a")
      numericalGrade = 4.0;
    else if(Courses[i].grade == "AB" || Courses[i].grade == "ab")
      numericalGrade = 3.5;
    else if(Courses[i].grade == "B" || Courses[i].grade == "b")
      numericalGrade = 3.0;
    else if(Courses[i].grade == "BC" || Courses[i].grade == "bc")
      numericalGrade = 2.5;
    else if(Courses[i].grade == "C" || Courses[i].grade == "c")
      numericalGrade = 2.0;
    else if(Courses[i].grade == "CD" || Courses[i].grade == "cd")
      numericalGrade = 1.5;
    else if(Courses[i].grade == "D" || Courses[i].grade == "d")
      numericalGrade = 1.0;
    else if(Courses[i].grade == "F" || Courses[i].grade == "f")
      numericalGrade = 0.0; 
    
    gradePointTotal = gradePointTotal + ((float)(Courses[i].credits)) * numericalGrade;
    creditTotal = creditTotal + Courses[i].credits;
    i++;
  }
  gpa = gradePointTotal/((float)(creditTotal));
  return gpa;  
}

/**
 * Return the number of courses the student is taking.
 * @return number of courses
 */
int Student::getNumCourses() {
  return nCourses;
}

/**
 * Add courses with given Course number, credit and grade to student
 * @param cNumber course number
 * @param credits number of credit of the course
 * @param lgrade letter grade of the course
 */
void Student::addStudentCourseInfo(int cNumber, int credits, char* lGrade) {
  Coursetype addCourse;
  addCourse.courseNum = cNumber;
  addCourse.credits = credits;
  addCourse.grade = lGrade;
  Courses.push_back(addCourse);
}

/**
 * see if the student have taken the give course
 * @param course number
 * @return true if the course has been taken
 */
bool Student::tookCourse(int courseNumber) {
  int i = 0;
  while(i < Courses.size()) {
    if(Courses[i].courseNum == courseNumber)
    {
      return true;
    }
    i++;
  }
  return false;
}

/**
 * Access student ID
 * @ return student ID.
 */
//getId
int Student::getId(void) {
  return studentID;
}
