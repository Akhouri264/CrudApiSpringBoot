# CrudApiSpringBoot

Open the project in STS or any other ide of java 

Run the main application 

go to Postman first try to get bearer token

using get API http://localhost:8085/authenticate copy token you received and 

go to authorization section select bearer token and fill that with the 
token you copy.

Now you can use CRUD, Pagination,Global Searching, Sorting,etc API.

for example:

http://localhost:8085/library/page/0/100/bookid/desc

http://localhost:8085/library/all/key/CC
