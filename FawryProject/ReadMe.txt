import the project as maven project
run the project
open the postman collection link
https://www.postman.com/altimetry-cosmologist-74731296/workspace/fawry/collection/25079949-770496cb-ecc9-4189-95e9-572cb1eba30c?action=share&creator=25079949
fork the collection to your work space
open postman desktop agent
send requests and test the project.


please note that:

the project is intialized with no Discounts and no transactions at all, so you will not be able to check discounts or ask for refund until you make some transactions and add some discounts

the bodies in postman requests are case senstive.

while attempting to add new service provider(admin functionality), write the form such that:
the first form query is used to read the amount to be paid,
the second form query is used to read the payment option, 
after that add whatever queries you need, don't forget to write the number of the queries in the request body.

when you attempt to pay for some service provider:
first View all services to get the name and the category name of the service provider.
then write these names to get service form function, this function will return the formm you need to fill.
then go to pay function, write the names and the form handler, this is shown in detail in postman collection.

when you're placing a refund request:
first run GetMyTransactions, to get all the transactions you've done, each specified by id.
then go to ask for refund and write the id of the transaction in the url.

this is the same to accepting refund requests(by admin), through functions get refund request and accept redund.

 