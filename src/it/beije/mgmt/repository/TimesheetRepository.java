package it.beije.mgmt.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.beije.mgmt.entity.Timesheet;

@Repository
public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
	
	List<Timesheet> findByIdUserAndDate(Long idUser, Date startDate );
	
	List<Timesheet> findByIdUserAndDateGreaterThanEqual(Long idUser, Date date );
	
	List<Timesheet> findByIdUser(Long idUser);
	
	List<Timesheet> findByIdUserAndDateGreaterThanEqualAndDateLessThanEqual(Long idUser, Date dateFrom, Date dateTo);
	
	//List<Timesheet> findByDateBetweenDate(Date dateFrom, Date dateTo);
	List<Timesheet> findByDateGreaterThanEqualAndDateLessThanEqual(Date dateFrom, Date dateTo);
	
	Timesheet deleteByIdUserAndDateAndType(Long idUser, Date dateFrom, String Type);
	
	List<Timesheet> deleteByIdUserAndDate(Long idUser, Date dateFrom);

	Timesheet findByIdUserAndType(Long idUser, char c);

}
