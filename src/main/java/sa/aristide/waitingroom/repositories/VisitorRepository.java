package sa.aristide.waitingroom.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import sa.aristide.waitingroom.entities.Visitor;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
	public Page<Visitor> findByFnameContainsAndLnameContains(String fname,String lname, Pageable pageable);

}
