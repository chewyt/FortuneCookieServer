# FortuneCookieServer

Template for making repo with customised pom.xml files

The objective of this workshop is to write a client/server fortune cookie
Added: write a multi-threaded client/server fortune cookie with Executor Service

Added: project scope for security

- 1.  Create a file user.txt with the following format. File is initially blank
- <username>/<password>
-
- 2.  when the first user tried to get a cookie, displaying the following:
- "You are not registered. Please choose a username and password e.g. abc/123"
-
- 3.Server saves the username/password in users.txt
-
- 4.  when the user logs in, set some variable isLogin to true.abstract
-
- 5.  When the next get-cookie command is >30seconds later than the previous
- one, isLogin becomes false. Server displays "Please log in again"
