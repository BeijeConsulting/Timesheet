package it.beije.mgmt.restcontroller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import it.beije.mgmt.service.TimetableService;


@RestController
@RequestMapping("api")
public class TimesheetApiController {
	
		@Autowired
		private TimetableService timetableService;

		@RequestMapping(value = "/timesheets", method = RequestMethod.GET)
		public @ResponseBody List<Timesheet> getTimesheets(Model model, HttpServletResponse response) throws IOException {
			return timetableService.caricaTutto();
		}
	
		@RequestMapping(value = "/timesheets", method = RequestMethod.POST,	consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<Timesheet> insertTimesheets(@RequestBody List<Timesheet> timesheets, Model model,	HttpServletResponse response) throws IOException {
			System.out.println("insert timesheets: " + timesheets);
	
			return timetableService.insert(timesheets);
		}
		@RequestMapping(value = "/timesheets/delete/{id}", method = RequestMethod.DELETE)
		public @ResponseBody boolean delete(@PathVariable int id,@RequestParam(value = "date", required = true)Date date) throws IOException {
			boolean b= timetableService.deleteRestController(id, date);
			return b;
		}
	
		@RequestMapping(value = "/timesheets/user/{id}", method = RequestMethod.GET)

		public @ResponseBody List<Timesheet> retrieveTimeSheetTables(@PathVariable int id,@RequestParam(value = "datefrom", required = true)Date datefrom,@RequestParam(value = "dateto", required = false)Date dateto) {
//			Map<String, Object> result = new HashMap<String, Object>();
			dateto = dateto == null? new Date(System.currentTimeMillis()):dateto;
			List<Timesheet> timetablelist = timetableService.retrieveTimatablesInDateRangeByUserId(id,datefrom,dateto);

			return timetablelist;
		}
		
		@RequestMapping(value = "/timesheets/modifica", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody  boolean modifyTimesheet (@RequestBody Timesheet newTable) {
			
			if (TimetableService.findRecordsFromId(newTable.getIdUser())==null)  {
				System.out.println("Utente non trovato");
				return false;
			}
			
				timetableService.updateRecord(newTable.getIdUser(),newTable.getDate(), newTable);	
			return true;
		}
		
		@RequestMapping(value = "/timesheets/validate/{id}", method = RequestMethod.GET)

		public @ResponseBody boolean validazione(@PathVariable int id,@RequestParam(value = "datefrom", required = true)Date datefrom,@RequestParam(value = "dateto", required = true)Date dateto) {
			return timetableService.Validator(id, datefrom, dateto);
		}


}
