package com.scopeProject.scopeProject.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.scopeProject.scopeProject.model.TableModel;
import com.scopeProject.scopeProject.model.registerModel;
import com.scopeProject.scopeProject.repository.registerRepository;
import com.scopeProject.scopeProject.repository.tableRepository;
import com.scopeProject.scopeProject.service.contactService;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller

public class MyController {

	@Autowired
	private tableRepository tablerepository;
	private registerRepository registerrepository;
//	  private CookController cook;

	private final CookController cookController;

	public MyController(CookController cookController) {
		this.cookController = cookController;
	}

	@GetMapping("/home")
	public String showIndexPage() {
		return "home";
	}

	@GetMapping("/table/{search}")
	public String showTablePage(HttpServletRequest request, Model model) {

		if (cookController.getUserIdFromCookie(request)) {
			List<TableModel> tableModels = tablerepository.findAll();

			model.addAttribute("table", tableModels);

			return "table";
		}
		// If userId cookie is not found or the user is not authenticated, redirect to
		// login
		return "redirect:/login";
	}

	@GetMapping("/table")
	public String showTablePage_search(HttpServletRequest request,
			@RequestParam(name = "search", required = false) String searchQuery, Model model) {

		if (cookController.getUserIdFromCookie(request)) {
			List<TableModel> tableModels = tablerepository.findAll();

			if (searchQuery != null && !searchQuery.isEmpty()) {
				// Perform filtering based on the search query
				tableModels = filterTables(tableModels, searchQuery);
			}

			model.addAttribute("table", tableModels);

			return "table";
		}
		// If userId cookie is not found or the user is not authenticated, redirect to
		// login
		return "redirect:/login";
	}

	
	
	
	
	private List<TableModel> filterTables(List<TableModel> tableModels, String searchQuery) {
		List<TableModel> filteredTables = new ArrayList<>();
		String searchQueryLower = searchQuery.toLowerCase();

		// Implement your filtering logic here based on the search query
		// For example, if your TableModel has a field 'name', you can check if the
		// searchQuery matches the name
		for (TableModel table : tableModels) {

			String courseNameLower = table.getCourse_name().toLowerCase();

			if (courseNameLower.contains(searchQueryLower)) {
				filteredTables.add(table);
			}
		}
		return filteredTables;
	}

	
	
	
	
	
	  @PostMapping("/add_course/{id}") public ResponseEntity<String>
	  addCourse(@PathVariable("id") long id, HttpServletRequest request,TableModel
	  tablemodel) { // Check if the user is authenticated
	  
	  
	  if (id > 0 && cookController.getUserIdFromCookie(request)) {
	  
	  
		 
	  return ResponseEntity.ok().body("Course added successfully");
	  } 
	  else { 
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR). body("Failed to add course"); 
		  }
	  
	  }
	

//    @GetMapping("/add_mycourse/{id}")
//    public String addmyCourse(@PathVariable("id") long id , HttpServletRequest request ) {
//        // Your processing logic here
//
//        // Simulate success for demonstration purposes
//        if (id > 0 && cookController.getUserIdFromCookie(request)) {
//        	  
//        	           
//        	    
//            return "redirect:/table";
//        } else {
//            // If something goes wrong, return an error response
//            return "table";
//        }
//    }
//     

	@GetMapping("/course")
	public String showCoursePage(HttpServletRequest request) {
		if (cookController.getUserIdFromCookie(request)) {
			return "course";
		}
		return "redirect:/login";
	}

	@GetMapping("/contact")
	public String showContactPage(HttpServletRequest request) {
		if (cookController.getUserIdFromCookie(request)) {
			return "contact";
		}
		return "redirect:/login";
	}

	@GetMapping("/knowscopeindia")
	public String showKnowScopeIndiaPage(HttpServletRequest request) {
		if (cookController.getUserIdFromCookie(request)) {
			return "knowscopeindia";
		}
		return "redirect:/login";
	}

	@GetMapping("/dashbord")
	public String showDashbord(HttpServletRequest request, Model model) {
		// Check if the user is authenticated (has userId cookie)
		if (cookController.getUserIdFromCookie(request)) {
			// Fetch the username from the cookies
			String userName = cookController.getUserNameFromCookie(request);

			if (userName != null) {
				// Use the username as needed
				model.addAttribute("userName", userName);

				return "dashbord"; // Return the dashboard page
			} else {
				model.addAttribute("error", "Username not found");
				return "errorPage";
			}
		} else {
			return "redirect:/login"; // Redirect to the login page if not authenticated
		}
	}

	@Autowired
	private JavaMailSender mailSender;

	@PostMapping("/contact")
	public String sendMain(@RequestParam String name, String course, String gmail, String phone)
			throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo("rajeshrajakumar1998@gmail.com");
		helper.setSubject("mail verification");
		boolean html = true;

		helper.setText("name :</b>" + " " + name + "<br>" + "<b>course :</b>" + " " + course + "<br>" + "<b>gmail :</b>"
				+ " " + gmail + "<br>" + "<b>phone :</b>" + " " + phone, html);
		mailSender.send(message);

		return "regsuccess";

	}

}






