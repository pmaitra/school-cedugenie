package com.qts.icam.dao.impl.library;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.qts.icam.dao.library.LibraryDAO;
import com.qts.icam.model.common.Item;
import com.qts.icam.model.common.Ledger;
import com.qts.icam.model.common.LoggingAspect;
import com.qts.icam.model.common.Resource;
import com.qts.icam.model.common.Vendor;
import com.qts.icam.model.common.VendorType;
import com.qts.icam.model.finance.Transaction;
import com.qts.icam.model.finance.TransactionDetails;
//import com.qts.icam.model.finance.PreviousYearFinanceData;
import com.qts.icam.model.library.Author;
import com.qts.icam.model.library.Book;
import com.qts.icam.model.library.BookAllocation;
import com.qts.icam.model.library.BookAllocationDetails;
import com.qts.icam.model.library.BookAuthor;
import com.qts.icam.model.library.BookCategory;
import com.qts.icam.model.library.BookId;
import com.qts.icam.model.library.BookLanguage;
import com.qts.icam.model.library.BookLifeCycleStatus;
import com.qts.icam.model.library.BookMedium;
import com.qts.icam.model.library.BookOperationalStatus;
import com.qts.icam.model.library.BookPublisher;
import com.qts.icam.model.library.BookPurchaseOrder;
import com.qts.icam.model.library.BookPurchaseOrderDetails;
import com.qts.icam.model.library.BookRating;
import com.qts.icam.model.library.BookRequest;
import com.qts.icam.model.library.BookRequestDetails;
import com.qts.icam.model.library.BookRequisition;
import com.qts.icam.model.library.BookRequisitionDetails;
import com.qts.icam.model.library.BookRequisitionReceivedDates;
import com.qts.icam.model.library.BookStatus;
import com.qts.icam.model.library.BookWaiting;
import com.qts.icam.model.library.Magazine;
import com.qts.icam.model.library.MagazinePublisher;
import com.qts.icam.model.ticket.ServiceType;
import com.qts.icam.model.ticket.Ticket;
import com.qts.icam.model.administrator.Approver;
import com.qts.icam.model.common.Department;
import com.qts.icam.model.common.Genre;
import com.qts.icam.utility.Utility;
import com.qts.icam.utility.customexception.CustomException;
import com.qts.icam.utility.date.DateUtility;

/**
 * LibraryDAO.java - This DAO is responsible for CRUD operation on library
 * related data.
 * 
 * @author sovan.mukherjee
 * @version 1.0
 */
@Repository
public class LibraryDAOImpl implements LibraryDAO {

	public static Logger logger = Logger.getLogger(LibraryDAOImpl.class);
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	/**
	 * @author anup.roy
	 * This addNewBook() method is used to enter new Book details into the database
	 * @param Book
	 */
	public String addNewBook(Book book) {
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		String returnStatus = "fail";
		try {
			/* Inserting Publisher Information */
			//String bookPeriodicityPublisher = book.getBookPeriodicityPublisher();
			/*BookPublisher bookPublisher = book.getBookPublisher();
			String publisherName = bookPublisher.getBookPublisherName();
			bookPublisher.setBookPublisherObjectId(util.getBase64EncodedID("LibraryDAO"));
			bookPublisher.setUpdatedBy(book.getUpdatedBy());
			bookPublisherRecordFromDB = session.selectOne("getBookPeriodicityPublisher",publisherName);
			if(null == bookPublisherRecordFromDB){
				session.insert("insertBookPublisher", bookPublisher);
				session.commit();
			}
			book.setBookPublisherId(publisherName);*/
			book.setBookObjectId(util.getBase64EncodedID("LibraryDAO"));
			/* Inserting Book Medium Information */
			/*BookMedium bookMedium = book.getBookMedium();
			bookMedium.setBookMediumObjectId(util.getBase64EncodedID("LibraryDAO"));
			bookMedium.setUpdatedBy(book.getUpdatedBy());
			session.insert("insertMedium", bookMedium);
			session.commit();*/
			/* Inserting Book Language Information */
			/*BookLanguage booklanguage = book.getBookLanguage();
			booklanguage.setBookLanguageObjectId(util.getBase64EncodedID("LibraryDAO"));
			booklanguage.setUpdatedBy(book.getUpdatedBy());
			session.insert("insertLanguage", booklanguage);
			session.commit();*/
			/* Inserting Book Genre Information 
			Genre genre = book.getGenre();
			genre.setGenreObjectId(util.getBase64EncodedID("LibraryDAO"));
			genre.setUpdatedBy(book.getUpdatedBy());
			session.insert("insertGenre", genre);
			session.commit();*/
			/* Inserting Book Information */
			/*book.setBookLanguageId(booklanguage.getBookLanguageObjectId());
			book.setBookMediumId(bookMedium.getBookMediumObjectId());*/
			/* Calling insert tag with id="addNewBook" in BookMapper.xml */
			//System.out.println("genre:::"+book.getGenre().getGenreName());
			/**
			 * @author anup.roy
			 * this will insert all info of book in book table
			 * except author details*/
			int insertStatus = session.insert("addNewBook", book);
			session.commit();
			/*List<Author> authorListToDB = book.getBookAuthorList();
			for (Author author : authorListToDB) {
				Author authorToInsert = new Author();
				authorToInsert.setAuthorObjectId(util.getBase64EncodedID("LibraryDAO"));
				authorToInsert.setUpdatedBy(book.getUpdatedBy());
				authorToInsert.setAuthorFullName(author.getAuthorFullName());
				*//** 
				 * Inserting Author Information in author table
				 * *//*
				session.insert("insertAuthorList", authorToInsert);
				session.commit();

				BookAuthor bookAuthor = new BookAuthor();
				bookAuthor.setUpdatedBy(book.getUpdatedBy());
				bookAuthor.setBookAuthorObjectId(util.getBase64EncodedID("LibraryDAO"));
				bookAuthor.setBookObjectId(book.getAccessionNumber());
				bookAuthor.setAuthorObjectId(authorToInsert.getAuthorFullName());
				*//** 
				 * Inserting Author Information in book_author table
				 * this will associate the book and author list
				 * *//*
				session.insert("insertBookAuthor", bookAuthor);
				session.commit();
			}*/
			if (insertStatus != 0) {
				returnStatus = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return returnStatus;
	}

	@Override
	public String getCodeForBook(String bookName) {
		String bookCodeDB = "";
		SqlSession session = sqlSessionFactory.openSession();
		try {		
			bookCodeDB = session.selectOne("getCodeForBook", bookName);
		} catch (Exception e) {
			logger.error(
					"Exception in searchThresholdDetailsForBook(Map<String, Object> parameters) method in LibraryDAOImpl ",
					e);
		}
		return bookCodeDB;
	}

	@Override
	public String checkAvailabilityForCode(String bookCode) {
		String bookCodeDB = "";
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			bookCodeDB = session
					.selectOne("checkAvailabilityForCode", bookCode);
		} catch (Exception e) {
			logger.error(
					"Exception in searchThresholdDetailsForBook(Map<String, Object> parameters) method in LibraryDAOImpl ",
					e);
		}
		return bookCodeDB;
	}

	@Override
	public List<String> getAuthorNameDB(String strQuery) {
		List<String> authorList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getAuthorNameDB() method in LibraryDAOImpl");			
			authorList = session.selectList("getAllAuthorNames", strQuery);
		} catch (Exception e) {
			logger.error(
					"getAuthorNameDB() method in LibraryDAOImpl Exception", e);
		}
		return authorList;
	}

