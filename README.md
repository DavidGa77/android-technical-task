## Solution to Bug 1 - Layout does not look as expected

I used the design view of `activity_login.xml` to move the components to the correct location. I then set the horizontal and vertical constraints so that the components would display correctly when the app starts.

## Solution to Bug 2 - Validation is incorrect

I removed the boolean variable `allValid` and replaced with booleans for each credential. This is because previously it was possible to login with only one valid credential. I also set the error message to null when credential is valid as previously the error message was showing even if the credential was valid.

## Solution to Bug 3 - Animation is looping incorrectly

I wasn't able to fix this bug. I found the method `setMinAndMaxFrame()`, however I only succeeded in making it loop the final animation, rather than the whole animation, then looping the final part.

## Add two new screens
I added the two new screens with the required buttons and they link together correctly i.e. Login Screen -> Accounts Screen, Accounts Screen -> Individual Account Screen. However I was unable to access the API, despite supplying the credentials provided. Therefore I could only really wireframe how the buttons and headers would look without the data from the API. 

I used the `org.jetbrains.anko.doAsync` method for this task as I was getting a runtime error caused by blocking the application thread with my call to the API. This method runs the task asynchronously to other tasks running.
