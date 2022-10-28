package sa.aristide.waitingroom.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import sa.aristide.waitingroom.entities.Visitor;
import sa.aristide.waitingroom.repositories.VisitorRepository;

@Service
@AllArgsConstructor
@Transactional
public class VisitorService {

	@Autowired
	VisitorRepository visitorRepository;

	public Visitor getById(long id) {
		return visitorRepository.findById(id).orElse(null);
	}

	public List<Visitor> getAll() {
		return visitorRepository.findAll();

	}

	public Visitor save(Visitor visitor) {
		return visitorRepository.save(visitor);
	}

	public void delete(Visitor visitor) {
		visitorRepository.deleteById(visitor.getId());
	}

	public void delete(long id) {
		visitorRepository.deleteById(id);
	}

	public Page<Visitor> getPage(int page, int size) {
		return visitorRepository.findAll(PageRequest.of(page, size));
	}

	  public Page<Visitor> getPageByFnameAndLname(String fname,String lname, int page, int size){
	        return visitorRepository.findByFnameContainsAndLnameContains(fname, lname, PageRequest.of(page, size));
	    }

	// transaction
	// begin
	// update set solde = solde - 100 where wompte = "290"

	// update set solde = solde - 100 where wompte = "400"
	// commit
	// rollback

}
