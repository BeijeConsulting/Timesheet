package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.sql.Date;
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
import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.service.TimesheetService;



@RestController
@RequestMapping("api")
public class TimesheetApiController {
	
		@Autowired
		private TimesheetService timesheetService;

		@RequestMapping(value = "/timesheets", method = RequestMethod.GET)
		public @ResponseBody List<Timesheet> getTimesheets(Model model, HttpServletResponse response) throws IOException {
			return timesheetService.caricaTutto();
		}
		@RequestMapping(value = "/timesheets/svuotaserver", method = RequestMethod.GET) // METODO USATO SOLO PER TESTARE
		public @ResponseBody boolean svuotaserver(Model model, HttpServletResponse response) throws IOException {
			TimesheetService.svuotaserver();
			return true;
		}
	
		@RequestMapping(value = "/timesheets", method = RequestMethod.POST,	consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<Timesheet> insertTimesheets(@RequestBody List<Timesheet> timesheets, Model model,	HttpServletResponse response) throws Exception {
			System.out.println("insert timesheets: " + timesheets);
	
			return timesheetService.insert(timesheets);
		}
		@RequestMapping(value = "/timesheets/delete/{id}", method = RequestMethod.DELETE)
		public @ResponseBody boolean delete(@PathVariable long id) throws IOException {
			 timesheetService.deleteOne(id);
			 return true;
		}
	
		@RequestMapping(value = "/timesheets/user/{id}", method = RequestMethod.GET)

		public @ResponseBody List<Timesheet> retrieveTimeSheetTables(@PathVariable int id,@RequestParam(value = "datefrom", required = true)Date datefrom,@RequestParam(value = "dateto", required = false)Date dateto) {
//			Map<String, Object> result = new HashMap<String, Object>();
			dateto = dateto == null? new Date(System.currentTimeMillis()):dateto;
			List<Timesheet> timetablelist = TimesheetService.retrieveTimatablesInDateRangeByUserId(id,datefrom,dateto);

			return timetablelist;
		}
		

		@RequestMapping(value = "/timesheets/modifica/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody  boolean modifyTimesheet (@PathVariable Long id, @RequestBody Timesheet timesheet) {
			
			TimesheetService.updateTimesheet(id,timesheet);
			return true;
		}

		@RequestMapping(value = "/timesheets/validate/{id}", method = RequestMethod.POST)

		public @ResponseBody boolean validazione(@PathVariable int id,@RequestParam(value = "datefrom", required = true)Date datefrom,@RequestParam(value = "dateto", required = false)Date dateto) {
			return timesheetService.validator(id, datefrom, dateto);
		}
		
		@RequestMapping(value = "/timesheets/submit/{id}", method = RequestMethod.POST)
		public @ResponseBody boolean submit(@PathVariable int id,@RequestParam(value = "datefrom", required = true)Date datefrom,@RequestParam(value = "dateto", required = false)Date dateto) throws Exception {
			if(dateto !=null) {
				int i=dateto.compareTo(datefrom);
				System.out.println(i);
				if(i>0) {
					return TimesheetService.submitUtente(id, datefrom, dateto);
				}
				else
					return false;
			}
			return TimesheetService.submitUtente(id, datefrom, dateto);
		}


		

}
