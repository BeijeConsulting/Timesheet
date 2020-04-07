package it.beije.mgmt.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.beije.mgmt.entity.Timesheet;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
	
	List<Timesheet> findByIdUtenteAndStartDate(int idUtente, Date startDate );
	
	List<Timesheet> findByIdUtenteAndStartDateGreaterThanEqual(int idUtente, Date startDate );
	
	List<Timesheet> findByIdUtente(int idUtente);
	
	List<Timesheet> findByIdUtenteAndDateFromBetweenDateTo(int idUtente, Date dateFrom, Date dateTo);
	
	List<Timesheet> findByDateFromBetweenDateTo(Date dateFrom, Date dateTo);
	
	Timesheet deleteByIdUtenteAndDateFromAndType(int idUtente, Date dateFrom, String Type);
	
	List<Timesheet> deleteByIdUtenteAndDateFrom(int idUtente, Date dateFrom);
	
	

}
