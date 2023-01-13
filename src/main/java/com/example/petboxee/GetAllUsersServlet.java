package com.example.petboxee;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "GetAllUsersServlet", value = "/GetAllUsersServlet")
public class GetAllUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int i=0;
       GetAllUsersImp getAllUsersImp= new GetAllUsersImp();
        ArrayList<UserBeam> users = getAllUsersImp.initializeUsers();
        JSONObject jsonObject = new JSONObject();
        try {
            for (UserBeam user:users) {
                jsonObject.put("name"+i, user.getName());
                jsonObject.put("address"+i, user.getAddress());
                jsonObject.put("shelter"+i, user.getShelter());
                jsonObject.put("name"+i,user.getName());
                jsonObject.put("email"+i,user.getEmail());
                jsonObject.put("lastname"+i,user.getLastname());
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

    }
}
