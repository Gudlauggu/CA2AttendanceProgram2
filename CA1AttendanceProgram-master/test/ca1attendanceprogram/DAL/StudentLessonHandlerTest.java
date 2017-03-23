/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.StudentLesson;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mecaa
 */
public class StudentLessonHandlerTest
  {
    
    public StudentLessonHandlerTest()
      {
      }
    
    @BeforeClass
    public static void setUpClass()
      {
      }
    
    @AfterClass
    public static void tearDownClass()
      {
      }
    
    @Before
    public void setUp()
      {
      }
    
    @After
    public void tearDown()
      {
      }

    /**
     * Test of getAllStudentLessons method, of class StudentLessonHandler.
     */
    @Test
    public void testGetAllStudentLessons()
      {
        System.out.println("getAllStudentLessons");
        StudentLessonHandler instance = new StudentLessonHandler();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getAllStudentLessons();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
      }

    /**
     * Test of getLessonId method, of class StudentLessonHandler.
     */
    @Test
    public void testGetLessonId()
      {
        System.out.println("getLessonId");
        StudentLessonHandler instance = new StudentLessonHandler();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getLessonId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
      }

    /**
     * Test of getStudentId method, of class StudentLessonHandler.
     */
    @Test
    public void testGetStudentId()
      {
        System.out.println("getStudentId");
        StudentLessonHandler instance = new StudentLessonHandler();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getStudentId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
      }

    /**
     * Test of getAttending method, of class StudentLessonHandler.
     */
    @Test
    public void testGetAttending()
      {
        System.out.println("getAttending");
        StudentLessonHandler instance = new StudentLessonHandler();
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.getAttending();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
      }

    /**
     * Test of getStudentLessonBasedOnCourse method, of class StudentLessonHandler.
     */
    @Test
    public void testGetStudentLessonBasedOnCourse()
      {
        System.out.println("getStudentLessonBasedOnCourse");
        int courseid = 1;
        StudentLessonHandler instance = new StudentLessonHandler();
        ArrayList<StudentLesson> expResult = null;
        ArrayList<StudentLesson> result = instance.getStudentLessonBasedOnCourse(courseid); 
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
          for (StudentLesson studentLesson : result)
            {
                System.out.println(studentLesson.getStudentName());
            }
      }
    
  }
