package com.example.petboxee.server;

import com.example.petboxee.servise.AnimalImp;
import com.example.petboxee.errors.Errors;
import com.example.petboxee.models.Animal;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "animal", value = "/animal")
public class AnimalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int i=0;
        AnimalImp animalImp = new AnimalImp();
        ArrayList<Animal> animals = animalImp.initializeAnimals();
        JSONObject jsonObject = new JSONObject();
        try {
            for (Animal animal:animals) {
                jsonObject.put("id"+i,animal.getId());
                jsonObject.put("type" + i, animal.getType());
                jsonObject.put("photo"+i, animal.getPhoto());
                jsonObject.put("name"+i, animal.getName());
                jsonObject.put("color"+i,animal.getColor());
                jsonObject.put("age"+i,animal.getAge());
                jsonObject.put("ageMeasure"+i,animal.getAgeMeasure());
                jsonObject.put("gender"+i,animal.getGender());

                System.out.println("gender"+i +"  =  " +animal.getGender());

                jsonObject.put("characters"+i,animal.getCharacters());
                jsonObject.put("address"+i, animal.getAddress());
                jsonObject.put("shelterName"+i, animal.getShelterName());
                jsonObject.put("contactName"+i,animal.getContactName());
                jsonObject.put("contactEmail"+i,animal.getContactEmail());
                i++;
            }
            jsonObject.put("size",i);
            PrintWriter pw = response.getWriter();
            pw.write(jsonObject.toString());
            pw.print(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name"),
                type = request.getParameter("type"),
                color = request.getParameter("color"),
                characters = request.getParameter("characters"),
                gender = request.getParameter("gender"),
                shelter_id = request.getParameter("shelter_id"),
                ageMeasure = request.getParameter("ageMeasure");

        int age = Integer.parseInt(request.getParameter("age")), photo = Integer.parseInt(request.getParameter("photo"));
        Animal animal = new Animal();
        animal.setName(name);
        animal.setType(type);
        animal.setCharacters(characters);
        animal.setColor(color);
        animal.setGender(gender);
        animal.setAge(age);
        animal.setAgeMeasure(ageMeasure);
        animal.setPhoto(photo);
        animal.setContactEmail(shelter_id);


        AnimalImp animalImp = new AnimalImp();

        int i = animalImp.insertAnimal(animal);
         if(i>0){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("message", Errors.SUCCESSFUL_CREATION.getCode());
                PrintWriter pw = response.getWriter();
                pw.write(jsonObject.toString());
                pw.print(jsonObject.toString());
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
    }
}
