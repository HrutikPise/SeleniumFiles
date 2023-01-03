Feature: EndToEnd_Testing

#@BeforeClass
Scenario Outline: Testing the rahulSheety academy shop
Given User in Login Home Page
When enter "<username>" and "<password>"
Then user appear in home page
And "AddProduct" to home Page
When "OrderProduct" it must be appear in order list
When "ViewProduct" it must be in My Cart list
When "DeleteOrder" after it must be delete
When "DeleteProduct" from webSite
When "AddToCart" check its add to the cart or not
When "DeleteAddToProduct" product from cart

Examples:
	|username | password |
	| manibike7829@gmail.com | Mani@123 |
	
#Help full for Hooks
#@AfterClass
#Scenario: Testing rahulaSheety Add Product shop
#Given	 Add the Product to Home_Page of rahulSheety academy
#When "AddProduct" to rahulsheety_academy
#Then Check "productId" is valid or not