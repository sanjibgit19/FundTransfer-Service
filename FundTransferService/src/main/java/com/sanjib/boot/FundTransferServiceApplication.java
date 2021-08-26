package com.sanjib.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class FundTransferServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FundTransferServiceApplication.class, args);
		
	/*	System.out.println(10+20+"hllo world");
		System.out.println("hllo world"+10+20);
		StringBuffer s1=new StringBuffer("complete");
		s1.setCharAt(1, 'i');
		s1.setCharAt(7, 'd');
		System.out.println(s1);
		
		int valu[]= {1,2,3,4,5,6,7,8,9,10};
		for(int i=0; i<89; ++i) {
			System.out.println(valu[i]);
			
		}
		*/
	}//main(-)

}//class
