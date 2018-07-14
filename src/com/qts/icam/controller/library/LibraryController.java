package com.qts.icam.controller.library;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.derby.tools.sysinfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.google.gson.Gson;
import com.qts.icam.model.common.Item;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.SessionObject;
import com.qts.icam.model.common.StatusOfItem;
import com.qts.icam.model.common.UpdateLog;
import com.qts.icam.model.common.UploadFile;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.erp.JobType;
import com.qts.icam.model.finance.Tax;
import com.qts.icam.model.inventory.Commodity;
import com.qts.icam.model.library.Author;
import com.qts.icam.model.library.Book;
import com.qts.icam.model.library.BookAllocation;
import com.qts.icam.model.library.BookAllocationDetails;
import com.qts.icam.model.library.BookCategory;
import com.qts.icam.model.library.BookId;
import com.qts.icam.model.library.BookLanguage;
import com.qts.icam.model.library.BookMedium;
import com.qts.icam.model.library.BookPublisher;
import com.qts.icam.model.library.BookPurchaseOrder;
import com.qts.icam.model.library.BookPurchaseOrderDetails;
import com.qts.icam.model.library.BookRating;
import com.qts.icam.model.library.BookRequest;
import com.qts.icam.model.library.BookRequestDetails;
import com.qts.icam.model.library.BookRequisition;
import com.qts.icam.model.library.BookRequisitionDetails;
import com.qts.icam.model.library.BookStatus;
import com.qts.icam.model.library.Magazine;
import com.qts.icam.model.library.MagazinePublisher;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Genre;
import com.qts.icam.service.administrator.AdministratorService;
import com.qts.icam.service.common.CommonService;
import com.qts.icam.service.finance.FinanceService;
import com.qts.icam.service.inventory.InventoryService;
import com.qts.icam.service.library.LibraryService;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.date.DateUtility;
import com.qts.icam.utility.uploaddownload.FileUploadDownload;

@Controller
@SessionAttributes("sessionObject")
public class LibraryController {
	public static Logger logger = Logger.getLogger(LibraryController.class);

	@Autowired
	LibraryService libraryService = null;
	@Value("${root.path}")
	private String rootPath;
	
	@Value("${excelDownload.folder}")
	private String excelDownloadFolder;
	
	@Value("${excelUpload.folder}")
	private String excelUploadfolder;
	
	@Value("${bookDetails.excel}")
	private String bookDetailsExcel;
	
	@Autowired
	FinanceService financeService;
	
	@Autowired
	InventoryService inventoryService;
	
	@Autowired
	AdministratorService administratorService = null;
	
	@Autowired
	CommonService commonService;	// added by Saif 21-03-2018
	
	/**
	 * @author anup.roy
	 * This method is to open the accession register page
	 */
	@RequestMapping(value = "/addBook", method = RequestMethod.GET)
	public ModelAndView addBook(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = null;
		try {
			logger.info("In addBook()-GET in LibraryController");
			List<BookCategory> categoryList = libraryService.getAllCategories();
			model.addAttribute("categoryList", categoryList);
			int lastAccessionNoForBook = libraryService.getLastAccessionNo();
			int nextAccessionNoForBook = lastAccessionNoForBook + 1;
			model.addAttribute("nextAccessionNoForBook", nextAccessionNoForBook);
			List<StatusOfItem> statusList = commonService.getAllStatusOfItems();
			model.addAttribute("statusList", statusList);
			strView = "library/addBook";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in addBook()GET method Of LibraryController", e);
		}
		return new ModelAndView(strView);
	}

	/**@author anup.roy*
	 * this method is for showing list of books page*
	 * library new*/
	
