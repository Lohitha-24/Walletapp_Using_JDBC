package org.cap.demo.model;

import java.time.LocalDateTime;

public class Transaction {
	
	private int transcationId;
	private TransactionType transactionType;
	private LocalDateTime transactionDateTime=LocalDateTime.now();
	private String descreption;
	private Account fromAccount;
	private Account toAccount;
	private double amount;
	
	public int getTranscationId() {
		return transcationId;
	}
	public void setTranscationId(int transcationId) {
		this.transcationId = transcationId;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public LocalDateTime getTransactionDateTime() {
		return transactionDateTime;
	}
	public void setTransactionDateTime(LocalDateTime transactionDateTime) {
		this.transactionDateTime = transactionDateTime;
	}
	public String getDescreption() {
		return descreption;
	}
	public void setDescreption(String descreption) {
		this.descreption = descreption;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Transaction(int transcationId, TransactionType transactionType, LocalDateTime transactionDateTime,
			String descreption, Account fromAccount, Account toAccount) {
		super();
		this.transcationId = transcationId;
		this.transactionType = transactionType;
		this.transactionDateTime = transactionDateTime;
		this.descreption = descreption;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}
	public Transaction() {
		super();
	}
	@Override
	public String toString() {
		return "Transaction [transcationId=" + transcationId + ", transactionType=" + transactionType
				+ ", transactionDateTime=" + transactionDateTime + ", descreption=" + descreption + ", fromAccount="
				+ fromAccount + ", toAccount=" + toAccount + ", amount=" + amount + "]";
	}
	

	
	
}
