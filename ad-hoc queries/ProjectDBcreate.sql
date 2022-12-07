CREATE TABLE Fall22_S003_15_MEMBERS
	(

		mem_id VARCHAR(6) not null,
		fname VARCHAR(30),
		lname VARCHAR(30),
		ph_no VARCHAR(10),
		address VARCHAR(50),

		Constraint member_pk PRIMARY KEY(mem_id)
	);	


CREATE TABLE Fall22_S003_15_MEMBERS_EMAIL
	(
		mem_id VARCHAR(6) not null, 
		email VARCHAR(30),

		Constraint memberem_pk PRIMARY KEY (mem_id, email),
		Constraint memberem_fk FOREIGN KEY(mem_id) REFERENCES Fall22_S003_15_MEMBERS(mem_id)

	);	


CREATE TABLE Fall22_S003_15_STUDENTS
	(
		mav_id VARCHAR(6) not null,
		mem_id VARCHAR(6) not null,
		degree VARCHAR(15),

		Constraint mav_pk PRIMARY KEY(mav_id, mem_id),
		Constraint memberss_fk FOREIGN KEY(mem_id) REFERENCES Fall22_S003_15_MEMBERS(mem_id)

	);


CREATE TABLE Fall22_S003_15_STAFF
	(
		mem_id VARCHAR(6) not null,
		staff_id VARCHAR(6) not null,
		type_of_employment VARCHAR(30),

		Constraint staffid_pk PRIMARY KEY(staff_id, mem_id),
		Constraint memberstid_fk FOREIGN KEY(mem_id) REFERENCES Fall22_S003_15_MEMBERS(mem_id)
        

	);

alter table Fall22_S003_15_STAFF add constraint staffid_uk unique(staff_id);

CREATE TABLE Fall22_S003_15_BOOKS
	(
		shelf_id VARCHAR(6),
		book_id VARCHAR(6) not null,
		language VARCHAR(50),
		category VARCHAR(100),
		copies VARCHAR(6),
		name VARCHAR(100),
		date_of_borrow date,
		mem_id VARCHAR(6),

		Constraint bookid_pk PRIMARY KEY(book_id),
		Constraint memberboid_fk FOREIGN KEY(mem_id) REFERENCES Fall22_S003_15_MEMBERS(mem_id) 

	);


CREATE TABLE Fall22_S003_15_BOOKS_EDITION
	(
		book_id VARCHAR(6) not null,
		edition VARCHAR(30),

		Constraint bookeditionid_pk PRIMARY KEY(book_id, edition),
		Constraint bookeditionid_fk FOREIGN KEY(book_id) REFERENCES Fall22_S003_15_BOOKS(book_id) 
	);


CREATE TABLE Fall22_S003_15_MEMBERS_RETURNS_BOOKS
	(
		mem_id VARCHAR(6) not null,
		book_id VARCHAR(6) not null,
        penality_no VARCHAR(6),
		date_of_return date,
        amount NUMBER(30),

		Constraint memreturnbookid_pk PRIMARY KEY(book_id, mem_id),
		Constraint mmemreturnmemid_fk FOREIGN KEY(mem_id) REFERENCES Fall22_S003_15_MEMBERS(mem_id),
		Constraint memreturnbookid_fk FOREIGN KEY(book_id) REFERENCES Fall22_S003_15_BOOKS(book_id)

	);



CREATE TABLE Fall22_S003_15_STAFFS_MANAGES_BOOKS
	(
		staff_id VARCHAR(6) not null ,
		book_id VARCHAR(6) not null,

		Constraint staffmanagestaffid_pk PRIMARY KEY(staff_id, book_id),
		Constraint staffmanagestaffid_fk FOREIGN KEY(staff_id) REFERENCES Fall22_S003_15_STAFF(staff_id), 
		Constraint staffmanagebookid_fk FOREIGN KEY(book_id) REFERENCES Fall22_S003_15_BOOKS(book_id)

	);



CREATE TABLE Fall22_S003_15_AUTHORS
	(

		author_id VARCHAR(6) not null,
		name VARCHAR(30),
		ph_no VARCHAR(10),


		Constraint authorid_pk PRIMARY KEY(author_id)
	);	


CREATE TABLE Fall22_S003_15_AUTHOR_WRITES_BOOKS
	(

		author_id VARCHAR(6) not null,
		book_id VARCHAR(6) not null,


		Constraint authorwritesid_pk PRIMARY KEY(author_id, book_id),
		Constraint authorwritesid_fk FOREIGN KEY(author_id) REFERENCES Fall22_S003_15_AUTHORS(author_id),
		Constraint authorwritesbooksid_fk FOREIGN KEY(book_id) REFERENCES Fall22_S003_15_BOOKS(book_id)
	);	


CREATE TABLE Fall22_S003_15_PUBLISHER
	(

		publisher_id VARCHAR(6) not null,
		name VARCHAR(30),
		ph_no VARCHAR(10),


		Constraint publisherid_pk PRIMARY KEY(publisher_id)
	);	



CREATE TABLE Fall22_S003_15_PUBLISHER_PUBLISHES_BOOKS
	(

		publisher_id VARCHAR(6) not null,
		date_of_publish date,
		book_id VARCHAR(6) not null,
		


		Constraint publisherpublishesid_pk PRIMARY KEY(publisher_id, book_id),

		Constraint publisherpublishesid_fk FOREIGN KEY(publisher_id) REFERENCES Fall22_S003_15_PUBLISHER(publisher_id),
		Constraint publisherpublishebookid_fk FOREIGN KEY(book_id) REFERENCES Fall22_S003_15_BOOKS(book_id)
	);	