	@RequestMapping(value = "/listBook", method = RequestMethod.GET)
	public ModelAndView listBook(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In listBook() method of LibraryController: ");
		ModelAndView  mav = new ModelAndView("library/listBook");
		try {
			List<BookCategory> categoryList = libraryService.getAllCategories();
			if(categoryList!=null && categoryList.size()!=0){
				model.addAttribute("categoryList", categoryList);
			}
			
			List<Book> listOfBooks=	libraryService.getBookList();
			if(listOfBooks!=null && listOfBooks.size()!=0){
				model.addAttribute("listOfBooks",listOfBooks);
			}
			
			List<Magazine> listOfMagazine = libraryService.getMagazineList();
			if(listOfMagazine!=null && listOfMagazine.size()!=0){
				model.addAttribute("listOfMagazine",listOfMagazine);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in listBook() method Of LibraryController",	e);
		}
		return mav;
	}
	
	
	/**
	 * @author anup.roy
	 * this method is for adding a book or magazine into catalogue*/
	
	@RequestMapping(value="/addNewBook" , method=RequestMethod.POST)
	public ModelAndView addNewBook(HttpServletRequest req, HttpServletResponse res, ModelMap model,
			Book book, Magazine mag,
			@ModelAttribute("sessionObject")SessionObject sessionObject) {
		try {
			logger.info("in addNewBook()Post in LibraryController.java:");
			String returnStatus = null;
			String category = req.getParameter("category");
			if(category.equals("BOOK_CATEGORY_1")) {
				String status = req.getParameter("statusOfBook");
				book.setUpdatedBy(sessionObject.getUserId());
				book.setBookType(category);
				book.setStatusOfItemName(status);
				returnStatus = libraryService.addNewBook(book);
			}else {
				String status = req.getParameter("statusOfMag");
				mag.setBookType(category);
				mag.setUpdatedBy(sessionObject.getUserId());
				mag.setStatusOfItemName(status);
				returnStatus = libraryService.addNewMagazine(mag);
			}
			model.addAttribute("Message", returnStatus);
		}catch (Exception e) {
			logger.error("Exception in addNewBook()Post in LibraryController.java:"+e);
			e.printStackTrace();
		}
		return addBook(req, res, model);
	}
	
	/**
	 * @author anup.roy
	 * this method is for edit book info
	 * library new
	 */
	@RequestMapping(value = "/editBook", method = RequestMethod.POST)
	public ModelAndView editBook(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Book book) {
		try {
			logger.info("calling editBook() method of LibraryService");
			model.addAttribute("book", book);
		} catch (Exception e) {
			logger.error("Exception in editBook() method Of LibraryController",	e);
			e.printStackTrace();
		}
		return new ModelAndView("library/editBook");
	}
	
	/**
	 * @author anup.roy
	 * this method is for update book info
	 * library new
	 */
	
	/* modified by sourav.bhadra on 20-02-2018 */
	@RequestMapping(value = "/updateBook", method = RequestMethod.POST)
	public ModelAndView updateBook(HttpServletRequest request,
									HttpServletResponse response,
									ModelMap model,
									Book book,
									//BookMedium bookMedium,
									//BookLanguage bookLanguage,
									//BookPublisher bookPublisher,
									//Genre genre,
									//@RequestParam(required = false, value = "authorFullName") String[] authorFullName,
									//@RequestParam(required = false, value = "noteAuthorFullName") String[] noteAuthorFullName,
									//ArrayList<Author> authorList,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("calling updateBook() method of LibraryService");
			
			/*book.setBookMedium(bookMedium);
			book.setBookLanguage(bookLanguage);
			book.setBookPublisher(bookPublisher);
			book.setGenre(genre);
			if (authorFullName != null && authorFullName.length != 0) {
				for (int i = 0; i < authorFullName.length; i++) {
					Author a = new Author();
					a.setAuthorFullName(authorFullName[i].trim());
					authorList.add(a);
				}
			}
			if (noteAuthorFullName != null && noteAuthorFullName.length != 0) {
				for (int i = 0; i < noteAuthorFullName.length; i++) {
					Author authorObj = new Author();
					authorObj.setAuthorFullName(noteAuthorFullName[i]);
					authorList.add(authorObj);
				}
			}
			book.setBookAuthorList(authorList);
			String status =libraryService.updateBook(book, sessionObject.getUserId());	//return type  modified by Saif 21-03-2018
			/**Added by @author Saif.Ali
			 * Date- 19/03/2018
			 * Used to insert the information into the activity_log table*/
			String description = "";
			String status = "";
			String newBookName= request.getParameter("bookName");	
			String newBookISBN= request.getParameter("bookIsbn");	
			String newBookDescription= request.getParameter("bookDesc");	
			String newBookType= request.getParameter("bookMediumName");	
			String newBookEdition= request.getParameter("bookEdition");	
			String newBookLanguage= request.getParameter("bookLanguageName");	
			String newBookGenre= request.getParameter("genreName");	
			
			String oldBookCategory= request.getParameter("bookType");
			String oldBookName= request.getParameter("oldBookName");	
			String oldBookISBN= request.getParameter("oldBookISBN");	
			String oldBookDescription= request.getParameter("oldBookDescription");	
			String oldBookType= request.getParameter("oldBookType");	
			String oldBookEdition= request.getParameter("oldBookEdition");	
			String oldBookLanguage= request.getParameter("oldBookLanguage");	
			String oldBookGenre= request.getParameter("oldBookGenre");
			if(!(newBookName.equalsIgnoreCase(oldBookName)))
			{
				description = description + ("Book Name :: " + oldBookName + " updated to new Book Name ::" + newBookName + " ; ");
			}
			if(!(newBookISBN.equalsIgnoreCase(oldBookISBN)))
			{
				description = description + ("Book ISBN :: " + oldBookISBN + " updated to new Book ISBN ::" + newBookISBN + " ; ");
			}
			if(!(newBookDescription.equalsIgnoreCase(oldBookDescription)))
			{
				description = description + ("Book Description :: " + oldBookDescription + " updated to new Book Description ::" + newBookDescription + " ; ");
			}
			if(!(newBookType.equalsIgnoreCase(oldBookType)))
			{
				description = description + ("Book Type :: " + oldBookType + " updated to new Book Type ::" + newBookType + " ; ");
			}
			if(!(newBookEdition.equalsIgnoreCase(oldBookEdition)))
			{
				description = description + ("Book Edition :: " + oldBookEdition + " updated to new Book Edition ::" + newBookEdition + " ; ");
			}
			if(!(newBookLanguage.equalsIgnoreCase(oldBookLanguage)))
			{
				description = description + ("Book Language :: " + oldBookLanguage + " updated to new Book Language ::" + newBookLanguage + " ; ");
			}
			if(!(newBookGenre.equalsIgnoreCase(oldBookGenre)))
			{
				description = description + ("Book Genre :: " + oldBookGenre + " updated to new Book Genre ::" + newBookGenre + " ; ");
			}
			if(oldBookCategory.equalsIgnoreCase("book"))
			{
				String oldBookPublisher= request.getParameter("oldBookPublisher");	
				String oldBookAuthor= request.getParameter("oldBookAuthor");
				String newBookPublisher= request.getParameter("bookPublisherName");	
				String newBookAuthor= request.getParameter("authorFullName");
				if(!(newBookPublisher.equalsIgnoreCase(oldBookPublisher)))
				{
					description = description + ("Book Publisher :: " + oldBookPublisher + " updated to new Book Publisher ::" + newBookPublisher + " ; ");
				}
				if(!(newBookAuthor.equalsIgnoreCase(oldBookAuthor)))
				{
					description = description + ("Book Author :: " + oldBookAuthor + " updated to new Book Author ::" + newBookAuthor + " ; ");
				}
				
			}
			if(oldBookCategory.equalsIgnoreCase("periodicals"))
			{
				String oldBookPlace= request.getParameter("oldBookPlace");	
				String oldBookPeriodicity= request.getParameter("oldBookPeriodicity");
				String newBookPlace= request.getParameter("bookPlace");	
				String newBookPeriodicity= request.getParameter("bookPeriodicity");
				if(!(newBookPlace.equalsIgnoreCase(oldBookPlace)))
				{
					description = description + ("Book Place :: " + oldBookPlace + " updated to new Book Place ::" + newBookPlace + " ; ");
				}
				if(!(newBookPeriodicity.equalsIgnoreCase(oldBookPeriodicity)))
				{
					description = description + ("Book Periodicity :: " + oldBookPeriodicity + " updated to new Book Periodicity ::" + newBookPeriodicity + " ; ");
				}
			}
			if(oldBookCategory.equalsIgnoreCase("note"))
			{
				String oldBookNoteAuthorFullName= request.getParameter("oldBookNoteAuthorFullName");
				String newBookNoteAuthorFullName= request.getParameter("noteAuthorFullName");
				if(!(newBookNoteAuthorFullName.equalsIgnoreCase(oldBookNoteAuthorFullName)))
				{
					description = description + ("Book Author :: " + oldBookNoteAuthorFullName + " updated to new Book Author ::" + newBookNoteAuthorFullName + " ; ");
				}
			}
			if(status.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT BOOK DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("LIBRARY");
				updateLog.setUpdatedFor(newBookName);
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 369 :: CommonController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/**Addition ends*/
		} catch (Exception e) {
			logger.error("Exception in updateBook() method Of LibraryController", e);
			e.printStackTrace();
		}
		return listBook(request, response, model);
	}

	

	@RequestMapping(value = "/getCodeForBook", method = RequestMethod.GET)
	public @ResponseBody
	String getCodeForBook(@RequestParam("bookName") String bookName) {
		String bookCodeDB = "";
		try {
			logger.info("In getVendorBooks() method of LibraryController");
			bookCodeDB = libraryService.getCodeForBook(bookName);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return (new Gson().toJson(bookCodeDB));
	}

	@RequestMapping(value = "/checkAvailabilityForCode", method = RequestMethod.GET)
	public @ResponseBody
	String checkAvailabilityForCode(@RequestParam("bookCode") String bookCode) {
		String bookCodeDB = "";
		try {
			logger.info("In getVendorBooks() method of LibraryController");
			bookCodeDB = libraryService.checkAvailabilityForCode(bookCode);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return (new Gson().toJson(bookCodeDB));
	}

	@RequestMapping(value = "/getAuthorName", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getAuthorName(@RequestParam("term") String strQuery) {
		List<String> authorNameList = null;
		try {
			logger.info("getAuthorName()In LibraryController.java: calling getBookName( String strQuery) in BackOfficeService.java");
			authorNameList = libraryService.getAuthorNameDB(strQuery);
		} catch (Exception e) {
			logger.error("getAuthorName() In LibraryController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(authorNameList));
	}

	/**
	 * @author anup.roy
	 * this is for fetching all publisher*/
	
	@RequestMapping(value = "/getPublisherName", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getPublisherName() {
		List<String> publisherNameList = null;
		try {
			logger.info("In getPublisherName() in LibraryController.java");
			publisherNameList = libraryService.getpublisherNameDB();
		} catch (Exception e) {
			logger.error("getPublisherName() In LibraryController.java: Exception:"+ e);
			e.printStackTrace();
		}
		return (new Gson().toJson(publisherNameList));
	}


	@RequestMapping(value = "/listStudentBookAllocation", method = RequestMethod.GET)
	public ModelAndView listStudentBookAllocation(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("library/listStudentBookAllocation");
		try {
			logger.info("calling issuingBookForBookAllocation() method of LibraryController");
			List<BookRequestDetails> bookRequestList = libraryService.getNotReturnedRequestedBookDetails();
			if (bookRequestList != null && bookRequestList.size() != 0) {
				
				mav.addObject("bookRequestList", bookRequestList);
			}
		} catch (Exception e) {
			logger.error("Exception in issuingBookForBookAllocation() method Of LibraryController", e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/studentBookAllocationListPagination", method = RequestMethod.GET)
	public ModelAndView studentBookAllocationListPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In studentBookAllocationListPagination() method of LibraryController");
			mav = new ModelAndView("library/listStudentBookAllocation ");
			logger.info("In LibraryController studentBookAllocationListPagination() method: calling getBookListPaging(HttpServletRequest request) in LibraryService.java");
			PagedListHolder<BookRequestDetails> pagedListHolder = libraryService.getAllocatedBookListPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController studentBookAllocationListPagination() method for Exception: ", e);
		}
		return mav;
	}

	/**
	 * @author anup.roy
	 * This viewBookStock() method is related to get all book stocks
	 * library new 
	 */
	@RequestMapping(value = "/viewBookStock", method = RequestMethod.GET)
	public ModelAndView viewBookStock(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In viewBookStock() method of LibraryController: ");
		ModelAndView mav = new ModelAndView("library/viewBookStock");
		try {
			List<Book> bookListFromDB = libraryService.getBookStock();
			if (bookListFromDB != null && bookListFromDB.size() != 0) {
				model.addAttribute("bookListFromDB", bookListFromDB);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in viewBookStock() method Of LibraryController", e);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/listBookStockPagination", method = RequestMethod.GET)
	public ModelAndView bookStockPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In bookStockPagination() method of LibraryController");
			mav = new ModelAndView("library/viewBookStock");
			logger.info("In LibraryController bookStockPagination() method: calling getBookStockPaging(HttpServletRequest request) in LibraryService.java");
			PagedListHolder<Book> pagedListHolder = libraryService.getBookStockPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController bookStockPagination() method for Exception: ", e);
		}
		return mav;
	}


	@RequestMapping(value = "/searchForViewBookStock", method = RequestMethod.POST)
	public ModelAndView searchForViewBookStock(
			@RequestParam("query") String strKey,
			@RequestParam("data") String strValue, HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Book book)
			throws ParseException {
		try {
			logger.info("calling searchForViewBookStock() method of LibraryController with searching parameter. Key:=" + strKey + " and Value:=" + strValue);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.clear();
			if (strKey.equalsIgnoreCase("BookCode")) {
				parameters.put("BookCode", strValue.trim());
			}
			if (strKey.equalsIgnoreCase("BookName")) {
				parameters.put("BookName", strValue.trim());
			}
			libraryService.searchForViewBookStock(parameters);
		} catch (Exception e) {
			logger.error("Exception in searchForViewBookStock() method Of LibraryController", e);
		}
		return  bookStockPagination(request, response);
	}

	/**
	 * @author anup.roy
	 * this method is to view book profile and lending history
	 * library new
	 */

	@RequestMapping(value = "/viewLendingHistory", method = RequestMethod.GET)
	public ModelAndView viewLendingHistory(@RequestParam("bookCode") String strBookCode,
			HttpServletRequest request, HttpServletResponse response, ModelMap model, Book book) {
		List<BookAllocation> bookAllocationList = null;
		ModelAndView mav = new ModelAndView("library/viewBookProfile");
		try {
			logger.info("calling viewLendingHistory() method of LibraryController with Parameter :"	+ strBookCode);
			
			book = libraryService.getLendingHistory(strBookCode);
			
			
			mav.addObject("BookProfile", book);
			mav.addObject("strBookCode", strBookCode);
			if (book != null) {
				
				bookAllocationList = book.getBookAllocationList();
				if (bookAllocationList != null && bookAllocationList.size() != 0) {
					logger.info("In LibraryController listBook() method: calling getBookListPaging(HttpServletRequest request) in LibraryService.java");
					mav.addObject("bookAllocationList", bookAllocationList);
				}
			}
		} catch (Exception e) {
			logger.error("Exception in viewLendingHistory() method Of LibraryController", e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/lendingHistoryPagination", method = RequestMethod.GET)
	public ModelAndView lendingHistoryPagination(@RequestParam("strBookCode") String strBookCode, HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In lendingHistoryPagination() method of LibraryController");
			mav = new ModelAndView("library/viewBookProfile");			
			Book book = libraryService.getLendingHistory(strBookCode);
			
			PagedListHolder<BookAllocation> pagedListHolder = libraryService.getLendHistoryPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
				mav.addObject("BookProfile", book);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController getBookListPaging() method for Exception: ", e);
		}
		return mav;
	}


	@RequestMapping(value = "/getLendingDates", method = RequestMethod.GET)
	public @ResponseBody
	String getLendingDates(@RequestParam("bookCode") String strBookCode,
			@RequestParam("userId") String userId, Book book) {
		String str = "";
		try {
			logger.info("calling getLendingDates() method of LibraryController with Parameter :"
					+ strBookCode);
			book.setBookCode(strBookCode);
			book.setUpdatedBy(userId);
			List<BookAllocationDetails> lendingDates = libraryService
					.getLendingDates(book);
			if (lendingDates != null && lendingDates.size() != 0) {
				for (BookAllocationDetails bad : lendingDates) {
					if (bad != null) {
						if (bad.getBookIssueDate() != null
								&& bad.getBookIssueDate().trim().length() != 0) {
							str = str + bad.getBookIssueDate() + ",";
						} else {
							str = str + "" + ",";
						}
						if (bad.getBookReturnDate() != null
								&& bad.getBookReturnDate().trim().length() != 0) {
							str = str + bad.getBookReturnDate() + "@";
						} else {
							str = str + "" + "@";
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error(
					"Exception in getLendingDates() method Of LibraryController",
					e);
			e.printStackTrace();
		}
		return (new Gson().toJson(str));
	}

	/*
	 * This viewBookList() method is related to get all book stocks and return
	 * to retirementBookCodeList.jsp page with populated data.
	 * 
	 * @param HttpServletRequest
	 * 
	 * @param HttpServletResponse
	 * 
	 * @param ModelMap
	 * 
	 * @return ModelAndView
	 */

@RequestMapping(value = "/retirementBookCodeList", method = RequestMethod.GET)
	public ModelAndView viewBookList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {		
		ModelAndView mav = new ModelAndView("library/retirementBookCodeList");
		try {
			logger.info("In viewBookList() method : calling getBookStockPaging() method of LibraryService");			
			List<Book> bookListFromDB = libraryService.getBookStock();
			model.addAttribute("bookListFromDB",bookListFromDB);
		} catch (Exception e) {
			logger.error("Exception in viewBookList() method Of LibraryController", e);
		}
		return mav;
	}

	
	/*@RequestMapping(value = "/retirementBookCodeListPagination", method = RequestMethod.GET)
	public ModelAndView retirementBookCodeListPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In retirementBookCodeListPagination() method of LibraryController");
			mav = new ModelAndView("library/retirementBookCodeList");
			logger.info("In LibraryController retirementBookCodeListPagination() method: calling getBookListPaging(HttpServletRequest request) in LibraryService.java");
			PagedListHolder<Book> pagedListHolder = libraryService.getBookStockPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController getBookListPaging() method for Exception: ", e);
		}
		return mav;
	}*/

	/*
	 * This viewBookList() method is related to get all book stocks and return
	 * to retirementBookCodeList.jsp page with populated data.
	 * 
	 * @param HttpServletRequest
	 * 
	 * @param HttpServletResponse
	 * 
	 * @param ModelMap
	 * 
	 * @return ModelAndView
	 */

@RequestMapping(value = "/retirementBookIdList.html", method = RequestMethod.POST)
	public ModelAndView viewBookIdList(HttpServletRequest request, HttpServletResponse response, ModelMap model, Book book) {
		logger.info("calling viewBookIdList() method of LibraryController");
		ModelAndView mav = new ModelAndView("library/retirementBookIdList");
		try {
			
			Book bookFromDB = libraryService.getBookIdList(book);
			if (bookFromDB != null) {
				List<BookId> bookIdListFromDB = bookFromDB.getBookIdList();
				if (bookIdListFromDB != null && bookIdListFromDB.size() != 0) {
					model.addAttribute("bookIdListFromDB", bookIdListFromDB);
				}
				mav.addObject("ViewBookIdList", bookFromDB);
			}
		} catch (Exception e) {
			logger.error("Exception in viewBookIdList() method Of LibraryController", e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/retiredBookList", method = RequestMethod.POST,params="bookIDRetireSearch")
	public ModelAndView viewBookIdListSearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Book book,@RequestParam("bkCode") String code) {
		ModelAndView mav = new ModelAndView("library/retirementBookIdList");
		try {
			logger.info("viewBookIdListSearch() method in LibraryController");
			String strKey = request.getParameter("query").trim();
			String strValue = request.getParameter("data").trim();
			BookId bookId = new BookId();
			if (strKey.equalsIgnoreCase("BookID")) {
				bookId.setBookId(strValue.trim());
			}
			if (strKey.equalsIgnoreCase("EntryDate")) {
				bookId.setNewBookEntryDate(strValue.trim());
			}
			book.setBookCode(code);
			book.setBookId(bookId);
			Book bookFromDB = libraryService.getBookIdList(book);
			PagedListHolder<BookId> pagedListHolder = libraryService.getBookIdListPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
			mav.addObject("ViewBookIdList", bookFromDB);
		} catch (Exception e) {
			logger.error("Error in LibraryController viewBookIdListSearch() method for Exception: ", e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/retirementBookIdListPagination", method = RequestMethod.GET)
	public ModelAndView retirementBookIdListPagination(@RequestParam("bkCode") String code , HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("library/retirementBookIdList");
		try {
			logger.info("In LibraryController retirementBookIdListPagination() method: calling getBookIdListPaging(HttpServletRequest request) in LibraryService.java");
			Book book = new Book();
			book.setBookCode(code);
			Book bookFromDB = libraryService.getBookIdList(book);
			PagedListHolder<BookId> pagedListHolder = libraryService.getBookIdListPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
			mav.addObject("ViewBookIdList", bookFromDB);
		} catch (Exception e) {
			logger.error("Error in LibraryController retirementBookIdListPagination() method for Exception: ", e);
		}
		return mav;
	}
	/**
	 * 
	 * @param strQuery
	 * @param strData
	 * @param request
	 * @param response
	 * @param model
	 * @param book
	 * @return ModelAndView
	 */

@RequestMapping(value = "/searchOnRetirementBook", method = RequestMethod.POST)
	public ModelAndView searchOnRetirementBook(
			@RequestParam("query") String strQuery,
			@RequestParam("data") String strData, HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Book book) {
		String strView = null;
		try {
			logger.info("calling searchOnRetirementBook() method of LibraryController with searching parameter. Key:=" + strQuery + " and Value:=" + strData);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.clear();
			if (strQuery.equalsIgnoreCase("BookCode")) {
				parameters.put("BookCode", strData.trim());
			}
			if (strQuery.equalsIgnoreCase("BookName")) {
				parameters.put("BookName", strData.trim());
			}
			List<Book> searchListOnRetirementBook = libraryService.searchListOnRetirementBook(parameters);
			if (searchListOnRetirementBook != null && searchListOnRetirementBook.size() != 0) {
				PagedListHolder<Book> pagedListHolder = libraryService.getBookStockPaging(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Exception in searchOnRetirementBook() method Of LibraryController", e);
		}
		return new ModelAndView("library/retirementBookCodeList");
	}

	/*
	 * This viewRetiredBookList() method is related to get all retired books and
	 * return to retiredBookList.jsp page with populated data.
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @return ModelAndView
	 * modified by ranita.sur 26062017
	 */

	@RequestMapping(value = "/retiredBookList", method = RequestMethod.POST)
	public ModelAndView viewRetiredBookList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Book book,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		List<BookId> bookIdList = null;
		try {			
			logger.info("calling retiredBookList() method of LibraryController");
			if (null != request.getParameterValues("bookIdentity")) {
				String[] strBookIds = request.getParameterValues("bookIdentity");
				bookIdList = new ArrayList<BookId>();
				for (int counter = 0; counter < strBookIds.length ; counter++) {	
					BookId bookIdObj = new BookId();
					bookIdObj.setUpdatedBy(sessionObject.getUserId());
					bookIdObj.setBookId(strBookIds[counter]);
					bookIdObj.setComment((request.getParameter("comment_" + strBookIds[counter])).trim());					
					bookIdList.add(bookIdObj);
				}
				libraryService.removeBookIds(bookIdList);
			}
		} catch (Exception e) {
			logger.error("Exception in retiredBookList() method Of LibraryController",e);
			e.printStackTrace();
		}
		return retiredBookListPagination(request,response,model);
	}
	

	@RequestMapping(value = "/retiredBookList", method = RequestMethod.GET)
	public ModelAndView retiredBookList(
									HttpServletRequest request, HttpServletResponse response,
									ModelMap model) {
		try {
			List<Book> retiredBookList = libraryService.getRetiredBookList();
			model.addAttribute("ViewRetiredBookList", retiredBookList);
		} catch (Exception e) {
			logger.error("Exception in retiredBookList() method Of LibraryController",e);
		}
		return retiredBookListPagination(request,response,model);
	}
	@RequestMapping(value = "/retiredBookSearch", method = RequestMethod.POST)
	public ModelAndView retiredBookSearch(HttpServletRequest request,HttpServletResponse response,
														ModelMap model
														) {
		try {
			logger.info("retiredBookSearch() method of LibraryController");
			String strKey = request.getParameter("query").trim();
			String strValue = request.getParameter("data").trim();
			Map<String, Object> parameters = new HashMap<String, Object>();
			if (strKey.equalsIgnoreCase("BookCode")) {
				parameters.put("BookCode", strValue.trim());
			}
			if (strKey.equalsIgnoreCase("BookName")) {
				parameters.put("BookName", strValue.trim());
			}
			libraryService.getRetiredBookSearch(parameters);
		} catch (Exception e) {
			logger.error("Exception in retiredBookSearch() GET method Of LibraryController",	e);
		}
		return retiredBookListPagination(request,response,model);	
	}
	
	@RequestMapping(value = "/retiredBookListPagination", method = RequestMethod.GET)
	public ModelAndView retiredBookListPagination(HttpServletRequest request, HttpServletResponse response,ModelMap model) {
		try {
			logger.info("In retiredBookListPagination() method of LibraryController");
			PagedListHolder<Book> pagedListHolder = libraryService.getRetiredBookListPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!= 0) {
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("bookPagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController retiredBookList() method for Exception: ", e);
		}
		return new ModelAndView("library/retiredBookList");
	}
	
	
	/**
	 * 
	 * @param bookCode
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/retiredBookDetails", method = RequestMethod.GET)
	public ModelAndView retiredBookDetails(
			@RequestParam("bookCode") String bookCode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String strView = null;
		Book bookFromDB = null;
		try {
			logger.info("calling retiredBookDetails() method of LibraryController");
			bookFromDB = (Book) libraryService.getRetiredBookDetails(bookCode);
			if (bookFromDB != null) {
				model.addAttribute("ViewRetiredBookDetails", bookFromDB);
				strView = "library/retiredBookDetails";
			} else {
				model.addAttribute("contactForm", "data not available...");
				model.addAttribute("checkMessage", "ceckmessage");
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			logger.error(
					"Exception in retiredBookDetails() method Of LibraryController",
					e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * This method is used to get the last requisitionId stored in the database
	 * and open addRequisition Form
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param bookRequisition
	 * @param book
	 * @param bookPublisher
	 * @return
	 * 
	 * modified by ranita.sur
	 * changes taken on 14062017
	 */
	@RequestMapping(value = "/createRequisition.html", method = RequestMethod.GET)
	public ModelAndView createRequisition(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam(required = false, value = "bookCode") String strBookCode) {
		try {
			logger.info("calling createRequisition()GET method of LibraryController");
			BookRequisition bookRequisition = libraryService.getLastRequisitionId();
			List<Genre> genreList = libraryService.getGenreList();
			List<Department> departmentList=libraryService.getDepartmentForBookRequisition();
			if(strBookCode!=null){
				Book book = new Book();
				book.setBookCode(strBookCode);
				book = libraryService.getBookDetails(book);	
				model.addAttribute("bookDetails", book);
			}
				model.addAttribute("strRequisitionUpComingId", bookRequisition);
				model.addAttribute("genreList",genreList);
				model.addAttribute("departmentList",departmentList);
				
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in createRequisition() GET method Of LibraryController",e);
		}
		return new ModelAndView("library/createRequisition");
	}

	/**
	 * This method is used to add Requisition and store in the database
	 * 
	 * @author sayani.datta
	 * @param strArrBookName
	 * @param strNumberOfBooksRequisitioned
	 * @param request
	 * @param response
	 * @param model
	 * @param bookPublisher
	 * @return
	 * 
	 * modified by ranita.sur
	 * changes taken on 14062017
	 */

	@RequestMapping(value = "/addRequisition", method = RequestMethod.POST)
	public ModelAndView addRequisition(@RequestParam(required = false, value="bookName") String[] strArrBookName,
										@RequestParam(required = false, value="bookAuthor") String[] strAuthorName,
										@RequestParam(required = false, value="bookEdition") String[] strEdition,
										@RequestParam(required = false, value="bookPublisher") String[] strPublisher,
										@RequestParam(required = false, value="numberOfBooksRequisitioned") String[] strNumberOfBooksRequisitioned,
										/* modified by sourav.bhadra on 13-09-2017 */
										@RequestParam(required = false, value="genreName") String[] genreName,
										@RequestParam(required = false, value="departmentName") String[] departmentName,
										HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model,
										BookRequisition bookRequisition,
										Vendor vendor,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
		
		try {
			logger.info("calling addRequisition() POST method of LibraryController");
			int intTotalNoOfBookCopies = 0;
			double price = 0.0;
			List<BookRequisition> bookRequisitionList = null;
			List<BookRequisitionDetails> bookRequisitionDetailsList = new ArrayList<BookRequisitionDetails>();
			/* setting individual book details */
			if (strArrBookName != null && strArrBookName.length != 0) {
				for (int i = 0; i < strArrBookName.length; i++) {
					BookRequisitionDetails bookRequisitionDetails = new BookRequisitionDetails();
					if (strArrBookName[i] != null) {
						bookRequisitionDetails.setBookName(strArrBookName[i]);
					}
					if (strAuthorName != null) {
						if (strAuthorName[i] != null && strAuthorName[i].trim().length()!=0) {
							bookRequisitionDetails.setBookAuthor(strAuthorName[i]);
						}
					}
					if (strEdition != null) {
						if (strEdition[i] != null) {
							bookRequisitionDetails.setBookEdition(strEdition[i]);
						}
					}
					if (strPublisher != null) {
						if (strPublisher[i] != null) {
							bookRequisitionDetails.setBookPublisher(strPublisher[i]);
						}
					}
					if (strNumberOfBooksRequisitioned != null && strNumberOfBooksRequisitioned.length != 0) {
						int intNoOfIndividualBookCopies = Integer.parseInt(strNumberOfBooksRequisitioned[i]);
						intTotalNoOfBookCopies = intTotalNoOfBookCopies	+ intNoOfIndividualBookCopies;
						bookRequisitionDetails.setNumberOfBooksRequisitioned(intNoOfIndividualBookCopies);
					}
					/* modified by sourav.bhadra on 13-09-2017 */
					bookRequisitionDetails.setGenre(genreName[i]);
					bookRequisitionDetails.setDepartmentName(departmentName[i]);
					bookRequisitionDetailsList.add(bookRequisitionDetails);
				}
			}
			bookRequisition.setBookRequisitionDetailsList(bookRequisitionDetailsList);
			bookRequisition.setTotalNumberOfBooksRequisitioned(intTotalNoOfBookCopies);
			bookRequisition.setTotalPrice(price);
			bookRequisition.setVendor(vendor);	
			bookRequisitionList = libraryService.addRequisition(bookRequisition, sessionObject.getUserId());
			if (bookRequisitionList != null && bookRequisitionList.size() != 0) {
				model.addAttribute("bookRequisitionList", bookRequisitionList);
				strView = "library/requisitionList";
			}
			/**Added by Saif 23-03-2018
			 * Used to insert data into the activity log*/
			if (bookRequisitionList != null && bookRequisitionList.size() != 0) 
			{
					UpdateLog updateLog=new UpdateLog();
					updateLog.setUpdatedByUserId(sessionObject.getUserId());
					updateLog.setFunctionality("CREATE BOOK REQUISITION");
					updateLog.setInsertUpdate("INSERT");
					updateLog.setModule("LIBRARY");
					if (strArrBookName != null && strArrBookName.length != 0) 
					{
						for (int i = 0; i < strArrBookName.length; i++)
						{
							updateLog.setUpdatedFor(strArrBookName[i]);
							updateLog.setDescription(" A Requisition of Book Name :: " + strArrBookName[i] + " of Author :: " + strAuthorName[i] + " of Edition :: " 
							+ strEdition[i] + " of Genre :: " + genreName[i] + " of Department :: " + departmentName[i] 
							+ " of Publisher :: " + strPublisher[i] + " of Quantity :: " + strNumberOfBooksRequisitioned[i] + " is raised");
						}
					}
					String response_update=commonService.insertIntoActivityLog(updateLog);
					System.out.println("LN 3178 :: BackOfficeController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
							":: Functonality ::"+ updateLog.getFunctionality() + 
							":: Module ::"+updateLog.getModule() + 
							":: Updated For ::"+ updateLog.getUpdatedFor() +
							":: Description :: "+updateLog.getDescription() +
							":: Response :: " + response_update +
							":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/***/
			strView = "library/requisitionList";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in addRequisition() method Of LibraryController", e);e.printStackTrace();
		}
		return new ModelAndView(strView);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */

@RequestMapping(value = "/viewRequisition", method = RequestMethod.GET)
	public ModelAndView viewRequisition(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		List<BookRequisition> bookRequisitionList = null;
		ModelAndView mav = new ModelAndView("library/requisitionList");
		try {
			logger.info("calling viewRequisition() GET method of LibraryController");
			bookRequisitionList = libraryService.viewRequisition();
			//System.out.println(bookRequisitionList.size());
			if (bookRequisitionList != null && bookRequisitionList.size() != 0) {
				mav.addObject("bookRequisitionList", bookRequisitionList);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in viewRequisition() GET method Of LibraryController", e);
		}
		return mav;
	}
		
	@RequestMapping(value = "/viewRequisitionListPagination", method = RequestMethod.GET)
	public ModelAndView viewRequisitionPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In viewRequisitionPagination() method of LibraryController");
			mav = new ModelAndView("library/requisitionList");
			logger.info("In LibraryController viewRequisitionPagination() method: calling viewRequisitionListPaging(HttpServletRequest request) in LibraryService.java");
			PagedListHolder<BookRequisition> pagedListHolder = libraryService.viewRequisitionListPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController viewRequisitionPagination() method for Exception: ", e);
		}
		return mav;
	}
	
	

	/**
	 * @param requisitionCode
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 * @author anup.roy
	 * 27062017
	 */

	@RequestMapping(value = "/listPurchaseOrder", method = RequestMethod.GET)
	public ModelAndView listPurchaseOrder(@RequestParam("requisitionCode") String requisitionCode, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("library/listBookPurchaseOrder");
		try {
			logger.info("In listPurchaseOrder() GET method of LibraryController");
			List<BookPurchaseOrder> bookPurchaseOrderList = libraryService.getPurchaseOrdersInRequition(requisitionCode);
			if (bookPurchaseOrderList != null && bookPurchaseOrderList.size() != 0) {
				model.addAttribute("bookPurchaseOrderList", bookPurchaseOrderList);
			}
		} catch (Exception e) {
			logger.error("Exception in listPurchaseOrder() GET method Of LibraryController", e);
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/purchaseOrderListPagination", method = RequestMethod.GET)
	public ModelAndView purchaseOrderListPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In purchaseOrderListPagination() method of LibraryController");
			mav = new ModelAndView("library/listBookPurchaseOrder");
			logger.info("In LibraryController purchaseOrderListPagination() method: calling getBookPurchaseOrderListPaging(HttpServletRequest request) in LibraryService.java");
			PagedListHolder<BookPurchaseOrder> pagedListHolder = libraryService.getBookPurchaseOrderListPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController purchaseOrderListPagination() method for Exception: ", e);
		}
		return mav;
	}

	/**
	 * @author anup.roy
	 * 27062017
	 * */
	
	@RequestMapping(value = "/updateReceiveForBook", method = RequestMethod.GET)
	public ModelAndView updateReceiveForBook(
			@RequestParam("purchaseOrderCode") String purchaseOrderCode,
			@RequestParam("vendorCode") String vendorCode,
			@RequestParam("totalPaidAmount") String totalPaidAmount,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String strView = null;
		List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList = null;
		try {
			logger.info("calling receiveRequisitionDetails() GET method of LibraryController");
			bookPurchaseOrderDetailsList = libraryService.getPurchaseOrdersDetailsInPurchaseOrders(purchaseOrderCode);
			List<Ledger>ledgerList = financeService.getLedgerList();
			List<Vendor> bankList = inventoryService.getBankDetailsList();
			model.addAttribute("bankList", bankList);
			if (ledgerList != null && ledgerList.size() != 0) {
				model.addAttribute("ledgerList",ledgerList);
			}
			/* added by sourav.bhadra on 31-07-2017 to get vendor's ledger */
			String vendorLedger = inventoryService.getVendorsLedgerForCommodityPO(vendorCode);
			if(null != vendorLedger && vendorLedger != ""){
				model.addAttribute("vendorLedger", vendorLedger);
			}
			
			if (bookPurchaseOrderDetailsList != null) {
				/* added by sourav.bhadra on 13-09-2017 to 
				 to calculate individual books paid amount */
				for(BookPurchaseOrderDetails bpod:bookPurchaseOrderDetailsList){
					double paidAmt = (bpod.getQtyReceived() * bpod.getRate());
					if(bpod.getTax() != 0){
						paidAmt = ((paidAmt*bpod.getTax())/100);
					}
					bpod.setPaidAmount(paidAmt);
				}
				model.addAttribute("bookPurchaseOrderDetailsList",bookPurchaseOrderDetailsList);
				/* added by sourav.bhadra on 13-09-2017 to display total paid amount */
				model.addAttribute("totalPaidAmount",totalPaidAmount);
				strView = "library/receiveRequisition";
			}else {
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			logger.error("Exception in receiveRequisitionDetails() GET method Of LibraryController",e);
		}
		return new ModelAndView(strView);
	}




	/*updated By sourav 24062017*/
	@RequestMapping(value = "/updatePurchaseOrderDetails", method = RequestMethod.POST)
	public ModelAndView updatePurchaseOrderDetails(@RequestParam("bookPurchaseOrderCode") String bookPurchaseOrderCode,
													@RequestParam("purchaseOrderDetailsCode") String[] purchaseOrderDetailsCode,
													@RequestParam("qtyOrdered") String[] qtyOrdered,
													@RequestParam("qtyReceived") String[] qtyReceived,
													@RequestParam("qtyReceiving") String[] qtyReceiving,
													@RequestParam("amount") String[] amount,
													@RequestParam("paidAmount") String[] paidAmount,
													/* added by sourav.bhadra on 12-09-2017 */
													@RequestParam("bookName") String[] bookName,
													@RequestParam("authorName") String[] authorName,
													@RequestParam("genre") String[] genre,
													@RequestParam("rate") String[] rate,
													@RequestParam("ledger") String ledger,
													HttpServletRequest request, HttpServletResponse response,
													ModelMap model,
													ArrayList<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList,
													BookPurchaseOrder bookPurchaseOrder,
													@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("calling updatePurchaseOrderDetails() GET method of LibraryController");
			double tord = 0.0;
			double trec = 0.0;
			double tdefi = 0.0;
			Double totalAmount = 0.0;
			if (purchaseOrderDetailsCode != null) {
				for (int i = 0; i < purchaseOrderDetailsCode.length; i++) {
					BookPurchaseOrderDetails bpod = new BookPurchaseOrderDetails();
					bpod.setUpdatedBy(sessionObject.getUserId());
					bpod.setPurchaseOrderDetailsCode(purchaseOrderDetailsCode[i]);
					if (qtyOrdered != null) {
						double ordered = Double.parseDouble(qtyOrdered[i].trim());
						double received = Double.parseDouble(qtyReceived[i].trim());
						double receiving = Double.parseDouble(qtyReceiving[i].trim());
						double remeaning = ordered - (received + receiving);
						if (remeaning > 0)
							bpod.setStatus("OPEN");
						else
							bpod.setStatus("CLOSED");
						bpod.setQtyDeficit(remeaning);
						bpod.setQtyReceived(received + receiving);
						/* added by sourav.bhadra on 13-09-2017 */
						bpod.setBookName(bookName[i].trim());
						bpod.setAuthorName(authorName[i].trim());
						bpod.setItemCode(genre[i].trim());
						bpod.setRate(Double.parseDouble(rate[i].trim()));
						bpod.setQtyReceiving(receiving);
						
						bookPurchaseOrderDetailsList.add(bpod);
						tord = tord + ordered;
						tdefi = tdefi + remeaning;
						trec = trec + received + receiving;
						/* added by sourav.bhadra on 13-09-2017 */
						totalAmount += Double.parseDouble(amount[i]);
					}
				}
			}
			bookPurchaseOrder.setUpdatedBy(sessionObject.getUserId());
			bookPurchaseOrder.setPurchaseOrderCode(bookPurchaseOrderCode);
			bookPurchaseOrder.setTotalQtyOrdered(tord);
			bookPurchaseOrder.setTotalQtyReceived(trec);
			bookPurchaseOrder.setTotalQtyDeficit(tdefi);
			bookPurchaseOrder.setLedger(ledger);/* added by sourav.bhadra on 13-09-2017 */
			if (tdefi > 0)
				bookPurchaseOrder.setStatus("OPEN");
			else
				bookPurchaseOrder.setStatus("CLOSED");

			libraryService.updatePurchaseOrderDetails(bookPurchaseOrder,bookPurchaseOrderDetailsList);
			
			/*new code to insert in transactional_working_area*/
			Double paymentAmount = Double.parseDouble(request.getParameter("payingAmount"));
			Double totalAmountPaid = paymentAmount +  Double.parseDouble(request.getParameter("totalPaidAmount")) ;
			
			/* modified by sourav.bhadra on 13-09-2017 */
			if(paymentAmount > 0){
				BookPurchaseOrder paymentDetails = new BookPurchaseOrder();
				paymentDetails.setAdvanceAmount(paymentAmount);
				paymentDetails.setLedger(ledger);
				paymentDetails.setUpdatedBy(sessionObject.getUserId());
				paymentDetails.setPurchaseOrderCode(bookPurchaseOrderCode);
				paymentDetails.setPurchaseOrderName("LIBRARY PO");
				paymentDetails.setPurchaseOrderDesc("LIBRARY PO");
				if(totalAmount>totalAmountPaid){
					paymentDetails.setAmountStatus("OPEN");
				}else{
					paymentDetails.setAmountStatus("CLOSED");
				}
				libraryService.updatePurchaseOrderPayment(paymentDetails);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in receiveRequisitionDetails() GET method Of LibraryController",e);
		}
		return viewRequisition(request, response, model);
	}

	/**done by naimisha
	 * for ledger add**/
	
	@RequestMapping(value = "/updatePayForBook", method = RequestMethod.GET)
	public ModelAndView updatePayForBook(
			@RequestParam("purchaseOrderCode") String purchaseOrderCode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String strView = null;
		BookPurchaseOrder bookPurchaseOrder = null;
		try {
			logger.info("calling receiveRequisitionDetails() GET method of LibraryController");
			bookPurchaseOrder = libraryService.getPurchaseOrdersForPayment(purchaseOrderCode);
			model.addAttribute("bookPurchaseOrder", bookPurchaseOrder);
			List<Ledger>ledgerList = financeService.getLedgerList();//Added By Naimisha 03012017
			
			if (ledgerList != null && ledgerList.size() != 0) {
				model.addAttribute("ledgerList",ledgerList);
			}
			strView = "library/payRequisition";
		} catch (Exception e) {
			logger.error("Exception in receiveRequisitionDetails() GET method Of LibraryController",e);
		}
		return new ModelAndView(strView);
	}

	@RequestMapping(value = "/updatePurchaseOrderPayment", method = RequestMethod.POST)
	public ModelAndView updatePurchaseOrderPayment(BookPurchaseOrder bookPurchaseOrder,
													HttpServletRequest request,
													HttpServletResponse response,
													ModelMap model,
													@ModelAttribute("sessionObject") SessionObject sessionObject) {

		try {
			//System.out.println("in controller(line1262)Adv:"+bookPurchaseOrder.getAdvanceAmount()+"-Net-"+bookPurchaseOrder.getNetAmount()+"-Total-"+bookPurchaseOrder.getTotalAmount());
			logger.info("calling receiveRequisitionDetails() GET method of LibraryController");
			if (bookPurchaseOrder.getPendingAmount()> 0)
				bookPurchaseOrder.setAmountStatus("OPEN");
			else
				bookPurchaseOrder.setAmountStatus("CLOSED");
				bookPurchaseOrder.setUpdatedBy(sessionObject.getUserId());
			libraryService.updatePurchaseOrderPayment(bookPurchaseOrder);
		} catch (Exception e) {
			logger.error("Exception in receiveRequisitionDetails() GET method Of LibraryController",e);
		}
		return viewRequisition(request, response, model);
	}

	/**@author anup.roy*
	 * this method is for view the listof books to be addded in catalogue*
	 * library new*/
	
	@RequestMapping(value = "/purchaseOrderDetailsList", method = RequestMethod.GET)
	public ModelAndView purchaseOrderDetailsList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String strView = null;	
		try {
			List<BookPurchaseOrderDetails> bookPODetails = libraryService.getBookPurchaseOrderDetailsList();
			model.addAttribute("bookPODetails", bookPODetails);
			strView = "library/purchaseOrderDetailsList";
		} catch (Exception e) {
			logger.error("Exception in purchaseOrderDetailsList() GET method Of LibraryController",	e);
		}
		return new ModelAndView(strView);
	}

	@RequestMapping(value = "/saveToCatalogueAfterReceive", method = RequestMethod.POST,params="purchaseOrderDetailsListSearch")
	public ModelAndView purchaseOrderDetailsListSearch(HttpServletRequest request,HttpServletResponse response,
														ModelMap model
														) {
		try {
			logger.info("calling bookListSearch() method of LibraryService");
			String strKey = request.getParameter("query").trim();
			String strValue = request.getParameter("data").trim();
			Map<String, Object> parameters = new HashMap<String, Object>();
			if (strKey.equalsIgnoreCase("RequisitionCode")) {
				parameters.put("RequisitionCode", strValue.trim());
			}
			if (strKey.equalsIgnoreCase("OrderCode")) {
				parameters.put("OrderCode", strValue.trim());
			}
			if (strKey.equalsIgnoreCase("BookName")) {
				parameters.put("BookName", strValue.trim());
			}
			if (strKey.equalsIgnoreCase("Author")) {
				parameters.put("Author", strValue.trim());
			}
			if (strKey.equalsIgnoreCase("Edition")) {
				parameters.put("Edition", strValue.trim());
			}
			if (strKey.equalsIgnoreCase("Amount")) {
				parameters.put("Amount", strValue.trim());
			}
			libraryService.getPurchaseOrderDetailsListSearch(parameters);
		} catch (Exception e) {
			logger.error("Exception in purchaseOrderDetailsList() GET method Of LibraryController",	e);
		}
		return purchaseOrderDetailsListPagination(request,response);	
	}
	
	@RequestMapping(value = "/purchaseOrderDetailsListPagination", method = RequestMethod.GET)
	public ModelAndView purchaseOrderDetailsListPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In purchaseOrderDetailsListPagination() method of LibraryController");
			mav = new ModelAndView("library/purchaseOrderDetailsList");
			logger.info("In LibraryController purchaseOrderDetailsListPagination() method: calling getBookListPaging(HttpServletRequest request) in LibraryService.java");
			PagedListHolder<BookPurchaseOrderDetails> pagedListHolder = libraryService.getPurchaseOrderDetailsListPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController purchaseOrderDetailsListPagination() method for Exception: ", e);
		}
		return mav;
	}

	/**@author anup.roy
	 * this method is to enter the details of new books to catalogue
	 * library new*/
	
	@RequestMapping(value = "/saveToCatalogueAfterReceive", method = RequestMethod.POST)
	public ModelAndView saveToCatalogueAfterReceive(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("bcode") String purchaseOrderDetailsCode) {
		String strView = "";
		try {
			String bookName = request.getParameter("bookName"+ purchaseOrderDetailsCode);
			String bookEdition = request.getParameter("edition"+ purchaseOrderDetailsCode);
			String copies = request.getParameter("qtyCat"+ purchaseOrderDetailsCode);
			String bookPublisher = request.getParameter("publisher"+ purchaseOrderDetailsCode);
			String bookAuthor[] = request.getParameterValues("author"+ purchaseOrderDetailsCode);
			String amount = request.getParameter("amount"+ purchaseOrderDetailsCode);
			String genre = request.getParameter("genre"+ purchaseOrderDetailsCode);
			Book book = libraryService.getBookCodeForBookName(bookName.trim());
			if(book==null){
				 book = new Book();
			}
			book.setBookObjectId(purchaseOrderDetailsCode);
			book.setBookName(bookName);
			book.setBookEdition(bookEdition);
			book.setTotalNumberOfBookCopies((int) (Double.parseDouble(copies.trim())));
			BookPublisher bP = new BookPublisher();
			bP.setBookPublisherName(bookPublisher);
			book.setBookPublisher(bP);
			book.setPrice(Double.parseDouble(amount));
			book.setGenreId(genre);
			List<Author> authorList = new ArrayList<Author>();
			bookAuthor= bookAuthor[0].split("~");
			if (bookAuthor != null && bookAuthor.length != 0) {
				for (int i = 0; i < bookAuthor.length; i++) {
					Author a = new Author();
					a.setAuthorFullName(bookAuthor[i]);
					authorList.add(a);
				}
			}
			book.setBookAuthorList(authorList);
			model.addAttribute("book", book);
			List<BookLanguage> bookLanguageList = libraryService.getbookLanguageList();
			//List<Genre> genreList = libraryService.getGenreList();
			model.addAttribute("bookLanguageList", bookLanguageList);
			//model.addAttribute("genreList", genreList);
			strView = "library/addBookAfterReceive";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView(strView);
	}

	/**@author anup.roy
	 * this method submits the data to add new book to catalogue
	 * library new**/
	
	@RequestMapping(value = "/addNewBookToCat", method = RequestMethod.POST)
	public ModelAndView addNewBookToCat(HttpServletRequest request,
										HttpServletResponse response,
										ModelMap model,
										Book book,
										Author author,
										BookMedium bookMedium,
										BookLanguage bookLanguage,
										BookPublisher bookPublisher,
										BindingResult binding,
										Genre genre,
										@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			if (binding.hasErrors()) {
				return purchaseOrderDetailsList(request, response, model);
			}
			libraryService.updateAddedToCatalogue(book);
			List<Author> authorNameList = new ArrayList<Author>();
			book.setBookMedium(bookMedium);
			book.setBookLanguage(bookLanguage);
			book.setBookPublisher(bookPublisher);
			book.setUpdatedBy(sessionObject.getUserId());
			String[] strArrAuthorName = null;
			strArrAuthorName = request.getParameterValues("authorFullName");
			for (int i = 0; i < (((strArrAuthorName.length))); i++) {
				String authorName = strArrAuthorName[i];
				Author authorObj = new Author();
				authorObj.setAuthorFullName(authorName);
				authorNameList.add(authorObj);
			}
			book.setBookAuthorList(authorNameList);
			book.setGenre(genre);
			//String returnStatus = libraryService.addNewBook(book, sessionObject.getUserId());
			//logger.info("in addNewBookToCat()POST method Of LibraryController: returnStatus: "+ returnStatus);
		} catch (Exception e) {
			logger.error("Exception in addNewBookToCat()POST method Of LibraryController",e);
		}
		return purchaseOrderDetailsList(request, response, model);
	}

	/**
	 * modified by sourav.bhadra 
	 * changes taken on 23062017
	 * @param request
	 * @param response
	 * @param model
	 * @param strRequisitionCode
	 * @param bookRequisition
	 * 
	 */
	@RequestMapping(value = "/createPurchaseOrderForBookRequisition", method = RequestMethod.GET)
	public ModelAndView createPurchaseOrderForBookRequisition(
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,
			@RequestParam("requisitionCode") String strRequisitionCode,
			BookRequisition bookRequisition) {
		String strView = null;
		BookRequisition requisitionDetails = null;
		try {
			logger.info("calling createPurchaseOrderForBookRequisition() GET method of LibraryController");
			requisitionDetails = libraryService.getRequisitionDetails(strRequisitionCode);
			List<Tax> taxList = financeService.getTaxPercentages();
			if(null != taxList){
				model.addAttribute("taxList", taxList);
			}
			if (requisitionDetails != null) {
				model.addAttribute("RequisitionDetails", requisitionDetails);
				strView = "library/bookRequisitionForCreatePurchaseOrder";
			} else {
				model.addAttribute("contactForm", "data not available...");
				model.addAttribute("checkMessage", "checkmessage");
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			logger.error(
					"Exception in createPurchaseOrderForBookRequisition() GET method Of LibraryController",
					e);
		}
		return new ModelAndView(strView);

	}

	@RequestMapping(value = "/getVendorsForCreatePurchaseOrder", method = RequestMethod.GET)
	public @ResponseBody
	String getVendorsForCreatePurchaseOrder(
			@RequestParam("strBookName") String bookName,
			@RequestParam("strAuthorName") String authorName, Book book) {
		List<Vendor> vendorList = null;
		String authorStr = "";
		try {
			logger.info("getVendorsForCreatePurchaseOrder()In LibraryController.java: calling getAllVenoderDetailsDBForBook( String strQuery) in BackOfficeService.java");
			book.setBookName(bookName.trim());
			book.setBookDesc(authorName.trim());
			vendorList = libraryService.getAllVenoderDetailsDBForBook(book);
			if (vendorList != null && vendorList.size() != 0) {
				for (Vendor vendor : vendorList) {
					authorStr = vendor.getVendorCode() + ","
							+ vendor.getVendorName() + ","
							+ vendor.getVendorSellingRate() + "@" + authorStr;
				}
			}
		} catch (NullPointerException e) {
			logger.error("getAllBookDetails() In LibraryController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getAllBookDetails() In LibraryController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(authorStr));
	}

	/**
	 * @author sayani.datta this method creates new purchase order
	 * @param purchaseOrder
	 * @param request
	 * @param response
	 * @param model
	 * @return listOrders.jsp
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/submitBookPurchaseOrder", method = RequestMethod.POST)
	public ModelAndView createPurchaseOrder(HttpServletRequest request,
											HttpServletResponse response, ModelMap model,
											@RequestParam("bookRequisitionCode") String bookRequisitionCode,
											@RequestParam("vendorCode") String[] vendorCode,
											@RequestParam("individualTotOrder") String[] individualTotOrder,
											@RequestParam("totalPrice") String[] totalPrice,
											@RequestParam("bookName") String[] bookName,
											@RequestParam("genre") String[] genre,/* added by sourav.bhadra on 13-09-2017 */
											@RequestParam("bookAuthorName") String[] bookAuthorName,
											@RequestParam("bookPublisher") String[] bookPublisher,
											@RequestParam("bookEdition") String[] bookEdition,
											@RequestParam("individualPrice") String[] individualPrice,
											@RequestParam("individualDiscount") String[] individualDiscount,
											@RequestParam("tax") String[] tax,
											BookPurchaseOrder bookPurchaseOrderObj,
											@ModelAttribute("sessionObject") SessionObject sessionObject) {
		
		try {
			//System.out.println(vendorCode+"--"+bookRequisitionCode);
			logger.info("In  createPurchaseOrder() method of LibraryController");
			List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList = new ArrayList<BookPurchaseOrderDetails>();
			List<Vendor> vendorList = new ArrayList<Vendor>();
			@SuppressWarnings("rawtypes")
			List<String> list = new ArrayList(Arrays.asList(vendorCode));
			@SuppressWarnings("rawtypes")
			HashSet hs = new HashSet();
			hs.addAll(list);
			list.clear();
			list.addAll(hs);
			String[] vendorCode2 = list.toArray(new String[list.size()]);
			int totOrd = 0;
			double totPrc = 0.0;
			for (int j = 0; j < vendorCode.length; j++) {
				BookPurchaseOrderDetails bookPurchaseOrderDetails = new BookPurchaseOrderDetails();
				bookPurchaseOrderDetails.setUpdatedBy(sessionObject.getUserId());
				bookPurchaseOrderDetails.setVendor(vendorCode[j]);
				bookPurchaseOrderDetails.setBookName(bookName[j].trim());
				bookPurchaseOrderDetails.setItemCode(genre[j].trim()); /* added by sourav.bhadra on 13-09-2017 */
				bookPurchaseOrderDetails.setAuthorName(bookAuthorName[j].trim());
				bookPurchaseOrderDetails.setPublisherName(bookPublisher[j].trim());
				bookPurchaseOrderDetails.setEdition(bookEdition[j].trim());
				double doubleindividualTotOrder = Double.parseDouble(individualTotOrder[j]);
				bookPurchaseOrderDetails.setQtyOrdered(doubleindividualTotOrder);
				bookPurchaseOrderDetails.setQtyDeficit(doubleindividualTotOrder);
				bookPurchaseOrderDetails.setQtyReceived(0);
				double doubleIndividualPrice = Double.parseDouble(individualPrice[j]);
				bookPurchaseOrderDetails.setRate(doubleIndividualPrice);
				double doubletotalPrice = Double.parseDouble(totalPrice[j]);
				bookPurchaseOrderDetails.setAmount(doubletotalPrice);
				double doubleIndividualDiscount = Double.parseDouble(individualDiscount[j]);
				bookPurchaseOrderDetails.setDiscount(doubleIndividualDiscount);
				double doubletax = Double.parseDouble(tax[j]);
				bookPurchaseOrderDetails.setTax(doubletax);
				bookPurchaseOrderDetailsList.add(bookPurchaseOrderDetails);
			}
			for (int i = 0; i < vendorCode2.length; i++) {
				for (int j = 0; j < vendorCode.length; j++) {
					if (vendorCode2[i].equalsIgnoreCase(vendorCode[j])) {
						totOrd = totOrd+ Integer.parseInt(individualTotOrder[j]);
						totPrc = totPrc + Double.parseDouble(totalPrice[j]);
					}
				}
				Vendor vendor = new Vendor();
				vendor.setVendorCode(vendorCode2[i]);
				vendor.setVendorSellingRate(totPrc);
				vendor.setQuantityOrdered(totOrd);
				vendorList.add(vendor);
				totPrc = 0.0;
				totOrd = 0;
			}
			bookPurchaseOrderObj.setBookRequisition(bookRequisitionCode);
			bookPurchaseOrderObj.setVendor(vendorList);
			bookPurchaseOrderObj.setBookPurchaseOrderDetailsList(bookPurchaseOrderDetailsList);
			List<BookPurchaseOrder> bookpurchaseOrderList = libraryService.getpurchaseOrderForBook(bookPurchaseOrderObj, sessionObject.getUserId());
			logger.info("createPurchaseOrder() In PurchaseController.java: return bookpurchaseOrderList: "+ bookpurchaseOrderList);
		} catch (NullPointerException e) {
			logger.error("createPurchaseOrder() In PurchaseController.java: NullPointerException"+ e);
		} catch (Exception e) {
			logger.error("createPurchaseOrder() In PurchaseController.java: Exception"+ e);
		}
		return viewRequisition(request, response, model);
	}

	@RequestMapping(value = "/getVendorName", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getVendorName(@RequestParam("term") String strQuery) {
		List<String> vendorNameList = null;
		try {
			logger.info("getVendorName()In LibraryController.java: calling getBookName( String strQuery) in BackOfficeService.java");
			vendorNameList = libraryService.getVendorName(strQuery);
		} catch (Exception e) {
			logger.error("getVendorName() In LibraryController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(vendorNameList));
	}
	
	
	/**
	 * FOR NEW AUTOCOMPLETE
	 * */
	
	@RequestMapping(value = "/getNameOfVendors", method = RequestMethod.GET)
	public @ResponseBody String getNameOfVendors() {
		String nameOfVendors = null;
		try {
			logger.info("getNameOfVendors()In LibraryController.java: calling getBookName( String strQuery) in BackOfficeService.java");
			nameOfVendors = libraryService.getNameOfVendors();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getNameOfVendors() In LibraryController.java: Exception"+ e);
		}
		return (new Gson().toJson(nameOfVendors.toString()));
	}
	
	@RequestMapping(value = "/getIdAgainstName", method = RequestMethod.GET)
	public @ResponseBody String getIdAgainstName(@RequestParam("vendorName") String vendorName, Vendor vendor) {
		String idAgainstName = null;
		try {
			vendor.setVendorName((vendorName).trim());
			logger.info("getVendorName()In LibraryController.java: calling getBookName( String strQuery) in BackOfficeService.java");
			idAgainstName = libraryService.getIdAgainstName(vendor);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getIdAgainstName() In LibraryController.java: Exception"+ e);
		}
		return (new Gson().toJson(idAgainstName));
	}

	@RequestMapping(value = "/getEdition", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getEdition(@RequestParam("term") String strQuery) {
		List<String> editionList = null;
		try {
			logger.info("getEdition()In LibraryController.java: calling getBookName( String strQuery) in BackOfficeService.java");
			editionList = libraryService.getEditionDB(strQuery);
		} catch (Exception e) {
			logger.error("getEdition() In LibraryController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(editionList));
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param strRequisitionCode
	 * @param bookRequisition
	 * @return
	 */
	@RequestMapping(value = "/paymentBookRequisition", method = RequestMethod.GET)
	public ModelAndView paymentBookRequisition(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("requisitionCode") String strRequisitionCode,
			BookRequisition bookRequisition) {
		String strView = null;
		try {
			logger.info("calling paymentBookRequisition() GET method of LibraryController");
			bookRequisition.setBookRequisitionCode(strRequisitionCode);
			bookRequisition = libraryService
					.getBookRequisitionDetails(bookRequisition);
			if (bookRequisition != null) {
				model.addAttribute("bookRequisition", bookRequisition);
				strView = "library/bookRequisitionPayment";
			} else {
				model.addAttribute("ResultError", "data not available...");
				model.addAttribute("checkMessage", "checkmessage");
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			logger.error(
					"Exception in paymentBookRequisition() GET method Of LibraryController",
					e);
		}
		return new ModelAndView(strView);
	}

	@RequestMapping(value = "/paymentBookRequisition", method = RequestMethod.POST)
	public ModelAndView savePaymentForBookRequisition(HttpServletRequest request,
														HttpServletResponse response,
														ModelMap model,
														BookRequisition bookRequisition,
														@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
		try {
			logger.info("calling savePaymentForBookRequisition() GET method of LibraryController");
			List<BookRequisition> bookRequisitionList = libraryService
					.savePaymentForBookRequisition(bookRequisition, sessionObject.getUserId());
			if (bookRequisitionList != null && bookRequisitionList.size() != 0) {
				model.addAttribute("bookRequisitionList", bookRequisitionList);
				strView = "library/requisitionList";
			} else {
				model.addAttribute("ResultError", "data not available...");
				model.addAttribute("checkMessage", "checkmessage");
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			logger.error(
					"Exception in savePaymentForBookRequisition() GET method Of LibraryController",
					e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addToCatalog", method = RequestMethod.GET)
	public ModelAndView addToCatalog(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("requisitionCode") String strRequisitionCode,
			@RequestParam("bookName") String strBookName,
			@RequestParam("receivedCopy") String strReceivedCopy,
			@RequestParam("receivedCode") String strReceivedId,
			BookRequisitionDetails bookRequisitionDetails) {
		String strView = null;
		try {
			logger.info("calling addToCatalog() GET method of LibraryController");
			BookStatus bs = null;
			if (strRequisitionCode != null && strRequisitionCode != "") {
				bookRequisitionDetails
						.setBookRequisitionCode(strRequisitionCode);
			}
			if (strBookName != null && strBookName != "") {
				bookRequisitionDetails.setBookName(strBookName);
			}
			if (strReceivedCopy != null && strReceivedCopy != "") {
				bookRequisitionDetails.setNumberOfBooksReceived(Integer
						.parseInt(strReceivedCopy));
			}
			if (strReceivedId != null && strReceivedId.length() != 0) {
				bookRequisitionDetails.setNumberOfBooksDeficit(Integer
						.parseInt(strReceivedId));
			}
			bs = libraryService.addToCatalog(bookRequisitionDetails);
			if (bs != null) {
				bs.setReceivedDateDetailsId(strReceivedId);
				model.addAttribute("BookStatus", bs);
				strView = "library/addBook";
			} else {
				model.addAttribute("ResultError", "data not available...");
				model.addAttribute("checkMessage", "checkmessage");
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			logger.error(
					"Exception in addToCatalog() GET method Of LibraryController",
					e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * 
	 * 
	 * @param requisitionCode
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/receiveRequisitionDetails", method = RequestMethod.GET)
	public ModelAndView requisitionDetails(
			@RequestParam("requisitionCode") String requisitionCode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		String strView = null;
		BookRequisition requisitionDetails = null;
		try {
			logger.info("calling receiveRequisitionDetails() GET method of LibraryController");
			requisitionDetails = libraryService
					.getRequisitionDetails(requisitionCode);
			if (requisitionDetails != null) {
				model.addAttribute("RequisitionDetails", requisitionDetails);
				strView = "library/receiveRequisition";
			} else {
				model.addAttribute("contactForm", "data not available...");
				model.addAttribute("checkMessage", "checkmessage");
				strView = "library/errorpage";
			}
		} catch (Exception e) {
			logger.error(
					"Exception in receiveRequisitionDetails() GET method Of LibraryController",
					e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * This requisitionFulfillment() method is related to get requisition list
	 * and return to requisitionList.jsp page with populated data.
	 * 
	 * @param String
	 * @param String
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param BookRequisitionDetails
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/requisitionFulfillment", method = RequestMethod.POST)
	public ModelAndView requisitionFulfillment(@RequestParam("numberOfBooks") String[] numberOfBooks,
												@RequestParam("bookName") String[] bookName,
												HttpServletRequest request,
												HttpServletResponse response,
												ModelMap model,
												BookRequisitionDetails bookRequisitionDetails,
												ArrayList<BookRequisitionDetails> bookRequisitionDetailsList,
												@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String strView = null;
		List<BookRequisition> bookRequisitionListFromDB = null;
		try {
			logger.info("calling requisitionFulfillment() method of LibraryController");
			for (int i = 0; i < (((numberOfBooks.length))); i++) {
				BookRequisitionDetails bookRequisitionDetail = new BookRequisitionDetails();
				if (numberOfBooks[i].equals("")) {
					numberOfBooks[i] = "0";
				}
				int intNoOfBookCopies = Integer.parseInt(numberOfBooks[i]);
				bookRequisitionDetail
						.setNumberOfBooksReceived(intNoOfBookCopies);
				bookRequisitionDetail.setUpdatedBy(sessionObject.getUserId());
				bookRequisitionDetail.setBookName(bookName[i].trim());
				bookRequisitionDetail
						.setBookRequisitionCode(bookRequisitionDetails
								.getBookRequisitionCode().trim());
				bookRequisitionDetailsList.add(bookRequisitionDetail);
			}
			bookRequisitionListFromDB = libraryService
					.getRequisitionFulfillment(bookRequisitionDetailsList);
			if (bookRequisitionListFromDB != null
					&& bookRequisitionListFromDB.size() != 0) {
				model.addAttribute("bookRequisitionList",
						bookRequisitionListFromDB);
				strView = "library/requisitionList";
			} else {
				model.addAttribute("contactForm", "data not available...");
				model.addAttribute("checkMessage", "checkmessage");
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			logger.error(
					"Exception in requisitionFulfillment() method Of LibraryController",
					e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * This viewRequisitionDetails() method is related to get Requisition
	 * details and return to viewRequisition.jsp page with populated data.
	 * 
	 * @param String
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param BookRequisition
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/viewRequisitionDetails.html", method = RequestMethod.GET)
	public ModelAndView viewRequisitionDetails(
			@RequestParam("requisitionCode") String requisitionCode,
			@RequestParam("requisitionStatus") String strRequisitionStatus,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model, BookRequisition bookRequisition) {
		String strView = null;
		BookRequisition viewBookRequisitionList = null;
		try {
			logger.info("calling viewRequisitionDetails() method of LibraryController");
			bookRequisition.setBookRequisitionCode(requisitionCode.trim());
			bookRequisition.setBookRequisitionStatus(strRequisitionStatus
					.trim());
			viewBookRequisitionList = libraryService
					.viewRequisitionDetails(bookRequisition);
			if (viewBookRequisitionList != null) {
				model.addAttribute("viewBookRequisitionList",
						viewBookRequisitionList);
				strView = "library/viewRequisition";
			} else {
				model.addAttribute("contactForm", "data not available...");
				strView = "common/errorpage";
			}
		} catch (Exception e) {
			logger.error(
					"Exception in viewRequisitionDetails() method Of LibraryController",
					e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * This getSearchList() method is related to get Requisition list on search
	 * value and return to requisitionList.jsp page with populated data.
	 * 
	 * @param HttpServletRequest
	 * @param HttpServletResponse
	 * @param ModelMap
	 * @param BookRequisition
	 * @return ModelAndView
	 * @throws ParseException
	 */

@RequestMapping(value = "/searchRequisition")
	public ModelAndView getSearchList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			BookRequisition bookRequisition) throws ParseException {
		String strView = null;
		try {
			String strKey = request.getParameter("query");
			String strValue = request.getParameter("data").trim();
			logger.info("calling getSearchList()of searchRequisition method of LibraryController with searching parameter. Key:="
					+ strKey + " and Value:=" + strValue);
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.clear();
			parameters.put("RequisitionID", null);
			parameters.put("Publisher", null);
			parameters.put("OpenDate", null);
			parameters.put("CloseDate", null);
			parameters.put("Status", null);
			parameters.put("Book", null);
			if (strKey.equalsIgnoreCase("Requisition ID")) {
				parameters.put("RequisitionID", strValue);
			}
			if (strKey.equalsIgnoreCase("Publisher")) {
				parameters.put("Publisher", strValue);
			}
			if (strKey.equalsIgnoreCase("Open Date")) {
				DateUtility util = new DateUtility();
				Integer epoch = util.humanReadableFormatToEpoch(strValue);
				parameters.put("OpenDate", epoch);
			}
			if (strKey.equalsIgnoreCase("Close Date")) {
				DateUtility util = new DateUtility();
				Integer epoch = util.humanReadableFormatToEpoch(strValue);
				parameters.put("CloseDate", epoch);
			}
			if (strKey.equalsIgnoreCase("Status")) {
				parameters.put("Status", strValue);
			}
			if (strKey.equalsIgnoreCase("Book")) {
				parameters.put("Book", strValue);
			}
			List<BookRequisition> requisitionSearchList = libraryService.getRequisitionSearchList(parameters);
			if (requisitionSearchList.size() != 0 && requisitionSearchList != null) {
				PagedListHolder<BookRequisition> pagedListHolder = libraryService.viewRequisitionListPaging(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", pagedListHolder);
				strView = "library/requisitionList";
			}
		} catch (Exception e) {
			logger.error("Exception in getSearchList() of searchRequisition method Of LibraryController", e);
		}
		return new ModelAndView(strView);
	}

	/**
	 * @param request
	 * @param response
	 * @param model
	 * @param book
	 * @return ModelAndView
	 * saif.ali 03072017
	 */

	/*@RequestMapping(value = "/createLodgingRequest", method = RequestMethod.GET)
	public ModelAndView createLodgingRequest(HttpServletRequest request, HttpServletResponse response,
											 ModelMap model, Book book,
											 @ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("calling createLodgingRequest() method of LibraryController");
		ModelAndView mav = new ModelAndView("library/createLodgingRequest");
		//String role= null;
		try {
			List<Book> bookList = libraryService.createLodgingRequest();
			String role= sessionObject.getCurrentRoleOrAccess();
			model.addAttribute("role", role);			
		} catch (Exception e) {
			logger.error("Exception in createLodgingRequest() method Of LibraryController",	e);
		}
		return lodgingRequestPagination( request, response,sessionObject);				
	}*/

	
	/**
	 * @author anup.roy
	 * this method is for getting the list of books
	 * also the book request creation page*/
	
	@RequestMapping(value = "/createLodgingRequest", method = RequestMethod.GET)
	public ModelAndView createLodgingRequest(HttpServletRequest request, HttpServletResponse response,
											 ModelMap model, Book book,
											 @ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("In createLodgingRequest()-GET method of LibraryController");
		String mav = null;
		try {
			List<Book> bookList = libraryService.createLodgingRequest();
			Integer maxNoOfBooksPerRequest = libraryService.getMaxNoOfBooksPerRequest(sessionObject.getResourceTpye());
			if(maxNoOfBooksPerRequest != null){
				model.addAttribute("maxNoOfBooksPerRequest", maxNoOfBooksPerRequest);
			}
			String lastBookRequestId = libraryService.getLastRequestId();
			model.addAttribute("lastBookRequestId", lastBookRequestId);
			model.addAttribute("bookList", bookList);
			mav = "library/createLodgingRequest";
		} catch (Exception e) {
			logger.error("Exception in createLodgingRequest()-GET method Of LibraryController",	e);
			e.printStackTrace();
		}
		return new ModelAndView(mav);
	}

	//unnecessary
	@RequestMapping(value = "/lodgingRequestPagination", method = RequestMethod.GET)
	public ModelAndView lodgingRequestPagination(HttpServletRequest request, HttpServletResponse response,
											@ModelAttribute("sessionObject") SessionObject sessionObject) {
		ModelAndView mav = null;
		try {
			logger.info("In lodgingRequestPagination() method of LibraryController");
			mav = new ModelAndView("library/createLodgingRequest");
			//Integer maxNoOfBooksPerRequest = libraryService.getMaxNoOfBooksPerRequest(sessionObject.getUserId());
			Integer maxNoOfBooksPerRequest = libraryService.getMaxNoOfBooksPerRequest(sessionObject.getResourceTpye());
			//System.out.println("maxNoOfBooksPerRequest for"+sessionObject.getUserId()+"is::>>"+maxNoOfBooksPerRequest);
			if(maxNoOfBooksPerRequest != null){
				mav.addObject("maxNoOfBooksPerRequest", maxNoOfBooksPerRequest);
			}
			String lastBookRequestId = libraryService.getLastRequestId();
			mav.addObject("lastBookRequestId", lastBookRequestId);
			logger.info("In LibraryController lodgingRequestPagination() method: calling getBookListPaging(HttpServletRequest request) in LibraryService.java");
			PagedListHolder<Book> pagedListHolder = libraryService.getLodgingRequestListPaging(request);
			//System.out.println(pagedListHolder.get(0).getBookRequest().getBookRequestCode());
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController lodgingRequestPagination() method for Exception: ", e);
		}
		return mav;
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param book
	 * @return
	 * @throws ParseException
	 */


@RequestMapping(value = "/searchLodgingRequest", method = RequestMethod.POST)
	public ModelAndView searchLodgingRequest(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Book book,
			HashMap<String, Object> parameters) throws ParseException {
		String strView = null;
		try {
			String strKey = request.getParameter("query");
			String strValue = request.getParameter("data").trim();
			logger.info("calling getSearchList()of searchLodgingRequest method of LibraryController with searching parameter. Key:="
					+ strKey + " and Value:=" + strValue);
			parameters.clear();
			if (strKey.equalsIgnoreCase("BookCode")) {
				parameters.put("BookCode", strValue);
			}
			if (strKey.equalsIgnoreCase("BookName")) {
				parameters.put("BookName", strValue);
			}
			if (strKey.equalsIgnoreCase("Author")) {
				parameters.put("Author", strValue);
			}
			if (strKey.equalsIgnoreCase("Publisher")) {
				parameters.put("Publisher", strValue);
			}
			if (strKey.equalsIgnoreCase("Edition")) {
				parameters.put("Edition", strValue);
			}
			List<Book> bookList = libraryService.searchLodgingRequest(parameters);
			if (bookList != null && bookList.size() != 0) {
				PagedListHolder<Book> pagedListHolder = libraryService.getLodgingRequestListPaging(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", pagedListHolder);
				strView = "library/createLodgingRequest";
			}
		} catch (Exception e) {
			logger.error("Exception in searchLodgingRequest() method Of LibraryController",e);
		}
		return new ModelAndView(strView);
	}
	/**
	 * @param request
	 * @param response
	 * @param model
	 * @param book
	 * @return ModelAndView
	 * saif.ali 03072017
	 */

	/*@RequestMapping(value = "/submitLodgingRequest", method = RequestMethod.POST)
	public ModelAndView submitLodgingRequest(
			@RequestParam("bookRequestBookCode") String[] strBookCode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,
			@RequestParam(value = "newAppliedBy", required = false) String newAppliedBy,
			@ModelAttribute("bookRequest") BookRequest bookRequest,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("calling submitLodgingRequest() method of LibraryController");
		List<BookRequestDetails> bookRequestDetailsList = new ArrayList<BookRequestDetails>();
		try {
			if(strBookCode != null && strBookCode.length!=0){
				for (int i = 0; i < strBookCode.length; i++) {
					BookRequestDetails bookCode = new BookRequestDetails();
					bookCode.setBookCode(strBookCode[i]);
					bookRequestDetailsList.add(bookCode);
				}
				if(null == newAppliedBy)
				{
					bookRequest.setUpdatedBy(sessionObject.getUserId());
				}
				if(null !=newAppliedBy ){
					bookRequest.setUpdatedBy(newAppliedBy);
				}
				bookRequest.setNumberOfBookRequested(bookRequestDetailsList.size());
				bookRequest.setBookRequestDetailsList(bookRequestDetailsList);
				Integer  requestStatus= libraryService.submitLodgingRequest(bookRequest);
			}
		} catch (Exception e) {
			logger.error("Exception in submitLodgingRequest() method Of LibraryController",e);
		}
		return createLodgingRequest(request,response,model,new Book(),sessionObject);
	}*/

	@RequestMapping(value = "/submitLodgingRequest", method = RequestMethod.POST)
	public ModelAndView submitLodgingRequest(
			@RequestParam("bookRequestBookCode") String[] strBookCode,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model,
			@ModelAttribute("bookRequest") BookRequest bookRequest,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("calling submitLodgingRequest() method of LibraryController");
		List<BookRequestDetails> bookRequestDetailsList = new ArrayList<BookRequestDetails>();
		try {
			if(strBookCode != null && strBookCode.length!=0){
				for (int i = 0; i < strBookCode.length; i++) {
					BookRequestDetails bookCode = new BookRequestDetails();
					bookCode.setBookCode(strBookCode[i]);
					bookRequestDetailsList.add(bookCode);
				}
				bookRequest.setUpdatedBy(sessionObject.getUserId());
				bookRequest.setNumberOfBookRequested(bookRequestDetailsList.size());
				bookRequest.setBookRequestDetailsList(bookRequestDetailsList);
				Integer requestStatus= libraryService.submitLodgingRequest(bookRequest);
			}
		} catch (Exception e) {
			logger.error("Exception in submitLodgingRequest() method Of LibraryController",e);
		}
		return createLodgingRequest(request,response,model,new Book(),sessionObject);
	}

	/**
	 * @author anup.roy
	 * this method is for showing the list of requests for book
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */

@RequestMapping(value = "/bookAllocation", method = RequestMethod.GET)
	public ModelAndView bookAllocationt(HttpServletRequest request,	HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("library/bookAllocation");
		try {
			logger.info("In bookAllocation() method of LibraryController.java");
			List<BookRequest> bookRequestIdList = libraryService.getBookRequestDetails();
			if (bookRequestIdList != null && bookRequestIdList.size() != 0) {
				mav.addObject("bookRequestIdList", bookRequestIdList);
			}
		} catch (Exception e) {
			logger.error("Exception in bookAllocation() method Of LibraryController", e);
			e.printStackTrace();
		}
		return mav;
	}
	
	//unnecessasry
	@RequestMapping(value = "/bookAllocationListPagination", method = RequestMethod.GET)
	public ModelAndView bookAllocationListPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In bookAllocationListPagination() method of LibraryController");
			mav = new ModelAndView("library/bookAllocation");
			logger.info("In LibraryController bookAllocationListPagination() method: calling getBookAllocationListPaging(HttpServletRequest request) in LibraryService.java");
			PagedListHolder<BookRequest> pagedListHolder = libraryService.getBookAllocationListPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController bookAllocationListPagination() method for Exception: ", e);
		}
		return mav;
	}

	/**
	 * 
	 * @param strKey
	 * @param strValue
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws ParseException
	 */

@RequestMapping(value = "/searchForBookRequest", method = RequestMethod.POST)
	public ModelAndView searchForBookRequest(
			@RequestParam("query") String strKey,
			@RequestParam("data") String strValue, HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			HashMap<String, Object> parameters) throws ParseException {
		String strView = null;
		try {
			logger.info("calling searchForBookRequest method of LibraryController with searching parameter. Key:="+ strKey + " and Value:=" + strValue);
			parameters.clear();
			if (strKey.equalsIgnoreCase("RequestID")) {
				parameters.put("RequestID", strValue.trim());
			}
			if (strKey.equalsIgnoreCase("UserID")) {
				parameters.put("UserID", strValue.trim());
			}
			if (strKey.equalsIgnoreCase("RequestedDate")) {
				DateUtility util = new DateUtility();
				Integer epoch = util.humanReadableFormatToEpoch(strValue.trim());
				parameters.put("RequestedDate", epoch);
			}
			if (strKey.equalsIgnoreCase("RequestExpireDate")) {
				DateUtility util = new DateUtility();
				Integer epoch = util.humanReadableFormatToEpoch(strValue.trim());
				parameters.put("RequestExpireDate", epoch);
			}
			if (strKey.equalsIgnoreCase("Status")) {
				parameters.put("Status", strValue.trim());
			}
			List<BookRequest> bookRequestList = libraryService.searchForBookRequest(parameters);
			if (bookRequestList != null && bookRequestList.size() != 0) {
				PagedListHolder<BookRequest> pagedListHolder = libraryService.getBookAllocationListPaging(request);
				model.addAttribute("first", pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last", pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Exception in bookAllocation() method Of LibraryController", e);
		}
		return new ModelAndView("library/bookAllocation");
	}

	/**
	 * @author anup.roy
	 * this method is for issue the book to the resource
	 * @param requestedBookId
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */
	
	@RequestMapping(value = "/issuingBookForBookAllocation", method = RequestMethod.GET)
	public ModelAndView issuingBookForBookAllocation(@RequestParam("requestId") String requestedBookId,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("library/issuingBookForBookAllocation");
		try {
			logger.info("In issuingBookForBookAllocation() method of LibraryController");
			BookRequest bookRequest = libraryService.getRequestedBookDetails(requestedBookId);
			if (bookRequest != null){
				if(bookRequest.getStatus()!=null && bookRequest.getStatus().equals("exceed")){
					mav.addObject("message", "User already allocated max number of books as per Library Policy");
				}
				List<BookRequestDetails> bookRequestDetailsList = bookRequest.getBookRequestDetailsList();
				if (bookRequestDetailsList != null && bookRequestDetailsList.size() != 0) {
					mav.addObject("bookRequestDetailsList", bookRequestDetailsList);
				}
				List<BookAllocation> bookAllocationList = bookRequest.getBookAllocationList();
				if (bookAllocationList != null && bookAllocationList.size() != 0) {
					mav.addObject("bookAllocationList", bookAllocationList);
				}
				mav.addObject("BookRequestResult", bookRequest);
			}
		} catch (Exception e) {
			logger.error("Exception in issuingBookForBookAllocation() method Of LibraryController",	e);
			e.printStackTrace();
		}
		return mav;
	}
	
	
	@RequestMapping(value = "/issuingBookListPagination", method = RequestMethod.GET)
	public ModelAndView issuingBookListPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In issuingBookListPagination() method of LibraryController");
			mav = new ModelAndView("library/issuingBookForBookAllocation");
			logger.info("In LibraryController issuingBookListPagination() method: calling getIssuingBookListPaging(HttpServletRequest request) in LibraryService.java");
			PagedListHolder<BookRequestDetails> pagedListHolder = libraryService.getIssuingBookListPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController issuingBookListPagination() method for Exception: ", e);
		}
		return mav;
	}

	/**
	 * 
	 * @param strAllocatedBookId
	 * @param request
	 * @param response
	 * @param model
	 * @param bookRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/submitIssuingBookForBookAllocation", method = RequestMethod.POST)
	public ModelAndView submitIssuingBookForBookAllocation(@RequestParam("allocatedBookId") String[] strAllocatedBookId,
															@RequestParam("returnDate") String[] strReturnDate,
															HttpServletRequest request,
															HttpServletResponse response,
															ModelMap model,
															@ModelAttribute("resource") Resource resource,
															@ModelAttribute("bookAllocation") BookAllocation bookAllocation,
															@ModelAttribute("sessionObject") SessionObject sessionObject) {
			Book objBook = null;
			List<BookId> bookIdList = new ArrayList<BookId>();
			try {
				logger.info("calling submitIssuingBookForBookAllocation() method of LibraryController");
				if(strAllocatedBookId != null && strAllocatedBookId.length != 0){
					for(int i = 0; i < (((strAllocatedBookId.length))); i++) {
						BookId bookId = new BookId();
						if (strAllocatedBookId[i]!=null && strAllocatedBookId[i].trim().length()!=0) {
							//System.out.println("book: "+strAllocatedBookId[i]+" date: "+strReturnDate[i]);
							bookId.setBookRetirementDate(strReturnDate[i]);
							bookId.setBookId(strAllocatedBookId[i]);
							bookIdList.add(bookId);
						}
				     } 
					objBook = new Book();
					objBook.setBookIdList(bookIdList);
					bookAllocation.setBook(objBook);
					bookAllocation.setUpdatedBy(sessionObject.getUserId());
					bookAllocation.setBookIssuedTo(resource);
			     }
			List<BookRequest> bookRequestIdList = libraryService.submitIssuingBookForBookAllocation(bookAllocation);
		} catch (Exception e) {
			logger.error("Exception in submitIssuingBookForBookAllocation() method Of LibraryController",e);
		}
		return bookAllocationt(request,	response, model);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return String
	 */
	@RequestMapping(value = "/userIdForReturnBook", method = RequestMethod.GET)
	public String userIdForReturnBook(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("calling userIdForReturnBook() method of LibraryController");
		return "library/userForBookReturn";
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param resource
	 * @return
	 */

@RequestMapping(value = "/submitUserIdForReturnBook", method = RequestMethod.POST)
	public ModelAndView submitUserIdForReturnBook(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @ModelAttribute("resource") Resource resource) {
		logger.info("calling submitUserIdForReturnBook() method of LibraryController");
		ModelAndView mav = new ModelAndView("library/returnBookDetails");
		try {
			List<BookAllocation> bookAllocationList = libraryService.getIssuedBookDetails(resource);
			if (bookAllocationList != null && bookAllocationList.size() != 0) {
				model.addAttribute("bookAllocationList", bookAllocationList);
			} 
		} catch (Exception e) {
			logger.error("Exception in submitUserIdForReturnBook() method Of LibraryController", e);
		}
		return mav;
	}
	


	/**
	 * 
	 * @param strAllocatedBookId
	 * @param strBookCode
	 * @param strComment
	 * @param strRating
	 * @param request
	 * @param response
	 * @param model
	 * @param resource
	 * @param bookAllocation
	 * @return
	 */
	@RequestMapping(value = "/submitReturnBookDetails", method = RequestMethod.POST)
	public String submitReturnBookDetails(@RequestParam("allocatedBookId") String[] strAllocatedBookId,
											@RequestParam(required = false, value = "strBookCode") String strBookCode,
											@RequestParam(required = false, value = "strComment") String strComment,
											@RequestParam(required = false, value = "strRating") String strRating,
											HttpServletRequest request,
											HttpServletResponse response,
											ModelMap model,
											@ModelAttribute("resource") Resource resource,
											BookAllocation bookAllocation,
											ArrayList<BookAllocationDetails> bookAllocationDetailsList,
											@ModelAttribute("sessionObject") SessionObject sessionObject) {
		logger.info("calling submitReturnBookDetails() method of LibraryController");
		try {
			HashMap<String, Object> bookRatingMap = new HashMap<String, Object>();
			if (strAllocatedBookId != null && strAllocatedBookId.length != 0) {
				for (int i = 0; i < (((strAllocatedBookId.length))); i++) {
					String[] s = strAllocatedBookId[i].split("#");
					BookAllocationDetails bookAllocationDetails = new BookAllocationDetails();
					bookAllocationDetails.setBookId(s[0]);
					bookAllocationDetails.setBookAllocationDetailsCode(s[1]);// bookIdObjectId(contains	bookAllocationCode) set into BookAllocationDetailsCode
					bookAllocationDetails.setBookIssuedTo(resource.getUserId());
					bookAllocationDetails.setUpdatedBy(sessionObject.getUserId());
					bookAllocationDetailsList.add(bookAllocationDetails);
				}
			}
			String[] strArrayBookCode = strBookCode.split("`~`");
			String[] strArrayComment = strComment.split("`~`");
			String[] strArrayRating = strRating.split("`~`");
			if (strArrayRating != null && strArrayRating.length != 0) {
				for (int i = 0; i < strArrayRating.length; i++) {
					BookRating bookRating = new BookRating();
					if (strArrayBookCode != null && strArrayBookCode.length != 0) {
						bookRating.setBookRatingDesc(strArrayRating[i]);
						//System.out.println("RATING CONT: " + strArrayRating[i]);
						if (strArrayComment != null && strArrayComment.length != 0) {
							bookRating.setBookRatingComments(strArrayComment[i]);
						//	System.out.println("RatingComments: " + strArrayComment[i]);
						}
						bookRating.setUserId(resource.getUserId());
						bookRating.setUpdatedBy(sessionObject.getUserId());
						bookRatingMap.put(strArrayBookCode[i], bookRating);
					}
				}
			}
			libraryService.submitReturnBookDetails(bookAllocationDetailsList,bookRatingMap);
		} catch (Exception e) {
			logger.error("Exception in submitReturnBookDetails() method Of LibraryController",e);
			e.printStackTrace();
		}
		return "library/userForBookReturn";
	}

	@RequestMapping(value = "/getBookName", method = RequestMethod.GET)
	public @ResponseBody
	String getBookName() {
		List<Book> bookNameList = null;
		String bookNameString = null;
		try {
			logger.info("getBookName()In LibraryController.java: calling getBookName( String strQuery) in BackOfficeService.java");
			bookNameList = libraryService.getBookNameDB();
			StringBuilder sb = new StringBuilder();
			for(Book book : bookNameList){
				sb.append(book.getBookName()+"*");
				bookNameString = sb.toString().substring(0, sb.toString().length()-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getBookName() In LibraryController.java: Exception"+ e);
		}
		return (new Gson().toJson(bookNameString));
	}

	@RequestMapping(value = "/getAllBookDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getAllBookDetails(@RequestParam("bookName") String bookName) {
		Book book = null;
		String totalStr = "";
		try {
			logger.info("getAllBookDetails()In LibraryController.java: calling getBookName( String strQuery) in BackOfficeService.java");
			book = libraryService.getAllBookDetailsDB(bookName);
			if (book != null) {
				List<Author> authorList = book.getBookAuthorList();
				if (authorList != null && authorList.size() != 0) {
					String authorStr = "";
					for (Author a : authorList) {
						authorStr = a.getAuthorFullName() + "@~" + authorStr;
					}
					totalStr = book.getBookEdition() + "##~"
							+ book.getBookPublisher().getBookPublisherName()
							+ "%~" + authorStr;
				}
			}
		} catch (NullPointerException e) {
			logger.error("getAllBookDetails() In LibraryController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("getAllBookDetails() In LibraryController.java: Exception"
					+ e);
		}
		return (new Gson().toJson(totalStr));
	}

	/**
	 * This method is responsible for Open bookVendorList.
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return listMessVendors.jsp
	 */

@RequestMapping(value = "/bookVendorList", method = RequestMethod.GET)
	public ModelAndView bookVendorList(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In bookVendorList() method of LibraryController");
		ModelAndView mav = new ModelAndView("library/listBookVendors");		
		try {
			List<Vendor> vendorList = libraryService.bookVendorList();
			if (vendorList != null && vendorList.size() != 0) {
//				PagedListHolder<Vendor> pagedListHolder = libraryService.getBookVendorListPaging(request);
//				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
//				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
//				mav.addObject("total", pagedListHolder.getNrOfElements());
//				mav.addObject("pagedListHolder", pagedListHolder);
				model.addAttribute("vendorList", vendorList);
			}
		} catch (NullPointerException e) {
			logger.error("bookVendorList() In LibraryController.java: NullPointerException"	+ e);
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("bookVendorList() In LibraryController.java: Exception" + e);
			e.printStackTrace();
		}
		return mav;
	}

	@RequestMapping(value = "/listBookVendorPagination", method = RequestMethod.GET)
	public ModelAndView listBookVendorPagination(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In listBookVendorPagination() method of LibraryController");
		ModelAndView mav = new ModelAndView("library/listBookVendors");
		try {
			PagedListHolder<Vendor> pagedListHolder = libraryService.getBookVendorListPaging(request);
			mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
			mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
			mav.addObject("total", pagedListHolder.getNrOfElements());
			mav.addObject("pagedListHolder", pagedListHolder);
		} catch (NullPointerException e) {
			logger.error("listBookVendorPagination() In LibraryController.java: NullPointerException" + e);
		} catch (Exception e) {
			logger.error("listBookVendorPagination() In LibraryController.java: Exception" + e);
		}
		return mav;
	}
	

	@RequestMapping(value = "/issuedBookListPagination", method = RequestMethod.GET)
	public ModelAndView issuedBookListPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In issuedBookListPagination() method of LibraryController");
			mav = new ModelAndView("library/returnBookDetails");
			logger.info("In LibraryController issuedBookListPagination() method: calling getIssuedBookListPaging(HttpServletRequest request) in LibraryService.java");
			PagedListHolder<BookAllocation> pagedListHolder = libraryService.getIssuedBookListPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController issuedBookListPagination() method for Exception: ", e);
		}
		return mav;
	}
	
@RequestMapping(value = "/searchBookVendorList", method = RequestMethod.POST)
	public ModelAndView searchBookVendorList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			HashMap<String, Object> parameters) {
		logger.info("In  searchBookVendorList() method of LibraryController");
		ModelAndView mav = new ModelAndView("library/listBookVendors");
		try {
			String strKey = request.getParameter("query").trim();
			String strValue = request.getParameter("data").trim();
			if (strKey.equalsIgnoreCase("VendorCode")) {
				parameters.put("VendorCode", strValue);
			}
			if (strKey.equalsIgnoreCase("VendorName")) {
				parameters.put("VendorName", strValue);
			}
			if (strKey.equalsIgnoreCase("ContactNo1")) {
				//System.out.println("2");
				parameters.put("ContactNo1", strValue);
			}
			if (strKey.equalsIgnoreCase("ContactNo2")) {
				parameters.put("ContactNo2", strValue);
			}
			if (strKey.equalsIgnoreCase("Address")) {
				parameters.put("Address", strValue);
			}
			List<Vendor> vendorList = libraryService
					.getBookVendorSearchList(parameters);
			if (vendorList != null && vendorList.size() != 0) {
				PagedListHolder<Vendor> pagedListHolder = libraryService
						.getBookVendorListPaging(request);
				mav.addObject("first",
						pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last",
						pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (NullPointerException e) {
			logger.error("searchBookVendorList() In LibraryController.java: NullPointerException"
					+ e);
		} catch (Exception e) {
			logger.error("searchBookVendorList() In LibraryController.java: Exception"
					+ e);
		}
		return mav;
	}

	/**
	 * This method is responsible for Open mapBookVendor and Book List.
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return addItemVendorMapping.jsp
	 */

@RequestMapping(value = "/mapBookVendor", method = RequestMethod.GET)
	public ModelAndView mapBookVendor(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		logger.info("In addBookVendorMapping() method of LibraryController");
		ModelAndView mav = new ModelAndView("library/mapBookVendor");
		try {
			VendorType vendorType = libraryService.getVendorAndBooks();
			if(vendorType != null){
				List<Item> itemList = vendorType.getItemList();
				if (itemList != null && itemList.size() != 0) {
					logger.info("In BackOfficeController listBook() method: calling getBookListPaging(HttpServletRequest request) in LibraryService.java");
					PagedListHolder<Item> pagedListHolder = libraryService.getmapBookVendorListPaging(request);
					mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
					mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
					mav.addObject("total", pagedListHolder.getNrOfElements());
					mav.addObject("pagedListHolder", pagedListHolder);
				}
				mav.addObject("vendorType", vendorType);
			}
		} catch (NullPointerException e) {
			e.printStackTrace();
			logger.error("addVendor() In LibraryController.java: NullPointerException" + e);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("addVendor() In LibraryController.java: Exception" + e);
		}
		return mav;
	}
	
	@RequestMapping(value = "/mapBookVendorListPagination", method = RequestMethod.GET)
	public ModelAndView mapBookVendorListPagination(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = null;
		try {
			logger.info("In mapBookVendorListPagination() method of LibraryController");
			mav = new ModelAndView("library/mapBookVendor");
			logger.info("In LibraryController mapBookVendorListPagination() method: calling getmapBookVendorListPaging(HttpServletRequest request) in LibraryService.java");
			PagedListHolder<Item> pagedListHolder = libraryService.getmapBookVendorListPaging(request);
			if (pagedListHolder != null && pagedListHolder.getNrOfElements()!=0) {
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Error in LibraryController mapBookVendorListPagination() method for Exception: ", e);
		}
		return mav;
	}

	/**
	 * This method is responsible for getting book supplied by vendor.
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return vendorItems
	 */
	@RequestMapping(value = "/getVendorBooks", method = RequestMethod.GET)
	public @ResponseBody
	String getVendorBooks(@RequestParam("vendorCode") String vendorCode) {
		logger.info("In getVendorBooks() method of LibraryController");
		String vendorBooks = libraryService.getVendorBooks(vendorCode);
		return (new Gson().toJson(vendorBooks));

	}

	/**
	 * This method is responsible for Open listMessVendor and Map Item and
	 * vendor.
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return listMessVendors.jsp
	 */
	@RequestMapping(value = "/updateBookVendorMaping", method = RequestMethod.POST)
	public ModelAndView updateBookVendorMaping(HttpServletRequest request,
												HttpServletResponse response,
												ModelMap model,
												Vendor vendor,
												@ModelAttribute("sessionObject") SessionObject sessionObject) {
		String updateBookVendorMapingStatus = "fail";
		try {
//			for(Item i:vendor.getVendorItems()){
//				System.out.println("\t\t"+i.getItemCode()+"\t"+i.getSellingRate());
//			}
			updateBookVendorMapingStatus = libraryService.updateBookVendorMaping(vendor, sessionObject.getUserId());
			/**Added by @author Saif.Ali
			 * Date- 22/03/2018
			 * Used to insert the information into the activity_log table*/
			String description = "";
			String []saveId= request.getParameterValues("saveId");
			String vendorCode= request.getParameter("vendorCode");
			String[] strBookCodeValue =new String[saveId.length];
			String[] strBookSellingValue =new String[saveId.length];
			if (saveId != null && saveId.length != 0) 
			{
				for (int i = 0; i < saveId.length; i++) 
				{
					String bookCodeValue = request.getParameter("vendorItems["+saveId[i]+"].itemCode");
					String bookSellingValue = request.getParameter("vendorItems["+saveId[i]+"].sellingRate");
					strBookCodeValue[i] = bookCodeValue;
					strBookSellingValue[i] = bookSellingValue;
				}
			}
			if (strBookCodeValue != null && strBookCodeValue.length != 0) 
			{
				for (int i = 0; i < strBookCodeValue.length; i++) 
				{
					String existBookCodeValue = strBookCodeValue[i];
					String existBookSellingValue = strBookSellingValue[i];
					if(existBookCodeValue != null)
					{
						description= description + " vendorcode " + vendorCode + " mapped with book code ::" + existBookCodeValue + " of book price :: " + existBookSellingValue + " ; ";
					}
				}
			}
			if(updateBookVendorMapingStatus.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT BOOK AND VENDOR MAPPING DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("LIBRARY");
				updateLog.setUpdatedFor(" vendor code ::" + vendorCode);
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 369 :: CommonController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/**Addition ends*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateBookVendorMaping() In LibraryController.java: Exception",e);
		}
		if (updateBookVendorMapingStatus.equals("success")) {
			return bookVendorList(request, response, model);
		} else {
			return mapBookVendor(request, response, model);
		}
	}


	/**
	 * this method get book price for particular vendor and return to
	 * editBookVendor.jsp for edit
	 * 
	 * @param request
	 * @param response
	 * @param vendor
	 * @param model
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/editBookVendor", method = RequestMethod.POST)
	public ModelAndView editBookVendor(HttpServletRequest request,
									   HttpServletResponse response,
									   @ModelAttribute("vendor") Vendor vendor, ModelMap model) {
		logger.info("In editBookVendor() method of LibraryController");
		ModelAndView mav = new ModelAndView("library/editBookVendor");
		try {
			logger.info(" editBookVendor in AdminController");
			vendor = libraryService.getVendorBookList(vendor);
			if (vendor != null) {
				model.addAttribute("vendor", vendor);// vendorBookMapping
				List<Item> itemList = vendor.getVendorItems();
				model.addAttribute("itemList", itemList);
			}
			logger.info("In bookVendorPaginationForEdit() method of LibraryController");
//			PagedListHolder<Item> pagedListHolder = libraryService.getBookVendorPaginationForEdit(request);
//				if(pagedListHolder!=null && pagedListHolder.getNrOfElements()!=0){
//					mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
//					mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
//					mav.addObject("total", pagedListHolder.getNrOfElements());
//					mav.addObject("vendorBookMappingPagedListHolder", pagedListHolder);
//				}
			} catch (Exception e) {
				logger.error("editBookVendor() In LibraryController.java: Exception"+ e);
				e.printStackTrace();
			}
			return mav;
	}
	
	@RequestMapping(value = "/updateBookVendorMaping", method = RequestMethod.POST, params="bookVendorPaginationForEditSearch")
	public ModelAndView bookVendorPaginationForEditSearch(HttpServletRequest request,
															HttpServletResponse response, ModelMap model, Vendor vendor) {
		try {
			String strKey = request.getParameter("query").trim();
			String strValue = request.getParameter("data").trim();
			Item item = new Item();
			if (strKey.equalsIgnoreCase("BookCode")) {
				item.setItemCode(strValue.trim());
			}
			if (strKey.equalsIgnoreCase("BookName")) {
				item.setItemName(strValue.trim());
			}
			vendor.setItem(item);
		} catch (Exception e) {
			logger.error("editBookVendor() In LibraryController.java: Exception", e);
			e.printStackTrace();
		}
		return editBookVendor(request,response,vendor,model);
	}
	
	@RequestMapping(value = "/bookVendorPaginationForEdit", method = RequestMethod.GET)
	public ModelAndView bookVendorPaginationForEdit(HttpServletRequest request, HttpServletResponse response,
													ModelMap model,
													@RequestParam("vendorName") String vendorName,
													@RequestParam("vendorCode") String vendorCode){
		ModelAndView mav = new ModelAndView("library/editBookVendor");
		try {
			Vendor vendor = new Vendor();
			vendor.setVendorCode(vendorCode);
			vendor.setVendorName(vendorName);
			model.addAttribute("vendor", vendor);
			logger.info("In bookVendorPaginationForEdit() method of LibraryController");
			PagedListHolder<Item> pagedListHolder = libraryService.getBookVendorPaginationForEdit(request);
			if(pagedListHolder!=null && pagedListHolder.getNrOfElements()!=0){
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("vendorBookMappingPagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("listProductsPagination() In LibraryController.java: Exception"+ e);
			e.printStackTrace();
		}
		return mav;
	}

	/**
	 * This block is responsible for Open addThreshold page populated with Book
	 * List having book's threshold value.
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return ModelAndView
	 */

@RequestMapping(value = "/addThresholdForBook", method = RequestMethod.GET)
	public ModelAndView addThresholdForBook(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		try {
			logger.info("In addThresholdForBook() method of LibraryController");
			List<Item> itemList = libraryService.getBooksForAddThreshold();
			
			if (itemList != null && itemList.size() != 0) {
				PagedListHolder<Item> pagedListHolder = libraryService.getBooksForAddThresholdPaging(request);
				model.addAttribute("first",pagedListHolder.getFirstElementOnPage() + 1);
				model.addAttribute("last",pagedListHolder.getLastElementOnPage() + 1);
				model.addAttribute("total", pagedListHolder.getNrOfElements());
				model.addAttribute("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("addThresholdForBook() In LibraryController.java: Exception"+ e);
		}
		return  new ModelAndView("library/addThresholdForBook");
	}
	
	
	@RequestMapping(value = "/addThresholdPaginationForBook", method = RequestMethod.GET)
	public ModelAndView listProductsPagination(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		ModelAndView mav = new ModelAndView("library/addThresholdForBook");
		try {
			logger.info("In addThresholdPaginationForBook() method of LibraryController");
			PagedListHolder<Item> pagedListHolder = libraryService.getBooksForAddThresholdPaging(request);
			mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
			mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
			mav.addObject("total", pagedListHolder.getNrOfElements());
			mav.addObject("pagedListHolder", pagedListHolder);
		} catch (Exception e) {
			logger.error("listProductsPagination() In LibraryController.java: Exception"+ e);
			e.printStackTrace();
		}
		return mav;
	}


	/**
	 * This Block is responsible for carrying Book's respective threshold value
	 * into the database .
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @param strItemThresholdValue
	 * @return List<Item>
	 */
	@RequestMapping(value = "/updateThresholdForBook", method = RequestMethod.POST)
	public ModelAndView updateThresholdForBook(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			ArrayList<Item> itemList,@ModelAttribute("sessionObject") SessionObject sessionObject) {	// SESSION OBJECT  ADDED BY SAIF 22-03-2018
		String updateThresholdForBookStatus = "fail";
		try {
			logger.info("In updateThresholdForBook() method of LibraryController");
			String[] strItemThresholdValue = request
					.getParameterValues("itemThresholdValue");
			if (strItemThresholdValue != null
					&& strItemThresholdValue.length != 0) {
				for (int i = 0; i < strItemThresholdValue.length; i++) {
					Item item = new Item();
					String existItemCode = strItemThresholdValue[i];
					String strItemthresholdValue = request
							.getParameter(existItemCode);
					if (strItemthresholdValue.trim() != "") {
						int intItemThresholdValue = Integer
								.parseInt(strItemthresholdValue);
						item.setItemCode(existItemCode);
						item.setThreshold(intItemThresholdValue);
						itemList.add(item);
					}
				}
			}
			updateThresholdForBookStatus = libraryService
					.updateThresholdForBook(itemList);
			if (updateThresholdForBookStatus.equals("success")) {
				model.addAttribute("Message","success");
			} else {
				model.addAttribute("Message","fail");
			}
			/**Added by @author Saif.Ali
			 * Date- 22/03/2018
			 * Used to insert the information into the activity_log table*/
			String description = "";
			String oldThresholdValue= request.getParameter("oldThresholdValue");
			String bookCode= "";
			if (strItemThresholdValue != null && strItemThresholdValue.length != 0) 
			{
				for (int i = 0; i < strItemThresholdValue.length; i++) 
				{
					String existItemCode = strItemThresholdValue[i];
					bookCode= existItemCode;
					String strItemthresholdValue = request.getParameter(existItemCode);
					if(!(strItemthresholdValue.equalsIgnoreCase(oldThresholdValue)))
					{
						description= description + " Threshold of Book Code::" + existItemCode + " of threshold " + oldThresholdValue + " updated to new threshold value ::" + strItemthresholdValue;
					}
				}
			}
			if(updateThresholdForBookStatus.equalsIgnoreCase("success")){
				UpdateLog updateLog=new UpdateLog();
				updateLog.setUpdatedByUserId(sessionObject.getUserId());
				updateLog.setFunctionality("EDIT BOOK THRESHOLD DETAILS");
				updateLog.setInsertUpdate("UPDATE");
				updateLog.setModule("LIBRARY");
				updateLog.setUpdatedFor(bookCode);
				updateLog.setDescription(description);
				String response_update=commonService.insertIntoActivityLog(updateLog);
				
				System.out.println("LN 369 :: CommonController :: UpdatedByUserId::"+updateLog.getUpdatedByUserId() +
						":: Functonality ::"+ updateLog.getFunctionality() + 
						":: Module ::"+updateLog.getModule() + 
						":: Updated For ::"+ updateLog.getUpdatedFor() +
						":: Description :: "+updateLog.getDescription() +
						":: Response :: " + response_update +
						":: Insert/Update :: " + updateLog.getInsertUpdate());
			}
			/**Addition ends*/
		} catch (Exception e) {
			logger.error("updateThresholdForBook() In LibraryController.java: Exception", e);
			e.printStackTrace();
		}
		return addThresholdForBook(request, response, model);
	}

	



@RequestMapping(value = "/searchBookThreshold")
	public ModelAndView searchBookThreshold(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, HashMap<String, Object> parameters) {
		ModelAndView mav = new ModelAndView("library/addThresholdForBook");
		try {
			String strKey = request.getParameter("query").trim();
			String strValue = request.getParameter("data").trim();
			parameters.clear();
			parameters.put("BookCode", null);
			parameters.put("BookName", null);
			parameters.put("BookThresholdValue", null);
			if (strKey.equalsIgnoreCase("BookCode")) {
				parameters.put("BookCode", strValue);
			}
			if (strKey.equalsIgnoreCase("BookName")) {
				parameters.put("BookName", strValue);
			}
			if (strKey.equalsIgnoreCase("BookThresholdValue")) {
				parameters.put("BookThresholdValue", strValue);
			}
			List<Item> itemList = libraryService.searchThresholdDetailsForBook(parameters);
			if (itemList != null && itemList.size() != 0) {
				PagedListHolder<Item> pagedListHolder = libraryService.getBooksForAddThresholdPaging(request);
				mav.addObject("first", pagedListHolder.getFirstElementOnPage() + 1);
				mav.addObject("last", pagedListHolder.getLastElementOnPage() + 1);
				mav.addObject("total", pagedListHolder.getNrOfElements());
				mav.addObject("pagedListHolder", pagedListHolder);
			}
		} catch (Exception e) {
			logger.error("Exception in searchBookThreshold()method of LibraryController: ", e);
		}
		return mav;
	}



	@RequestMapping(value = "/libraryProcessFlow", method = RequestMethod.GET)
	public ModelAndView showLibraryProcessFlow(ModelMap model) {
		String strView = null;
		try {
			logger.info("In LibraryController libraryPolicy() method.");
				strView = "library/libraryProcessFlow";	
		} catch (NullPointerException e) {
			logger.error(
					"Error in LibraryController libraryPolicy() method for NullPointerException: ",
					e);
		} catch (Exception e) {
			logger.error(
					"Error in LibraryController libraryPolicy() method for Exception: ",
					e);
		}
		return new ModelAndView(strView);
	}
	
	
	/*
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * */
	
	@RequestMapping(value = "/downloadBookDetailsExcel", method = RequestMethod.GET)
	public ModelAndView downloadBookDetailsExcel(HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		try{
			FileUploadDownload util = new FileUploadDownload();
			String returnMessage = util.downloadFile(response, rootPath+excelDownloadFolder,bookDetailsExcel);
			if(returnMessage==null){
				model.addAttribute("ResultError", "File not available");
			}
		}catch(Exception e){
			logger.error("Exception in downloadBookDetailsExcel() for download Template Excel IN LibraryController", e);
		}
		return addBook(request, response, model);
	}
	

	
	@RequestMapping(value = "/getUpdateBookLogDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getUpdateBookLogDetails(@RequestParam("bookCode") String bookCode) {
		String returnData = "";
		try{
			logger.info("In  getUpdateBookLogDetails() method of LibraryController: ");
			List<LoggingAspect> LogList = libraryService.getUpdateBookLogDetails(bookCode);
			if (LogList != null && LogList.size() != 0) {
				for (LoggingAspect loggingAspect : LogList) {
					if (loggingAspect != null) {
						returnData=returnData+loggingAspect.getUpdatedBy()+"*~*"+loggingAspect.getLoggingTime()+"*~*"+loggingAspect.getLoggingDesc()+"~*~";
					}
				}
			}else{
				returnData="Details Not Available";
			}
		}catch(Exception e){
			logger.error("Exception In LibraryController getUpdateBookLogDetails() method:",e);
		}
		return (new Gson().toJson(returnData));
	}

	@RequestMapping(value = "/getRetiredBookLogDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getRetiredBookLogDetails(@RequestParam("bookCode") String bookCode) {
		String returnData = "";
		try{
			logger.info("In  getRetiredBookLogDetails() method of LibraryController: ");
			List<LoggingAspect> LogList = libraryService.getRetiredBookLogDetails(bookCode);
			if (LogList != null && LogList.size() != 0) {
				for (LoggingAspect loggingAspect : LogList) {
					if (loggingAspect != null) {
						returnData=returnData+loggingAspect.getUpdatedBy()+"*~*"+loggingAspect.getLoggingTime()+"*~*"+loggingAspect.getLoggingDesc()+"~*~";
					}
				}
			}else{
				returnData="Details Not Available";
			}
		}catch(Exception e){
			logger.error("Exception In LibraryController getRetiredBookLogDetails() method:",e);
		}
		return (new Gson().toJson(returnData));
	}
	
	@RequestMapping(value = "/getVendorBookPriceHistory", method = RequestMethod.GET)
	public @ResponseBody
	String getVendorBookPriceHistory(
			@RequestParam("vendorCode") String vendorCode,
			@RequestParam("bookCode") String itemId) {
		logger.info("In  getVendorBookPriceHistory() method of CommonController");
		Item item= new Item();
		String priceHistory = "";
		try {
			item.setVendorCode(vendorCode);
			item.setItemCode(itemId);
			priceHistory = libraryService.getVendorBookPriceHistory(item);
		} catch (NullPointerException ne) {
			logger.error("getRemeaningAssetItems() In LibraryController.java: NullPointerException"
					+ ne);
			ne.printStackTrace();
		} catch (Exception e) {
			logger.error("getRemeaningAssetItems() In CommonController.java: Exception"
					+ e);
			e.printStackTrace();
		}
		return (new Gson().toJson(priceHistory.toString()));
	}
	
	/**
	 * @author anup.roy
	 * take user id for get reading habit
	 * library new
	 * */
	
	@RequestMapping(value = "/userForCheckBookReadingHabit", method = RequestMethod.GET)
	public String userForCheckBookReadingHabit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		logger.info("calling userIdForReturnBook() method of LibraryController");
		return "library/userForCheckBookReadingHabit";
	}
	
	/**@author anup.roy
	 * this gets the reading habit for the userid
	 * library new 
	 * */
	
	@RequestMapping(value = "/submitUserIdForCheckReadingHabit", method = RequestMethod.POST)
	public ModelAndView submitUserIdForCheckReadingHabit(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, @ModelAttribute("resource") Resource resource) {
		logger.info("calling submitUserIdForReturnBook() method of LibraryController");
		ModelAndView mav = new ModelAndView("library/bookReadingHabitOfResource");
		try {
			List<BookAllocation> bookLendedByResource = libraryService.getReadingHabitOfResource(resource);
			if (bookLendedByResource != null && bookLendedByResource.size() != 0) {
				model.addAttribute("bookLendedByResource", bookLendedByResource);
			}
		} catch (Exception e) {
			logger.error("Exception in submitUserIdForCheckReadingHabit() method Of LibraryController", e);
		}
		return mav;
	}
	
	/**
	 * @author ranita.sur
	 * this method is for view all allocated status of book to user
	 * */
	
	@RequestMapping(value = "/getBookAllocatedStudentDetails", method = RequestMethod.GET)
	public String getBookAllocatedStudentDetails(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String strView = "library/getBookAllocatedDetails";
		try {
			logger.info("Inside Method getBook-GET of BackOfficeController");
			List<BookAllocation> bookAllocatedList = libraryService.getBookAllocatedStudentDetailsList();
			model.addAttribute("bookAllocatedList", bookAllocatedList);
		} catch (CustomException ce){
			ce.printStackTrace();
			logger.error("Exception in method getSocialCategory-GET of BackOfficeController", ce);
		}
		return strView;
	}
	
	/**
	 * @author ranita.sur
	 * changes taken on 17062017
	 * */
	
	@RequestMapping(value = "/getBankAllDetails", method = RequestMethod.GET)
	public @ResponseBody
	String getSubGroup(@RequestParam("bankName") String bank) {
		String bankCode="";
		try {
			bankCode = libraryService.getBankAllDetails(bank);			
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(bankCode));
	}
	
	/**
	 * @author sourav.bhadra
	 * changes taken on 23062017
	 * this method returns tax percentage against tax code*/
	

	@RequestMapping(value = "/getTaxPercentageAgainstTaxCode", method = RequestMethod.GET)
	public @ResponseBody
	String getTaxPercentageAgainstTaxCode(@RequestParam("taxCode") String taxCode) {
		String percentage="";
		try {
			percentage = financeService.getTaxPercentageAgainstTaxCode(taxCode);
		}catch (Exception e){
			e.printStackTrace();
		}
		return (new Gson().toJson(percentage));
	}

	@RequestMapping(value = "/getGenreListForCreateRequisition", method = RequestMethod.GET)
	public @ResponseBody String getGenreListForCreateRequisition() {
		StringBuffer sb = new StringBuffer();
		try {
			logger.info("In String getGenreListForCreateRequisition() of LibraryController.java");
			List<Genre> genreList = libraryService.getGenreList();
			for(Genre genre : genreList){
				sb.append("<option value =\"" + genre.getGenreCode() + "\">"+ genre.getGenreName() + "</option>");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	@RequestMapping(value = "/getDepartmentListForCreateRequisition", method = RequestMethod.GET)
	public @ResponseBody String getDepartmentListForCreateRequisition() {
		StringBuffer sb = new StringBuffer();
		try {
			logger.info("In String getDepartmentListForCreateRequisition() of LibraryController.java");
			List<Department> departmentList=libraryService.getDepartmentForBookRequisition();
			for(Department dept : departmentList){
				sb.append("<option value =\"" + dept.getDepartmentCode() + "\">"+ dept.getDepartmentName() + "</option>");
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * saif.ali
	 * 03072017*/
	
	@RequestMapping(value = "/getUserIdList", method = RequestMethod.GET, headers = "Accept=*/*")
	public @ResponseBody
	String getUserIdList(@RequestParam("term") String strQuery) {
		logger.info("In getUserIdList() Ajax method of BackOfficeController");
		List<String> UserIdList = libraryService.getUserIdList(strQuery);
		return (new Gson().toJson(UserIdList));
	}
	
	/**
	 * saif.ali
	 * 03072017*/
	
	@RequestMapping(value = "/getMaximunNumberOfBook", method = RequestMethod.GET)
	public @ResponseBody String getMaximumNumberOfBook(@RequestParam("bookRequestedFor") String bookRequestedFor){
		logger.info("In getMaximumNumberOfBook() Ajax method of LibraryController");
		int maxbook= 0;
		try{
			maxbook = libraryService.getMaximumNumberBook(bookRequestedFor);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return (new Gson().toJson(maxbook));
	}
	
	/* added by sourav.bhadra on 12-02-2018 */
	@RequestMapping(value = "/editMagazine", method = RequestMethod.POST)
	public ModelAndView editMagazine(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			@RequestParam("magazineCode") String magazineCode, Magazine mag) {
		try {
			logger.info("calling editMagazine() method of LibraryService");
			mag.setMagazineCode(magazineCode);
			model.addAttribute("magazineDetails", mag);
			/*book.setBookCode(bookCode);
			book = libraryService.getBookDetails(book);
			model.addAttribute("book", book);*/
		} catch (Exception e) {
			logger.error("Exception in editMagazine() method Of LibraryController",	e);
			e.printStackTrace();
		}
		return new ModelAndView("library/editMagazine");
	}
	
	/* added by sourav.bhadra on 15-02-2018 */
	@RequestMapping(value = "/updateMagazine", method = RequestMethod.POST)
	public ModelAndView updateMagazine(HttpServletRequest request,
									HttpServletResponse response,
									ModelMap model,
									Magazine magazine,
									@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("calling updateMagazine() method of LibraryService");
			magazine.setUpdatedBy(sessionObject.getUserId());
			libraryService.updateMagazine(magazine);
		} catch (Exception e) {
			logger.error("Exception in updateMagazine() method Of LibraryController", e);
			e.printStackTrace();
		}
		return listBook(request, response, model);
	}
	
	/**
	 * @author anup.roy
	 * this method will show the book cataloguing page*/
	
	@RequestMapping(value = "/getBookCatalogue", method = RequestMethod.GET)
	public String getBookCatalogue(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		String strView = null;
		try {
			logger.info("In String getBookCatalogue(HttpServletRequest req, HttpServletResponse res, ModelMap model) of libraryController.java");
			List<BookCategory> categoryList = libraryService.getAllCategories();
			model.addAttribute("categoryList", categoryList);
			List<StatusOfItem> statusList = commonService.getAllStatusOfItems();
			model.addAttribute("statusList", statusList);
			strView = "library/bookCatalogue";
		}catch (Exception e) {
			logger.error("Exception in String getBookCatalogue(HttpServletRequest req, HttpServletResponse res, ModelMap model) of libraryController.java:"+e);
			e.printStackTrace();
		}
		return strView;
	}
	
	/**
	 * @author anup.roy
	 * this method is for adding a new book to catalogue*/
	
	@RequestMapping(value="/addToCatalogueFromLibrary", method=RequestMethod.POST)
	public String addToCatalogueFromLibrary (HttpServletRequest req, HttpServletResponse res, ModelMap model,
			Book book, BookPublisher bookPublisher, Magazine mag, MagazinePublisher magPublisher,
			@ModelAttribute("sessionObject") SessionObject sessionObject) {
		try {
			logger.info("In String addToCatalogueFromLibrary()-POST of LibraryController.java");
			String returnStatus = null;
			String category = req.getParameter("category");
			if(category.equals("BOOK_CATEGORY_1")) {
				System.out.println("In book portion");
				String status = req.getParameter("statusOfBook");
				List<Author> authorList = new ArrayList<Author>();
				String bookAuthorName = req.getParameter("author");
				String[] strAuthor = null;
				if(null!=bookAuthorName && bookAuthorName.length()!=0) {
					strAuthor = bookAuthorName.split(",");
					if(null!=strAuthor && strAuthor.length!=0) {
						for(String str : strAuthor) {
							Author auth = new Author();
							auth.setAuthorFullName(str);
							authorList.add(auth);
						}
					}
				}
				book.setBookType(category);
				book.setUpdatedBy(sessionObject.getUserId());
				book.setBookAuthorList(authorList);
				book.setBookPublisher(bookPublisher);
				book.setStatusOfItemName(status);
				returnStatus = libraryService.addBookToCatalogueFromLibrary(book);
			}else {
				System.out.println("In magazine");
				String status = req.getParameter("statusOfMag");
				mag.setBookType(category);
				mag.setUpdatedBy(sessionObject.getUserId());
				mag.setMagazinePublisher(magPublisher);
				mag.setStatusOfItemName(status);
				returnStatus = libraryService.addMagazineToCatalogueFromLibrary(mag);
			}
			model.addAttribute("Message", returnStatus);
		}catch (Exception e) {
			logger.error("Exception in String addToCatalogueFromLibrary()-POST of LibraryController.java:"+e);
		}
		return getBookCatalogue(req, res, model);
	}
	
	/**
	 * @author anup.roy
	 * this method is fetching list of items with code from catalogue*/
	@RequestMapping(value = "/getListOfItemsFromCatalogue", method = RequestMethod.GET)
	@ResponseBody
	public String getListOfItemsFromCatalogue(@RequestParam("category")String category) {
		StringBuffer sb = new StringBuffer();
		try {
			logger.info("In String getListOfItemsFromCatalogue(String category) of LibraryController.java");
			List<Item> itemList = libraryService.getListOfItemsFromCatalogue(category);
			sb.append("<option value=\""+"\">"+"Select..."+"</option>");
			for(Item item : itemList){
				sb.append("<option value =\"" + item.getItemCode() + "\">"+ item.getItemName() + "</option>");
			}
		}catch (Exception e){
			logger.info("Exception in getListOfItemsFromCatalogue(String category) of librarycontroller.java:"+e);
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	@RequestMapping(value = "/getAllDetailsOfItemsFromCatalogue", method = RequestMethod.GET)
	@ResponseBody
	public String getAllDetailsOfItemsFromCatalogue(@RequestParam(required=false,value="bookCode")String bookCode,
													@RequestParam(required=false,value="magCode")String magCode,
													@RequestParam(value="category")String category) {
		String bookData = null;
		String authorData = null;
		String fullData = null;
		String magData = null;
		try {
			logger.info("In String getAllDetailsOfItemsFromCatalogue(String bookCode,String magCode,String category) of LibraryController.java");
			if(category.equalsIgnoreCase("BOOK_CATEGORY_1")) {
				Book bookDetails = libraryService.getAllDetailsOfBooksFromCatalogue(bookCode);
				StringBuilder sb = new StringBuilder();
				StringBuilder sb1 = new StringBuilder();
				if(null != bookDetails) {
					sb.append(bookDetails.getBookName()+"#");
					sb.append(bookDetails.getBookPlace()+"#");
					sb.append(bookDetails.getBookPublisher().getBookPublisherName()+"#");
					sb.append(bookDetails.getPublishingYear()+"#");
					sb.append(bookDetails.getPages()+"#");
					sb.append(bookDetails.getVolume()+"#");
					sb.append(bookDetails.getSource()+"#");
					sb.append(bookDetails.getClassNo()+"#");
					sb.append(bookDetails.getBookNo()+"#");
					bookData = sb.toString().substring(0, sb.toString().length() - 1);
					List<Author> authorList = bookDetails.getBookAuthorList();
					if(null != authorList) {
						for(Author author : authorList) {
							sb1.append(author.getAuthorFullName()+"$");
							authorData = sb1.toString().substring(0, sb1.toString().length() - 1);
						}
					}
					fullData = bookData+"^^"+authorData;
				}
			}else {
				Magazine magazineDetails = libraryService.getAllDetailsOfMagazinesFromCatalogue(magCode);
				StringBuilder sb = new StringBuilder();
				if(null != magazineDetails) {
					sb.append(magazineDetails.getMagazineName()+"#");
					sb.append(magazineDetails.getMagazinePublisher().getMagazinePublisherName()+"#");
					sb.append(magazineDetails.getMagazinePeriod()+"#");
					magData = sb.toString().substring(0, sb.toString().length() - 1);
					fullData = magData;
				}
			}
		}catch (Exception e){
			logger.info("Exception in getAllDetailsOfItemsFromCatalogue(String bookCode,String magCode,String category) of librarycontroller.java:"+e);
			e.printStackTrace();
		}
		System.out.println(fullData.toString());
		return fullData.toString();
	}
	
}
