PROBLEM: SALES TAXES 
 
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions. 
 
When I purchase items I receive a receipt which lists the name of all the items and their price (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid. The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax. 
 
Write an application that prints out the receipt details for these shopping baskets
 
INPUT: 
 
Input 1: 
1 book at 12.49 
1 music CD at 14.99 
1 chocolate bar at 0.85 
 
Input 2: 
1 imported box of chocolates at 10.00 
1 imported bottle of perfume at 47.50 
 
Input 3: 1 imported bottle of perfume at 27.99 
1 bottle of perfume at 18.99 
1 packet of headache pills at 9.75 
1 box of imported chocolates at 11.25 
 
OUTPUT 
 
Output 1: 
1 book : 12.49 
1 music CD: 16.49 
1 chocolate bar: 0.85 
Sales Taxes: 1.50 
Total: 29.83 
 
Output 2: 
1 imported box of chocolates: 10.50 
1 imported bottle of perfume: 54.65 
Sales Taxes: 7.65 
Total: 65.15 
 
Output 3: 
1 imported bottle of perfume: 32.19 
1 bottle of perfume: 20.89 
1 packet of headache pills: 9.75 
1 imported box of chocolates: 11.85 
Sales Taxes: 6.70 
Total: 74.68 


Design

1.
	Parsing a string which represents a new basket
		Item XXX:
	Parsing a string which represents a new item
		quantity [imported] productDescription {at} price
 	where:
 		at -> delimiter for product description
 		imported -> can exists or not 
I created a parser with a behaviour like to the SAX parser
because I considered the use of this application with big amount of data
-> the parser will create at most one new basket in memory
I created two error classes in order to handle malformed inputs:
	BasketMalformedError and ItemMalformedError

2.	The rounding rules for decimal numbers
	In order to avoid the lost of precisions I used the BigDecimal class

3.	In order to decide if an item is exempt or not from the sales taxes I created 
	cache implemented by a Map. Obviously a better solution would be a DB. 
	I simulated this behaviour by creating a data generator for the only exempt 
	products mentioned on the above test case

4.	Generality of the solution
	I create a small Main (even if It was not required) in order to test
	my solution with an input as File
	In my Unit Tests the solution is checked by a generator of fake data string

5. 	Application won't be used in multi-thread environments like WebApps or Mobile Apps
	
6.	Log4J
	TDD -> starting on the design of the tests
	UML Diagrams Not required → Not implemented
	Text messages translation Not required → Not implemented
