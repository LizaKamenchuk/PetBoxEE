package com.example.petboxee.server;

import com.example.petboxee.servise.EnterImp;
import com.example.petboxee.errors.Errors;
import com.example.petboxee.models.UserBeam;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "enter", value = "/enter")
public class EnterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        EnterImp enterImp = new EnterImp();
        UserBeam userBeam= enterImp.autorizeUser(email,password);
        JSONObject jsonObject= new JSONObject();
        if(userBeam!=null){
            try{
                jsonObject.put("email",email);
                jsonObject.put("password",password);
                jsonObject.put("name",userBeam.getName());
                jsonObject.put("lastname",userBeam.getLastname());
                jsonObject.put("address",userBeam.getAddress());
                jsonObject.put("shelterName",userBeam.getShelter());
                jsonObject.put("message", Errors.SUCCESSFUL_AUTHORIZATION.getCode());

                PrintWriter pw = response.getWriter();
                pw.write(jsonObject.toString());
                pw.print(jsonObject.toString());
                System.out.println("Authorization successful" + jsonObject.toString());

            }catch (Exception e) {
                e.printStackTrace();
             }
        } else {
                try{
                    jsonObject.put("message", Errors.NON_EXISTENT_USER.getCode());
                    PrintWriter pw = response.getWriter();
                    pw.write(jsonObject.toString());
                    pw.print(jsonObject.toString());
                }catch (Exception e){
                    e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
