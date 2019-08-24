package com.revision.ctci.mdatabase;

public class FEntityRelationshipDiagram {
    /* People | Professionals | Companies
     *
     * PEOPLE: Let's Consider that the People consist of both Professionals
     * and others without a profession now they will have the following attributes
     * - people_id
     * - name
     * - age
     * - address
     * - email
     * - contact
     * - profession_id (if any) OPTIMIZATION consider when a current profession is required
     * then their professional table to be searched for all the jobs they have been and then loaded,
     * at least if their current profession for example is being greatly used could be held in their
     * main people table for easy retrieval (not like DENORMALIZATION but taken the professionals table
     * is indexed then based on profession_id faster retrieval is possible rather than joining all the
     * previous jobs and finding the latest one). There is one another way we could use the date_of_leaving
     * value to check if that is the current one (null value signifies so it is upto us which idea to use)
     *
     * PROFESSIONALS: The Professionals will be a person and he will be ideally be a part of the
     * company. Their features will be that they have a
     * - profession_id
     * - people_id
     * - company_id
     * - salary
     * - job_id
     * - date_of_join
     * - date_of_leaving (null if current)
     *
     * COMPANIES: The professionals are related to the company and some people can be working
     * as a consultant for more than one company and so can have multiple entries. So again this might affect
     * current profession optimization which was earlier thought for the peoples table
     * - company_id
     * - company_name
     * - company_type_id
     * - no_of_employees OPTIMIZATION - possibly the count of employee change might not need to be accurate
     * and could be updated at a certain interval of time to make it more easy by reducing load at server
     *
     * JOBS: This a additional table created out of the professionals table which could contain
     * - job_id
     * - job_name
     * - job_description
     *
     * CITY | STATE | COUNTRY | COMPANY_TYPE and other tables are excess but needed to normalize */
}