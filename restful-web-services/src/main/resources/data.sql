-- Default data file which will be used by H2 DB automatically on server startup to insert the data

insert into user values(101, sysdate(), 'Ash')
insert into user values(102, sysdate(), 'Jack')
insert into user values(103, sysdate(), 'Jill')

insert into post values(201, 'My first post', 101)
insert into post values(202, 'My second post', 101)
