package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 20291 파일정리
 * .으로는 split 불가, \\.으로 해줘야함
 * .을 기준으로 split하여 배열에 넣어준 후 가장 마지막 요소를 map에 넣는다.
 * 각 확장자 별로 개수를 센 후 keySet을 리스트로 만들어 정렬 후 value값을 출력한다.
 * @author 미령
 *
 */
public class BOJ20291 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		Map<String, Integer> map = new HashMap<String, Integer>();

		for (int t = 0, end = Integer.parseInt(br.readLine()); t < end; t++) {
			String[] str = br.readLine().split("\\.");
			String key = str[str.length - 1];
			if (map.containsKey(key)) {
				map.put(key, map.get(key) + 1);
			} else {
				map.put(key, 1);
			}
		}
		List<String> keyList = new ArrayList<String>(map.keySet());
		Collections.sort(keyList);
		for (String s : keyList) {
			bw.write(s + " " + map.get(s) + "\n");
		}
		bw.flush();
		bw.close();
	}
}
