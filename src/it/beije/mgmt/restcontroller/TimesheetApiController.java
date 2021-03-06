package it.beije.mgmt.restcontroller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import it.beije.mgmt.dto.TimesheetDto;
import it.beije.mgmt.dto.TimesheetSearchRequest;
import it.beije.mgmt.entity.Timesheet;
import it.beije.mgmt.exception.MasterException;
import it.beije.mgmt.service.TimesheetService;


@RestController
@RequestMapping("api")
public class TimesheetApiController {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
		@Autowired
		private TimesheetService timesheetService;

		@RequestMapping(value = "/timesheets", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody List<Timesheet> searchUser(@RequestBody TimesheetSearchRequest req) {
			log.debug("GET /timesheets (search)");
			
			return timesheetService.searchTimesheets(req);

		}
//		@RequestMapping(value = "/timesheets", method = RequestMethod.GET)
//		public @ResponseBody List<Timesheet> getTimesheets(Model model, HttpServletResponse response) {
//			log.info("GET /timesheets");
//			System.out.println("GET /timesheets");
//			return timesheetService.findAll();
//		}
//		@RequestMapping(value = "/timeshee/timesheetsts/svuotaserver", method = RequestMethod.GET) // METODO USATO SOLO PER TESTARE
//		public @ResponseBody boolean svuotaserver(Model model, HttpServletResponse response) {
//			log.debug("GET /timesheets/svuotaserver");
//			timesheetService.svuotaserver();
//			return true;
//		}
	
		@RequestMapping(value = { "/timesheet/default/user/{idUser}" }, method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
		public @ResponseBody Timesheet insertDefaultTimesheet(@PathVariable long idUser,@RequestBody Timesheet timsheet, Model model,HttpServletResponse response) {
			log.debug("POST /timesheet/default/user/" + idUser);
	
			return timesheetService.insertDefault(idUser,timsheet);		
		}
		
		@RequestMapping(value = { "/timesheet/default/user/{idUser}" }, method = RequestMethod.GET)
		public @ResponseBody Timesheet getDefaultTimesheet(@PathVariable long idUser, Model model,HttpServletResponse response) {
			log.debug("GET /timesheet/default/user/" + idUser);
			
			return timesheetService.getDefaultTimesheet(idUser);		
		}
			
		@RequestMapping(value = "/timesheets/user/{idUser}", method = RequestMethod.GET)
		public @ResponseBody List<Timesheet> getTimesheetsUser(@PathVariable long idUser, @RequestParam(value = "datefrom", required = false)Date datefrom, @RequestParam(value = "dateto", required = false)Date dateto) {
			log.info("GET /timesheets/user/" + idUser);
			System.out.println("GET /timesheets/user/" + idUser);
			
			if (datefrom == null) {
				throw new MasterException("datefrom parameter is mandatory");
			}
			
			if (dateto == null) {
				dateto = new Date(System.currentTimeMillis());
			}
			List<Timesheet> timetablelist = timesheetService.retrieveTimesheetsInDateRangeByUserId(idUser, datefrom, dateto);

			return timetablelist;
		}

//		//IM 20200518 : serve ?
//		@RequestMapping(value = "/timesheets", method = RequestMethod.POST,	consumes = MediaType.APPLICATION_JSON_VALUE)
//		public @ResponseBody List<Timesheet> insertTimesheets(@RequestBody List<Timesheet> timesheets, Model model,	HttpServletResponse response) {
//			log.debug("POST /timesheets");
//			System.out.println("insert timesheets: " + timesheets);
//	
//			return timesheetService.insert(timesheets);
//		}
		
		@RequestMapping(value = "/timesheet/user/{idUser}", method = RequestMethod.POST,	consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody Timesheet insertTimesheet(@PathVariable long idUser, @RequestBody Timesheet timesheet, Model model,	HttpServletResponse response) {
			log.debug("POST /timesheets/user/" + idUser);
			System.out.println("insert timesheets: " + timesheet);
	
			return timesheetService.insertSingleTimesheet(timesheet);
		}
		
		@RequestMapping(value = "/timesheet/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody  boolean modifyTimesheet (@PathVariable long id, @RequestBody Timesheet timesheet) {
			log.debug("PUT /timesheets/" + id);
			System.out.println("PUT /timesheets/" + id);
			
			timesheetService.updateTimesheet(id,timesheet);
			return true;
		}

		//@RequestMapping(value = "/timesheet/delete/{id}", method = RequestMethod.DELETE)
		@RequestMapping(value = "/timesheet/{id}", method = RequestMethod.DELETE)
		public @ResponseBody boolean delete(@PathVariable long id)  {
			log.debug("DELETE /timesheets/" + id);
			
			timesheetService.deleteOne(id);
			return true;
		}
		

		@RequestMapping(value = "/timesheets/submit/{id}", method = RequestMethod.POST)
		public @ResponseBody boolean submit(@PathVariable long id, @RequestParam(value = "datefrom", required = true)Date datefrom, @RequestParam(value = "dateto", required = false)Date dateto) throws Exception {
			log.debug("POST /timesheets/submit/{id}");
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

		@RequestMapping(value = "/timesheets/validate/{id}", method = RequestMethod.POST)
		public @ResponseBody boolean validazione(@PathVariable Long id,@RequestParam(value = "datefrom", required = true)Date datefrom,@RequestParam(value = "dateto", required = false)Date dateto) {
			log.debug("POST /timesheets/validate/{id}");
			return timesheetService.validator(id, datefrom, dateto);
		}
		
		@RequestMapping(value = "/timesheets/current", method = RequestMethod.GET)
		public @ResponseBody List<Timesheet> getCurrentTimesheet(Model model, HttpServletResponse response) {
			log.debug("GET /timesheet/current");
			
			try {
				/*return timesheetService.retrieveTimesheetsInDateRangeByUserId((long) 1 , 
						Date.valueOf(LocalDate.now().minus(Period.of(0, 1, LocalDate.now().getDayOfMonth()-1))),
						Date.valueOf(LocalDate.now()));*/
				return timesheetService.retrieveCurrentNotSubmitted(1L);
			}catch(MasterException e) {
				throw e;
			}
		}

		@RequestMapping(value = "/timesheets/active", method = RequestMethod.GET)
		public @ResponseBody List<TimesheetDto> getActiveTimesheet(Model model, HttpServletResponse response) {
			log.debug("GET /timesheet/active");
			
			try {
				return timesheetService.generateTimesheetDto();
			}catch(MasterException e) {
				throw e;
			}
		}
		
}
