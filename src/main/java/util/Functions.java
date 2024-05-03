package util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Toy;

public class Functions {

    ObjectMapper mapper = new ObjectMapper();

    public int generateID(){
        return (int) (Math.random() * 2000000000);
    }

    public int generateChance(){
        return (int) (Math.random() * 100);
    }



    public void addData(File file, List<Toy> list) {
        try {
            mapper.writeValue(file, list);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

