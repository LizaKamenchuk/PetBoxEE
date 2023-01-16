package com.example.petboxee.server;

import com.example.petboxee.errors.Errors;
import com.example.petboxee.servise.RegisterImp;
import com.example.petboxee.models.UserBeam;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "register", value = "/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email"),
                password = request.getParameter("password"),
                lastname = request.getParameter("lastname"),
                name = request.getParameter("name"),
                address = request.getParameter("address"),
                shelterName = request.getParameter("shelterName");

        //salt = SHA512.getSalt();


        UserBeam newUser = new UserBeam();

        newUser.setEmail(email);
        newUser.setPassword(password);
        //newUser.setPassword(SHA512.getHash(password, salt));
        //newUser.setSalt(salt);
        newUser.setLastname(lastname);
        newUser.setName(name);
        newUser.setAddress(address);
        newUser.setShelter(shelterName);

        RegisterImp registerImp = new RegisterImp();

        int i = registerImp.registerUser(newUser);
        if(i== Errors.EXISTENT_USER.getCode()){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("message",Errors.EXISTENT_USER.getCode());
                PrintWriter pw = response.getWriter();
                pw.write(jsonObject.toString());
                pw.print(jsonObject.toString());
            }catch(JSONException e){
                e.printStackTrace();
            }
        } else if(i>0){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("message", Errors.SUCCESSFUL_REGISTRATION.getCode());
                PrintWriter pw = response.getWriter();
                pw.write(jsonObject.toString());
                pw.print(jsonObject.toString());
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
    }
}
