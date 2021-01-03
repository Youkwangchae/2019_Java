package konkuk.sw;

import java.io.File;
import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("1조 강병우 유광채 이송헌 최승헌");
		int choice = 0;
		File file = new File("test.txt");
		Voca voca1 = new Voca();
		Scanner scan;
		Scanner scanner = new Scanner(System.in);
		System.out.println(voca1);
		
		boolean b = true;
		while(b) {
			System.out.println("단어장의 기능을 선택하십시오.");
			System.out.println("1) 주관식 2) 객관식 3) 단어추가 4) 빈출단어 5) 종료");
			System.out.println("****************************");
			choice = scanner.nextInt();
			switch(choice) {
			case 1:
				voca1.quiz();
				break;
			case 2:
				voca1.multipleChoice();
				break;
			case 3:
				voca1.inputVoca();
				break;
			case 4:
				voca1.top();
				break;
			case 5:
				b=false;
				break;
			default:
				System.out.println("1,2,3,4번 중에 선택해주십시오.");
			}
		}
	}

}
