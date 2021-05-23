# IPlytics coding challenge
This coding challenge is for candidates for the position "Backend Developer - Web Applications"

## Overview
Our software lets users search for Patents and Standards, amongst other things. Our web application is the gateway to our data sets, and we would like to see how well you can build an application that models a little part of our data universe.

We want to know how well you can work with the following:
* Spring Boot framework
* Modern Java API's
* RESTful API design
* Unit and integration testing
* Written and generated documentation

## What we provide you
Fork this repo or clone it to your local environment. You will have a fully working Spring Boot webapp, which you can run and access at http://localhost:8080 to use a very simple API. Make a `GET` request to http://localhost:8080/patents/US1234A1 to load a dummy patent we created for you to get started.

The repo also contains some SQL scripts managed with Flyway, to manage our data model and contents. Data is stored in an in-memory H2 database which is recreated when the application starts up, so you don't need to configure anything external.

Run it from the entry point in the class `CodingchallengeBackendWebappApplication`.

## What we want you to do

You must solve a few small challenges in order to pass our test. In addition to doing the tasks below, we want to see that you can:
* Write clean code and structure it well
* Cover your code with tests in a suitable manner
* Write good commit messages and break up your work into sensibly-sized chunks
* Add documentation where necessary. This could be handwritten or auto-generated from your code

Here are your tasks:

### 1. Fix an endpoint bug
There is a bug in the `/patents/{publicationNumber}` GET endpoint. When we request a patent that we don't have in our database (For example, http://localhost:8080/patents/I-DO-NOT-EXIST ), the endpoint should return a `404 NOT FOUND` but that's not working. Instead we see a `500 INTERNAL SERVER ERROR`.

We already have a failing test case to cover this, in the class: `PatentControllerIntegrationTest`.

* Make the test pass, and try do it in a way which makes the best use of the Spring Framework's features.

### 2. CRUD for patents 
Our users need to add their own patents to the system. We have the `R` of CRUD but not the rest! 

* Add new endpoints to the `PatentController` so we can create new patent entries, or update/delete existing ones. Make sure to use the best practices of RESTful API design, to return sensible responses, and the correct status codes as required. You should also add integration and unit tests as needed.

### 3. Handle Declarations
One interesting aspect of industrial or technology standards is how they are composed. Companies or organisations typically work with a standard-setting organisation (like ISO or ETSI) to "declare" their patent to be a necessary part of a standard. So there is a relational connection between patents and standards, which we can model as a "declaration".

* Create a SQL model for a declaration, and include the appropriate foreign key relationships.

* Create new endpoint(s) which let a user declare their patent(s) to be part of a standard. As in the previous task, ensure that appropriate responses and status codes are returned.

## How to submit
Once you're happy with your work and want to submit, `zip` the repo folder and submit it via email to our HR manager. Remember to include the whole assignment directory (including hidden files), so we can see your git commit history.

Best of luck and thanks for taking the time to complete this challenge.