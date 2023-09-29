## CodeFellowShip
Application that allows people learning to code to connect with each other and support each other on their coding journeys.
User can create posts, See other users in the service, Follow and Unfollow other users, browsing their posts and add comments on it.
## Features 
- **Spring boot Authentication**. 
- **PostgreSQL** with:
1. `@OneToMany` relationship between: *ApplicationUser* and *Post*.
2. `@OneToMany` relationship between: *Post* and *Comments*.
3. `@ManyToOne` relationship between: *ApplicationUser-follower* and *ApplicationUser-followed*.
4. `@ManyToOne` relationship between: *ApplicationUser-followed* and *ApplicationUser-follower*.

## Routes and Methods
| Route               | Method             | HTTP Request Type | Description                                                                                          |
|---------------------|--------------------|-------------------|------------------------------------------------------------------------------------------------------|
| /login              | getLoginPage()     | GET               | Display the login page.                                                                             |
| /signup             | getSignupPage()    | GET               | Display the signup page.                                                                            |
| /signup             | signUp()           | POST              | Process user signup, validate input, and redirect to the home page. Displays error messages if validation fails. |
| /logout             | getLogoutPage()    | GET               | Display the home page.                                                                              |
| /                   | getHomePage()      | GET               | Display the home page with user info and a logout button if authenticated. Links to login and signup if the user is NOT logged in. |
| /myprofile          | getMyProfile()     | GET               | Display the user's profile page if authenticated. Redirect to the home page if not logged in.     |
| /profile/{id}       | getOthersProfile() | GET               | Display the profile page of other users based on their id. Throws an exception if the user is not found. |
| /add-post           | addPost()          | POST              | Allows authenticated users to add a new post with a given body. Redirects to the home page after adding a post. |
| /follow/{followedId}| newFollow()        | POST              | Allows authenticated users to follow another user. Redirects to the feed page.                       |
| /feed               | getFeed()          | GET               | Display the feed page with posts from followed users.                                                |
| /unfollow/{id}      | unFollow()         | POST              | Allows authenticated users to unfollow another user. Redirects to the home page.                      |
| /add-comment/{postId}     | addComment()       | POST              | Allows users to add a comment to a specific post. Redirects to the feed page after adding the comment. |
| /delete-comment/{commentId}/{postId} | deleteComment()    | POST              | Allows users to delete a comment on their own post. Redirects to the home page after deleting the comment. |

## Templates

| Template Name         | Description                                                                                                                                                                                                                                                                        |
|-----------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `login.html`          | Provides a login form for users to enter their credentials (username and password).<br>  Includes a link to the signup page for users who don't have an account.                                                                                                                   |
| `signup.html`         | Provides a signup form for users to create a new account.<br>  Includes fields for *first name*, *last name*, *date of birth*, *bio*, *username* and *password*.<br>  Includes a link to the login page for users who already have an account.                                     |
| `user-profile.html`   | This template displays a user's profile information, including their name, username, date of birth, bio, and a list of their posts. Users can create new posts if logged in.                                                                                                       |
| `others-profile.html` | This template displays the profile of other users, showing their username, name, date of birth, bio, and a list of their posts. It allows viewing profiles of other users.                                                                                                         |
| `index.html`          | The index template serves as the home page for the Codefellowship application. It displays a welcome message and provides navigation options for user profiles, login, signup, and logout. For *logged in Users*: it  lists all users and allows the current user to create posts. |
| `feeds.html`          | This template serves as the Feeds page for the Logged-in Users. It displays the posts of other users that that the current user is following.                                                                                                                                      |

## *Set up the Code*
1. *Clone the repo*.
2. *Update the `application.properties` file to include your JDBC connection*.
3. *Sign Up with a new account and choose a unique username*.