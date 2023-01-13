package com.example.petboxee;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteHelpAdServlet", value = "/DeleteHelpAdServlet")
public class DeleteHelpAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        DeleteHelpAdImp deleteHelpAdImp = new DeleteHelpAdImp();

        int i = deleteHelpAdImp.deleteHelpAd(id);
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
