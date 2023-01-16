package com.example.petboxee.server;

import com.example.petboxee.errors.Errors;
import com.example.petboxee.servise.HelpAdImp;
import com.example.petboxee.models.HelpAd;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "HelpAdServlet", value = "/HelpAdServlet")
public class HelpAdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int i = 0;
        HelpAdImp helpAdImp = new HelpAdImp();
        ArrayList<HelpAd> helpAds = helpAdImp.initializeHelpAds();
        JSONObject jsonObject = new JSONObject();
        try {
            for (HelpAd helpAd : helpAds) {
                jsonObject.put("id" + i, helpAd.getId());
                jsonObject.put("type" + i, helpAd.getType());
                jsonObject.put("description" + i, helpAd.getDescription());
                jsonObject.put("address" + i, helpAd.getAddress());
                jsonObject.put("shelterName" + i, helpAd.getShelterName());
                jsonObject.put("contactName" + i, helpAd.getContactName());
                jsonObject.put("contactEmail" + i, helpAd.getContactEmail());

                i++;
            }
            jsonObject.put("size", i);
            PrintWriter pw = response.getWriter();
            pw.write(jsonObject.toString());
            pw.print(jsonObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type"),
                description = request.getParameter("description"),
                 shelter_id = request.getParameter("shelter_id");

        HelpAd helpAd = new HelpAd();
        helpAd.setType(type);
        helpAd.setDescription(description);
        helpAd.setContactEmail(shelter_id);

        HelpAdImp helpAdImp = new HelpAdImp();

        int i = helpAdImp.insertHelpAd(helpAd);
        System.out.println(i);
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
