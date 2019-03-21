/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOImpl;

import DAO.RecipeDAO;
import database.Database;
import models.Employee;
import models.Person;
import models.Recipe;

/**
 *
 * @author George.Pasparakis
 */
public class RecipeDAOImpl implements RecipeDAO {

    @Override
    public Recipe create(String title, String description, String image) {
        Recipe r = new Recipe(title, description, image);
        addRecipeToDb(r);
        return r;
    }
    
    public int addRecipeToDb(Recipe r) {
        Database db = new Database();
        String server = "localhost:3306";
        String username = "root";
        String password = "root";
        String database = "cooking_site";
        String query = "INSERT INTO `cooking_site`.`recipes` (`title`, `description`, `image`) VALUES('" + 
                r.getTitle() +  "','" + r.getDescription() + "','" + r.getImage() + "')";
        int i = db.Database(server, database, username, password, query, (byte)1);
        
        return i;
    }
    
    void testPersonEmp() {
        Person p = new Employee();
        Employee e = (Employee) new Person();
        Person p2 = new Person();
        
    }
    
}
