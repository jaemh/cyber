Start from here:

Fourth problem:
Issue: A5-Security Misconfiguration
Now you are using old Spring version. You can fix the problem by going to project and open a pom.xml file. As you can see now you Spring using version 3.2.10. Change it to 4.0.0. to run the project.

It is very important to ensure that you have the latest version. This way you can be sure that you have the latest corrected updates.

First problem:
Issue: A4-Insecure Direct Object References
Steps to reproduce:
1. Connect to the server localhost:8080/login.
2. Enter a username: “user” and for the password “user”.
3. Press Submit!
4. Press the Show! -button under the Go to your user List -text 
5. You ended up seeing the information of all the people you should not have seen.
Type /logout to the address bar.

Fix the problem:
Go to the SecurityConfiguration file
Find configure method
Remove from the line .antMatchers("/userList").hasAnyAuthority("USER", "ADMIN”)  word “USER”.
Now only ADMIN has right to see user list. 
You can try run project again
Now when you try go to the user list, access is denied.
Thank you for fixing the problem.

Second problem:
Issue: A10-Unvalidated Redirects and Forwards
Steps to reproduce:
1. Connect to the server localhost:8080/login.
2. Enter a username: “admin” and for the password “admin”.
3. Press Submit!
4. Under the greetings, Press Show!
5. As you can see you were redirected to the wrong page
6. Type /logout to the address bar.

Fix the problem:
Go to the SignupController
Find getAdmin method
Remove redirection from  the line return "redirect:/done";
Change path /done to the /adminPage.
Now run the project again. 
As you can see now you see correct greetings.
Thank you for fixing the problem.

We can avoid this kind of problems by avoiding redirects and forwards.

Third problem:
Issue: A3-Cross-Site Scripting (XSS)
  1. Connect to the server localhost:8080/login.
  2. Enter a username: “user” and for the password “user”.
  3. Press Submit!
  4. Go to fill the form by pressing the Form button.
  5. Fill the name.
  6. Insert to the address: <script>alert(“Attack!");</script>
  7. Press Submit!
  8. Now pops up the window where it reads Alert!
Type /logout to the address bar.

Fix the problem:
Go to the resource
Go to the template package
Open the userList page
Change from the row <li th:utext="${s.address}”> the th:utext to th:text.
Now try again! 
As you can see, now you can’t manipulate the html by using javascript. 
Than you fo fixing the problem.

We can avoid this kind on problems by ensuring that all the fields where user can write is secure from site scripting. 



Fifth problem
Issue: A8-Cross-Site Request Forgery (CSRF)
Now application login system is not using CSRF token. 
Without such a token, attackers can forge requests and might get access to your cookies. If this happens, the attacker will be able to steal the session and use it how he/she likes to.

Fix the problem: 
Unfortunately, I did not succeed in fixing the problem to the end so I can not give you complite instruction for fixing it. But i will give you everything that i figure it out. 
Go to the SecurityConfiguration file
Remove line .logoutRequestMatcher(new AntPathRequestMatcher("/logout”))
Go to the resource 
Go to the menu -template
Insert <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}”/>” (This is ensures that you include the CSRF token in all PATCH, POST, PUT, and DELETE methods)
But now i get stuck and do not know how to continue. 
