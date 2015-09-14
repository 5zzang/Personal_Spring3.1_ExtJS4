/**
 * @author 5zzang
 * Nov 15, 2012
 */
package com.sample.app.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * @author 5zzang
 * Nov 15, 2012
 */
public abstract class BaseController {
	@Autowired public RequestSession requestSession;
	@Autowired public MessageResource messageResource;
	
	public Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").setPrettyPrinting().create();
	public HttpHeaders responseHeaders = new HttpHeaders();
}