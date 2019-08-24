package com.revision.ctci.mdatabase;

public class GDesignGradeDatabase {
    /* STUDENT:
     * - student_id
     * - student_name
     *
     * COURSE:
     * - course_id
     * - course_name
     *
     * In case we are hving varied number of subjects then the below table needs to be altered
     * but the assumption is that across schools, colleges we will tend to have same number of
     * subjects only the name will differ based on the courses they take. even then the marks
     * shared among them will be same as otherwise it is not a fair competition. It is given as
     * a simple database so we might not consider too much into the complexities and create the
     * MARKS table which combines the student with his course and then list out the marks for
     * each subject. If needed we could have another table list out the subject name based on the
     * column name and the course_id.
     * MARKS: (STUDENT_COURSE)
     * - student_id
     * - course_id
     * - subject_1
     * - subject_2
     * - subject_3
     * - subject_4
     * - subject_5
     *
     * Now for calculating the grades first we need to decide if we are going to take the entire
     * set of students or only a subset. currently for simplicity we consider that we take all.
     * in future we could include concepts like based on the class or the year they are studying
     * and other similar conditions. The logic will be to create the list of students to be graded
     * and then take that set and then calculate the grade average for them and now take the no of subjects
     * and then add all the marks and take average and then sort in descending order of average
     *
     * final view looks similar as given below
     * [STUDENT_ID | STUDENT_NAME | COURSE_ID | COURSE_NAME | AVERAGE_GRADE]
     *
     * now for the 10% percent (student count)we take the initial list of students we have taken for the calculation and then
     * top_10 = (count * 10 / 100)
     *
     * use TOP(top_10) function to get the first 10 percentage of the student
     *
     * IMPORTANT: in case if it is related to top 10% marks scored we might need some calculation for determining the 10% percent
     * range based on the values and then return those set of students the rows may be greater than 10% of earlier
     * calculation based on student since here there will/might be same average marks so plan accordingly
     * */

    /*
     * CREATE TABLE student (student_id int,student_name varchar(50));
     * CREATE TABLE course (course_id int,course_name varchar(50));
     * CREATE TABLE marks (student_id int,course_id int, subject_1 int, subject_2 int, subject_3 int, subject_4 int, subject_5 int);
     *
     * INSERT into student(student_id,student_name) VALUES(1,"Mark");
     * INSERT into student(student_id,student_name) VALUES(2,"Adam");
     * INSERT into student(student_id,student_name) VALUES(3,"Terry");
     * INSERT into student(student_id,student_name) VALUES(4,"Steve");
     * INSERT into student(student_id,student_name) VALUES(5,"Sento");
     * INSERT into student(student_id,student_name) VALUES(6,"Tennese");
     * INSERT into student(student_id,student_name) VALUES(7,"Kaira");
     * INSERT into student(student_id,student_name) VALUES(8,"Roger");
     * INSERT into student(student_id,student_name) VALUES(9,"Amy");
     * INSERT into student(student_id,student_name) VALUES(10,"Kai");
     * INSERT into student(student_id,student_name) VALUES(11,"Shino");
     * INSERT into student(student_id,student_name) VALUES(12,"Abe");
     * INSERT into student(student_id,student_name) VALUES(13,"Kenny");
     * INSERT into student(student_id,student_name) VALUES(14,"Andy");
     * INSERT into student(student_id,student_name) VALUES(15,"Carrol");
     * INSERT into student(student_id,student_name) VALUES(16,"Sita");
     * INSERT into student(student_id,student_name) VALUES(17,"Teddy");
     * INSERT into student(student_id,student_name) VALUES(18,"Sogunate");
     * INSERT into student(student_id,student_name) VALUES(19,"Mandy");
     * INSERT into student(student_id,student_name) VALUES(20,"Gruman");
     *
     * INSERT into course(course_id,course_name) VALUES(1,"Electronics and Communication");
     * INSERT into course(course_id,course_name) VALUES(2,"Electronics and Instrumentation");
     * INSERT into course(course_id,course_name) VALUES(3,"Computer Science");
     *
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (1, 1, 78, 86, 87, 80, 78);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (2, 1, 80, 82, 81, 85, 69);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (3, 1, 90, 86, 85, 82, 78);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (4, 2, 87, 89, 81, 89, 75);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (5, 2, 79, 87, 86, 85, 85);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (6, 1, 72, 79, 97, 91, 69);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (7, 1, 81, 81, 78, 75, 79);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (8, 1, 66, 90, 75, 92, 98);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (9, 2, 80, 79, 79, 90, 80);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (10, 2, 79, 81, 86, 82, 85);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (11, 2, 78, 81, 82, 83, 81);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (12, 3, 85, 71, 81, 70, 69);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (13, 2, 70, 86, 75, 82, 78);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (14, 3, 77, 79, 81, 79, 75);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (15, 2, 79, 67, 87, 75, 80);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (16, 1, 78, 86, 87, 70, 78);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (17, 3, 70, 82, 71, 85, 69);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (18, 1, 70, 86, 85, 72, 78);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (19, 3, 77, 89, 71, 89, 75);
     * INSERT into marks (student_id, course_id, subject_1, subject_2, subject_3, subject_4, subject_5) VALUES (20, 3, 79, 87, 76, 75, 85);
     *
     * SELECT
     * student.student_name,
     * course.course_name,
     * ((subject_1 + subject_2 + subject_3 + subject_4 + subject_5) / 5) average
     * from marks
     *  join student on marks.student_id = student.student_id
     *  join course on marks.course_id = course.course_id
     * GROUP BY student.student_id
     * HAVING average >= (SELECT top.average
     *                   FROM
     *                     (SELECT
     *                        student.student_name                                              student_name,
     *                        course.course_name,
     *                        ((subject_1 + subject_2 + subject_3 + subject_4 + subject_5) / 5) average
     *                      from marks
     *                        join student on marks.student_id = student.student_id
     *                        join course on marks.course_id = course.course_id
     *                      ORDER BY average DESC LIMIT (( SELECT COUNT(student_id) from student) * 10/100)) top
     *                   ORDER BY top.average LIMIT 1)
     * ORDER BY average DESC;
     * */
}