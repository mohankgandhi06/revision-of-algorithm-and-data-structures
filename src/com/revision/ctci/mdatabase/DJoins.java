package com.revision.ctci.mdatabase;

public class DJoins {
    /* Joins are inner join, outer join, cross join, self join
     * INNER JOIN or we can call normal JOIN will join the tables based on the unique_identifier
     * of the first table and the second table here we could use equality (EQUI) or non-equality(THETA)
     * as the CLAUSE for joining the tables
     * OUTER JOIN will be either RIGHT, LEFT, FULL outer join and the condition is like whole
     * of one table with the matching rows of the other like suppose we need list of people in a home
     * and the number of mobile phone they have children might not have them but they are a family member\
     * so in the outer join they will be listed with 0 mobile and not left out like in normal join
     * CROSS JOIN will be like a cartesian product - mapping one with each one of the other table
     * can be used in scenarios were combinatorial values are to be listed from each table's column
     * SELF JOIN is joining the same table to itself used when same table is needed to gather some details
     * for same table. for example lets take employees in a company and their managers so everyone will be
     * a employee and so to find ones manager again we have to join the same employee table but joining
     * like in one_table person's_manager_id and the other_table person's_id */
}