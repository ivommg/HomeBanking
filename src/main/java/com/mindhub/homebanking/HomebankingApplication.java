package com.mindhub.homebanking;

import com.mindhub.homebanking.models.*;
import com.mindhub.homebanking.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class HomebankingApplication {

	@Autowired
	private PasswordEncoder passwordEncoder;
	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}


	public static void main(String[] args) {
		SpringApplication.run(HomebankingApplication.class, args);
	}
	@Bean
	public CommandLineRunner initData(ClientRepository clientRepository, AccountRepository accountRepository , TransactionRepository transactionRepository, LoanRepository loanRepository, ClientLoanRepository clientLoanRepository , CardRepository cardRepository){
		return args -> {

//
//			Client client1 = new Client("Melba","Lorenzo","melba@mindhub.com",passwordEncoder.encode("melba"));
//			Client client2= new Client("Cecilia","Romero","ceciromero@gmail.com", passwordEncoder.encode("1234"));
//
//			Account account1 = new Account("VIN"+ getRandomNumber(10000000,100000000), LocalDate.now(), 5000.00);
//			Account account2 = new Account("VIN"+ getRandomNumber(10000000,100000000), LocalDate.now().minusMonths(5), 7000.00);
//			Account account3 = new Account("VIN"+ getRandomNumber(10000000,100000000), LocalDate.now(), 10000.00);
//			Account account4 = new Account("VIN"+ getRandomNumber(10000000,100000000), LocalDate.now(), 11000.00);
//
//
//			client1.addAcconts(account1);
//			client1.addAcconts(account2);
//			client2.addAcconts(account3);
//			client2.addAcconts(account4);
//
//
//
//			Transaction transaction1 = new Transaction(TransactionType.DEBIT,-500.00,LocalDateTime.now(),"coca");
//			Transaction transaction2 = new Transaction(TransactionType.DEBIT,-800.00,LocalDateTime.now(),"comida");
//			Transaction transaction3 = new Transaction(TransactionType.CREDIT,1000.00,LocalDateTime.now(),"venta de ropa");
//			Transaction transaction4 = new Transaction(TransactionType.CREDIT,700.00,LocalDateTime.now(),"venta");
//			Transaction transaction5 = new Transaction(TransactionType.DEBIT,-500.00,LocalDateTime.now(),"coca");
//			Transaction transaction6 = new Transaction(TransactionType.DEBIT,-800.00,LocalDateTime.now(),"comida");
//			Transaction transaction7 = new Transaction(TransactionType.CREDIT,1000.00,LocalDateTime.now(),"venta de ropa");
//			Transaction transaction8 = new Transaction(TransactionType.CREDIT,700.00,LocalDateTime.now(),"venta");
//
//
//
//
//			Card card1 = new Card(CardType.DEBIT,CardColor.GOLD,getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000), getRandomNumber(100,1000),LocalDate.now(),LocalDate.now().plusYears(5),client1);
//			Card card2 = new Card(CardType.DEBIT,CardColor.TITANIUM,getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000), getRandomNumber(100,1000),LocalDate.now(),LocalDate.now().plusYears(5),client1);
//			Card card3 = new Card(CardType.CREDIT,CardColor.SILVER,getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000),getRandomNumber(100,1000),LocalDate.now(),LocalDate.now().plusYears(5),client2);
//			Card card4 = new Card(CardType.CREDIT,CardColor.GOLD,getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000)+" "+getRandomNumber(1000,10000),getRandomNumber(100,1000),LocalDate.now(),LocalDate.now().plusYears(5),client2);
//
//
//
//			account1.addtransaction(transaction1);
//			account1.addtransaction(transaction2);
//			account1.addtransaction(transaction3);
//			account1.addtransaction(transaction4);
//			account2.addtransaction(transaction5);
//			account2.addtransaction(transaction6);
//			account2.addtransaction(transaction7);
//			account2.addtransaction(transaction8);
//
//
//			Loan loan1= new Loan("Hipotecario",500000.00,List.of(12,24,36,48,60));
//			Loan loan2= new Loan("Personal",100000.00, List.of(6,12,24));
//			Loan loan3= new Loan("Automotriz", 300000.00,List.of(6,12,24,36));
//
//
//			ClientLoan loanMelba = new ClientLoan(400000.00,12,client1,loan1);
//			ClientLoan loanMelba2 = new ClientLoan(50000.00,6,client1,loan2);
//			ClientLoan loanMelba3= new ClientLoan(100000.00,6,client1,loan3);
//
//
//
//			clientRepository.save(client1);
//			clientRepository.save(client2);
//
//
//			accountRepository.save(account1);
//			accountRepository.save(account2);
//			accountRepository.save(account3);
//			accountRepository.save(account4);
//
//
//			transactionRepository.save(transaction1);
//			transactionRepository.save(transaction2);
//			transactionRepository.save(transaction3);
//			transactionRepository.save(transaction4);
//			transactionRepository.save(transaction5);
//			transactionRepository.save(transaction6);
//			transactionRepository.save(transaction7);
//			transactionRepository.save(transaction8);
//
//
//			loanRepository.save(loan1);
//			loanRepository.save(loan2);
//			loanRepository.save(loan3);
//
//
//			clientLoanRepository.save(loanMelba);
//			clientLoanRepository.save(loanMelba2);
//			clientLoanRepository.save(loanMelba3);
//
//
//			cardRepository.save(card1);
//			cardRepository.save(card2);
//			cardRepository.save(card3);
//			cardRepository.save(card4);









		};
	}
}
