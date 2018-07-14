package com.qts.icam.service.impl.library;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestUtils;


import com.qts.icam.dao.library.LibraryDAO;
import com.qts.icam.model.common.Item;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.library.Book;
import com.qts.icam.model.library.BookAllocation;
import com.qts.icam.model.library.BookAllocationDetails;
import com.qts.icam.model.library.BookCategory;
import com.qts.icam.model.library.BookId;
import com.qts.icam.model.library.BookLanguage;
import com.qts.icam.model.library.BookPurchaseOrder;
import com.qts.icam.model.library.BookPurchaseOrderDetails;
import com.qts.icam.model.library.BookRequest;
import com.qts.icam.model.library.BookRequestDetails;
import com.qts.icam.model.library.BookRequisition;
import com.qts.icam.model.library.BookRequisitionDetails;
import com.qts.icam.model.library.BookRequisitionReceivedDates;
import com.qts.icam.model.library.BookStatus;
import com.qts.icam.model.library.Magazine;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Genre;
import com.qts.icam.service.library.LibraryService;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;

/**
 * LibraryService.java-This Service is responsible for call to LibraryDAO.java
 * class for data insert or retrieve on library related data.
 * 
 * @author sovan.mukherjee
 * @version 1.0
 */
@Service
public class LibraryServiceImpl implements LibraryService {
	public static Logger logger = Logger.getLogger(LibraryServiceImpl.class);
	@Autowired
	LibraryDAO libraryDao;
	List<Vendor> bookVendorList = null;
	List<Item> bookList = null;	
	
	List<Item> itemList = null;
	List<Book> allBookList = null;
	List<Book> bookStockList = null;
	List<BookId> bookIdList = null;
	List<BookRequest> bookRequestIdList = null;
	List<BookAllocation> bookAllocationList = null;
	List<BookRequisition> bookRequisitionList = null;
	List<BookPurchaseOrder> bookPurchaseOrderList = null;
	List<BookRequestDetails> bookRequestDetailsList = null;
	List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList = null;
	List<BookRequisitionDetails> bookRequisitionDetailsList = null;


	/**
	 * @author anup.roy
	 * This method is used to insert new Book details
	 * @param Book
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String addNewBook(Book book) {
		logger.info("addNewBook(Book book) method in LibraryServiceImpl");
		return libraryDao.addNewBook(book);
	}

	/**
	 * This method is used to fetch the List of Book Status
	 * @return BookStatus
	 */
	public BookStatus getBookStatus() {
		logger.info("getBookStatus() method in LibraryServiceImpl");
		return libraryDao.getBookStatus();
	}

	/**
	 * This method call LibraryDAO.java class for retrieve stock of books and
	 * return List<Book> type of object to controller.
	 * 
	 * @return List<Book>
	 */


	public List<Book> searchForViewBookStock(Map<String, Object> parameters) {
		logger.info("searchForViewBookStock(Map<String, Object> parameters) method in LibraryServiceImpl");
		allBookList = libraryDao.searchForViewBookStock(parameters);
		return allBookList;
	}

	/**
	 * 
	 * @param parameters
	 * @return
	 */
	public List<Book> searchListOnRetirementBook(Map<String, Object> parameters) {
		logger.info("searchListOnRetirementBook(Map<String, Object> parameters) method in LibraryServiceImpl");
		allBookList = libraryDao.searchListOnRetirementBook(parameters);
		return allBookList;
	}

	/**
	 * This method call LibraryDAO.java class for retrieve details information
	 * on particular book and return Book type of object to LibraryController.
	 * 
	 * @param String
	 * @return Book
	 */
//	public Book getBookProfile(String bookCode) {
//		logger.info("getBookProfile(String bookCode) method in LibraryServiceImpl");
//		return libraryDao.getBookProfile(bookCode);
//	}

	
	


	/**
	 * This method is used to get list of Retired Books
	 * 
	 * @param List
	 *            <String>
	 * @return none
	 */
	public List<Book> removeBookIds(List<BookId> bookIdList) {
		logger.info("getBookIdList(String bookCode) method in LibraryServiceImpl");
		allBookList = libraryDao.removeBookIds(bookIdList);
		return allBookList;
	}

