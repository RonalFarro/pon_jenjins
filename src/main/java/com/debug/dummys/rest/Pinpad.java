package com.debug.dummys.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.debug.dummys.util.Utilitario;

@RestController
public class Pinpad {

	@Autowired
	private Utilitario jsonUtil;
	
	@RequestMapping(value="/getAT", method=RequestMethod.GET, produces=MediaType.TEXT_PLAIN_VALUE)
	public String prueba(){
		return jsonUtil.obtenerJson("agregador-terminal").toString();
	}
	
	@RequestMapping(value="/operador", method= RequestMethod.GET)
	public String operador(){
		return jsonUtil.obtenerJson("tarjeta-operador").toString();
	}
	
	@RequestMapping(value="/tarjeta", method=RequestMethod.GET)
	public String tarjeta(){
		return jsonUtil.obtenerJson("tarjeta-cliente").toString();
	}
	
	@RequestMapping(value="/pinpad", method=RequestMethod.GET)
	public String pinpad(){
		return "paso";
	}
	
	@RequestMapping(value="/nuevojson", method=RequestMethod.GET)
	public String nuevojson(){
		return jsonUtil.obtenerJson("ticket").toString();
	}
	
	@RequestMapping(value="/set", method=RequestMethod.GET)
	public void set(){}	
	
}
