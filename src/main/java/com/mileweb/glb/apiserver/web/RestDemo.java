package com.mileweb.glb.apiserver.web;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.mileweb.glb.apiserver.entity.Dog;
import com.mileweb.glb.apiserver.entity.SuccessInfo;
import com.mileweb.glb.apiserver.exception.BizRuntimeException;
import com.mileweb.glb.apiserver.exception.ExceptionWrapper;

//@RestController()
@Controller
@RequestMapping("/restdemo")
public class RestDemo extends BaseController {

	@RequestMapping(value="/putjson/{cname}", method={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	@ResponseBody
	public Dog test(@RequestBody(required=false) @Valid Dog dog, BindingResult result, @PathVariable("cname") String key) {
		
		if(result.hasErrors()) {
			System.out.println(result.getFieldErrors());
			System.out.println(result.getGlobalErrors());
			
			throw new RuntimeException("error");
		}
		
		System.out.println(dog + "\n" + key);
		
		Dog d = new Dog();
		d.setBirth(new Date());
		d.setName("doggy");
		
		return d;
	}
	
	@RequestMapping(value="/valid")
	@ResponseBody
	public String test(@Valid Dog dog, BindingResult result) {
		System.out.println(dog);
		if(result.hasErrors()) {
			System.out.println(result.getFieldErrors());
			System.out.println(result.getGlobalErrors());
			
			System.out.println(result.getAllErrors());
			
			ObjectError e = result.getAllErrors().get(0);
			
			System.out.println(e.getCode() + "-" + e.getDefaultMessage());
			
			System.out.println(Arrays.asList(e.getCodes()));
			System.out.println(Arrays.asList(e.getArguments()));
			
			throw new RuntimeException(e.getDefaultMessage());
		}
		
		return "valid pass";
	}
	
	@RequestMapping(value="/i18n")
	@ResponseBody
	public Dog test(HttpServletRequest request) {
		
		Dog d = new Dog();
		d.setBirth(new Date());
		
		RequestContext requestContext = new RequestContext(request);
		
		String name = requestContext.getMessage("msg");
		d.setName(name);
		
		System.out.println(RequestContextUtils.getLocale(request));
		System.out.println(request.getLocale());
		
		return d;
	}
	
	@RequestMapping(value="/suc")
	@ResponseBody
	public SuccessInfo suc(HttpServletRequest request) {
		
		Dog d = new Dog();
		d.setBirth(new Date());
		
		RequestContext requestContext = new RequestContext(request);
		
		String name = requestContext.getMessage("msg");
		d.setName(name);
		System.out.println(name);
		
		SuccessInfo info = this.getI18NSuccessInfo();
		
		info.setEventId(8);
		
		return info;
	}
	
	@RequestMapping(value="/error", method={RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
	@ResponseBody
	public String error(@RequestParam(required=false) String isWrap) {
		if("1".equals(isWrap)) {
			try {
				int i = 1/0;
			} catch (Exception e) {
				throw new BizRuntimeException(ExceptionWrapper.sucError_100, new Object[]{"suc"}); 
			}
		} else if("0".equals(isWrap)) {
			int i = 1/0;
		}
		
		return "show exception";
	}
	
	@RequestMapping("/definitejson")
	@ResponseBody
	public ModelAndView myjson() {
		Dog d = new Dog();
		d.setBirth(new  Date());
		d.setName("doggy");
		
		ModelAndView m = new ModelAndView();
		m.setViewName("definiteJson");
		m.addObject("json", d);
		return m;
	}
	
	@RequestMapping("/html")
	public String test2(Map<String, Object> m) {
		System.out.println(m);
		return "demo";
	}
	
	@GetMapping("/path/{name}")
	public String get(@PathVariable String name, Model model) {
		System.out.println(name);
		return "demo";
	}

}

