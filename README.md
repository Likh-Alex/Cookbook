# Cookbook Application

### Application is used to manage `Recipes`
### Application is created using `Java` and `Spring boot`
### Tests are created using `Junit` and `Mockito`

## Application has following operations
- `Get` a list of `recipes` (date created, description)
- `Add` a new `recipe`
- `Add` a new `recipe` based on another one - culinary `fork` of a recipe. 
  `Child` recipes `do not inherit` anything from a parent recipe - they are just shown as children of a parent one. User can add child recipes to recipes on any depth.
- `Modify` an existing `recipe` (on any depth).
- `Get` all `previous` recipe `versions` (on any depth).
- Recipes are sorted alphabetically (on any depth).

## In order to use the application
- Download the [source code](https://github.com/Likh-Alex/Cookbook)
- Project is delivered as Maven project
- [Download and Install Maven](https://maven.apache.org/install.html)
- Open in your IDE
- Once you opened the project in the IDE `run` - `CookbookApplication` and application will start.
- `unit` tests for `RecipeMapper` and `RecipeController` included in the `test` directory. 

## As Frontend part is in progress - in order to use the application
- Download and Install any API testing tool, e.g [Postman](https://www.postman.com)
### Endpoints description
- `http://localhost:8080/recipes` - default endpoint.
- `/` - `Get` `all` saved `recipes` sorted `alphabetically`
- `/save` - `Post` a `recipe` and revert with `ResponseDto`.
- `/modify/{id}` - `Put` a `recipe`. Modify existing `recipe`, if `recipe` not found - `HttpStatus.NOT_FOUND` status will be returned.
 If found - will be modified with supplied `RequestDto`, saved to DB and returned as a `ResponseDto`.
- `/fork/{id}` - `fork` a `recipe`. If `recipe` is found by supplied `id` it will become a forked version of a found `recipe`. 
  `Recipe` which is found by `id` becomes a parent of new `recipe`.
  if `recipe` not found - `HttpStatus.NOT_FOUND` status will be returned.
- `/versions/{id}` - `Get` a `List` of all previous versions of a `recipe`. If no `recipe` found by supplied `id` `HttpStatus.NOT_FOUND` will be returned.
- `/delete/{id}` - Finds and `deletes` a `recipe` by supplied `id` and returns a message `Recipe deleted`. If not found by `id` will return `Recipe not found` message. 
