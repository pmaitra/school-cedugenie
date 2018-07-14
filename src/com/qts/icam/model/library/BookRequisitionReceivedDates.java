/**
 * 
 */
package com.qts.icam.model.library;

	/**
	 * @author saikat.das
	 * 
	 */
	/**
	 * @author saikat.das
	 * 
	 */
	public class BookRequisitionReceivedDates {
		private String bookRequisitionReceivedDatesId;
		private String bookRequisitionReceivedDatesObjectId;
		private String bookRequisitionDetailsCode;
		private int numberOfBooksReceived;
		private String dateBooksReceived;
		private String updatedBy;
		private boolean addedToStock;
		
	
		
		public String getBookRequisitionReceivedDatesObjectId() {
			return bookRequisitionReceivedDatesObjectId;
		}
	
		public void setBookRequisitionReceivedDatesObjectId(
				String bookRequisitionReceivedDatesObjectId) {
			this.bookRequisitionReceivedDatesObjectId = bookRequisitionReceivedDatesObjectId;
		}
	
		public String getBookRequisitionDetailsCode() {
			return bookRequisitionDetailsCode;
		}
	
		public void setBookRequisitionDetailsCode(String bookRequisitionDetailsCode) {
			this.bookRequisitionDetailsCode = bookRequisitionDetailsCode;
		}
	
		public int getNumberOfBooksReceived() {
			return numberOfBooksReceived;
		}
	
		public void setNumberOfBooksReceived(int numberOfBooksReceived) {
			this.numberOfBooksReceived = numberOfBooksReceived;
		}
	
		public String getDateBooksReceived() {
			return dateBooksReceived;
		}
	
		public void setDateBooksReceived(String dateBooksReceived) {
			this.dateBooksReceived = dateBooksReceived;
		}
	
		public String getUpdatedBy() {
			return updatedBy;
		}
	
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}

		public String getBookRequisitionReceivedDatesId() {
			return bookRequisitionReceivedDatesId;
		}

		public void setBookRequisitionReceivedDatesId(
				String bookRequisitionReceivedDatesId) {
			this.bookRequisitionReceivedDatesId = bookRequisitionReceivedDatesId;
		}

		public boolean isAddedToStock() {
			return addedToStock;
		}

		public void setAddedToStock(boolean addedToStock) {
			this.addedToStock = addedToStock;
		}
		

		
}
