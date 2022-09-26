# CrudApiSpringBoot

Open the project in STS or any other ide of java 

Run the main application 

go to Postman first try to get bearer token

using get API http://localhost:8085/api/v1/authenticate copy token you received and 

go to authorization section select bearer token and fill that with the 
token you copy.

Now you can use CRUD, Pagination,Global Searching, Sorting,etc API.


** configuration related with postgreSQL required for the implementation.

for example:

http://localhost:8085/api/v1/library/page/0/100/bookid/desc

http://localhost:8085/api/v1/library/all/key/CC