	@Override
	public List<String> getPublisherNameDB() {
		List<String> publisherList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getPublisherNameDB() method in LibraryDAOImpl.java");
			publisherList = session.selectList("getAllPublishersName");
		} catch (Exception e) {
			logger.error("In getPublisherNameDB() method in LibraryDAOImpl Exception",e);
			e.printStackTrace();
		}
		return publisherList;
	}

	@Override
	public List<BookRequestDetails> getNotReturnedRequestedBookDetails() {
		List<BookRequestDetails> bookRequestDetailsList = new ArrayList<BookRequestDetails>();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			bookRequestDetailsList = session
					.selectList("getNotReturnedRequestedBookDetails");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookRequestDetailsList;
	}

	/**
	 * This method is used to get List of Book Status
	 * 
	 * @return BookStatus
	 */
	public BookStatus getBookStatus() {
		SqlSession session = sqlSessionFactory.openSession();
		List<BookOperationalStatus> bookOperationalStatusList = null;
		List<BookLifeCycleStatus> bookLifeCycleStatusList = null;
		BookStatus bookStatus = new BookStatus();
		try {
			bookOperationalStatusList = session.selectList("selectOperationalStatusList");
			bookLifeCycleStatusList = session.selectList("selectLifeCycleStatusList");
			bookStatus.setBookLifeCycleStatusList(bookLifeCycleStatusList);
			bookStatus.setBookOperationalStatusList(bookOperationalStatusList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookStatus;
	}

	/**
	 * This getBookStock() method fetches list of books and return List<Book>
	 * type of object.
	 * 
	 * @return List<Book>
	 */
	public List<Book> getBookStock() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Book> bookListFromDB = null;
		try {
			/* Calling select tag with id="selectBookStock" to BookMapper.xml */
			bookListFromDB = session.selectList("selectBookStock");
			if(bookListFromDB != null && bookListFromDB.size()!=0){
				for(Book book : bookListFromDB){
					Integer userBookRating = session.selectOne("selectUserBookRating",book);
					if(userBookRating != null){
						book.setUserBookRating(userBookRating);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Inside Catch from getBookStock() of LibraryDAOImpl ",
					e);
		} finally {
			session.close();
		}
		return bookListFromDB;
	}

	public List<Book> searchForViewBookStock(Map<String, Object> parameters) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Book> bookListFromDB = null;
		try {
			bookListFromDB = session.selectList("searchForViewBookStock",parameters);
			if(bookListFromDB != null && bookListFromDB.size()!=0){
				for(Book book : bookListFromDB){
					Integer userBookRating = session.selectOne("selectUserBookRating",book);
					if(userBookRating != null){
						book.setUserBookRating(userBookRating);
					}
				}
			}
		}catch (Exception e) {
			logger.error("Exception in searchForViewBookStock() method in LibraryDAOImpl",e);
		} finally {
			session.close();
		}
		return bookListFromDB;
	}

	/**
	 * 
	 * @param parameters
	 * @return
	 */
	public List<Book> searchListOnRetirementBook(Map<String, Object> parameters) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Book> bookListFromDB = null;
		try {
			bookListFromDB = session.selectList("searchForViewBookStock",
					parameters);
			if (bookListFromDB != null) {
				logger.info("From getBookStock() of LibraryDAO - Size of stock  list is : "
						+ bookListFromDB.size());
			} else {
				logger.info("From getBookStock() of LibraryDAO - DATA NOT RETURNED....");
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return bookListFromDB;
	}

	/**
	 * This getBookProfile() method fetches profile of books and return Book
	 * type of object.
	 * 
	 * @param book
	 * @return Book
	 */
//	public Book getBookProfile(String bookCode) {
//		Book book = null;
//		SqlSession session = sqlSessionFactory.openSession();
//		try {
//			book = session.selectOne("selectBookProfile", bookCode);
//		} catch (Exception e) {
//			logger.error(e);
//		} finally {
//			session.close();
//		}
//		return book;
//	}

	/**
	 * This getLendingHistory() method fetches profiles and lending history on
	 * particular book and return Book type of object.
	 * 
	 * @param strBookCode
	 * @return Book
	 */
	public Book getLendingHistory(String strBookCode) {
		Book book = new Book();
		List<BookAllocation> bookAllocationList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			book.setBookCode(strBookCode);
			book = session.selectOne("getBookDetailsToEdit", book);
			
			List<Author> bookAuthorList = session.selectList("getBookAuthorList", book);
			if (bookAuthorList != null && bookAuthorList.size() != 0) {
				book.setBookAuthorList(bookAuthorList);
			}
			List<BookAllocation> studentBookAllocationList = session.selectList("selectBookAllocationListForStudent",strBookCode);
			
			if (studentBookAllocationList != null && studentBookAllocationList.size() != 0) {
				for (BookAllocation ba : studentBookAllocationList) {
					ba.setBook(book);
					BookRating br = session.selectOne("selectBookRatingForBookCodeAndUser", ba);
					ba.setBookRating(br);
				}
				bookAllocationList = studentBookAllocationList;
			}
			List<BookAllocation> staffBookAllocationList = session.selectList("selectBookAllocationListForStaff", strBookCode);
			
			if (staffBookAllocationList != null && staffBookAllocationList.size() != 0) {
				if (bookAllocationList != null && bookAllocationList.size() != 0) {
					for (BookAllocation ba : staffBookAllocationList) {
						ba.setBook(book);
						BookRating br = session.selectOne("selectBookRatingForBookCodeAndUser", ba);
						ba.setBookRating(br);
						bookAllocationList.add(ba);
					}
				} else {
					for (BookAllocation ba : staffBookAllocationList) {
						ba.setBook(book);
						BookRating br = session.selectOne("selectBookRatingForBookCodeAndUser", ba);
						ba.setBookRating(br);
					}
					bookAllocationList = staffBookAllocationList;
				}
			}
			
			if (book != null) {
				//logger.error("From getLendingHistory() of LibraryDAO - Size of Book Allocation List is : ");
				book.setBookAllocationList(bookAllocationList);
			} else {
				//logger.debug("From getLendingHistory() of LibraryDAO - DATA NOT RETURNED....");
			}
		} catch (Exception e) {
			//logger.error(e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	@Override
	public List<BookAllocationDetails> getLendingDates(Book book) {
		List<BookAllocationDetails> badList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			badList = session.selectList(
					"selectIssueDateAndReturnDateForStaffStudent", book);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return badList;
	}

	/**
	 * This method is used to get a list of BookIds for particular Book Code
	 * 
	 * @return Book
	 */
	@Override
	public Book getBookIdList(Book book) {
		
		List<BookId> bookIdList = null;
		List<Author> authorList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {	
			Book bookSearch=book;
			book = session.selectOne("getBookDetails", book.getBookCode());

			bookIdList = session.selectList("getBookIdList", bookSearch);
			authorList = session.selectList("getAuthorList", book.getBookCode());
			if (bookIdList != null && bookIdList.size() != 0) {
				book.setBookIdList(bookIdList);
			}
			if (authorList != null && authorList.size() != 0) {
				book.setBookAuthorList(authorList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	/**
	 * This method is used to get a list of BookIds for particular Book Code
	 * @param List
	 * @return none
	 * modified by ranita.sur 26062017
	 */
	public List<Book> removeBookIds(List<BookId> bookIdList) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Book> retiredBookListFromDB = null;
		try {
			if(bookIdList != null && bookIdList.size()!=0){
				for (BookId bookId : bookIdList) {
					session.update("makeBookRetire", bookId);
				}
			}
			session.commit();
			retiredBookListFromDB = session.selectList("getRetiredBookList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return retiredBookListFromDB;
	}
	
	@Override
	public List<Book> getRetiredBookList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Book> retiredBookListFromDB = null;
		try {
			retiredBookListFromDB = session.selectList("getRetiredBookList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	return retiredBookListFromDB;
	}
	
	@Override
	public List<Book> getRetiredBookSearch(Map<String, Object> parameters) {
		SqlSession session = sqlSessionFactory.openSession();
		List<Book> retiredBookListFromDB = null;
		try {
			retiredBookListFromDB = session.selectList("getRetiredBookList",parameters);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	return retiredBookListFromDB;
	}

	/**
	 * This method is used to get the Retired Book Details
	 * 
	 * @param bookCode
	 * @return
	 */
	public Book getRetiredBookDetails(String bookCode) {
		Book book = null;
		List<BookId> bookIdList = null;
		List<Author> authorList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			book = session.selectOne("getBookDetails", bookCode);
			if(book!=null){
				bookIdList = session.selectList("getBookDetailsList", bookCode);
				authorList = session.selectList("getAuthorList", bookCode);
				book.setBookIdList(bookIdList);
				book.setBookAuthorList(authorList);
			}else {
				logger.info("FROM getRetiredBookDetails() of LibraryDAO book.getBookIdList().size(): ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	public BookRequisition getLastRequisitionId() {
		SqlSession session = sqlSessionFactory.openSession();
		BookRequisition bookRequisition = new BookRequisition();
		List<Vendor> vendorList = new ArrayList<Vendor>();
		try {
			vendorList = session.selectList("getBookVendorList");
			BookRequisition bookRequisitionfromDb = session.selectOne("getLastRequisitionId");
			if (bookRequisitionfromDb != null) {
				bookRequisition.setBookRequisitionCode(bookRequisitionfromDb
						.getBookRequisitionCode());
			}
			if (vendorList != null && vendorList.size() != 0) {
				bookRequisition.setVendorList(vendorList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookRequisition;
	}

	


	/**
		 * This method is used to insert Book Requisition Details
		 * 
		 * @author sayani.datta
		 * @param bookRequisition
		 * 
		 * 
		 * method modified by ranita.sur
		 * changes taken on 12042017
		 * revert back on 12042017
		 */
	public List<BookRequisition> addRequisition(BookRequisition bookRequisition, String updatedBy) {
		Utility util = new Utility();
		SqlSession session = sqlSessionFactory.openSession();
		List<BookRequisition> bookRequisitionList = null;
		try {
			Vendor vendor = bookRequisition.getVendor();
			List<Author> authorListDB = session.selectList("authorList");
			List<BookRequisitionDetails> bookRequisitionDetailsForAuthor = bookRequisition.getBookRequisitionDetailsList();
			for (BookRequisitionDetails bookRequisitionDetails : bookRequisitionDetailsForAuthor) {
				/* Author insertion */
				Map<String, Author> authorMap = new HashMap<String, Author>();
				String totalAuthorName = bookRequisitionDetails.getBookAuthor();
				String[] arrAuthorName = totalAuthorName.split("~");
				for (int a = 0; a < arrAuthorName.length; a++) {
					Author author = new Author();
					author.setAuthorObjectId(util.getBase64EncodedID("LibraryDAO"));
					author.setUpdatedBy(updatedBy);
					author.setAuthorFullName(arrAuthorName[a]);
					authorMap.put(arrAuthorName[a], author);
				}
				/* list checking starts */
				List<Author> authorList = new ArrayList<Author>();
				if (authorListDB != null && authorListDB.size() != 0) {
					for (Author author : authorListDB) {
						if (authorMap != null && authorMap.size() != 0) {
							if (authorMap.containsKey(author.getAuthorFullName())) {
								if (authorMap.get(author.getAuthorFullName()).equals(author.getAuthorFullName())) {
									authorMap.remove(author.getAuthorFullName());
								}
							}
						}
					}
				}
				for (Object key : authorMap.keySet()) {
					if(authorMap.get(key).getAuthorFullName() != ""){
						authorList.add(authorMap.get(key));
					}
					
				}
				for (Author author : authorList) {
					session.insert("insertAuthorList", author);
					session.commit();
				}
				/* Publisher insertion */
				String publishername = bookRequisitionDetails.getBookPublisher();
				BookPublisher bookPublisher = new BookPublisher();
				bookPublisher.setBookPublisherObjectId(util.getBase64EncodedID("LibraryDAO"));
				List<BookPublisher> publisherListDB = session.selectList("publisherList");
				bookPublisher.setUpdatedBy(updatedBy);
				bookPublisher.setBookPublisherName(publishername);
				Set<BookPublisher> authorSet = new HashSet<BookPublisher>();
				authorSet.add(bookPublisher);
				if (publisherListDB != null && publisherListDB.size() != 0) {
					for (BookPublisher publisher : publisherListDB) {
						if (authorSet.contains(publisher.getBookPublisherName().equals(publisher.getBookPublisherName()))) {
							authorSet.remove(publisher);
						}
					}
				}
				if (authorSet != null && authorSet.size() != 0) {
					for (BookPublisher publisher : authorSet) {
						session.insert("insertBookPublisher", publisher);
						session.commit();
					}
				}
			}
			/* Inserting BookRequisition Information */
			bookRequisition.setBookRequisitionObjectId(util.getBase64EncodedID("LibraryDAO"));
			bookRequisition.setVendorName(vendor.getVendorName());
			bookRequisition.setUpdatedBy(updatedBy);
			session.insert("insertRequisition", bookRequisition);
			session.commit();

			/* Inserting BookRequisitionDetails Information */
			List<BookRequisitionDetails> bookRequisitionDetailsList = bookRequisition.getBookRequisitionDetailsList();
			for (BookRequisitionDetails bookRequisitionDetails : bookRequisitionDetailsList) {
				bookRequisitionDetails.setBookRequisitionDetailsObjectId(util.getBase64EncodedID("LibraryDAO"));
				bookRequisitionDetails.setUpdatedBy(updatedBy);
				bookRequisitionDetails.setBookRequisitionDetailsCode("hardcodeCode");
				bookRequisitionDetails.setBookRequisitionCode(bookRequisition.getBookRequisitionObjectId());
				//bookRequisitionDetails.setBookAuthor(bookRequisitionDetails.getBookAuthor().replace("~", ","));
				int insertStatus = session.insert("insertRequisitionDetails",bookRequisitionDetails);
				logger.info("addRequisition(BookRequisition bookRequisition)method in LibraryDAOImpl::: "+ insertStatus);
				session.commit();
				bookRequisitionList = session.selectList("getAllBookRequisition");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookRequisitionList;
	}


	/**
	 * 
	 * @return
	 */
	public List<BookRequisition> viewRequisition() {
		SqlSession session = sqlSessionFactory.openSession();
		List<BookRequisition> bookRequisitionList = null;
		try {
			bookRequisitionList = session.selectList("getAllBookRequisition");
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
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
		SqlSession session = sqlSessionFactory.openSession();
		List<BookRequisitionDetails> bookRequisitionList = null;
		try {
			requisitionDetails = session.selectOne("selectRequisitionDetails",requisitionCode);
			bookRequisitionList = session.selectList("selectBookRequisitionList", requisitionCode);
			if (bookRequisitionList != null && bookRequisitionList.size() != 0) {
				requisitionDetails.setBookRequisitionDetailsList(bookRequisitionList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return requisitionDetails;
	}

	/**
	 * 
	 */
	public BookStatus addToCatalog(BookRequisitionDetails bookRequisitionDetails) {
		BookStatus bookStatus = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			BookRequisitionDetails brd = new BookRequisitionDetails();
			bookStatus = getBookStatus();
			session = sqlSessionFactory.openSession();
			brd = session.selectOne("addToCatalogFromBookRequisitionDetails",
					bookRequisitionDetails);
			if (brd != null) {
				bookStatus.setBookRequisitionDetails(brd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookStatus;
	}

	/**
	 * This getRequisitionFulfillment() method fetches requisition list and
	 * return List<BookRequisition> type of object.
	 * 
	 * @param List
	 *            <BookRequisitionDetails>
	 * @return List<BookRequisition>
	 */
	public List<BookRequisition> getRequisitionFulfillment(
			List<BookRequisitionDetails> bookRequisitionDetailsList) {
		List<BookRequisition> bookRequisitionListFromDB = null;
		logger.debug("Size of bookRequisitionDetailsList :"
				+ bookRequisitionDetailsList.size());
		SqlSession session = sqlSessionFactory.openSession();
		try {
			for (BookRequisitionDetails bookRequisitionDetails : bookRequisitionDetailsList) {
				if (bookRequisitionDetails.getNumberOfBooksReceived() != 0) {
					/**
					 * Calling select tag with id="getRequisitionFulfillment" to
					 * LibraryMapper.xml for update book requisition.
					 */
					session.update("getRequisitionFulfillment",
							bookRequisitionDetails);
					session.commit();
					/**
					 * Calling select tag with id="getAllBookRequisition" to
					 * LibraryMapper.xml for retrieve book requisition list.
					 */
				}
			}
			bookRequisitionListFromDB = session
					.selectList("getAllBookRequisition");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookRequisitionListFromDB;
	}

	/**
	 * 
	 * @param bookRequisition
	 * @return
	 */

	public BookRequisition viewRequisitionDetails(
			BookRequisition bookRequisition) {
		SqlSession session = sqlSessionFactory.openSession();
		BookRequisition bookRequisitionFromDB = null;
		try {
			/*
			 * Calling select tag with id="selectRequisitionId" to
			 * LibraryMapper.xml to retrieve requisition details
			 */
			bookRequisitionFromDB = session.selectOne("selectRequisitionId",
					bookRequisition);
			if (bookRequisitionFromDB != null) {
				bookRequisitionFromDB.setBookRequisitionCode(bookRequisition
						.getBookRequisitionCode());
				List<BookRequisitionDetails> bookRequisitionDetailsList = session
						.selectList("selectRequisitionIdDetails",
								bookRequisition.getBookRequisitionCode());
				if (bookRequisitionDetailsList != null
						&& bookRequisitionDetailsList.size() != 0) {
					for (BookRequisitionDetails bookRequisitionDetails : bookRequisitionDetailsList) {
						List<BookRequisitionReceivedDates> bookRequisitionReceivedDatesList = session
								.selectList(
										"selectRequisitionDetailsDate",
										Integer.parseInt(bookRequisitionDetails
												.getBookRequisitionDetailsObjectId()));
						bookRequisitionDetails
								.setBookRequisitionReceivedDatesList(bookRequisitionReceivedDatesList);
					}
					bookRequisitionFromDB
							.setBookRequisitionDetailsList(bookRequisitionDetailsList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookRequisitionFromDB;
	}

	/**
	 * This getRequisitionSearchList() method fetches requisition list on search
	 * value and return List<BookRequisition> type of object.
	 * 
	 * @param parameters
	 * @return List<BookRequisition>
	 */
	public List<BookRequisition> getRequisitionSearchList(
			Map<String, Object> parameters) {
		SqlSession session = sqlSessionFactory.openSession();
		List<BookRequisition> bookRequisitionList = null;
		try {
			/*
			 * Calling select tag with id="getAllBookRequisition" to
			 * LibraryMapper.xml
			 */
			bookRequisitionList = session.selectList(
					"getAllBookRequisition", parameters);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookRequisitionList;
	}

	/**
	 * @author anup.roy
	 * This method is used to get
	 * list of books for creating loan request
	 */
	public List<Book> createLodgingRequest() {
		List<Book> bookListFromDb = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In createLodgingRequest() of LibraryDaoImpl");
			bookListFromDb = session.selectList("createLodgingRequest");
		} catch (Exception e) {
			logger.error("Exception in createLodgingRequest() of LibraryDaoImpl:",e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookListFromDb;
	}

	@Override
	public BookRequest getLastRequestId() {
		BookRequest bookRequest = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			 bookRequest = session.selectOne("getLastRequestId");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookRequest;
	}
	/**
	 * 
	 * @param parameters
	 * @return
	 */
	public List<Book> searchLodgingRequest(Map<String, Object> parameters) {
		List<Book> bookListFromDb = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			bookListFromDb = session.selectList("searchLodgingRequest",
					parameters);
			BookRequest bookRequestObj = session.selectOne("getLastRequestId");
			if (bookListFromDb != null && bookListFromDb.size() != 0) {
				if (bookRequestObj != null) {
					BookRequest br = new BookRequest();
					br.setBookRequestCode(bookRequestObj.getBookRequestCode());
					bookListFromDb.get(0).setBookRequest(br);
				} else {
					BookRequest br = new BookRequest();
					br.setBookRequestCode("null");
					bookListFromDb.get(0).setBookRequest(br);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookListFromDb;
	}

	/**
	 * 
	 * @param bookRequest
	 * @return bookListFromDb
	 */
	public Integer submitLodgingRequest(BookRequest bookRequest) {
		Integer insertstatus = 0;
		Utility util = new Utility();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			bookRequest.setBookRequestObjectId(util.getBase64EncodedID("LibraryDAO"));
			List<BookRequestDetails> bookRequestDetailsList = bookRequest.getBookRequestDetailsList();
			session.insert("insertingBookLodgingRequestId", bookRequest);
			session.commit();
			/*for (BookRequestDetails bookRequestDetails : bookRequestDetailsList) {
				bookRequestDetails.setBookRequestDetailsObjectId(util.getBase64EncodedID("LibraryDAO"));
				bookRequestDetails.setBookRequestDetailsCode(bookRequest.getBookRequestObjectId());
				bookRequestDetails.setUpdatedBy(bookRequest.getUpdatedBy());
			}*/
			insertstatus = session.insert("insertingBookLodgingRequestDetailsBookCode",bookRequestDetailsList);
			/*for (BookRequestDetails bookRequestDetails : bookRequestDetailsList) {
				BookRating bookRating = new BookRating();
				bookRating.setUpdatedBy(bookRequest.getUpdatedBy());
				bookRating.setBookRatingCode(bookRequestDetails.getBookCode());
				//System.out.println("bookRating::"+bookRating.getBookRatingCode());
				insertstatus = session.update("updateBookRatingForBookRequest",bookRating);
			}*/
			session.commit();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return insertstatus;
	}

	/**
	 * 
	 * @return bookRequestIdList
	 */
	public List<BookRequest> getBookRequestDetails() {
		List<BookRequest> bookRequestIdList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			bookRequestIdList = session.selectList("getBookRequestId");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookRequestIdList;
	}

	public List<BookRequest> searchForBookRequest(Map<String, Object> parameters) {
		SqlSession session = sqlSessionFactory.openSession();
		List<BookRequest> bookRequestFromDB = null;
		try {
			bookRequestFromDB = session.selectList("searchForBookRequest",
					parameters);
			if (bookRequestFromDB != null) {
				logger.info("From getBookStock() of LibraryDAO - Size of stock  list is : "
						+ bookRequestFromDB.size());
			} else {
				logger.info("From getBookStock() of LibraryDAO - DATA NOT RETURNED....");
			}
		} catch (Exception e) {
			logger.error(e);
		} finally {
			session.close();
		}
		return bookRequestFromDB;
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
		SqlSession session = sqlSessionFactory.openSession();
		try {
			bookAllocation.setBookAllocationObjectId(util.getBase64EncodedID("LibraryDAO"));
			session.insert("allocateBook", bookAllocation);
			session.commit();
			Book b = bookAllocation.getBook();
			List<BookId> bookIdList = b.getBookIdList();
			for (BookId bid : bookIdList) {
				bid.setBookIdObjectId(util.getBase64EncodedID("LibraryDAO"));
				bid.setBookCode(bookAllocation.getBookAllocationObjectId());
				bid.setUpdatedBy(bookAllocation.getUpdatedBy());
				bid.setBookLifeCycleCode(bookAllocation.getBookRequestedId());// book requestedId  set into BookLifeCycleCode
			}
			session.insert("allocateBookDetails", bookIdList);
			session.commit();
			bookRequestIdList = session.selectList("getBookRequestId");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookRequestIdList;
	}

	/**
	 * 
	 * @param requestedBookId
	 * @return
	 */
	public BookRequest getRequestedBookDetails(String requestedBookId) {
		BookRequest bookRequest = null;
		List<BookRequestDetails> bookRequestDetailsList = new ArrayList<BookRequestDetails>();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			bookRequest = session.selectOne("getBookRequestDetails",requestedBookId);
			if (bookRequest != null) {
				Integer maxNoOfAllocateBooksPolicy = session.selectOne("getMaxNoOfAllocateBooksPolicy", bookRequest.getBookRequestFor().getResourceType().getResourceTypeName());
				int allocatedBooksByUser = 0;
				for (BookRequestDetails bookRequestDetails : bookRequest.getBookRequestDetailsList()) {
					bookRequest.setAccessionNumber(bookRequestDetails.getBookCode());
					String expectedBookReturnDate = session.selectOne("getExpectedBookReturnDate", bookRequest);
					bookRequestDetails.setExpectedBookReturnDate(expectedBookReturnDate);
					/*List<BookId> bookIdList = session.selectList("getAvailableBookId",bookRequestDetails.getBookCode());
					bookRequestDetails.setBookIdList(bookIdList);
					List<BookWaiting> bookWaitingList = session.selectList("getWaitingBookCode",bookRequestDetails.getBookCode());
					bookRequestDetails.setBookWaitingList(bookWaitingList);*/
					bookRequestDetailsList.add(bookRequestDetails);
				}
				bookRequest.setBookRequestDetailsList(bookRequestDetailsList);
				List<BookAllocation> bookAllocationList = session.selectList("getIssuerAllocatedLodgingHistory", requestedBookId);
				if (bookAllocationList != null && bookAllocationList.size() != 0) {
					for (BookAllocation bookAllocation : bookAllocationList) {
						List<BookAllocationDetails> issuedHistoryBookDetails = session.selectList("getIssuerAllocatedDetailsLodgingHistory",bookAllocation.getBookAllocationCode());
						if (issuedHistoryBookDetails != null && issuedHistoryBookDetails.size() != 0) {
							allocatedBooksByUser=allocatedBooksByUser+issuedHistoryBookDetails.size();
							bookAllocation.setBookAllocationDetails(issuedHistoryBookDetails);
						}
					}
					bookRequest.setBookAllocationList(bookAllocationList);
				}
				if(maxNoOfAllocateBooksPolicy!=null && maxNoOfAllocateBooksPolicy <= allocatedBooksByUser){
					bookRequest.setStatus("exceed");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookRequest;
	}

	/**
	 * 
	 * @param resource
	 * @return
	 */
	public List<BookAllocation> getIssuedBookDetails(Resource resource) {
		List<BookAllocation> issuedBookList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			issuedBookList = session.selectList("getAllocationList", resource);
			session.update("countPenalty", resource);
			session.commit();
			if (issuedBookList != null) {
				for (BookAllocation bookAllocation : issuedBookList) {
					List<BookAllocationDetails> issuedBookDetails = session.selectList("getAllocationDetailsList",bookAllocation.getBookAllocationCode());
					bookAllocation.setBookAllocationDetails(issuedBookDetails);
					bookAllocation.setBookIssuedTo(resource);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return issuedBookList;
	}

	/**
	 * 
	 * @param bookAllocation
	 * @return
	 */
	public void submitReturnBookDetails(
			List<BookAllocationDetails> bookAllocationDetailsList,
			Map<String, Object> bookRatingMap) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Utility util = new Utility();
			if (bookRatingMap != null && !bookRatingMap.isEmpty()) {
				for (String br : bookRatingMap.keySet()) {
					if (br != null && br.trim().length() != 0) {
						BookRating bookRating = (BookRating) bookRatingMap.get(br);
						bookRating.setBookRatingCode(br);
						bookRating.setBookRatingObjectId(util.getBase64EncodedID("LibraryDAO"));
						session.insert("submitBookRatingDetails", bookRating);
						session.update("updateBookRatingForBookRequest", bookRating);
						session.commit();
					}
				}
			}
			session.update("submitReturnBookDetails", bookAllocationDetailsList);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public BookRequisition getBookRequisitionDetails(
			BookRequisition bookRequisition) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getBookRequisitionDetails(BookRequisition bookRequisition) method in LibraryDAOImpl");
			bookRequisition = session.selectOne("getBookRequisitionDetails",
					bookRequisition);
		} catch (Exception e) {
			logger.error(
					"getBookRequisitionDetails(BookRequisition bookRequisition) method in LibraryDAOImpl",
					e);
		} finally {
			session.close();
		}
		return bookRequisition;
	}

	@Override
	public List<BookRequisition> savePaymentForBookRequisition(BookRequisition bookRequisition, String updatedBy) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("savePaymentForBookRequisition(BookRequisition bookRequisition) method in LibraryDAOImpl");
//			PreviousYearFinanceData pyf = new PreviousYearFinanceData();
//			Utility util = new Utility();
//			pyf.setPreviousDataObjectId(util.getBase64EncodedID("LibraryDAOImpl"));
//			pyf.setUpdatedBy(updatedBy);
//			pyf.setNarration("Books purchase from  "+ bookRequisition.getVendor().getVendorName() + "by"+ bookRequisition.getPaymentMode());
//			pyf.setAmount(bookRequisition.getTotalPrice());
//			pyf.setIncomeExpense("EXPENSE");
//			if (bookRequisition.getPaymentMode().equals("CASH")) {
//				pyf.setMode("CASH");
//			} else {
//				pyf.setMode("BANK");
//			}
//			session.insert("insertCapital", pyf);
			session.update("updatePaymentModeForBookRequisition", bookRequisition);
			session.commit();
		} catch (Exception e) {
			logger.error("savePaymentForBookRequisition(BookRequisition bookRequisition) method in LibraryDAOImpl Exception",e);
		}
		return viewRequisition();
	}

	@Override
	public List<Book> getBookNameDB() {
		List<Book> bookList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getBookNameDB() method in LibraryDAOImpl");			
			bookList = session.selectList("getAllBookNames");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getBookNameDB() method in LibraryDAOImpl Exception",e);
		}
		return bookList;
	}

	@Override
	public Book getAllBookDetailsDB(String bookName) {
		Book bookDB = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getBookNameDB() method in LibraryDAOImpl");			
			bookDB = session.selectOne("getAllBookDetails", bookName);
		} catch (Exception e) {
			logger.error("getBookNameDB() method in LibraryDAOImpl Exception",
					e);
		}
		return bookDB;
	}

	@Override
	public List<Vendor> bookVendorList() {
		List<Vendor> vendorList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In bookVendorList() method of LibraryDAOImpl");			
			vendorList = session.selectList("getBookVendorList");
		} catch (NullPointerException e) {
			logger.error(
					"bookVendorList() In LibraryDAOImpl.java: NullPointerException",
					e);
		} catch (Exception e) {
			logger.error("bookVendorList() In LibraryDAOImpl.java: Exception",
					e);
		} finally {
			session.close();
		}
		return vendorList;
	}

	@Override
	public List<Vendor> getBookVendorSearchList(Map<String, Object> parameters) {
		logger.info("getBookVendorSearchList(Map<String, Object> parameters) In LibraryDAOImpl.java:");
		SqlSession session = sqlSessionFactory.openSession();
		List<Vendor> bookVendorList = null;
		try {
			bookVendorList = session
					.selectList("getBookVendorList", parameters);
		} catch (NullPointerException e) {
			logger.error(
					"getBookVendorSearchList(Map<String, Object> parameters) In LibraryDAOImpl.java: NullPointerException",
					e);
		} catch (Exception e) {
			logger.error(
					"getBookVendorSearchList(Map<String, Object> parameters) In LibraryDAOImpl.java: Exception",
					e);
		} finally {
			session.close();
		}
		return bookVendorList;
	}

	@Override
	public VendorType getVendorAndBooks() {
		List<Vendor> vendorList = null;
		List<Item> vendorBooks = null;
		VendorType vendorType = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getVendorAndItems() method of LibraryDAOImpl");			
			vendorList = session.selectList("getBookVendorList");
			vendorBooks = session.selectList("getBookList");
			vendorType = new VendorType();
			vendorType.setItemList(vendorBooks);
			vendorType.setVendorList(vendorList);
		} catch (NullPointerException e) {
			logger.error(
					"getVendorList() In LibraryDAOImpl.java: NullPointerException",
					e);
		} catch (Exception e) {
			logger.error("getVendorList() In LibraryDAOImpl.java: Exception", e);
		} finally {
			session.close();
		}
		return vendorType;
	}

	@Override
	public String getVendorBooks(String vendorCode) {
		String vendorBooks = "";
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In  getVendorBooks(String vendorCode) method of LibraryDAOImpl");			
			List<Item> itemList = null;
			itemList = session.selectList("getVendorBooksMapping", vendorCode);
			if (itemList != null && itemList.size() != 0)
				for (Item i : itemList) {
					vendorBooks = vendorBooks + i.getItemCode() + "##"
							+ i.getSellingRate() + "+-+";
				}
		} catch (NullPointerException e) {
			logger.error(
					" getVendorBooks(String vendorCode) In LibraryDAOImpl.java: NullPointerException",
					e);
		} catch (Exception e) {
			logger.error(
					" getVendorBooks(String vendorCode) In LibraryDAOImpl.java: Exception",
					e);
		} finally {
			session.close();
		}
		return vendorBooks;
	}

	@Override
	public int updateBookVendorMaping(Vendor vendor, String updatedBy) {
		int updatestatus = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Utility util = new Utility();
			logger.info("In updateBookVendorMaping(Vendor vendor) method of LibraryDAOImpl");			
			List<Item> BookListFromDB = null;
			BookListFromDB = session.selectList("getVendorBooksMapping", vendor.getVendorCode());
			if (BookListFromDB != null && BookListFromDB.size() != 0) {
				List<Item> updateItemList = new ArrayList<Item>();
				Map<String, Object> DbItemCodePriceMap = new HashMap<String, Object>();
				if (BookListFromDB != null && BookListFromDB.size() != 0) {
					for (Item item : BookListFromDB) {
						DbItemCodePriceMap.put(item.getItemCode(),
								item.getSellingRate());
					}
				}
				if (vendor != null && vendor.getVendorItems() != null
						&& vendor.getVendorItems().size() != 0) {
					for (Item i : vendor.getVendorItems()) {
						if (i.getItemCode() != null && i.getSellingRate() > 0) {
							if (DbItemCodePriceMap.containsKey(i.getItemCode())) {
								if (DbItemCodePriceMap.get(i.getItemCode()).equals(i.getSellingRate())) {
								} else {
									if (i.getItemCode() != null && i.getSellingRate() > 0) {
										Book b = new Book();
										b.setBookCode(i.getItemCode());
										vendor.setBook(b);
										vendor.setVendorSellingRate(i.getSellingRate());
										updatestatus = session.update("updateVendorBookMapping", vendor);
										Item itm = new Item();
										itm.setItemObjectId(util.getBase64EncodedID("LibraryDAOImpl"));
										itm.setVendorCode(vendor.getVendorCode());
										itm.setStatus(i.getItemCode() + "-"
														+ i.getSellingRate() + "-"
														+ vendor.getVendorCode());
										itm.setItemCode(i.getItemCode());
										itm.setUpdatedBy(updatedBy);
										itm.setSellingRate(i.getSellingRate());
										session.insert("insertIntoPriceHistory", itm);
										updateItemList.add(i);
									}
								}
								DbItemCodePriceMap.remove(i.getItemCode());
							} else {
								if (i.getItemCode() != null && i.getSellingRate() > 0) {
									updateItemList.add(i);
									Item item = new Item();
									item.setItemDesc(vendor.getVendorCode());
									//item.setVendorCode(vendor.getVendorCode());
									item.setItemCode(i.getItemCode());
									item.setUpdatedBy(updatedBy);
									item.setSellingRate(i.getSellingRate());
									item.setItemObjectId(util.getBase64EncodedID("LibraryDAOImpl"));
									//System.out.println("Inserting ::"+item.getItemCode()+"\t"+item.getSellingRate()+"\t"+item.getItemDesc());
									updatestatus = session.insert("insertVendorBookMapping", item);
									item.setVendorCode(vendor.getVendorCode());
									item.setStatus(i.getItemCode() + "-"
												+ i.getSellingRate() + "-"
												+ vendor.getVendorCode());
									session.insert("insertIntoPriceHistory",item);
								}
							}
						}
					}
					@SuppressWarnings("rawtypes")
					Iterator iter = DbItemCodePriceMap.entrySet().iterator();
					while (iter.hasNext()) {
						@SuppressWarnings("rawtypes")
						Map.Entry mEntry = (Map.Entry) iter.next();
						vendor.setUpdatedBy(mEntry.getKey().toString());
						updatestatus = session.update("inactiveVendorBookMapping", vendor);
					}
				}
			} else {
				if (vendor.getVendorItems() != null
						&& vendor.getVendorItems().size() != 0) {
					for (Item i : vendor.getVendorItems()) {
						if (i.getItemCode() != null && i.getSellingRate() > 0) {
							Item item = new Item();
							item.setItemDesc(vendor.getVendorCode());
							item.setUpdatedBy(updatedBy);
							item.setItemCode(i.getItemCode());
							item.setSellingRate(i.getSellingRate());
							item.setItemObjectId(util.getBase64EncodedID("AdminDAOImpl"));
							updatestatus = session.insert("insertVendorBookMapping", item);
							item.setVendorCode(vendor.getVendorCode());
							item.setStatus(i.getItemCode() + "-"
										+ i.getSellingRate() + "-"
										+ vendor.getVendorCode());
							session.insert("insertIntoPriceHistory", item);
						}
					}

				}
			}
			session.commit();
		} catch (NullPointerException e) {
			updatestatus = 0;
			e.printStackTrace();
		} catch (Exception e) {
			updatestatus = 0;
			e.printStackTrace();
		} finally {
			session.close();
		}
		return updatestatus;
	}

	@Override
	public Vendor getVendorBookList(Vendor vendor) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getVendorBookList(Vendor vendor) method of LibraryDAOImpl");			
			vendor = session.selectOne("selectVendorBookList", vendor);
		} catch (Exception e) {
			logger.error("Exception in getVendorBookList(Vendor vendor) method in LibraryDAOImpl ",e);
		}
		return vendor;
	}

	@Override
	public List<Item> getBooksForAddThreshold() {
		List<Item> bookList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In getBooksForAddThreshold() method of LibraryDAOImpl");			
			bookList = session.selectList("getBookList");
		} catch (Exception e) {
			logger.error(
					"Exception in getBooksForAddThreshold() method in LibraryDAOImpl ",
					e);
		}
		return bookList;
	}

	@Override
	public int updateThresholdForBook(List<Item> itemList) {
		int updateStatus = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In updateThresholdForBook(List<Item> itemList) method of LibraryDAOImpl");			
			if (itemList != null && itemList.size() != 0) {
				for (Item i : itemList) {
					updateStatus = session.update("updateBookForThreshold", i);
				}
			}
		} catch (Exception e) {
			updateStatus = 0;
			logger.error(
					"Exception in updateThresholdForBook(List<Item> itemList) method in LibraryDAOImpl ",
					e);
		}
		return updateStatus;
	}

	@Override
	public List<Item> searchThresholdDetailsForBook(
			Map<String, Object> parameters) {
		List<Item> bookList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In searchThresholdDetailsForBook(Map<String, Object> parameters) method of LibraryDAOImpl");
			bookList = session.selectList("getBookList", parameters);
		} catch (Exception e) {
			logger.error(
					"Exception in searchThresholdDetailsForBook(Map<String, Object> parameters) method in LibraryDAOImpl ",
					e);
		}
		return bookList;
	}

	@Override
	public List<BookPurchaseOrder> getPurchaseOrdersInRequition(
			String requisitionCode) {
		List<BookPurchaseOrder> bookPurchaseOrderList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getVendorNameDB() method in LibraryDAOImpl");			
			bookPurchaseOrderList = session.selectList("getPurchaseOrdersInRequition", requisitionCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookPurchaseOrderList;
	}

	@Override
	public List<BookPurchaseOrderDetails> getPurchaseOrdersDetailsInPurchaseOrders(
			String purchaseOrderCode) {
		List<BookPurchaseOrderDetails> BookPurchaseOrderDetailsList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getVendorNameDB() method in LibraryDAOImpl");			
			BookPurchaseOrderDetailsList = session.selectList("getPurchaseOrdersDetailsInPurchaseOrder",purchaseOrderCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return BookPurchaseOrderDetailsList;
	}

	
	/** modified by sourav.bhadra on 06-10-2017 **/
	@Override
	public void updatePurchaseOrderDetails(BookPurchaseOrder bookPurchaseOrder,
			List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList) {
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		try {	
			String narration = "";
			for (BookPurchaseOrderDetails bpod : bookPurchaseOrderDetailsList) {
				session.update("receiveBookPurchaseOrderDetails", bpod);
				
				/* added by sourav.bhadra on 13-09-2017
				 * to make a transaction for book receive 
				 * transaction details :: genre ledger debit to vendor ledger credit */
				double totalAmt = (bpod.getQtyReceiving() * bpod.getRate());
				
				TransactionDetails trd = new TransactionDetails();
				trd.setUpdatedBy(bpod.getUpdatedBy());
				trd.setObjectId(util.getBase64EncodedID("LibraryDaoImpl"));
				
				//for vendor (credit)
				String vendorLedger = bookPurchaseOrder.getLedger();
				Ledger vendorLedgerBalance = session.selectOne("selectBalanceForParentLedger", vendorLedger);
				trd.setLedger(bookPurchaseOrder.getLedger());
				trd.setGrossAmount(vendorLedgerBalance.getOpeningBal());
				if(vendorLedgerBalance.getCurrentBal() < 0 ){
					/*if current balance is -ve then in debit side
					 * credit on debit = subtract */
					trd.setOnbasic(vendorLedgerBalance.getCurrentBal() + totalAmt);
					System.out.println("LN1539 :: Vendor -ve(debit)  Final Amount :: "+trd.getOnbasic());
					session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
				}else{
					/*if current balance is +ve then in credit side
					 * credit on credit = add */
					trd.setOnbasic(vendorLedgerBalance.getCurrentBal() + totalAmt);
					System.out.println("LN1545 :: Vendor +ve(credit)  Final Amount :: "+trd.getOnbasic());
					session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
				}
				TransactionDetails tranDetVen = new TransactionDetails();
				tranDetVen.setUpdatedBy(bpod.getUpdatedBy());
				tranDetVen.setObjectId(util.getBase64EncodedID("LibraryDaoImpl"));
				tranDetVen.setLedger(vendorLedger);
				tranDetVen.setAmount(totalAmt);
				tranDetVen.setDebit(false);
				
				//for genre (debit)
				String genre = bpod.getItemCode();
				String genreLedger = session.selectOne("selectLedgerOfAGenre", genre);
				Ledger genreLedgerBalance = session.selectOne("selectBalanceForParentLedger", genre);
				trd.setLedger(genreLedger);
				trd.setGrossAmount(genreLedgerBalance.getOpeningBal());
				
				if(genreLedgerBalance.getCurrentBal() < 0 ){
					/*if current balance is -ve then in credit side
					 * debit on credit = subtract */
					trd.setOnbasic(genreLedgerBalance.getCurrentBal() + totalAmt);
					System.out.println("LN1566 :: Genre -ve(credit) Final Amount :: "+trd.getOnbasic());
					session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
				}else{
					/*if current balance is +ve then in debit side
					 * debit on debit = add */
					trd.setOnbasic(genreLedgerBalance.getCurrentBal() + totalAmt);
					System.out.println("LN1572 :: Genre +ve(debit)  Final Amount :: "+trd.getOnbasic());
					session.insert("insertIntoLedgerBalanceForCreditAndDebit",trd);
				}
				TransactionDetails tranDetBook = new TransactionDetails();
				tranDetBook.setUpdatedBy(bpod.getUpdatedBy());
				tranDetBook.setObjectId(util.getBase64EncodedID("LibraryDaoImpl"));
				tranDetBook.setLedger(genreLedger);
				tranDetBook.setAmount(totalAmt);
				tranDetBook.setDebit(true);
				
				List<TransactionDetails> transactionDetailsList = new ArrayList<TransactionDetails>();
				transactionDetailsList.add(tranDetBook);
				transactionDetailsList.add(tranDetVen);
				
				narration = "Receiving "+bpod.getQtyReceiving()+" piece(s) "+bpod.getBookName()+" Book(s) (written by "+bpod.getAuthorName()+") from "+vendorLedger;
				Transaction transaction = new Transaction();
				transaction.setObjectId(util.getBase64EncodedID("InventoryDaoImpl"));
				transaction.setUpdatedBy(bpod.getUpdatedBy());
				transaction.setVoucherTypeCode("JOURNAL");
				transaction.setVoucherTypeName("JOURNAL");
				transaction.setNarration(narration);
				transaction.setTrDetList(transactionDetailsList);
				session.insert("createTransactionForBookReceive", transaction);
				
				/*System.out.println("*****************LN1596*******************");
				System.out.println("Book Name :: "+bpod.getBookName());
				System.out.println("Author Name :: "+bpod.getAuthorName());
				System.out.println("Genre :: "+bpod.getItemCode());
				System.out.println("Qty :: "+bpod.getQtyReceiving());
				System.out.println("Rate :: "+bpod.getRate());
				System.out.println("Total Amount :: "+totalAmt);
				System.out.println("Narration :: "+narration);
				System.out.println("------------------Genre Ledger Details-------------------");
				System.out.println("Genre Ledger :: "+genreLedger);
				System.out.println("Opening Balance :: "+genreLedgerBalance.getOpeningBal());
				System.out.println("Current Balance :: "+genreLedgerBalance.getCurrentBal());
				System.out.println("------------------Vendor Ledger Details-------------------");
				System.out.println("Vendor :: "+vendorLedger);
				System.out.println("Vendor Ledger :: "+vendorLedger);
				System.out.println("Opening Balance :: "+vendorLedgerBalance.getOpeningBal());
				System.out.println("Current Balance :: "+vendorLedgerBalance.getCurrentBal());
				System.out.println("******************LN1613******************");*/
			}
			
			session.update("receiveBookPurchaseOrder", bookPurchaseOrder);
			List<BookPurchaseOrder> bpol = session.selectList("getReqAndAllStatus", bookPurchaseOrder.getPurchaseOrderCode());
			if (bpol != null && bpol.size() != 0) {
				String reqCode = "";
				int flag = 0;
				for (BookPurchaseOrder bpo : bpol) {
					if (bpo.getAmountStatus().trim().equalsIgnoreCase("CLOSED")
							&& bpo.getStatus().trim().equalsIgnoreCase("CLOSED")) {
						reqCode = bpo.getBookRequisition();
						session.update("setPurchaseOrderCloseDate", bpo);
					} else
						flag = 1;
				}
				if (flag == 0) {
					session.update("setBookReqCloseDate", reqCode);
				}
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
	}

	@Override
	public BookPurchaseOrder getPurchaseOrdersForPayment(
			String purchaseOrderCode) {
		BookPurchaseOrder bookPurchaseOrder = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getVendorNameDB() method in LibraryDAOImpl");			
			bookPurchaseOrder = session.selectOne(
					"getPurchaseOrdersForPayment", purchaseOrderCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookPurchaseOrder;
	}

	@Override
	public void updatePurchaseOrderPayment(BookPurchaseOrder bookPurchaseOrder) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			session.update("updatePurchaseOrderPayment", bookPurchaseOrder);
			Utility util = new Utility();
			String ledger = bookPurchaseOrder.getLedger();
			bookPurchaseOrder.setPurchaseOrderObjectId(util.getBase64EncodedID("LibraryDAO"));
			session.insert("insertIntoTransactionalWorkingAreaForLibraryPurchaseOrderPayment", bookPurchaseOrder);
			/* modified by sourav.bhadra on 13-09-2017 */
	/*		double currentBalance = session.selectOne("getCurrentBalanceForALedger", ledger);
			System.out.println("Current Balance :: "+currentBalance);
			currentBalance += bookPurchaseOrder.getAdvanceAmount();
			System.out.println("Current Balance :: "+currentBalance);
			Ledger ledgerObj = new Ledger();
			ledgerObj.setCurrentBal(currentBalance);
			ledgerObj.setLedgerCode(ledger);
			session.update("updateCurrentBalanceOfALedger", ledgerObj);			*/
			
			List<BookPurchaseOrder> bpol = session.selectList("getReqAndAllStatus",bookPurchaseOrder.getPurchaseOrderCode());
			if(bpol != null && bpol.size() != 0) {
				String reqCode = "";
				int flag = 0;
				for (BookPurchaseOrder bpo : bpol) {
					if (bpo.getAmountStatus().trim().equalsIgnoreCase("CLOSED")	&& bpo.getStatus().trim().equalsIgnoreCase("CLOSED")) {
						reqCode = bpo.getBookRequisition();
						session.update("setPurchaseOrderCloseDate", bpo);
					}else{
						flag = 1;
					}
				}
				if(flag == 0) {
					session.update("setBookReqCloseDate", reqCode);
				}
			}
			session.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	@Override
	public List<BookPurchaseOrderDetails> getBookPurchaseOrderDetailsList() {
		List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getBookPurchaseOrderDetailsList() method in LibraryDAOImpl");			
			bookPurchaseOrderDetailsList = session.selectList("getPurchaseOrderDetailsList");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return bookPurchaseOrderDetailsList;
	}

	@Override
	public List<BookPurchaseOrderDetails> getPurchaseOrderDetailsListSearch(Map<String, Object> parameters) {
		List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getPurchaseOrderDetailsListSearch() method in LibraryDAOImpl");			
			bookPurchaseOrderDetailsList = session.selectList("getPurchaseOrderDetailsList",parameters);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	return bookPurchaseOrderDetailsList;
	}

	
	@Override
	public void updateAddedToCatalogue(Book book) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getVendorNameDB() method in LibraryDAOImpl");			
			session.update("updateAddedToCatalogue", book);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public Book getBookCodeForBookName(String bookName) {
		Book book = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getVendorNameDB() method in LibraryDAOImpl");			
			book = session.selectOne("getBookCodeForBookName", bookName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	@Override
	public List<Vendor> getAllVenoderDetailsDBForBook(Book book) {
		List<Vendor> vendorList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {			
			vendorList = session.selectList("getAllVendorsName", book);
		} catch (Exception e) {
			logger.error(
					"getAllVenoderDetailsDBForBook() method in LibraryDAOImpl Exception",
					e);
			e.printStackTrace();
		}
		return vendorList;
	}

	@Override
	public List<String> getVendorNameDB(String strQuery) {
		List<String> vendorNameList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getVendorNameDB() method in LibraryDAOImpl");			
			vendorNameList = session.selectList("getAllVendorsNameForAuto",strQuery);

		} catch (Exception e) {
			logger.error(
					"getVendorNameDB() method in LibraryDAOImpl Exception", e);
		}
		return vendorNameList;
	}
	
	@Override
	public String getNameOfVendors() {
		List<Vendor> nameOfVendors = null;
		String finalString = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getNameOfVendors() method in LibraryDAOImpl");		
			nameOfVendors = session.selectList("getNameOfVendors");
			StringBuilder sb = new StringBuilder();
			for(Vendor vendor : nameOfVendors){
				sb.append(vendor.getVendorName()+"*");
				finalString = sb.toString().substring(0, sb.toString().length()-1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getVendorNameDB() method in LibraryDAOImpl Exception", e);
		}
		//System.out.println("finalString::"+finalString);
		return finalString;
	}
	
	@Override
	public String getIdAgainstName(Vendor vendor) {
		String idAgainstName = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getIdAgainstName() method in LibraryDAOImpl");
			//System.out.println("name::"+vendor.getVendorName());
			idAgainstName = session.selectOne("getIdAgainstName",vendor);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getIdAgainstName() method in LibraryDAOImpl Exception", e);
		}
		return idAgainstName;
	}

	@Override
	public List<BookPurchaseOrder> getpurchaseOrderForBook(BookPurchaseOrder bookPurchaseOrderObj, String updatedBy) {
		List<BookPurchaseOrder> bookPurchaseOrderList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			Utility util = new Utility();
			int insertionStatus = 0;
			List<Vendor> vendorList = bookPurchaseOrderObj.getVendor();
			List<Vendor> previousVendorList = session.selectList("vendorListForChecking");
			Map<String, Vendor> vendorMap = new HashMap<String, Vendor>();
			for (Vendor vendorObj : vendorList) {
				/* Author insertion */
				Vendor vendorObject = new Vendor();
				vendorObject.setUpdatedBy(updatedBy);
				vendorObject.setVendorObjectId(util.getBase64EncodedID("PurchaseDaoImpl"));
				vendorObject.setVendorName(vendorObj.getVendorCode());
				vendorObject.setVendorType("BV");
				vendorObject.setVendorCode(vendorObj.getVendorCode());
				vendorMap.put(vendorObj.getVendorCode(), vendorObject);
			}
			/* list checking starts */
			List<Vendor> vendorListAnother = new ArrayList<Vendor>();
			if (previousVendorList != null && previousVendorList.size() != 0) {
				for (Vendor vn : previousVendorList) {
					if (vendorMap != null && vendorMap.size() != 0) {
						if (vendorMap.containsKey(vn.getVendorCode())) {
							vendorMap.remove(vn.getVendorCode());
						}

					}
				}
			}
			if (vendorMap != null && vendorMap.size() != 0) {
				for (Object key : vendorMap.keySet()) {
					vendorListAnother.add(vendorMap.get(key));
				}
			}
			List<BookPurchaseOrderDetails> bookPurchaseOrderDetailsList = bookPurchaseOrderObj.getBookPurchaseOrderDetailsList();
			for (Vendor vendor : vendorList) {
				BookPurchaseOrder bookPurchaseOrderObject = new BookPurchaseOrder();
				bookPurchaseOrderObject.setUpdatedBy(updatedBy);
				bookPurchaseOrderObject.setPurchaseOrderCode("BPID");
				bookPurchaseOrderObject.setPurchaseOrderDesc("BPID");
				if (vendorListAnother != null && vendorListAnother.size() != 0) {
					for (Vendor newVendor : vendorListAnother) {
						session.insert("insertVendorListFromRequisition",newVendor);
						session.commit();
						if (vendorMap.containsKey(vendor.getVendorCode())) {
							bookPurchaseOrderObject.setVendorCode(newVendor.getVendorCode());
						} else {
							bookPurchaseOrderObject.setVendorCode(vendor.getVendorCode());
						}
					}
				} else {
					bookPurchaseOrderObject.setVendorCode(vendor.getVendorCode());
				}
				bookPurchaseOrderObject.setTotalQtyOrdered(vendor.getQuantityOrdered());
				bookPurchaseOrderObject.setTotalAmount(vendor.getVendorSellingRate());
				bookPurchaseOrderObject.setTotalQtyDeficit(vendor.getQuantityOrdered());
				bookPurchaseOrderObject.setTotalQtyReceived(0);
				bookPurchaseOrderObject.setAmountStatus("OPEN");
				bookPurchaseOrderObject.setStatus("OPEN");
				bookPurchaseOrderObject.setBookRequisition(bookPurchaseOrderObj.getBookRequisition().trim());
				bookPurchaseOrderObject.setPurchaseOrderObjectId(util.getBase64EncodedID("PurchaseDaoImpl"));
				insertionStatus = session.insert("insertIntoBookPurchaseOrder",bookPurchaseOrderObject);
				session.commit();
				for (BookPurchaseOrderDetails bookPurchaseOrderDetails : bookPurchaseOrderDetailsList) {
					if (bookPurchaseOrderDetails.getVendor().equals(vendor.getVendorCode())) {
						bookPurchaseOrderDetails.setPurchaseOrderDetailsObjectId(util.getBase64EncodedID("PurchaseDaoImpl"));
						bookPurchaseOrderDetails.setPurchaseOrderDetailsCode("BPDID");
						bookPurchaseOrderDetails.setPurchaseOrderDetailsDesc("BPDID");
						bookPurchaseOrderDetails.setStatus("OPEN");
						BookPurchaseOrder bpoObj = new BookPurchaseOrder();
						bpoObj.setPurchaseSerialId(bookPurchaseOrderObject.getPurchaseSerialId());
						bookPurchaseOrderDetails.setBookPurchaseOrder(bpoObj);
						session.insert("insertIntoBookPurchaseOrderDetails",bookPurchaseOrderDetails);
						session.commit();
					}
				}
			}
			if (insertionStatus != 0) {
				session.update("updateBookRequisitionForPurchaseOrder",bookPurchaseOrderObj.getBookRequisition().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			logger.error("getVendorNameDB() method in LibraryDAOImpl Exception", e);
		}
		return bookPurchaseOrderList;
	}

	@Override
	public List<String> getEditionDB(String strQuery) {
		List<String> editionList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getEditionDB() method in LibraryDAOImpl");			
			editionList = session.selectList("getAlleditions", strQuery);
		} catch (Exception e) {
			logger.error("getEditionDB() method in LibraryDAOImpl Exception", e);
		}
		return editionList;
	}

	@Override
	public List<Book> getBookList() {
		List<Book> bookList = new ArrayList<Book>();
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getBookList() method in LibraryDAOImpl");			
			bookList = session.selectList("getBookListForEdit");
			if (bookList != null && bookList.size() != 0) {
				for (Book b : bookList) {
					List<Author> bookAuthorList = new ArrayList<Author>();
					bookAuthorList = session.selectList("getBookAuthorList", b);
					if (bookAuthorList != null && bookAuthorList.size() != 0) {
						b.setBookAuthorList(bookAuthorList);
					}
				}
			}
		} catch (Exception e) {
			logger.error("getBookList() method in LibraryDAOImpl", e);
			e.printStackTrace(); /* added by sourav.bhadra on 16-02-2018 */
		} finally {
			session.close();
		}
		return bookList;
	}


	@Override
	public List<Book> getSearchBooksList(Map<String, Object> parameters) {
		List<Book> returnBookList = new ArrayList<Book>();
		
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getBookList() method in LibraryDAOImpl");			
			List<Book> bookList = session.selectList("getBookListForEdit",parameters);
			/**
			 * check search on author 
			 */
			if(parameters!=null && parameters.get("Author")!=null){
				if (bookList != null && bookList.size() != 0) {
					for (Book b : bookList) {
						List<Author> bookAuthorList = new ArrayList<Author>();
						b.setBookObjectId(parameters.get("Author").toString());
						bookAuthorList = session.selectList("getBookAuthorList", b);
						/**
						 * this block is used for if 'bookAuthorList' is not null then check search 
						 * author is matches with existing author  
						 */
						if(bookAuthorList != null && bookAuthorList.size() != 0) {
							int count =0;
							for(Author author : bookAuthorList){
								if(author.getAuthorFullName().matches("(.*)"+parameters.get("Author").toString()+"(.*)")){
									count =1;
								}
							}
							if(count!=0){
								Book book = new Book();
								book=b;
								book.setBookAuthorList(bookAuthorList);
								returnBookList.add(book);
							}
						}
					}
				}
			}else{
				if (bookList != null && bookList.size() != 0) {
					for (Book b : bookList) {
						List<Author> bookAuthorList = new ArrayList<Author>();
						bookAuthorList = session.selectList("getBookAuthorList", b);
						if (bookAuthorList != null && bookAuthorList.size() != 0) {
							b.setBookAuthorList(bookAuthorList);
						}
					}
				}
				returnBookList=bookList;
			}
			
		} catch (Exception e) {
			logger.error("getBookList() method in LibraryDAOImpl", e);
		} finally {
			session.close();
		}
		return returnBookList;
	}
	
	
	/**@author anup.roy
		library new
	**/
	@Override
	public Book getBookDetails(Book book) {
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("getBookList() method in LibraryDAOImpl");
			
			book = session.selectOne("getBookDetailsToUpdate", book);
			List<BookLanguage> bookLanguageList = session.selectList("getbookLanguageList");
			List<Author> bookAuthorList = session.selectList("getBookAuthorList", book);
			if (bookAuthorList != null && bookAuthorList.size() != 0) {
				book.setBookAuthorList(bookAuthorList);
			}
			book.setBookLanguageList(bookLanguageList);
			List<Genre> genreList = session.selectList("getGenreList");
			book.setGenreList(genreList);
		} catch (Exception e) {
			logger.error("getBookList() method in LibraryDAOImpl", e);
			e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	@Override
	public String updateBook(Book book, String updatedBy) {	//Added by Saif 21-03-2018
		SqlSession session = sqlSessionFactory.openSession();
		String status= "" ;	//	Added by Saif 20-03-2018
		try {
			logger.info("updateBook() method in LibraryDAOImpl");			
			Utility util = new Utility();
			book.setBookObjectId(util.getBase64EncodedID("LibraryDAO"));
			book.setUpdatedBy(updatedBy);
			session.update("updateBookCataloging", book);
			session.update("inactiveBookAuthorMapping", book);
			if (book.getBookAuthorList() != null && book.getBookAuthorList().size() != 0) {
				for (Author a : book.getBookAuthorList()) {
					Author authorToInsert = new Author();
					authorToInsert.setAuthorObjectId(util.getBase64EncodedID("LibraryDAO"));
					authorToInsert.setUpdatedBy(book.getUpdatedBy());
					authorToInsert.setAuthorFullName(a.getAuthorFullName());
					/*
					 * Calling insert tag with id="insertAuthorList" in
					 * BookMapper.xml
					 */
					session.insert("insertAuthorList", authorToInsert);
					/* Inserting Book-Author Information in Association Table */
					BookAuthor bookAuthor = new BookAuthor();
					bookAuthor.setUpdatedBy(updatedBy);
					bookAuthor.setBookAuthorObjectId(util.getBase64EncodedID("LibraryDAO"));
					bookAuthor.setBookObjectId(book.getBookObjectId());
					bookAuthor.setAuthorObjectId(authorToInsert.getAuthorObjectId());
					int insertStatus= session.insert("insertBookAuthor", bookAuthor);
					if(insertStatus!=0)
					{
						status= "success";
					}
					else
					{
						status= "fail";
					}
				}
			}
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("updateBook() method in LibraryDAOImpl", e);
		} finally {
			session.close();
		}
		return status;
	}

	@Override
	public List<BookLanguage> getbookLanguageList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<BookLanguage> bookLanguageList = null;
		try {
			logger.info("getbookLanguageList() method in LibraryDAOImpl");			
			bookLanguageList = session.selectList("getbookLanguageList");
		}catch (Exception e) {
			logger.error("getbookLanguageList() method in LibraryDAOImpl", e);
		} finally {
			session.close();
		}
		return bookLanguageList;
	}

	@Override
	public Integer getMaxNoOfBooksPerRequest(String userId) {
		SqlSession session = sqlSessionFactory.openSession();
		Integer maxNoOfBooksPerRequest = null;
		try {
			logger.info("getMaxNoOfBooksPerRequest() method in LibraryDAOImpl");
			maxNoOfBooksPerRequest = session.selectOne("selectMaxNoOfBooksPerRequest",userId);
		}catch (Exception e) {
			logger.error("getMaxNoOfBooksPerRequest() method in LibraryDAOImpl", e);
		} finally {
			session.close();
		}
		return maxNoOfBooksPerRequest;
	}

	@Override
	public List<LoggingAspect> getUpdateBookLogDetails(String bookCode) {
			SqlSession session =sqlSessionFactory.openSession();
			List<LoggingAspect> logDetails = null;
			try{
				logger.info("getUpdateBookLogDetails( ) method in AdmissionDaoImpl");
				logDetails = session.selectList("selectUpdateBookLogDetails",bookCode);			
			}catch(Exception e){
				logger.error(" Exception in From getUpdateBookLogDetails() of AdmissionDriveDAO :",e);
			}finally{
				session.close(); 
			}
			return logDetails;
		}

	@Override
	public List<LoggingAspect> getRetiredBookLogDetails(String bookCode) {
		SqlSession session =sqlSessionFactory.openSession();
		List<LoggingAspect> logDetails = null;
		try{
			logger.info("getRetiredBookLogDetails( ) method in AdmissionDaoImpl");
			logDetails = session.selectList("selectRetiredBookLogDetails",bookCode);			
		}catch(Exception e){
			logger.error(" Exception in From getRetiredBookLogDetails() of AdmissionDriveDAO :",e);
		}finally{
			session.close(); 
		}
		return logDetails;
	}

	@Override
	public String getVendorBookPriceHistory(Item item) {
		logger.info("In  getVendorBookPriceHistory() method of LibraryDAOIMpl");
		SqlSession session =sqlSessionFactory.openSession();
		//System.out.println("getVendorBookPriceHistory");
		String priceHistory = "";
		try {
			List<Item> itemList = new ArrayList<Item>();
			itemList = session.selectList("getVendorBookPriceHistory", item);
			for (Item i : itemList) {
				//System.out.println(i.getStatus() + "\t" + i.getSellingRate());
				priceHistory = priceHistory + i.getStatus() + "*"+ i.getSellingRate() + "#";
			}
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			logger.error("getVendorBookPriceHistory() In LibraryController.java: NullPointerException"+ ne);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getVendorBookPriceHistory() In LibraryController.java: Exception"+ e);
		}
		return priceHistory;
	}

	/**@author anup.roy
		library new
	**/

	@Override
	public List<Genre> getGenreList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Genre> genreList = null;
		try {
			logger.info("In getGenreList() method in LibraryDAOImpl");			
			genreList = session.selectList("getGenreList");
		}catch (Exception e) {
			logger.error("In getGenreList() method in LibraryDAOImpl", e);
		} finally {
			session.close();
		}
		return genreList;
	}

	/**@author anup.roy
		library new
	**/

	@Override
	public List<BookAllocation> getReadingHabitOfResource(Resource resource) {
		SqlSession session = sqlSessionFactory.openSession();
		List<BookAllocation> bookRequestCodes = null;
		try{
			logger.info("In getReadingHabitOfResource() in libraryDaoImpl");
			bookRequestCodes = session.selectList("getBookRequestCodes", resource);
			if (bookRequestCodes != null) {
				for (BookAllocation bookAllocation : bookRequestCodes) {
					List<BookAllocationDetails> issuedBookDetails = session.selectList("getRequestedBookAgainstRequestCode",bookAllocation.getBookAllocationCode());
					bookAllocation.setBookAllocationDetails(issuedBookDetails);
					bookAllocation.setBookIssuedTo(resource);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return bookRequestCodes;
	}
	
	@Override
	public List<BookAllocation>  getBookAllocatedStudentDetailsList()throws CustomException {
		List<BookAllocation> bookAllocatedList = null;
		SqlSession session =sqlSessionFactory.openSession();
		try{
			bookAllocatedList=session.selectList("selectAllocatedBookDetails");
		}catch(Exception e) {
			logger.error(e);
			CustomException.exceptionHandler(e);
		}finally{
			session.close();
		}
		return bookAllocatedList;
	}
	
	@Override
	public List<Department> getDepartmentForBookRequisition() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Department> departmentList = null;
		try {
			logger.info("In getGenreList() method in LibraryDAOImpl");			
			departmentList = session.selectList("selectDepartmentForBookRequisition");
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("In getGenreList() method in LibraryDAOImpl", e);
		} finally {
			session.close();
		}
		return departmentList;
	}
	
	@Override
	public String getBankAllDetails(String bank) {
		logger.info("In getBankDetails() method of FinanceDaoImpl");
		String bankRecord = "";
		SqlSession session =sqlSessionFactory.openSession();
		try {
			Vendor bankDetails = new Vendor();
			bankDetails = session.selectOne("getBankAllDetailsList",bank);
			bankRecord += bankDetails.getBankCode()+"~"+bankDetails.getAccountNo()+"~"+bankDetails.getBankLocation();
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("In getGroupTypeList() method of FinanceDaoImpl", e);
		} finally {
			session.close();
		}
		return bankRecord;	
	}
	
	/**
	 * saif.ali
	 * 03072017*/
	@Override
	public List<String> getUserIdList(String strQuery) {
		logger.info("In getUserIdList(String strQuery) method of BackOfficeDAOImpl: ");
		SqlSession session = sqlSessionFactory.openSession();
		List<String> UserIdList = null;
		try {
			UserIdList = session.selectList("selectUserIdListByAjax",strQuery);
		} catch (NullPointerException e) {
			logger.error("Error In getUserIdList(String strQuery) method of LibraryDAOImpl By Ajax call:NullPointerException:: "+ e);
		} catch (Exception e) {
			logger.error("Error In getUserIdList(String strQuery) method of LibraryDAOImpl By Ajax call:Exception:: "+ e);
		} finally {
			session.close();
		}
		return UserIdList;
	}
	
	/**
	 * saif.ali
	 * 03072017*/


	@Override
	public int getMaximumNumberBook(String bookRequestedFor) {
		logger.info("In getMaximumNumberBook(String strQuery) method of LibraryDAOImpl: ");
		SqlSession session = sqlSessionFactory.openSession();
		int maxNumberBook = 0;
		try {
			maxNumberBook = session.selectOne("selectMaximumNumberBookByAjax",bookRequestedFor);
		} catch (NullPointerException e) {
			logger.error("Error In getMaximumNumberBook(String bookRequestedFor) method of LibraryDAOImpl By Ajax call:NullPointerException:: "+ e);
		} catch (Exception e) {
			logger.error("Error In getMaximumNumberBook(String bookRequestedFor) method of LibraryDAOImpl By Ajax call:Exception:: "+ e);
		} finally {
			session.close();
		}
		return maxNumberBook;
	}

	/**
	 * @author anup.roy
	 * this method is for getting all categories in library
	 * returns list
	 * */
	@Override
	public List<BookCategory> getAllCategories() {
		List<BookCategory> categoryList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("In List<BookCategory> getAllCategories() of LibraryDaoImpl.java");
			categoryList = session.selectList("getAllCategories");
		}catch(Exception e){
			logger.error("Exception in List<BookCategory> getAllCategories() of LibraryDaoImpl.java", e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return categoryList;
	}
	
	/**
	 * @author anup.roy
	 * this method is for getting last accession no of book in library*/

	@Override
	public int getLastAccessionNo() {
		int lastAccessionNo = 0;
		SqlSession session = sqlSessionFactory.openSession();
		try{
			logger.info("In int getLastAccessionNo() of LibraryDaoImpl(try block)");
			String accessionStr = session.selectOne("getLastAccessionNo");
			if(null == accessionStr){
				lastAccessionNo = 0;
			}else{
				lastAccessionNo = Integer.parseInt(accessionStr);
			}
		}catch (Exception e) {
			logger.error("Exception in int getLastAccessionNo() of LibraryDaoImpl:"+e);
			e.printStackTrace();
		}finally{
			session.close();
		}
		return lastAccessionNo;
	}
	
	/**
	 * @author anup.roy
	 * this method is for adding new magazine in library*/

	@Override
	public String addNewMagazine(Magazine mag) {
		String insertStatus = "fail";
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		int magazineInsertStatus = 0;
		try{
			logger.info("In String addNewMagazine(Magazine mag) of LibraryDaoImpl.java");
			mag.setMagazineObjectId(util.getBase64EncodedID("LibraryDao"));
			/*MagazinePublisher magPublisher = mag.getMagazinePublisher();
			String magPublisherName = mag.getMagazinePublisher().getMagazinePublisherName();
			String magazineRecord = session.selectOne("getMagazinePublisherID",magPublisherName);
			if (magazineRecord == null) {
				magPublisher.setMagazinePublisherObjId(util.getBase64EncodedID("LibraryDao"));
				magPublisher.setUpdatedBy(mag.getUpdatedBy());
				publisherInsertStatus = session.insert("insertMagazinePublisher", magPublisher);
			}*/
			magazineInsertStatus = session.insert("insertMagazine", mag);
			session.commit();
			if(magazineInsertStatus != 0){
				insertStatus = "success";
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in String addNewMagazine(Magazine mag) of LibraryDaoImpl.java:"+e);
		}
		return insertStatus;
	}
	/**
	 * @author Sourav.Bhadra on 12-02-2018
	 */
	@Override
	public List<Magazine> getMagazineList() {
		SqlSession session = sqlSessionFactory.openSession();
		List<Magazine> listOfMagazine = null;
		try{
			listOfMagazine = session.selectList("getMagazineListForEdit");
		}catch(Exception e){
			e.printStackTrace();
		}
		return listOfMagazine;
	}
	/**
	 * @author Sourav.Bhadra on 15-02-2018
	 */
	@Override
	public void updateMagazine(Magazine magazine) {
		SqlSession session = sqlSessionFactory.openSession();
		try{
			session.update("updateMagazine", magazine);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @author anup.roy
	 * this method is for adding a new book to catalogue*/
	@Override
	public String addBookToCatalogueFromLibrary(Book book) {
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		String returnStatus = "fail";
		String bookPublisherRecordFromDB = null;
		int authorInsertStatus = 0;
		int publiserInsertStatus = 0;
		int bookinsertStatus = 0;
		try {
			/* Inserting Publisher Information */
			BookPublisher bookPublisher = book.getBookPublisher();
			String publisherName = bookPublisher.getBookPublisherName();
			bookPublisher.setBookPublisherObjectId(util.getBase64EncodedID("LibraryDAO"));
			bookPublisher.setUpdatedBy(book.getUpdatedBy());
			bookPublisherRecordFromDB = session.selectOne("getBookPeriodicityPublisher",publisherName);
			if(null == bookPublisherRecordFromDB){
				publiserInsertStatus = session.insert("insertBookPublisher", bookPublisher);
				session.commit();
			}
			book.setBookPublisherId(publisherName);
			book.setBookObjectId(util.getBase64EncodedID("LibraryDAO"));
			/**
			 * @author anup.roy
			 * this will insert all info of book in book table
			 * except author details*/
			bookinsertStatus = session.insert("addBookToCatalogueFromLibrary", book);
			String itemType = book.getBookType();
			String itemCode = session.selectOne("getLastInsertedItemCode", itemType);
			session.commit();
			List<Author> authorListToDB = book.getBookAuthorList();
			for (Author author : authorListToDB) {
				Author authorToInsert = new Author();
				authorToInsert.setAuthorObjectId(util.getBase64EncodedID("LibraryDAO"));
				authorToInsert.setUpdatedBy(book.getUpdatedBy());
				authorToInsert.setAuthorFullName(author.getAuthorFullName());
				/** 
				 * Inserting Author Information in author table
				 * */
				session.insert("insertAuthorList", authorToInsert);
				session.commit();

				BookAuthor bookAuthor = new BookAuthor();
				bookAuthor.setUpdatedBy(book.getUpdatedBy());
				bookAuthor.setBookAuthorObjectId(util.getBase64EncodedID("LibraryDAO"));
				bookAuthor.setBookObjectId(itemCode);
				bookAuthor.setAuthorObjectId(authorToInsert.getAuthorFullName());
				/** 
				 * Inserting Author Information in book_author table
				 * this will associate the book and author list
				 * */
				authorInsertStatus = session.insert("insertBookAuthor", bookAuthor);
				session.commit();
			}
			if(bookPublisherRecordFromDB != null) {
				if(bookinsertStatus != 0 && authorInsertStatus != 0) {
					returnStatus = "success";
				}
			}else {
				if(publiserInsertStatus != 0 && bookinsertStatus != 0 && authorInsertStatus != 0) {
					returnStatus = "success";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		} finally {
			session.close();
		}
		return returnStatus;
	}

	/**
	 * @author anup.roy
	 * this method is for adding a new magazine to catalogue*/
	@Override
	public String addMagazineToCatalogueFromLibrary(Magazine mag) {
		String insertStatus = "fail";
		SqlSession session = sqlSessionFactory.openSession();
		Utility util = new Utility();
		int publisherInsertStatus = 0;
		int magazineInsertStatus = 0;
		try{
			logger.info("In String addMagazineToCatalogueFromLibrary(Magazine mag) of LibraryDaoImpl.java");
			mag.setMagazineObjectId(util.getBase64EncodedID("LibraryDao"));
			MagazinePublisher magPublisher = mag.getMagazinePublisher();
			String magPublisherName = mag.getMagazinePublisher().getMagazinePublisherName();
			String magazineRecord = session.selectOne("getMagazinePublisherID",magPublisherName);
			if (magazineRecord == null) {
				magPublisher.setMagazinePublisherObjId(util.getBase64EncodedID("LibraryDao"));
				magPublisher.setUpdatedBy(mag.getUpdatedBy());
				publisherInsertStatus = session.insert("insertMagazinePublisher", magPublisher);
			}
			magazineInsertStatus = session.insert("addMagazineToCatalogueFromLibrary", mag);
			session.commit();
			if(magazineRecord != null) {
				if(magazineInsertStatus != 0) {
					insertStatus = "success";
				}
			}else {
				if(publisherInsertStatus != 0 && magazineInsertStatus != 0) {
					insertStatus = "success";
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception in String addMagazineToCatalogueFromLibrary(Magazine mag) of LibraryDaoImpl.java:"+e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return insertStatus;
	}
	
	/**
	 * @author anup.roy
	 * this method is for fetching all items from catalogue*/
	
	@Override
	public List<Item> getListOfItemsFromCatalogue(String category) {
		List<Item> itemList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In List<Book> getListOfItemsFromCatalogue(String category) of libraryDaoImpl.java");
			itemList = session.selectList("getListOfItemsFromCatalogue", category);
		}catch (Exception e) {
			logger.error("Exception in List<Book> getListOfItemsFromCatalogue(String category) of libraryDaoImpl.java:"+e);
			e.printStackTrace();
		}finally {
			session.close();
		}
		return itemList;
	}
	
	/**
	 * @author anup.roy
	 * this method is for fetching all details of book from library catalogue w.r.t code*/

	@Override
	public Book getAllDetailsOfBooksFromCatalogue(String bookCode) {
		Book bookDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In Book getAllDetailsOfBooksFromCatalogue(String bookCode) of libraryDaoImpl.java");
			bookDetails = session.selectOne("getAllDetailsOfBooksFromCatalogue", bookCode);
		}catch (Exception e) {
			logger.error("Exception in Book getAllDetailsOfBooksFromCatalogue(String bookCode) of libraryDaoImpl.java:"+e);
			e.printStackTrace();
		}
		return bookDetails;
	}
	
	/**
	 * @author anup.roy
	 * this method is for fetching all details of magazines from library catalogue w.r.t code*/

	@Override
	public Magazine getAllDetailsOfMagazinesFromCatalogue(String magCode) {
		Magazine magDetails = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			logger.info("In Book getAllDetailsOfMagazinesFromCatalogue(String magCode) of libraryDaoImpl.java");
			magDetails = session.selectOne("getAllDetailsOfMagazinesFromCatalogue", magCode);
		}catch (Exception e) {
			logger.error("Exception in Book getAllDetailsOfMagazinesFromCatalogue(String magCode) of libraryDaoImpl.java:"+e);
			e.printStackTrace();
		}
		return magDetails;
	}
}
