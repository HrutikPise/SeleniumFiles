Feature: Learing API automation with java

Scenario Outline: rahulshettyacademy page
		Given Open AddBook.php <name> <isbn> <aisle>
		When Add values request
		Then extract the Msg and ID
		
Examples: 
| name | isbn | aisle |
| Java | pavya | 7986 |
| Python | gangya | 558f |