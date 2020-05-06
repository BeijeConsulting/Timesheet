package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.beije.mgmt.dto.TimesheetSearchRequest;
import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.service.TimesheetService;


@RestController
@RequestMapping("api")
public class TimesheetApiController {
	
		@Autowired
		private TimesheetService timesheetService;

		@RequestMapping(value = "/timesheets", method = RequestMethod.GET)
		public @ResponseBody List<Timesheet> getTimesheets(Model model, HttpServletResponse response) throws IOException {
			return timesheetService.findAll();
		}
		@RequestMapping(value = "/timesheets/svuotaserver", method = RequestMethod.GET) // METODO USATO SOLO PER TESTARE
		public @ResponseBody boolean svuotaserver(Model model, HttpServletResponse response) {
			timesheetService.svuotaserver();
			return true;
		}
	
		@RequestMapping(value = "/timesheets", method = RequestMethod.POST,	consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<Timesheet> insertTimesheets(@RequestBody List<Timesheet> timesheets, Model model,	HttpServletResponse response) {
			System.out.println("insert timesheets: " + timesheets);
	
			return timesheetService.insert(timesheets);
		}
		
		@RequestMapping(value = { "/timesheet/default/user/{idUser}" }, method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
		public @ResponseBody Timesheet insertDefaultTimesheet(@PathVariable long idUser,@RequestBody Timesheet timsheet, Model model,HttpServletResponse response) {
	
			return timesheetService.insertDefault(idUser,timsheet);		
		}
		
		@RequestMapping(value = { "/timesheet/default/user/{idUser}" }, method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
		public @ResponseBody Timesheet getDefaultTimesheet(@PathVariable long idUser, Model model,HttpServletResponse response) {
			
			return timesheetService.getDefaultTimesheet(idUser);		
		}
			
		@RequestMapping(value = "/timesheets/delete/{id}", method = RequestMethod.DELETE)
		public @ResponseBody boolean delete(@PathVariable long id)  {
			
			timesheetService.deleteOne(id);
			 return true;
		}
	
		@RequestMapping(value = "/timesheets/user/{id}", method = RequestMethod.GET)
		public @ResponseBody List<Timesheet> retrieveTimeSheetTables(@PathVariable Long id,@RequestParam(value = "datefrom", required = true)Date datefrom,@RequestParam(value = "dateto", required = false)Date dateto) {
//			Map<String, Object> result = new HashMap<String, Object>();
			
			dateto = dateto == null? new Date(System.currentTimeMillis()) : dateto;
			List<Timesheet> timetablelist = timesheetService.retrieveTimatablesInDateRangeByUserId(id,datefrom,dateto);

			return timetablelist;
		}
		

		@RequestMapping(value = "/timesheets/modifica/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody  boolean modifyTimesheet (@PathVariable Long id, @RequestBody Timesheet timesheet) {
			
			timesheetService.updateTimesheet(id,timesheet);
			return true;
		}

		@RequestMapping(value = "/timesheets/validate/{id}", method = RequestMethod.POST)

		public @ResponseBody boolean validazione(@PathVariable Long id,@RequestParam(value = "datefrom", required = true)Date datefrom,@RequestParam(value = "dateto", required = false)Date dateto) {
			return timesheetService.validator(id, datefrom, dateto);
		}
		
		@RequestMapping(value = "/timesheets/submit/{id}", method = RequestMethod.POST)
		public @ResponseBody boolean submit(@PathVariable Long id,@RequestParam(value = "datefrom", required = true)Date datefrom,@RequestParam(value = "dateto", required = false)Date dateto) throws Exception {
			if(dateto !=null) {
				int i=dateto.compareTo(datefrom);
				System.out.println(i);
				if(i>0) {
					return timesheetService.submitUser(id, datefrom, dateto);
				}
				else
					return false;
			}
			return timesheetService.submitUser(id, datefrom, dateto);
		}

		@RequestMapping(value = "/timesheet/search", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<Timesheet> searchUser(@RequestBody TimesheetSearchRequest req) {

			return new TimesheetService().searchTimesheets(req);

		}
		

		@RequestMapping(value = "/timesheet/current", method = RequestMethod.GET)
		public @ResponseBody List<Timesheet> getCurrentTimesheet(Model model, HttpServletResponse response) {
			
			try {
				return timesheetService.retrieveTimatablesInDateRangeByUserId((long) 1 , 
						Date.valueOf(LocalDate.now().minus(Period.of(0, 1, LocalDate.now().getDayOfMonth()-1))),
						Date.valueOf(LocalDate.now()));
			}catch(MasterException e) {
				throw e;
			}
		}
}
