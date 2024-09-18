package HiJaeYoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
* Input
*   문자열의 개수 N <= 10,000 ,문자열의 길이 <= 100, 1~9,AaBb..Zz
* Output 1sec
*   natural sort 결과
*
* 1. 조건에 맞춰 정렬하기
*   - 입력받은 문자를 잘라서 저장(잘린 문자열 + 문자 번호)
*   - 그 다음 요소를 비교햐야하는 경우는 서로 다른 문자열이 같은 요소롤 갖고 있을때이다.
* 2. 클래스 만들기
*   - stringValue
*       List<String> values;
*       public StringValue(String String) {values 생성}
*       Comparable 을 사용하여 정렬 기준 정의
 */
public class Num20210 {
    private static class StringValue implements Comparable<StringValue>{
        List<String> values;
        String string;
        public StringValue(String string){
            this.string = string;
            values = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            sb.append(string.charAt(0));
            for(int i=1;i<string.length();i++){
                if('0'<= string.charAt(i) && string.charAt(i) <='9'){
                    if('0'<= sb.charAt(sb.length()-1) && sb.charAt(sb.length()-1)<='9'){
                        sb.append(string.charAt(i));
                    }else{
                        values.add(sb.toString());
                        sb = new StringBuilder();
                        sb.append(string.charAt(i));
                    }
                }else{
                    if('0'<= sb.charAt(sb.length()-1) && sb.charAt(sb.length()-1)<='9'){
                        values.add(sb.toString());
                        sb = new StringBuilder();
                        sb.append(string.charAt(i));
                    }else{
                        sb.append(string.charAt(i));
                    }
                }
            }
            values.add(sb.toString());
        }
        @Override
        public int compareTo(StringValue o1){
            int index =0;
            while(true){
                if(index == this.values.size()) return -1;
                if(index == o1.values.size()) return 1;
                if(this.values.get(index).equals(o1.values.get(index))){
                    index++;
                }else{
                    if(isNumber(this.values.get(index).charAt(0)) && isNumber(o1.values.get(index).charAt(0))){
                        return compareNumeric(this.values.get(index),o1.values.get(index));
                    }else if(!isNumber(this.values.get(index).charAt(0)) && !isNumber(o1.values.get(index).charAt(0))){
                        return compareString(this.values.get(index),o1.values.get(index));
                    }else if(isNumber(this.values.get(index).charAt(0)) && !isNumber(o1.values.get(index).charAt(0))){
                        return -1;
                    }else {
                        return 1;
                    }
                }
            }
        }

        private boolean isNumber(char c) {
            return '0' <= c && c <= '9';
        }

        public int compareString(String a, String b){
            int aLen = a.length();
            int bLen = b.length();
            int index = 0;
            while (true) {
                if(index==aLen) return -1;
                if(index==bLen) return 1;
                boolean isAUp = true;
                boolean isBUp = true;
                char nowA = a.charAt(index);
                if (!Character.isUpperCase(nowA)) {
                    nowA = Character.toUpperCase(nowA);
                    isAUp = false;
                }
                char nowB = b.charAt(index);
                if (!Character.isUpperCase(nowB)) {
                    nowB = Character.toUpperCase(nowB);
                    isBUp = false;
                }
                if(nowA==nowB){
                    if(isAUp == isBUp){
                        index++;
                        continue;
                    }
                    if(isBUp) return 1;
                    return -1;
                }
                return nowA-nowB;
            }
        }
        public int compareNumeric(String a, String b){
            int a0 = 0;
            int b0 = 0;
            while(!a.isEmpty() && a.charAt(0) == '0'){
                if(a.equals("0")){
                    a = "0";
                    break;
                }
                a0 ++;
                a = a.substring(1);
            }
            while(!b.isEmpty() && b.charAt(0) == '0'){
                if(b.equals("0")){
                    b = "0";
                    break;
                }
                b0 ++;
                b = b.substring(1);
            }
            if(a.length() == b.length()){
                for(int i=0;i<a.length();i++){
                    if(a.charAt(i) == b.charAt(i))  continue;
                    return a.charAt(i) - b.charAt(i);
                }
            }else{
                return a.length() - b.length();
            }
            return a0 - b0;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<StringValue> stringValues = new ArrayList<>();
        for(int i=0;i<n;i++){
            stringValues.add(new StringValue(br.readLine()));
        }
        Collections.sort(stringValues);
        for (StringValue x : stringValues){
            System.out.println(x.string);
        }
    }
}
