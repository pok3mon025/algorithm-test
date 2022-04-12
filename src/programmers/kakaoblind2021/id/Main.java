package programmers.kakaoblind2021.id;

public class Main {
    public static void main(String[] args) {
        System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
        System.out.println(solution("z-+.^."));
        System.out.println(solution("=.="));
        System.out.println(solution("123_.def"));
        System.out.println(solution("abcdefghijklmn.p"));
    }

    public static String solution(String new_id) {

        // 1. 모든 대문자를 소문자로 치환
        new_id = new_id.toLowerCase();

        // 2. 알파벳 소문자, 숫자, -, _, .를 제외한 모든 문자 제거
        new_id = new_id.replaceAll("[^-_.a-z0-9]", "");

        // 3. 연속된 마침표를 하나의 마침표로 치환
        new_id = new_id.replaceAll("\\.+", ".");

        // 4. 처음이나 끝에 위치하는 마침표 제거
        new_id = new_id.replaceAll("^\\.|\\.$", "");

        // 5. 빈 문자열일 경우 "a" 치환
        if ("".equals(new_id))
            new_id = "a";

        // 6. 16자 이상이면 앞에서부터 15개의 문자만 남김
        if (new_id.length() > 15) {
            new_id = new_id.substring(0, 15);
            new_id = new_id.replaceAll("^\\.|\\.$", "");
        }

        // 7. 2자 이하이면 마지막 문자를 길이가 3이 될때까지 끝에 붙임
        if (new_id.length() < 3) {
            char last = new_id.charAt(new_id.length()-1);
            while (new_id.length() < 3)
                new_id += last;
        }

        return new_id;
    }
}