	/**
	 * This method is used to get Retired book Details
	 * 
	 * @param bookCode
	 * @return
	 */
	public Book getRetiredBookDetails(String bookCode) {
		Utility util = new Utility();
		Book retiredBookDetailsFromDB = null;
		try {
			retiredBookDetailsFromDB = libraryDao.getRetiredBookDetails(bookCode);
			List<BookId> bookIdList = retiredBookDetailsFromDB.getBookIdList();
			for (BookId bookId : bookIdList) {
				if (bookId.getNewBookEntryDate() != null) {
					bookId.setNewBookEntryDate(util.epochToHumanReadableFormat(new Integer(bookId.getNewBookEntryDate()).toString()));
					bookId.setBookRetirementDate(util.epochToHumanReadableFormat(new Integer(bookId.getBookRetirementDate()).toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retiredBookDetailsFromDB;
	}

	/**
	 * 
	 * @return
	 */
	public BookRequisition getLastRequisitionId() {
		String strReturnRequisitionId = null;
		BookRequisition bookRequisition = null;
		try {
			bookRequisition = libraryDao.getLastRequisitionId();
			if (bookRequisition.getBookRequisitionCode() != null) {
				String strRequisitionId = bookRequisition.getBookRequisitionCode();
				String str1value = strRequisitionId.substring(4);
				String strCode = strRequisitionId.substring(0, 4);
				int int1value = Integer.parseInt(str1value);
				int1value = int1value + 1;
				strReturnRequisitionId = strCode + int1value;
			} else {
				strReturnRequisitionId = "RQID1";
			}
			bookRequisition.setBookRequisitionCode(strReturnRequisitionId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookRequisition;
	}
	
	/**
	 * modified by ranita.sur
	 * changes taken on 12042017
	 * revert back on 12042017**/

	public List<BookRequisition> addRequisition(BookRequisition bookRequisition, String updatedBy) {
		
		Utility util = new Utility();
		try {
			bookRequisitionList = libraryDao.addRequisition(bookRequisition, updatedBy);
			for (BookRequisition bookReq : bookRequisitionList) {
				if (bookReq.getBookRequisitionOpenDate() != null) {
					bookReq.setBookRequisitionOpenDate(util.epochToHumanReadableFormat(new Integer(bookReq.getBookRequisitionOpenDate()).toString()));
				}
				if (bookReq.getBookRequisitionCloseDate() != null) {
					bookReq.setBookRequisitionCloseDate(util.epochToHumanReadableFormat(new Integer(bookReq.getBookRequisitionCloseDate()).toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookRequisitionList;
	}

	

	/**
	 * 
	 * @param requisitionCode
	 * @return
	 */
	public BookRequisition getRequisitionDetails(String requisitionCode) {
		BookRequisition requisitionDetails = null;
		Utility util = new Utility();
		try {
			requisitionDetails = libraryDao.getRequisitionDetails(requisitionCode);
			if (requisitionDetails!=null && requisitionDetails.getBookRequisitionOpenDate() != null) {
				requisitionDetails.setBookRequisitionOpenDate(util.epochToHumanReadableFormat(new Integer(requisitionDetails.getBookRequisitionOpenDate()).toString()));
			}
			if (requisitionDetails.getBookRequisitionCloseDate() != null) {
				requisitionDetails.setBookRequisitionCloseDate(util.epochToHumanReadableFormat(new Integer(requisitionDetails.getBookRequisitionCloseDate()).toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return requisitionDetails;
	}

	/**
	 * This method call LibraryDAO.java class for retrieve Requisition list and
	 * return List<BookRequisition> type of object to LibraryController.
	 * 
	 * @param List
	 *            <BookRequisitionDetails>
	 * @return List<BookRequisition>
	 */
	public List<BookRequisition> getRequisitionFulfillment(
			List<BookRequisitionDetails> bookRequisitionDetailsList) {
		List<BookRequisition> bookRequisitionListFromDB = null;
		Utility util = new Utility();
		bookRequisitionListFromDB = libraryDao
				.getRequisitionFulfillment(bookRequisitionDetailsList);

		/*
		 * Checking all requisition open date and close date is null. if open
		 * date and close date is not null then retrieve requisition open and
		 * close date and BooksReceived date from 'bookRequisitionListFromDB'and
		 * call epochToHumanReadableFormat() method in Utility.java class to
		 * covert epoch time to human readable date and set to BookRequisition
		 * object.
		 */
		try {
			for (BookRequisition bookReq : bookRequisitionListFromDB) {
				if (bookReq.getBookRequisitionOpenDate() != null) {
					bookReq.setBookRequisitionOpenDate(util
							.epochToHumanReadableFormat(new Integer(bookReq
									.getBookRequisitionOpenDate()).toString()));
				}
				if (bookReq.getBookRequisitionCloseDate() != null) {
					bookReq.setBookRequisitionCloseDate(util
							.epochToHumanReadableFormat(new Integer(bookReq
									.getBookRequisitionCloseDate()).toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookRequisitionListFromDB;
	}

	/**
	 * This method call LibraryDAO.java class for retrieve Requisition list and
	 * return List<BookRequisition> type of object to LibraryController.
	 * 
	 * @param bookRequisition
	 * @return BookRequisition
	 */
	public BookRequisition viewRequisitionDetails(
			BookRequisition bookRequisition) {
		BookRequisition viewBookRequisitionList = null;
		Utility util = new Utility();
		try {
			viewBookRequisitionList = libraryDao
					.viewRequisitionDetails(bookRequisition);
			if (viewBookRequisitionList != null) {
				/*
				 * Checking all requisition open date and close date and
				 * BooksReceived date is null. if open date and close date and
				 * and BooksReceived date is not null then retrieve requisition
				 * open and close date and BooksReceived date from
				 * 'bookRequisitionList'and call epochToHumanReadableFormat()
				 * method in Utility.java class to covert epoch time to human
				 * readable date and set to BookRequisition object.
				 */
				if (viewBookRequisitionList.getBookRequisitionOpenDate() != null) {
					viewBookRequisitionList.setBookRequisitionOpenDate(util
							.epochToHumanReadableFormat(new Integer(
									viewBookRequisitionList
											.getBookRequisitionOpenDate())
									.toString()));
				}
				if (viewBookRequisitionList.getBookRequisitionCloseDate() != null) {
					viewBookRequisitionList.setBookRequisitionCloseDate(util
							.epochToHumanReadableFormat(new Integer(
									viewBookRequisitionList
											.getBookRequisitionCloseDate())
									.toString()));
				}
				List<BookRequisitionDetails> bookRequisitionDetailsList = viewBookRequisitionList
						.getBookRequisitionDetailsList();
				if (bookRequisitionDetailsList != null
						&& bookRequisitionDetailsList.size() != 0) {
					for (BookRequisitionDetails bookRequisitionDetails : bookRequisitionDetailsList) {
						List<BookRequisitionReceivedDates> bookRequisitionReceivedDatesList = bookRequisitionDetails
								.getBookRequisitionReceivedDatesList();
						if (bookRequisitionReceivedDatesList != null
								&& bookRequisitionReceivedDatesList.size() != 0) {
							for (BookRequisitionReceivedDates bookRequisitionReceivedDates : bookRequisitionReceivedDatesList) {
								if (bookRequisitionReceivedDates
										.getDateBooksReceived() != null) {
									bookRequisitionReceivedDates
											.setDateBooksReceived((util
													.epochToHumanReadableFormat(new Integer(
															bookRequisitionReceivedDates
																	.getDateBooksReceived())
															.toString())));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return viewBookRequisitionList;
	}

	@Override
	public BookStatus addToCatalog(BookRequisitionDetails bookRequisitionDetails) {
		return libraryDao.addToCatalog(bookRequisitionDetails);
	}

	/**
	 * This method call LibraryDAO.java class for retrieve Requisition list on
	 * search value and return List<BookRequisition> type of object to
	 * LibraryController.
	 * 
	 * @param Map
	 *            <String, Object>
	 * @return List<BookRequisition>
	 */
	public List<BookRequisition> getRequisitionSearchList(
			Map<String, Object> parameters) {
		Utility util = new Utility();
		try {
			/*
			 * This method call getRequisitionSearchList() method in
			 * LibraryDAO.java class for get Requisition List.
			 */
			bookRequisitionList = libraryDao.getRequisitionSearchList(parameters);
			/*
			 * Checking all requisition open date and close date is null. if
			 * open date and close date is not null then retrieve requisition
			 * open and close date from 'bookRequisitionList'and call
			 * epochToHumanReadableFormat() method in Utility.java class to
			 * covert epoch time to human readable date and set to
			 * BookRequisition object.
			 */
			for (BookRequisition bookReq : bookRequisitionList) {
				if (bookReq.getBookRequisitionOpenDate() != null) {
					bookReq.setBookRequisitionOpenDate(util
							.epochToHumanReadableFormat(new Integer(bookReq
									.getBookRequisitionOpenDate()).toString()));
				}
				if (bookReq.getBookRequisitionCloseDate() != null) {
					bookReq.setBookRequisitionCloseDate(util
							.epochToHumanReadableFormat(new Integer(bookReq
									.getBookRequisitionCloseDate()).toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookRequisitionList;
	}	

	/**
	 * 
	 * @param parameters
	 * @return
	 */
	public List<Book> searchLodgingRequest(Map<String, Object> parameters) {
		String strReturnRequestId = null;
		try {
			allBookList = libraryDao.searchLodgingRequest(parameters);
			if (allBookList != null && allBookList.size() != 0) {
				if (!allBookList.get(0).getBookRequest().getBookRequestCode().equals("null")) {
					String strRequestId = allBookList.get(0).getBookRequest().getBookRequestCode();
					String str1value = strRequestId.substring(4);
					String strCode = strRequestId.substring(0, 4);
					int int1value = Integer.parseInt(str1value);
					int1value = int1value + 1;
					strReturnRequestId = strCode + int1value;
					allBookList.get(0).getBookRequest().setBookRequestCode(strReturnRequestId);
				} else {
					strReturnRequestId = "RQID1";
					allBookList.get(0).getBookRequest().setBookRequestCode(strReturnRequestId);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBookList;
	}

	/**
	 * 
	 * @param bookCodesList
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public Integer submitLodgingRequest(BookRequest bookRequest) {
		return libraryDao.submitLodgingRequest(bookRequest);
	}

	

	public List<BookRequest> searchForBookRequest(Map<String, Object> parameters) {
		Utility util = new Utility();
		try {
			bookRequestIdList = libraryDao.searchForBookRequest(parameters);
			if (bookRequestIdList != null) {
				for (BookRequest bookRequest : bookRequestIdList) {
					if (bookRequest.getBookRequestOpenDate() != null) {
						bookRequest.setBookRequestOpenDate(util.epochToHumanReadableFormat(new Integer(bookRequest.getBookRequestOpenDate()).toString()));
					}
					if (bookRequest.getBookRequestCloseDate() != null) {
						bookRequest.setBookRequestCloseDate((util.epochToHumanReadableFormat(new Integer(bookRequest.getBookRequestCloseDate()).toString())));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookRequestIdList;
	}

	/**
	 * 
	 * @param bookRequest
	 * @return bookRequestIdList
	 */
	public List<BookRequest> submitIssuingBookForBookAllocation(
			BookAllocation bookAllocation) {
		List<BookRequest> bookRequestIdList = null;
		Utility util = new Utility();
		try {
			bookRequestIdList = libraryDao.submitIssuingBookForBookAllocation(bookAllocation);
			if (bookRequestIdList != null) {
				for (BookRequest bookRequest : bookRequestIdList) {
					if (bookRequest.getBookRequestOpenDate() != null) {
						bookRequest.setBookRequestOpenDate(util.epochToHumanReadableFormat(new Integer(bookRequest.getBookRequestOpenDate()).toString()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookRequestIdList;
	}

	

	

	/**
	 * 
	 * @param bookAllocation
	 * @return
	 */
	public void submitReturnBookDetails(List<BookAllocationDetails> bookAllocationDetailsList,Map<String, Object> bookRatingMap) {
		logger.info("submitReturnBookDetails(List<BookAllocationDetails> bookAllocationDetailsList,Map<String,Object> bookRatingMap) method in LibraryServiceImpl");
		libraryDao.submitReturnBookDetails(bookAllocationDetailsList,bookRatingMap);
	}

	@Override
	public BookRequisition getBookRequisitionDetails(
			BookRequisition bookRequisition) {
		logger.info("getBookRequisitionDetails(BookRequisition bookRequisition) method in LibraryServiceImpl");
		return libraryDao.getBookRequisitionDetails(bookRequisition);
	}

	@Override
	public List<BookRequisition> savePaymentForBookRequisition(BookRequisition bookRequisition, String updatedBy) {
		logger.info("savePaymentForBookRequisition(BookRequisition bookRequisition) method in LibraryServiceImpl");
		return libraryDao.savePaymentForBookRequisition(bookRequisition, updatedBy);
	}

	@Override
	public List<Book> getBookNameDB() {
		logger.info("getBookNameDB() method in LibraryServiceImpl");
		return libraryDao.getBookNameDB();
	}

	@Override
	public Book getAllBookDetailsDB(String bookName) {
		logger.info("savePaymentForBookRequisition(BookRequisition bookRequisition) method in LibraryServiceImpl");
		return libraryDao.getAllBookDetailsDB(bookName);
	}

	@Override
	public List<Vendor> bookVendorList() {
		logger.info("savePaymentForBookRequisition(BookRequisition bookRequisition) method in LibraryServiceImpl");
		bookVendorList=libraryDao.bookVendorList();
		return bookVendorList;
	}

	@Override
	public PagedListHolder<Vendor> getBookVendorListPaging(
			HttpServletRequest request) {
		logger.info("In getmessVendorListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<Vendor> pagedListHolder = new PagedListHolder<Vendor>(
				bookVendorList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5; // ??getVendorListPaging
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	@Override
	public List<Vendor> getBookVendorSearchList(Map<String, Object> parameters) {
		logger.info("savePaymentForBookRequisition(BookRequisition bookRequisition) method in LibraryServiceImpl");
		bookVendorList = libraryDao.getBookVendorSearchList(parameters);
		return bookVendorList;
	}

	

	@Override
	public String getVendorBooks(String vendorCode) {
		logger.info("savePaymentForBookRequisition(BookRequisition bookRequisition) method in LibraryServiceImpl");
		return libraryDao.getVendorBooks(vendorCode);
	}

	@Override
	public String updateBookVendorMaping(Vendor vendor, String updatedBy) {
		String updateBookVendorMapingStatus = "fail";
		try {
			logger.info("savePaymentForBookRequisition(BookRequisition bookRequisition) method in LibraryServiceImpl");
			int updateStatus = libraryDao.updateBookVendorMaping(vendor, updatedBy);
			if (updateStatus != 0) {
				updateBookVendorMapingStatus = "success";
			}
		} catch (Exception e) {
			logger.error("In updateBookVendorMaping(Vendor vendor) method of LibraryServiceImpl");
		}
		return updateBookVendorMapingStatus;
	}

	@Override
	public Vendor getVendorBookList(Vendor vendor) {
		logger.info("savePaymentForBookRequisition(BookRequisition bookRequisition) method in LibraryServiceImpl");
		vendor = libraryDao.getVendorBookList(vendor);
		if(vendor!=null){
			itemList = vendor.getVendorItems();
		}
		return vendor;
	}
	
	@Override
	public PagedListHolder<Item> getBookVendorPaginationForEdit(HttpServletRequest request) {
		logger.info("In getBookVendorPaginationForEdit(	HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<Item> pagedListHolder = new PagedListHolder<Item>(itemList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}


	@Override
	public List<Item> getBooksForAddThreshold() {
		logger.info("savePaymentForBookRequisition(BookRequisition bookRequisition) method in LibraryServiceImpl");
		bookList=libraryDao.getBooksForAddThreshold();
		return bookList;
	}

	@Override
	public PagedListHolder<Item> getBooksForAddThresholdPaging(
			HttpServletRequest request) {
		logger.info("In getBooksForAddThresholdPaging(	HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<Item> pagedListHolder = new PagedListHolder<Item>(bookList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 10;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;

	}

	@Override
	public String updateThresholdForBook(List<Item> itemList) {
		String updateThresholdForBookstatus = "fail";
		try {
			logger.info("updateThresholdForBook(List<Item> itemList) method in LibraryServiceImpl");
			int updateStatus = libraryDao.updateThresholdForBook(itemList);
			if (updateStatus != 0) {
				updateThresholdForBookstatus = "success";
			}
		} catch (Exception e) {
			logger.error(
					"In updateThresholdForBook(List<Item> itemList) method of LibraryServiceImpl",
					e);
		}
		return updateThresholdForBookstatus;
	}

	@Override
	public List<Item> searchThresholdDetailsForBook(Map<String, Object> parameters) {
		logger.info("searchThresholdDetailsForBook(Map<String, Object> parameters) method in LibraryServiceImpl");
		bookList = libraryDao.searchThresholdDetailsForBook(parameters);
		return bookList;
	}

	@Override
	public List<BookAllocationDetails> getLendingDates(Book book) {
		logger.info("getLendingDates(Book book) method in LibraryServiceImpl");
		return libraryDao.getLendingDates(book);
	}

	@Override
	public String getCodeForBook(String bookName) {
		logger.info("getCodeForBook(String bookName) method in LibraryServiceImpl");
		return libraryDao.getCodeForBook(bookName);
	}

	@Override
	public String checkAvailabilityForCode(String bookCode) {
		logger.info("checkAvailabilityForCode(String bookCode) method in LibraryServiceImpl");
		return libraryDao.checkAvailabilityForCode(bookCode);
	}

	@Override
	public List<String> getAuthorNameDB(String strQuery) {
		logger.info("getAuthorNameDB(String strQuery) method in LibraryServiceImpl");
		return libraryDao.getAuthorNameDB(strQuery);
	}

	/**
	 * @author anup.roy*/
	@Override
	public List<String> getpublisherNameDB() {
		logger.info("getpublisherNameDB(String strQuery) method in LibraryServiceImpl");
		return libraryDao.getPublisherNameDB();
	}

	

	@Override
	public List<BookPurchaseOrderDetails> getPurchaseOrdersDetailsInPurchaseOrders(
			String purchaseOrderCode) {
		logger.info("getPurchaseOrdersDetailsInPurchaseOrders(String purchaseOrderCode) method in LibraryServiceImpl");
		return libraryDao
				.getPurchaseOrdersDetailsInPurchaseOrders(purchaseOrderCode);
	}

	@Override
	public void updatePurchaseOrderDetails(BookPurchaseOrder bookPurchaseOrder,
			List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList) {
		logger.info("updatePurchaseOrderDetails(BookPurchaseOrder bookPurchaseOrder, List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList) method in LibraryServiceImpl");
		libraryDao.updatePurchaseOrderDetails(bookPurchaseOrder,
				bookPurchaseOrderDetailsList);
	}

	@Override
	public BookPurchaseOrder getPurchaseOrdersForPayment(
			String purchaseOrderCode) {
		logger.info("getPurchaseOrdersForPayment(String purchaseOrderCode) method in LibraryServiceImpl");
		return libraryDao.getPurchaseOrdersForPayment(purchaseOrderCode);
	}

	@Override
	public void updatePurchaseOrderPayment(BookPurchaseOrder bookPurchaseOrder) {
		logger.info("updatePurchaseOrderPayment(BookPurchaseOrder bookPurchaseOrder) method in LibraryServiceImpl");
		libraryDao.updatePurchaseOrderPayment(bookPurchaseOrder);
	}


	@Override
	public void updateAddedToCatalogue(Book book) {
		logger.info("updateAddedToCatalogue(Book book) method in LibraryServiceImpl");
		libraryDao.updateAddedToCatalogue(book);
	}

	@Override
	public Book getBookCodeForBookName(String bookName) {
		logger.info("getBookCodeForBookName(String bookName) method in LibraryServiceImpl");
		return libraryDao.getBookCodeForBookName(bookName);
	}

	@Override
	public List<Vendor> getAllVenoderDetailsDBForBook(Book book) {
		logger.info("getAllVenoderDetailsDBForBook(Book book) method in LibraryServiceImpl");
		return libraryDao.getAllVenoderDetailsDBForBook(book);
	}

	@Override
	public List<String> getVendorName(String strQuery) {
		logger.info("getVendorName(String strQuery) method in LibraryServiceImpl");
		return libraryDao.getVendorNameDB(strQuery);
	}
	
	@Override
	public String getNameOfVendors() {
		logger.info("getUserIdOfVendors() method in LibraryServiceImpl");
		return libraryDao.getNameOfVendors();
	}
	
	@Override
	public String getIdAgainstName(Vendor vendor){
		logger.info("getIdAgainstName() method in LibraryServiceImpl");
		return libraryDao.getIdAgainstName(vendor);
	}
	
	@Override
	public List<BookPurchaseOrder> getpurchaseOrderForBook(BookPurchaseOrder bookPurchaseOrderObj, String updatedBy) {
		logger.info("getpurchaseOrderForBook(BookPurchaseOrder bookPurchaseOrderObj) method in LibraryServiceImpl");
		return libraryDao.getpurchaseOrderForBook(bookPurchaseOrderObj, updatedBy);
	}

	@Override
	public List<String> getEditionDB(String strQuery) {
		logger.info("getEditionDB(String strQuery) method in LibraryServiceImpl");
		return libraryDao.getEditionDB(strQuery);
	}


	@Override
	public Book getBookDetails(Book book) {
		logger.info("getBookDetails(Book book) method in LibraryServiceImpl");
		return libraryDao.getBookDetails(book);
	}

	@Override
	public String updateBook(Book book, String updatedBy) {			// modified by Saif 21-03-2018
		logger.info("updateBook(Book book) method in LibraryServiceImpl");
		return libraryDao.updateBook(book, updatedBy);
	}

	
	
	
	
	
	public List<Book> getBookStock() {
		logger.info("getBookStock() method in LibraryServiceImpl");
		allBookList = libraryDao.getBookStock();
		return allBookList;
	}
	
	@Override
	public PagedListHolder<Book> getBookStockPaging(HttpServletRequest request) {
		logger.info("In getBookStockPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<Book> pagedListHolder = new PagedListHolder<Book>(allBookList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}

	
	/**
	 * @author anup.roy
	 * this method gets the list of books for loan request*/
	@Override
	public List<Book> createLodgingRequest() {
		try {
			allBookList = libraryDao.createLodgingRequest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allBookList;
	}


	@Override
	public String getLastRequestId() {
		String  strReturnRequestId = "RQID1";
		try{
			BookRequest bookRequest = libraryDao.getLastRequestId();
			if(bookRequest!=null){
				String str1value = bookRequest.getBookRequestCode().substring(4);
				String strCode = bookRequest.getBookRequestCode().substring(0, 4);
				int int1value = Integer.parseInt(str1value);
				int1value = int1value + 1;
				strReturnRequestId = strCode + int1value;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	return strReturnRequestId;
	}
	
	@Override
	public PagedListHolder<Book> getLodgingRequestListPaging(HttpServletRequest request) {
		logger.info("In getBookListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<Book> pagedListHolder = new PagedListHolder<Book>(allBookList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}


@Override
	public List<Book> getBookList() {
		logger.info("getBookList() method in LibraryServiceImpl");
		allBookList = libraryDao.getBookList();
		return allBookList;
	}

@Override
public List<Book> getSearchBooksList(Map<String, Object> parameters) {
	logger.info("getSearchBooksList() method in LibraryServiceImpl");
	allBookList = libraryDao.getSearchBooksList(parameters);
	return allBookList;
}
	@Override
	public PagedListHolder<Book> getBookListPaging(HttpServletRequest request) {
		logger.info("In getBookListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<Book> pagedListHolder = new PagedListHolder<Book>(allBookList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}
	
	
	public Book getLendingHistory(String strBookCode) {
		logger.info("getLendingHistory(String strBookCode) method in LibraryServiceImpl");
		Book book = libraryDao.getLendingHistory(strBookCode);
		bookAllocationList = book.getBookAllocationList();
		return book;
	}
	
	@Override
	public PagedListHolder<BookAllocation> getLendHistoryPaging(HttpServletRequest request) {
		logger.info("In getBookStockPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<BookAllocation> pagedListHolder = new PagedListHolder<BookAllocation>(bookAllocationList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}	


	@Override
	public Book getBookIdList(Book book) {
		Book bookFromDB = null;
		Utility util = new Utility();
		try {
			logger.info("getBookIdList(String bookCode) method in LibraryServiceImpl");
			bookFromDB = libraryDao.getBookIdList(book);
			bookIdList = bookFromDB.getBookIdList();
			if(bookFromDB!=null && bookFromDB.getBookIdList()!= null){
				for (BookId bookId : bookFromDB.getBookIdList()) {
					bookId.setNewBookEntryDate(util.epochToHumanReadableFormat(new Integer(bookId.getNewBookEntryDate()).toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookFromDB;
	}
	
	@Override
	public PagedListHolder<BookId> getBookIdListPaging(HttpServletRequest request) {
		logger.info("In getBookIdListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<BookId> pagedListHolder = new PagedListHolder<BookId>(bookIdList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}



	public List<BookRequisition> viewRequisition() {		
		Utility util = new Utility();
		try {
			bookRequisitionList = libraryDao.viewRequisition();
			for (BookRequisition bookReq : bookRequisitionList) {
				if (bookReq.getBookRequisitionOpenDate() != null) {
					bookReq.setBookRequisitionOpenDate(util.epochToHumanReadableFormat(new Integer(bookReq.getBookRequisitionOpenDate()).toString()));
				}
				if (bookReq.getBookRequisitionCloseDate() != null) {
					bookReq.setBookRequisitionCloseDate(util.epochToHumanReadableFormat(new Integer(bookReq.getBookRequisitionCloseDate()).toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookRequisitionList;
	}
	
	
	@Override
	public PagedListHolder<BookRequisition> viewRequisitionListPaging(HttpServletRequest request) {
		logger.info("In getBookListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<BookRequisition> pagedListHolder = new PagedListHolder<BookRequisition>(bookRequisitionList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}



	@Override
	public List<BookPurchaseOrderDetails> getBookPurchaseOrderDetailsList() {
		logger.info("getBookPurchaseOrderDetailsList() method in LibraryServiceImpl");
		bookPurchaseOrderDetailsList = libraryDao.getBookPurchaseOrderDetailsList();
	return bookPurchaseOrderDetailsList;
	}
	
	@Override
	public List<BookPurchaseOrderDetails> getPurchaseOrderDetailsListSearch(Map<String, Object> parameters) {
		logger.info("getBookPurchaseOrderDetailsList() method in LibraryServiceImpl");
			bookPurchaseOrderDetailsList = libraryDao.getPurchaseOrderDetailsListSearch(parameters);
	return bookPurchaseOrderDetailsList;
	}
	@Override
	public PagedListHolder<BookPurchaseOrderDetails> getPurchaseOrderDetailsListPaging(HttpServletRequest request) {
		logger.info("In getBookListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<BookPurchaseOrderDetails> pagedListHolder = new PagedListHolder<BookPurchaseOrderDetails>(bookPurchaseOrderDetailsList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}	



	public List<BookRequest> getBookRequestDetails() {		
		Utility util = new Utility();
		try {
			bookRequestIdList = libraryDao.getBookRequestDetails();
			if (bookRequestIdList != null) {
				for (BookRequest bookRequest : bookRequestIdList) {
					if (bookRequest.getBookRequestOpenDate() != null) {
						bookRequest.setBookRequestOpenDate(util.epochToHumanReadableFormat(new Integer(bookRequest.getBookRequestOpenDate()).toString()));
					}
					if (bookRequest.getBookRequestCloseDate() != null) {
						bookRequest.setBookRequestCloseDate((util.epochToHumanReadableFormat(new Integer(bookRequest.getBookRequestCloseDate()).toString())));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookRequestIdList;
	}	
	
	@Override
	public PagedListHolder<BookRequest> getBookAllocationListPaging(HttpServletRequest request) {
		logger.info("In getBookListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<BookRequest> pagedListHolder = new PagedListHolder<BookRequest>(bookRequestIdList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}





@Override
	public VendorType getVendorAndBooks() {
		logger.info("getVendorAndBooks() method in LibraryServiceImpl");
		VendorType vType= libraryDao.getVendorAndBooks();		
		itemList = vType.getItemList(); 
		return vType;
	}
	
	@Override
	public PagedListHolder<Item> getmapBookVendorListPaging(HttpServletRequest request) {
		logger.info("In getBookListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<Item> pagedListHolder = new PagedListHolder<Item>(itemList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}






@Override
	public List<BookRequestDetails> getNotReturnedRequestedBookDetails() {
		logger.info("getNotReturnedRequestedBookDetails() method in LibraryServiceImpl");
		bookRequestDetailsList = libraryDao.getNotReturnedRequestedBookDetails();
		return bookRequestDetailsList;
	}
	
	@Override
	public PagedListHolder<BookRequestDetails> getAllocatedBookListPaging(HttpServletRequest request) {
		logger.info("In getBookListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<BookRequestDetails> pagedListHolder = new PagedListHolder<BookRequestDetails>(bookRequestDetailsList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}


public List<BookAllocation> getIssuedBookDetails(Resource resource) {
//		List<BookAllocation> issuedBookList = null;
		Utility util = new Utility();
		try {
			bookAllocationList = libraryDao.getIssuedBookDetails(resource);
			System.out.println("line 1039 List Size::"+bookAllocationList.size());
			if (bookAllocationList != null) {
				for (BookAllocation bookAllocation : bookAllocationList) {
					if (bookAllocation.getBookIssueDate() != null) {
						bookAllocation.setBookIssueDate(util.epochToHumanReadableFormat(new Integer(bookAllocation.getBookIssueDate()).toString()));
					}
					if (bookAllocation.getBookReturnDate() != null) {
						bookAllocation.setBookReturnDate(util.epochToHumanReadableFormat(new Integer(bookAllocation.getBookReturnDate()).toString()));
					}
					for (BookAllocationDetails bookAllocationDetails : bookAllocation.getBookAllocationDetails()) {
						if (bookAllocationDetails.getActualReturnDate() != null) {
							bookAllocationDetails.setActualReturnDate(util.epochToHumanReadableFormat(new Integer(bookAllocationDetails.getActualReturnDate()).toString()));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookAllocationList;
	}
	
	@Override
	public PagedListHolder<BookAllocation> getIssuedBookListPaging(HttpServletRequest request) {
		logger.info("In getBookListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<BookAllocation> pagedListHolder = new PagedListHolder<BookAllocation>(bookAllocationList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}





@Override
	public List<BookPurchaseOrder> getPurchaseOrdersInRequition(String requisitionCode) {
		logger.info("getPurchaseOrdersInRequition(String requisitionCode) method in LibraryServiceImpl");
		bookPurchaseOrderList = libraryDao.getPurchaseOrdersInRequition(requisitionCode);
		return bookPurchaseOrderList;
	}
	
	@Override
	public PagedListHolder<BookPurchaseOrder> getBookPurchaseOrderListPaging(HttpServletRequest request) {
		logger.info("In getBooksForAddThresholdPaging(	HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<BookPurchaseOrder> pagedListHolder = new PagedListHolder<BookPurchaseOrder>(bookPurchaseOrderList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}





	public BookRequest getRequestedBookDetails(String requestedBookId) {
		BookRequest bookRequest = null;
		Utility util = new Utility();
		try {
			bookRequest = libraryDao.getRequestedBookDetails(requestedBookId);
			if (bookRequest != null) {
				bookAllocationList = bookRequest.getBookAllocationList();
				bookRequestDetailsList = bookRequest.getBookRequestDetailsList();
				if (bookRequest.getBookRequestOpenDate() != null) {
					bookRequest.setBookRequestOpenDate(util.epochToHumanReadableFormat(new Integer(bookRequest.getBookRequestOpenDate()).toString()));
				}
				if (bookRequest.getBookRequestCloseDate() != null) {
					bookRequest.setBookRequestCloseDate(util.epochToHumanReadableFormat(new Integer(bookRequest.getBookRequestCloseDate()).toString()));
				}
				if (bookRequest.getBookAllocationList() != null) {
					for (BookAllocation bookAllocation : bookRequest
							.getBookAllocationList()) {
						if (bookAllocation.getBookIssueDate() != null) {
							bookAllocation.setBookIssueDate((util.epochToHumanReadableFormat(new Integer(bookAllocation.getBookIssueDate()).toString())));
						}
						if (bookAllocation.getBookReturnDate() != null) {
							bookAllocation.setBookReturnDate((util.epochToHumanReadableFormat(new Integer(bookAllocation.getBookReturnDate()).toString())));
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookRequest;
	}


	@Override
	public PagedListHolder<BookRequestDetails> getIssuingBookListPaging(HttpServletRequest request) {
		logger.info("In getBookListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<BookRequestDetails> pagedListHolder = new PagedListHolder<BookRequestDetails>(bookRequestDetailsList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}


	@Override
	public List<BookLanguage> getbookLanguageList() {
		return libraryDao.getbookLanguageList();
	}
	
	@Override
	public List<Book> getRetiredBookList() {
		allBookList = libraryDao.getRetiredBookList();
		return  allBookList;
	}
	@Override
	public List<Book> getRetiredBookSearch(Map<String, Object> parameters) {
		allBookList = libraryDao.getRetiredBookSearch(parameters);
		return  allBookList;
	}
	
	
	@Override
	public Integer getMaxNoOfBooksPerRequest(String userId) {
		return libraryDao.getMaxNoOfBooksPerRequest(userId);
	}
	
	@Override
	public PagedListHolder<Book> getRetiredBookListPaging(HttpServletRequest request) {
		logger.info("In getRetiredBookListPaging(HttpServletRequest request) method of LibraryServiceImpl");
		PagedListHolder<Book> pagedListHolder = new PagedListHolder<Book>(allBookList);
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);
		pagedListHolder.setPage(page);
		int pageSize = 5;
		pagedListHolder.setPageSize(pageSize);
		return pagedListHolder;
	}
	
	
	@Override
	public List<LoggingAspect> getUpdateBookLogDetails(String bookCode) {
		return  libraryDao.getUpdateBookLogDetails(bookCode);
	}
	
	@Override
	public List<LoggingAspect> getRetiredBookLogDetails(String bookCode) {
		return  libraryDao.getRetiredBookLogDetails(bookCode);
	}
	
	@Override
	public String getVendorBookPriceHistory(Item item) {
		String priceHistory = libraryDao.getVendorBookPriceHistory(item);
		return priceHistory;
	}
/**
 * gap work started**/
	
	
	@Override
	public List<Genre> getGenreList() {
		return libraryDao.getGenreList();
	}

	@Override
	public List<BookAllocation> getReadingHabitOfResource(Resource resource) {
		List<BookAllocation> readingHabitOfResource = null;
		Utility util = new Utility();
		try{
			readingHabitOfResource = libraryDao.getReadingHabitOfResource(resource);
			System.out.println("line 1206 List Size::"+readingHabitOfResource.size());
			if (bookAllocationList != null) {
				for (BookAllocation bookAllocation : bookAllocationList) {
					if (bookAllocation.getBookIssueDate() != null) {
						bookAllocation.setBookIssueDate(util.epochToHumanReadableFormat(new Integer(bookAllocation.getBookIssueDate()).toString()));
					}
					if (bookAllocation.getBookReturnDate() != null) {
						bookAllocation.setBookReturnDate(util.epochToHumanReadableFormat(new Integer(bookAllocation.getBookReturnDate()).toString()));
					}
					if(null!= bookAllocation.getBookAllocationDetails()){
						for (BookAllocationDetails bookAllocationDetails : bookAllocation.getBookAllocationDetails()) {
							if (bookAllocationDetails.getActualReturnDate() != null) {
								bookAllocationDetails.setActualReturnDate(util.epochToHumanReadableFormat(new Integer(bookAllocationDetails.getActualReturnDate()).toString()));
							}
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return readingHabitOfResource;
	}
	
	@Override
	public List<BookAllocation> getBookAllocatedStudentDetailsList() throws CustomException {
		return libraryDao.getBookAllocatedStudentDetailsList();
	}
	
	@Override
	public List<Department> getDepartmentForBookRequisition() {
		return libraryDao.getDepartmentForBookRequisition();
	}
	
	@Override
	public String getBankAllDetails(String bank){
		return libraryDao.getBankAllDetails(bank);
	}
	
	@Override
	public List<String> getUserIdList(String strQuery) {
		return libraryDao.getUserIdList(strQuery);
	}

	@Override
	public int getMaximumNumberBook(String bookRequestedFor) {
		return libraryDao.getMaximumNumberBook(bookRequestedFor);
	}
	
	/**
	 * @author anup.roy
	 * this method is for get the categories of resources in library*/
	@Override
	public List<BookCategory> getAllCategories(){
		return libraryDao.getAllCategories();
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting the last accession no for entry of a book*/
	@Override
	public int getLastAccessionNo(){
		return libraryDao.getLastAccessionNo();
	}
	/**
	 * @author anup.roy
	 * this method is for adding a magazine in library */
	@Override
	public String addNewMagazine(Magazine mag) {
		return libraryDao.addNewMagazine(mag);
	}
	/**
	 * @author Sourav.Bhadra on 12-02-2018
	 */
	@Override
	public List<Magazine> getMagazineList() {
		return libraryDao.getMagazineList();
	}
	/**
	 * @author Sourav.Bhadra on 15-02-2018
	 */
	@Override
	public void updateMagazine(Magazine magazine) {
		libraryDao.updateMagazine(magazine);
	}

	/**
	 * @author anup.roy
	 * this method is for adding a new book to catalogue*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String addBookToCatalogueFromLibrary(Book book) {
		return libraryDao.addBookToCatalogueFromLibrary(book);
	}

	/**
	 * @author anup.roy
	 * this method is for adding a new magazine to catalogue*/
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public String addMagazineToCatalogueFromLibrary(Magazine mag) {
		return libraryDao.addMagazineToCatalogueFromLibrary(mag);
	}

	/**
	 * @author anup.roy
	 * this method is for fetching all items in catalogue*/
	@Override
	public List<Item> getListOfItemsFromCatalogue(String category) {
		return libraryDao.getListOfItemsFromCatalogue(category);
	}
	
	/**
	 * @author anup.roy
	 * this method is for fetching all details of book from library catalogue w.r.t code*/
	@Override
	public Book getAllDetailsOfBooksFromCatalogue(String bookCode) {
		return libraryDao.getAllDetailsOfBooksFromCatalogue(bookCode);
	}

	/**
	 * @author anup.roy
	 * this method is for fetching all details of magazines from library catalogue w.r.t code*/
	@Override
	public Magazine getAllDetailsOfMagazinesFromCatalogue(String magCode) {
		return libraryDao.getAllDetailsOfMagazinesFromCatalogue(magCode);
	}
}
