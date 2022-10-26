package tw.com.ispan.eeit48.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import tw.com.ispan.eeit48.domain.EmpBean;
import tw.com.ispan.eeit48.domain.ReCaptchaResponse;
import tw.com.ispan.eeit48.service.EmpService;

@Controller
public class LoginController {

    @Autowired
    private EmpService empService;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(path = "/login.controller")
    public String handlerMethod(
            Model model, Integer empID, String passwd, HttpSession session,
            @RequestParam(name = "g-recaptcha-response") String captchaResponse
            ) {
//        String url = " https://www.google.com/recaptcha/api/siteverify";
//        String params = "?secret=6LcQ42giAAAAAJ_Jg_zVtcdfUx_dr0iLYyFh8Rco&response=" + captchaResponse;
//
//        ReCaptchaResponse reCaptchaResponse = restTemplate
//                .exchange(url + params, HttpMethod.POST, null, ReCaptchaResponse.class).getBody();

//        if (reCaptchaResponse.isSuccess()) {
//            
//        } else {
//           
//        }
       

//         接收資料
//         驗證資料
		if (empID == null || empID.SIZE == 0) {
			System.out.println("EMPID NO");
			return "/";
		}
//         呼叫model
		EmpBean bean = empService.login(empID, passwd);
		
		//根據model執行結果，導向view
		if(bean != null && vetifyReCAPTCHA(captchaResponse)) {
			System.out.println("google:"+captchaResponse);
			System.out.println("OK");
			session.setAttribute("user", bean);
			return "redirect:/home/home.html";
		}else {
			return "redirect:/index.html";
		}
    }
    
    private boolean vetifyReCAPTCHA(String captchaResponse) {
    	String url = "https://www.google.com/recaptcha/api/siteverify";
        String secret = "6LcQ42giAAAAAJ_Jg_zVtcdfUx_dr0iLYyFh8Rco";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("secret",secret );
        map.add("response",captchaResponse );
        
        HttpEntity<MultiValueMap<String, String>> request= new HttpEntity<>(map,headers);
        ReCaptchaResponse response =  restTemplate.postForObject(url, request, ReCaptchaResponse.class);
        System.out.println("sucess:"+  response.isSuccess());
        System.out.println("Hostname:"+  response.getHostname());
        System.out.println("Challenge Timestamp:"+  response.getChallenge_ts());
        if(response.getErrorCodes() != null ) {
        	for(String error : response.getErrorCodes()) {
        		System.out.println("\t"+error);
        	}
        }
        return response.isSuccess();
    
    }
}
