/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import models.Recipe;

/**
 *
 * @author George.Pasparakis
 */

public interface RecipeDAO {
    Recipe create(String title, String description, String image);
    
}
