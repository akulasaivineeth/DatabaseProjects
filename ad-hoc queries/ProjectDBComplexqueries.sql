1) Retrive the date when More number of books are borrowed?

select date_of_borrow
from FALL22_S003_15_BOOKS
group by date_of_borrow
having count(date_of_borrow) = ALL (select max(count(date_of_borrow))
                                    from FALL22_S003_15_BOOKS
                                    group by date_of_borrow);


2) Retrive all the names of the books which are borrowed more than 1 time?


select DISTINCt B.BOOK_ID, B.name, B.DATE_OF_BORROW 
from FALL22_S003_15_BOOKS B
where B.DATE_OF_BORROW =
                    (select date_of_borrow
                    from FALL22_S003_15_BOOKS
                    group by date_of_borrow
                    having count(date_of_borrow) = ALL (select max(count(date_of_borrow))
                                                        from FALL22_S003_15_BOOKS
                                                        group by date_of_borrow));


3) Find out which degree students borrowed more number of books?



select degree
from FALL22_S003_15_STUDENTS
where mem_id IN
            (select mem_id
            from FALL22_S003_15_BOOKS                                       
            where mem_id IN           
                        (select mem_id 
                         from FALL22_S003_15_STUDENTS)) 
Group by degree
having count(degree) = (select max(count(degree))
                        from FALL22_S003_15_STUDENTS
                        where mem_id IN
                                    (select mem_id
                                    from FALL22_S003_15_BOOKS                                       
                                    where mem_id IN           
                                                (select mem_id
                                                 from FALL22_S003_15_STUDENTS)) 
                        Group by degree);




4) Find out the Fname, Lname of the members who has the maximum penality? 

SELECT mem_id, fname, lname, ph_no
FROM Fall22_S003_15_MEMBERS
WHERE mem_id in (SELECT mem_id
                        FROM    (   SELECT mem_id, MAX(amount) as MAX_PENALTY
                                     FROM Fall22_S003_15_MEMBERS_RETURNS_BOOKS
                                     where amount is not null
                                      Group by mem_id
                                     order by MAX_PENALTY desc
                                      Fetch First 3 rows only));



5) Retrive all the book names and their editions for the books which has more than 1 edition?

SELECT b.name,be.edition
FROM Fall22_S003_15_BOOKS b,FALL22_S003_15_BOOKS_EDITION be
WHERE b.book_id=be.book_id AND b.book_id IN
                     ( SELECT book_id
                      FROM FALL22_S003_15_BOOKS_EDITION
                      GROUP BY book_id
                      HAVING COUNT(book_id)>1);

 







6) Retrive all the author names and their id who has written more number of books?

Select name, author_id
From  Fall22_S003_15_AUTHORS
Where author_id =
                (Select author_id
                From Fall22_S003_15_AUTHOR_WRITES_BOOKS
                Group by author_id
                having count((author_id)) =  (Select max(count(author_id))
                                            From Fall22_S003_15_AUTHOR_WRITES_BOOKS
                                            Group by author_id));

 

7)  Generates total amount for all possible combinations of the memberid and bookid  using CUBE ?

SELECT mem_id, book_id, SUM(amount) as TOTAL
FROM Fall22_S003_15_MEMBERS_RETURNS_BOOKS
WHERE amount IS NOT NULL
GROUP BY CUBE(mem_id,book_id);



8) Generates total amount of the mem_id and book_id in a hierarchical way USING ROLLUP?

SELECT mem_id, book_id, SUM(amount) as TOTAL
FROM Fall22_S003_15_MEMBERS_RETURNS_BOOKS
WHERE amount IS NOT NULL
GROUP BY ROLLUP(mem_id,book_id);

 

9) Retrive member ID and the maximum penality amount of that member using Over clause?

SELECT  MAX(amount) OVER (ORDER BY mem_id), mem_id
FROM  FALL22_S003_15_MEMBERS_RETURNS_BOOKS;



10) Retrive the top 5 member_ids who have the highest penality amount?

SELECT mem_id, sum(amount) as Total_penality
from FALL22_S003_15_MEMBERS_RETURNS_BOOKS
where amount is not null
group by mem_id
order by sum(amount) desc
fetch first 5 rows only;


11) Retrive the names and no of copies of the books based on the category?


(SELECT category, sum(copies) as Total_copies
from FALL22_S003_15_BOOKS
group by category);



12) Retrive the shelf Id based on the book name.

Select shelf_id
From FALL22_S003_15_BOOKS
where name = 'Intestallar';


13) Retrive the book name, id of the member who borrowed it and on which date also expected date of return? 

select  mem_id, name, date_of_borrow, date_of_borrow+14 as date_of_return
from  FALL22_S003_15_BOOKS
where mem_id = 'M001';



14) Retrive the book names that are available to borrow?

select name
from FALL22_S003_15_BOOKS
where date_of_borrow is null


 
