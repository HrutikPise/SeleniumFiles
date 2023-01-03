Feature: Add Delete get APIs Demos
Scenario: AddBook GetBook DeleteBook parameterization
Given Open AddBook.php Page
When "addPlaceAPI" values request "Post" method
Then extract the "ID" and "Msg"
And check Id is matching with "getPlaceAPI" Query Params and method "Get"
And "deletePlaceAPI" delete the exisiting method author and method "delete"