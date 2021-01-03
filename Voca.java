package konkuk.sw;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Voca {
	private ArrayList<Word> words = new ArrayList<>();
	private Map<String, Word> map = new HashMap<>();
	private boolean[] b;
	private static Scanner scan = new Scanner(System.in);
	private String[] ans;

	public Voca() {
		readVoca();
	}

	public void quiz() {
		b = new boolean[map.size()];
		int count = 0;
		for (int i = 0; i < 5; i++) {
			Random rand = new Random();
			int index = rand.nextInt(map.size());
			if (b[index]) {
				i--;
				continue;
			}

			Set<String> keyset = map.keySet();
			Iterator<String> it = keyset.iterator();
			for (int j = 0; j < index; j++) {
				it.next();
			}

			String eng = it.next();
			map.get(eng).increasecount();
			System.out.println(eng);
			b[index] = true;
			System.out.print("정답을 입력하세요 : ");
			String input = scan.nextLine();
			ans = map.get(eng).getKor().split("/");
			boolean ch = true;
			for (String answer : ans) {
				if (input.equals(answer)) {
					ch = false;
					System.out.println("맞혔습니다!");

					count++;
					break;
				}
			}
			if (ch) {
				System.out.println("틀렸습니다!");
			}

			System.out.println("*******************************");

		}
		System.out.println("맞은 개수 : " + count + " / 5");

	}

	public void readVoca() {
		try {
			File file = new File("test.txt");
			Scanner scan = new Scanner(file);
			while (scan.hasNextLine()) {
				String str = scan.nextLine();
				String[] engkr = str.split(" : ");

				words.add(new Word(engkr[0], engkr[1]));
				map.put(engkr[0], new Word(engkr[0], engkr[1]));
			}
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void multipleChoice() {
		int i = 0; // while문 종료 조건 변수
		int right = 0; // 맞은 개수
		Map<String, String> ansMap = new HashMap<>(5); // 5문제 정답 map<영단어, 뜻> 객체
		Set<String> tmp = map.keySet();
		Iterator<String> it = tmp.iterator();
		String[] keys = new String[map.size()];
		int m = 0;
		while (it.hasNext())
			keys[m++] = it.next();
		while (i < 5) {
			int h = 0;
			String[] kor = new String[4]; // 문제 배열
			Random rand = new Random();
			int ans = rand.nextInt(map.size());
			if (ansMap.containsKey(keys[ans]))
				continue; // 중복체크

			String answer_eng = map.get(keys[ans]).getEng();
			map.get(keys[ans]).increasecount();
			String answer_kor = map.get(keys[ans]).getKor();
			ansMap.put(answer_eng, answer_kor);

			int ans_num = rand.nextInt(4);
			kor[ans_num] = answer_kor; // 1~4번 중에서 하나 정답으로
			System.out.println("단어  : " + answer_eng);
			Map<Integer, String> fourMap = new HashMap<>(); // 4지선다 맵 자료형 <index, 뜻>
			fourMap.put(ans, answer_kor);
			while (h < 4) { // 틀린 문제들 넣기
				if (kor[h] == null) {
					int tmpRand;
					do {
						tmpRand = rand.nextInt(map.size());
					} while (fourMap.containsKey(tmpRand)); // 4지선다 값이 다 다르도록
					fourMap.put(tmpRand, map.get(keys[tmpRand]).getKor());
					kor[h] = fourMap.get(tmpRand);
				}
				h++;
			}
			for (int l = 0; l < 4; l++) {
				System.out.print((l + 1) + ")");
				System.out.println(kor[l]);

			}
			Scanner sc = new Scanner(System.in);
			if (kor[sc.nextInt() - 1].equals(answer_kor)) {
				right++;
				System.out.println("맞추셨습니다! 현재 맞춘 개수 : " + right + "/5");
			} else {
				System.out.println("틀렸습니다! 현재 맞춘 개수 : " + right + "/5");
			}

			i++;
		}

	}

	public void inputVoca() {
		System.out.println("영단어를 입력해주세요: ");
		String eng = scan.nextLine();
		System.out.println("뜻을 입력해주세요: ");
		String kor = scan.nextLine();
		Word w = new Word(eng, kor);

		if (map.containsKey(eng)) {
			System.out.println("이미 있는 단어입니다.");
		} else {
			System.out.println("단어가 정상적으로 추가되었습니다.");
			map.put(eng, w);
			try {

				File file = new File("test.txt");
				Scanner scan = new Scanner(file);
				String str = w.getEng() + " : " + w.getKor();

				BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));

				bw.write(str);
				bw.newLine();
				bw.flush();
				bw.close();
				scan.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	void top() {
		ArrayList<Word> arr = new ArrayList<>();

		Set<String> keyset = map.keySet();
		Iterator<String> it = keyset.iterator();
		for (int j = 0; j < map.size(); j++) {
			String key = it.next();
			if (map.get(key).getCount() != 0)// 0이 아닐때만 받는다.
				arr.add(map.get(key));
			
		}
		
			System.out.println("1번 : 오름차순, 2번 : 내림차순");
			
		switch(scan.nextInt()) {
			case 1:
				Collections.sort(arr,Collections.reverseOrder());
				break;
			case 2:
				Collections.sort(arr);
				break;
		}
		for(int i =0;i<10;i++) {
			System.out.print((i+1)+"번째 빈출단어 : ");
			System.out.println(arr.get(i));
		}
	
	}
		
		
			
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub

		String str = "";
		for (int i = 0; i < words.size(); i++) {

			str += words.get(i).getEng() + " : " + words.get(i).getKor() + "\n";
		}
		return str;
	}
}
