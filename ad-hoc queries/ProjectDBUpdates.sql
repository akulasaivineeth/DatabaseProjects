UPDATE Fall22_S003_15_MEMBERS
SET FName = 'Suresh'
where mem_id = 'M001';

UPDATE Fall22_S003_15_MEMBERS_EMAIL
SET email = 'Suresh@gmail.com'
where Mem_id = 'M001';

UPDATE Fall22_S003_15_STUDENTS
SET Mav_id = 'axa041'
where Mem_id = 'M001';

UPDATE Fall22_S003_15_STAFF
SET Staff_id = 'S042'
where mem_id =  'M060';

UPDATE Fall22_S003_15_BOOKS
SET language= 'French'
where mem_id = 'M001';

UPDATE Fall22_S003_15_BOOKS_EDITION
SET edition = 'E2'
where book_id = 'B012';


UPDATE Fall22_S003_15_MEMBERS_RETURNS_BOOKS
SET amount = 10
where mem_id = 'M001';


UPDATE Fall22_S003_15_AUTHORS
SET name = 'Goutham'
where author_id = 'A001';

UPDATE Fall22_S003_15_AUTHOR_WRITES_BOOKS
SET book_id = 'B041'
where author_id = 'A001';


UPDATE Fall22_S003_15_PUBLISHER
SET name = 'sunny'
where publisher_id = 'P001';


UPDATE Fall22_S003_15_PUBLISHER_PUBLISHES_BOOKS
SET date_of_publish = TO_DATE('2007-07-17','yyyy-mm-dd')
where publisher_id = 'P001';


UPDATE Fall22_S003_15_BOOKS
SET DATE_OF_BORROW = NULL , mem_id = NULL
where book_id = 'B002';

UPDATE Fall22_S003_15_BOOKS
SET DATE_OF_BORROW = NULL , mem_id = NULL
where book_id = 'B003';

UPDATE Fall22_S003_15_BOOKS
SET DATE_OF_BORROW = NULL , mem_id = NULL
where book_id = 'B007';

UPDATE Fall22_S003_15_BOOKS
SET DATE_OF_BORROW = NULL , mem_id = NULL
where book_id = 'B008';