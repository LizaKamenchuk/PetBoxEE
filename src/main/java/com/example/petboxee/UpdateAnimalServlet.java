package com.example.petboxee;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "UpdateAnimalServlet", value = "/UpdateAnimalServlet")
public class UpdateAnimalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        int age = Integer.parseInt(request.getParameter("age")), photo = Integer.parseInt(request.getParameter("photo")), id = Integer.parseInt(request.getParameter("id"));
        Animal animal = new Animal();
        animal.setId(id);
        animal.setName(name);
        animal.setType(type);
        animal.setCharacters(characters);
        animal.setColor(color);
        animal.setGender(gender);
        animal.setAge(age);
        animal.setAgeMeasure(ageMeasure);
        animal.setPhoto(photo);
        animal.setContactEmail(shelter_id);

        UpdateAnimalImp animalImp = new UpdateAnimalImp();

        int i = animalImp.updateAnimal(animal);
        if(i>0){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("message", Errors.SUCCESSFUL_EDIT.getCode());
                PrintWriter pw = response.getWriter();
                pw.write(jsonObject.toString());
                pw.print(jsonObject.toString());
            }catch(JSONException e){
                e.printStackTrace();
            }
        }

    }
}
