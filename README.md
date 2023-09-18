## CodeFellowShip
Application that allows people learning to code to connect with each other and support each other on their coding journeys.

## Routes and Methods

| Route       | Method                  | HTTP Request Type | Description                                                                                                                           |
|-------------|-------------------------|-------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| `/login`    | `getLoginPage()`        | GET               | Display the login page.                                                                                                               |
| `/signup`   | `getSignupPage()`       | GET               | Display the signup page.                                                                                                              |
| `/signup`   | `signUp()`              | POST              | Process user signup and redirect to home page.                                                                                        |
| `/logout`   | `getLogoutPage()`       | GET               | Display the home page.                                                                                                                |
| `/`         | `getHomePage()`         | GET               | - Display the home page, user info and logout button if authenticated.<br/> - Links to login and signup if the user is NOT logged in. |

## Templates

| Template     | Description                                                                                                                                                                                                                                                                        |
|--------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| login.html   | - Provides a login form for users to enter their credentials (username and password).<br> - Includes a link to the signup page for users who don't have an account.                                                                                                                |
| signup.html  | - Provides a signup form for users to create a new account.<br> - Includes fields for *first name*, *last name*, *date of birth*, *bio*, *username* and *password*.<br> - Includes a link to the login page for users who already have an account.                                 |
| index.html   | - Displays a welcome message for users who are logged in.<br> - If a user is logged in, it shows their *username* and *bio*.<br> - Includes a "Log Out" link for users to log out if they are logged in.<br> - If no user is logged in, it shows options to "Log In" or "Sign Up". |
