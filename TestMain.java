package konkuk.sw;

import java.io.File;
import java.util.Scanner;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("1�� ������ ����ä �̼��� �ֽ���");
		int choice = 0;
		File file = new File("test.txt");
		Voca voca1 = new Voca();
		Scanner scan;
		Scanner scanner = new Scanner(System.in);
		System.out.println(voca1);
		
		boolean b = true;
		while(b) {
			System.out.println("�ܾ����� ����� �����Ͻʽÿ�.");
			System.out.println("1) �ְ��� 2) ������ 3) �ܾ��߰� 4) ����ܾ� 5) ����");
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
				System.out.println("1,2,3,4�� �߿� �������ֽʽÿ�.");
			}
		}
	}

}
