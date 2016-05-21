
public class RomanToInteger {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(RomanToInteger.romanToInt("MCDXLI"));
	}
	public static int romanToInt(String s) {
		int re=0;
        int n=s.length();
        re=chToInt(s.charAt(0));
		for(int i=1;i<n;i++){
			char ch=s.charAt(i);
			 if (chToInt(s.charAt(i-1)) < chToInt(s.charAt(i))) {  
	                re += chToInt(s.charAt(i)) - 2 * chToInt(s.charAt(i-1));  
	            } else {  
	                re += chToInt(s.charAt(i));  
	            }  
		}
		return re;                                                                                                                                                                                                                   
		
    }
	public static int chToInt(char ch){
		switch(ch){
		case 'I':return 1;
		case 'V':return 5;
		case 'X':return 10;
		case 'L':return 50;
		case 'C':return 100;
		case 'D':return 500;
		case 'M':return 1000;
		}
		return 0;
	}
}
