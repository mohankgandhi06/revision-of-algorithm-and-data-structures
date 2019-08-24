-- Pattern of the SQL is as follows
-- UPDATE table_name SET column_name = column_new_value, column_name_2 = column_2_new_value
-- WHERE apartment_id IN (list_of_apartments_in_given_building)

UPDATE Requests
SET status = "Closed", description = "Closed for Renovation"
WHERE aptid IN (SELECT aptid
                from Apartments
                WHERE Apartments.buildingid = 11);