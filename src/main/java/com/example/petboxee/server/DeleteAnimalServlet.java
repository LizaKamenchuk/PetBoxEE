package com.example.petboxee.server;

import com.example.petboxee.servise.DeleteAnimalImp;
import com.example.petboxee.errors.Errors;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteAnimalServlet", value = "/DeleteAnimalServlet")
public class DeleteAnimalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      int id = Integer.parseInt(request.getParameter("id"));

      DeleteAnimalImp deleteAnimalImp = new DeleteAnimalImp();

        int i = deleteAnimalImp.deleteAnimal(id);
        if(i>0){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("message", Errors.SUCCESSFUL_DELETE.getCode());
                PrintWriter pw = response.getWriter();
                pw.write(jsonObject.toString());
                pw.print(jsonObject.toString());
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
    }
}
