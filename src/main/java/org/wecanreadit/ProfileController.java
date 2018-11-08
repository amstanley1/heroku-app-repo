package org.wecanreadit;


import java.util.concurrent.atomic.AtomicLong;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

	@Resource
	ReadingGroupRepository groupRepo;

	@Resource
	ReaderRepository readerRepo;

    @RequestMapping("/profileInfo")
    public Iterable<Reader> collection() {
    	return readerRepo.findAll();
    }
    
    @RequestMapping("/updateBio")
    public Reader updateBio(String name, String bio) {
    	Reader reader = readerRepo.findByUsername(name);
    	reader.setBio(bio);
    	readerRepo.save(reader);
    	return reader;
    }
    //Add custom Exceptions
    @RequestMapping("/verifyLogin")
    public Reader verifyLogin(
    	@RequestBody LoginRequest login,
    	HttpServletResponse response) throws Exception {
    	Reader reader = readerRepo.findByUsername(login.name);
    	
    	String readerPassword = reader.getPassword();
    	if (!reader.getPassword().equals(login.password)) {
    		throw new Exception();
    	}
    	//Makes new cookie, takes in string,string name, id
    	Cookie readerIdCookie = new Cookie("readerId", reader.getId().toString());
    	response.addCookie(readerIdCookie);
    	return reader;
    	
    }
    public static class LoginRequest {
    	public String name;
    	public String password;
    }	
    
    @RequestMapping("/readerProfile")
    public String readerProfile(String id) {
    	if(!id.equals(id)) {
    		return "redirect:/ReaderLogin";
    	}else {
    		
    		return "redirect:/readerHomePage";
    	}	
    }
}

