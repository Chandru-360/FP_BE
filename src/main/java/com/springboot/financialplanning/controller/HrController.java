package com.springboot.financialplanning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enums.Role;
import com.springboot.financialplanning.dto.HrDto;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Hr;
import com.springboot.financialplanning.model.User;
import com.springboot.financialplanning.service.HrService;
import com.springboot.financialplanning.service.UserService;


@RestController
@RequestMapping("/hr")
public class HrController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private HrService hrService;
	
	
	/*Insert HR */
	@PostMapping("/add")
	public Hr insertHr(@RequestBody Hr hr) {
		/*save user info in db*/
		User user=hr.getUser();
		String passwordPlain=user.getPassword();
		String encodedPassword=passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		
		user.setRole(Role.HR);
		/*save user in db*/
		user=userService.insert(user);
		/*attach user to hr*/
		hr.setUser(user);
		
		return hrService.insert(hr);
	}
	
	/* Get Hr By Id */
	@GetMapping("/getone/{hid}")
	public ResponseEntity<?> getOne(@PathVariable("hid")int hid) {
		
		try {
			Hr hr = hrService.getByHrId(hid);
			return ResponseEntity.ok().body(hr);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}  
	
	/* Delete Hr By Id */
	@DeleteMapping("/delete/{hid}")
	public ResponseEntity<?> deleteHr(@PathVariable("hid") int hid) {
		
		try {
			//validate id
			Hr hr = hrService.getByHrId(hid);
			//delete
			hrService.deleteHr(hr);
			return ResponseEntity.ok().body("HR deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/* Update HR By Id */
	@PutMapping("/update/{hid}")  //:update: which record to update?   give me new value for update
	public ResponseEntity<?> updateHr(@PathVariable("hid") int hid,
							@RequestBody HrDto newHr) {
		try {
			//validate id
			Hr oldHr = hrService.getByHrId(hid);
			if(newHr.getName() != null)
				oldHr.setName(newHr.getName());
			if(newHr.getEmail() != null) 
				oldHr.setEmail(newHr.getEmail()); 
			if(newHr.getPhoneNumber() != null) 
				oldHr.setPhoneNumber(newHr.getPhoneNumber()); 
			 
			oldHr = hrService.insert(oldHr); 
			return ResponseEntity.ok().body(oldHr);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
}
