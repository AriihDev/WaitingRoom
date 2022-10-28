package sa.aristide.waitingroom.web;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sa.aristide.waitingroom.entities.Visitor;
import sa.aristide.waitingroom.services.VisitorService;

// MVC
// Model  : les données affichés par la vue 

@Controller
@RequestMapping(path="/visitors")
public class VisitorController {
	
	@Autowired
	VisitorService visitorService;

	/*
	 * @GetMapping("/index") public String visitors(Model model) { List<Visitor>
	 * visitors = visitorService.getAll(); int nombreVisitor = visitors.size();
	 * model.addAttribute("visitors", visitors); model.addAttribute("nombreVisitor",
	 * nombreVisitor); return "visitors"; }
	 */
	
	@GetMapping("/")
	public String getAll(Model model , @RequestParam(name= "page", defaultValue = "0") int page,
			@RequestParam(name= "size", defaultValue = "4") int size,
			@RequestParam(name= "fname", defaultValue = "") String fname,
			@RequestParam(name= "lname", defaultValue = "") String lname
			) {
		
		Page<Visitor> visitorsPage =visitorService.getPageByFnameAndLname(fname,lname,page,size);
		model.addAttribute("visitors", visitorsPage);
		model.addAttribute("fname", fname);
		model.addAttribute("lname", lname);
		model.addAttribute("pages", new int [visitorsPage.getTotalPages()]);
		return "visitors";
	}
	
	@GetMapping("/delete")
    public String delete(long id) {
        visitorService.delete(id);
        return "redirect:/visitors/";
    }
	
	@GetMapping("/new/")
    public String newVisitor(Model model) {
        model.addAttribute("visitor", new Visitor());
        return "newvisitor";
    }
    
	@PostMapping(path ="/save" )
    public String saveVisitor(@Valid Visitor visitor, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            return "newvisitor";
        }
        
        Visitor savedVisitor = visitorService.save(visitor);
        model.addAttribute("visitor", savedVisitor);
        return "confirm-save-visitor";
    }
	
	@GetMapping("/edit/")
    public String editVisitor(long id,  Model model) {
        Visitor visitor = visitorService.getById(id);
       model.addAttribute("visitor", visitor);
        return "newvisitor";
    }
}
