/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca1attendanceprogram.DAL;

import ca1attendanceprogram.BE.Course;
import ca1attendanceprogram.BE.Lesson;
import ca1attendanceprogram.BE.Teacher;
import java.util.List;
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
public class LessonHandlerTest
  {
    
    public LessonHandlerTest()
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
     * Test of getLessonsFromTeacher method, of class LessonHandler.
     */
    @Test
    public void testGetLessonsFromTeacher()
      {
        System.out.println("getLessonsFromTeacher");
        Teacher teacher = null;
        LessonHandler instance = new LessonHandler();
        List<Course> expResult = null;
        List<Course> result = instance.getLessonsFromTeacher(teacher);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
      }

    /**
     * Test of createNewLesson method, of class LessonHandler.
     */
    @Test
    public void testCreateNewLesson()
      {
        System.out.println("createNewLesson");
        Course course = null;
        LessonHandler instance = new LessonHandler();
        boolean expResult = false;
        boolean result = instance.createNewLesson(course);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
      }

    /**
     * Test of getNewestLesson method, of class LessonHandler.
     */
    @Test
    public void testGetNewestLesson()
      {
        System.out.println("getNewestLesson");
        LessonHandler instance = new LessonHandler();
        Lesson expResult = null;
        Lesson result = instance.getNewestLesson();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
      }
    
  }
