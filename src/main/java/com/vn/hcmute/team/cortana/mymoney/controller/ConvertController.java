package com.vn.hcmute.team.cortana.mymoney.controller;

import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vn.hcmute.team.cortana.mymoney.Constraint;
import com.vn.hcmute.team.cortana.mymoney.bean.Converter;
import com.vn.hcmute.team.cortana.mymoney.bean.JsonResponse;

@Component
@Path("converter")
public class ConvertController {
	public ConvertController() {

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String convert(@QueryParam("a") String amount, @QueryParam("from") String from,
			@QueryParam("to") String to) {
		String url = String.format(Constraint.URL_CONVERTER, amount, from, to);
		JsonResponse<Converter> response = new JsonResponse<Converter>(Converter.class);
		try {
			Document doc = Jsoup.connect(url).get();

			response.setStatus("success");
			response.setMessage("ok");

			Converter converter = new Converter();
			converter.setAmount(amount);
			converter.setFrom(from);
			converter.setTo(to);
			converter.setValue(doc.select("[class=bld]").text());

			response.setData(converter);

		} catch (Exception e) {
			response.setStatus("failure");
			response.setMessage("Error");
			response.setData(null);

		}
		return response.toString();
	}

	@GET
	@Path("update")
	@Produces(MediaType.APPLICATION_JSON)
	public String get() {
		JsonResponse<JsonObject> response = new JsonResponse<JsonObject>(JsonObject.class);
		try {
			URL url = new URL("http://www.apilayer.net/api/live?access_key=42aa111fd1dc6217e3cb3f5bb68c2676&format=1");
			String str = IOUtils.toString(url, "UTF-8");
			Gson gson = new Gson();
			JsonElement jsonElement = gson.fromJson(str, JsonElement.class);

			response.setStatus("success");
			response.setMessage("ok");
			response.setData(jsonElement.getAsJsonObject().get("quotes").getAsJsonObject());
		} catch (Exception e) {
			response.setStatus("failure");
			response.setMessage(e.getMessage());
			response.setData(null);
		}
		return response.toString();

	}

}
