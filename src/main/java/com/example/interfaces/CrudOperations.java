package com.example.interfaces;

import java.util.List;

public interface CrudOperations {
    void create(String name, String email) throws Exception;
    List<String[]> read() throws Exception;
    boolean update(String oldName, String newName, String newEmail) throws Exception;
    boolean delete(String nameToDelete) throws Exception;
    
    void create() throws Exception;
    void update() throws Exception;
    void delete() throws Exception;
}
