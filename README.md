# Cookbook Application

##Application is used to manage `Recipes`
### Application has following operations

- `Get` a list of `recipes` (date created, description)
- `Add` a new `recipe`
- `Add` a new `recipe` based on another one - culinary `fork` of a recipe. `Child` recipes `do not inherit` anything from a parent recipe - they are just shown as children of a parent one. User can add child recipes to recipes on any depth.
- `Modify` an existing `recipe` (on any depth).
- `Get` all `previous` recipe `versions` (on any depth).
- Recipes are sorted alphabetically (on any depth).

## In order to use the application
- Download the source code
- Project is delivered as Maven project
- [Download and Install Maven](https://maven.apache.org/install.html)
- Open in your IDE
- Once you opened the project in the IDE run - `CookbookApplication` and application will start.
- `unit` tests for `RecipeMapper` and `RecipeController` included in the `test` directory. 