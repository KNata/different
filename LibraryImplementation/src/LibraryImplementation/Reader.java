package LibraryImplementation;

import java.sql.Date;

public class Reader {
	
	private String readerName;
	private String readerAddress;
	private int readerPhone;
	private String readerBDay;
	private String readerCard;
	
	
	Reader(String aReaderName, String aReaderAddress, int aReaderPhone, String aReaderBDay, 
			String aReaderCard) {
		readerName = aReaderName;
		readerAddress = aReaderAddress;
		readerPhone = aReaderPhone;
		readerBDay = aReaderBDay;
		readerCard = aReaderCard;
	}


	public String getReaderName() {
		return readerName;
	}


	public String getReaderAddress() {
		return readerAddress;
	}


	public int getReaderPhone() {
		return readerPhone;
	}


	public String getReaderBDay() {
		return readerBDay;
	}


	public String getReaderCard() {
		return readerCard;
	}
	
}
