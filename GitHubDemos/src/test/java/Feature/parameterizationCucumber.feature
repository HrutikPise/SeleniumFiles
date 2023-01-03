Feature: parameterization Demos
Scenario: AddBook parameterization
Given Open AddBook.php
When "addPlaceAPI" values request and "Post" method
Then extract the "ID"