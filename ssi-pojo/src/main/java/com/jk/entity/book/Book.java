package com.jk.entity.book;

import java.io.Serializable;

public class Book implements Serializable{
	  private static final long serialVersionUID = 3688774250221539664L;

	private String bookName;
	  
	  private Integer	bookID;
	  
	  private String 	bookPhoto;
	  
	  
	  public String getBookName()
	  {
	    return this.bookName;
	  }

	  public void setBookName(String bookName) {
	    this.bookName = bookName;
	  }

	public Integer getBookID() {
		return bookID;
	}

	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}

	public String getBookPhoto() {
		return bookPhoto;
	}

	public void setBookPhoto(String bookPhoto) {
		this.bookPhoto = bookPhoto;
	}

	@Override
	public String toString() {
		return "Book [bookName=" + bookName + ", bookID=" + bookID + ", bookPhoto=" + bookPhoto + "]";
	}
	
}
