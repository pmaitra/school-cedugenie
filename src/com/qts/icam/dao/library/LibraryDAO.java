package com.qts.icam.dao.library;

import java.util.List;
import java.util.Map;
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
import com.qts.icam.model.library.BookStatus;
import com.qts.icam.model.library.Magazine;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Genre;

public interface LibraryDAO {

	/**
	 * @author anup.roy*/
	public String addNewBook(Book book);

	public BookStatus getBookStatus();

	public List<Book> getBookStock();

	public List<Book> searchForViewBookStock(Map<String, Object> parameters);

	public List<Book> searchListOnRetirementBook(Map<String, Object> parameters);

	//public Book getBookProfile(String bookCode);

	public Book getLendingHistory(String strBookCode);

	public Book getBookIdList(Book book);

	public List<Book> removeBookIds(List<BookId> bookIdList);

	public Book getRetiredBookDetails(String bookCode);

	public BookRequisition getLastRequisitionId();

	public List<BookRequisition> addRequisition(BookRequisition bookRequisition, String updatedBy);

	public List<BookRequisition> viewRequisition();

	public BookStatus addToCatalog(BookRequisitionDetails bookRequisitionDetails);

	public BookRequisition getRequisitionDetails(String requisitionCode);

	public List<BookRequisition> getRequisitionFulfillment(
			List<BookRequisitionDetails> bookRequisitionDetailsList);

	public BookRequisition viewRequisitionDetails(
			BookRequisition bookRequisition);

	public List<BookRequisition> getRequisitionSearchList(
			Map<String, Object> parameters);

	public List<Book> createLodgingRequest();

	public List<Book> searchLodgingRequest(Map<String, Object> parameters);

	public Integer submitLodgingRequest(BookRequest bookRequest);

	public List<BookRequest> getBookRequestDetails();

	public List<BookRequest> searchForBookRequest(Map<String, Object> parameters);

	public List<BookRequest> submitIssuingBookForBookAllocation(
			BookAllocation bookAllocation);

	public BookRequest getRequestedBookDetails(String requestedBookId);

	public List<BookAllocation> getIssuedBookDetails(Resource resource);

	public void submitReturnBookDetails(
			List<BookAllocationDetails> bookAllocationDetailsList,
			Map<String, Object> bookRatingMap);

	public BookRequisition getBookRequisitionDetails(
			BookRequisition bookRequisition);

	public List<BookRequisition> savePaymentForBookRequisition(
			BookRequisition bookRequisition, String updatedBy);

	public List<Book> getBookNameDB();

	public Book getAllBookDetailsDB(String bookName);

	public List<Vendor> bookVendorList();

	public List<Vendor> getBookVendorSearchList(Map<String, Object> parameters);

	public VendorType getVendorAndBooks();

	public String getVendorBooks(String vendorCode);

	public int updateBookVendorMaping(Vendor vendor, String updatedBy);

	public Vendor getVendorBookList(Vendor vendor);

	public List<Item> getBooksForAddThreshold();

	public int updateThresholdForBook(List<Item> itemList);

	public List<Item> searchThresholdDetailsForBook(
			Map<String, Object> parameters);

	public List<BookAllocationDetails> getLendingDates(Book book);

	public String getCodeForBook(String bookName);

	public String checkAvailabilityForCode(String bookCode);

	public List<String> getAuthorNameDB(String strQuery);

	/**
	 * @author anup.roy*/
	public List<String> getPublisherNameDB();

	public List<BookRequestDetails> getNotReturnedRequestedBookDetails();

	public List<BookPurchaseOrder> getPurchaseOrdersInRequition(String requisitionCode);

	public List<BookPurchaseOrderDetails> getPurchaseOrdersDetailsInPurchaseOrders(
			String purchaseOrderCode);

	public void updatePurchaseOrderDetails(BookPurchaseOrder bookPurchaseOrder,
			List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList);

	public BookPurchaseOrder getPurchaseOrdersForPayment(
			String purchaseOrderCode);

	public void updatePurchaseOrderPayment(BookPurchaseOrder bookPurchaseOrder);

	public List<BookPurchaseOrderDetails> getBookPurchaseOrderDetailsList();

	public void updateAddedToCatalogue(Book book);

	public Book getBookCodeForBookName(String bookName);

	public List<Vendor> getAllVenoderDetailsDBForBook(Book book);

	public List<String> getVendorNameDB(String strQuery);

	public List<BookPurchaseOrder> getpurchaseOrderForBook(
			BookPurchaseOrder bookPurchaseOrderObj, String updatedBy);

	public List<String> getEditionDB(String strQuery);

	public List<Book> getBookList();

	public Book getBookDetails(Book book);

	public String updateBook(Book book, String updatedBy);		// modified by Saif 21-03-2018

	public List<BookLanguage> getbookLanguageList();

	public List<Book> getRetiredBookList();

	public Integer getMaxNoOfBooksPerRequest(String userId);

	public List<Book> getSearchBooksList(Map<String, Object> parameters);

	public List<BookPurchaseOrderDetails> getPurchaseOrderDetailsListSearch(Map<String, Object> parameters);

	public List<Book> getRetiredBookSearch(Map<String, Object> parameters);

	public BookRequest getLastRequestId();

	public List<LoggingAspect> getUpdateBookLogDetails(String bookCode);

	public List<LoggingAspect> getRetiredBookLogDetails(String bookCode);

	//public List<BookRequisitionDetails> getBookWiseRequisition();
	
	public String getVendorBookPriceHistory(Item item);

	public String getNameOfVendors();

	public String getIdAgainstName(Vendor vendor);
/**
 * gap work Started*/
	public List<Genre> getGenreList();

	public List<BookAllocation> getReadingHabitOfResource(Resource resource);

	public List<BookAllocation> getBookAllocatedStudentDetailsList() throws CustomException;

	public List<Department> getDepartmentForBookRequisition();
	
	public String getBankAllDetails(String bank);
	
	public List<String> getUserIdList(String strQuery);

	public int getMaximumNumberBook(String bookRequestedFor);
	/**
	 * @author anup.roy*/
	public List<BookCategory> getAllCategories();
	/**
	 * @author anup.roy*/
	public int getLastAccessionNo();
	/**
	 * @author anup.roy*/
	public String addNewMagazine(Magazine mag);
	/**
	 * @author Sourav.Bhadra on 12-02-2018
	 */
	public List<Magazine> getMagazineList();
	/**
	 * @author Sourav.Bhadra on 15-02-2018
	 */
	public void updateMagazine(Magazine magazine);

	/**
	 * @author anup.roy
	 * this method is for adding a new book to catalogue*/
	public String addBookToCatalogueFromLibrary(Book book);

	/**
	 * @author anup.roy
	 * this method is for adding a new magazine to catalogue*/
	public String addMagazineToCatalogueFromLibrary(Magazine mag);

	/**
	 * @author anup.roy
	 * this method is for fetching all items from catalogue*/
	public List<Item> getListOfItemsFromCatalogue(String category);
	
	/**
	 * @author anup.roy
	 * this method is for fetching all details of book from library catalogue w.r.t code*/
	public Book getAllDetailsOfBooksFromCatalogue(String bookCode);

	/**
	 * @author anup.roy
	 * this method is for fetching all details of magazines from library catalogue w.r.t code*/
	public Magazine getAllDetailsOfMagazinesFromCatalogue(String magCode);
}
