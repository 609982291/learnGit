
public class lengthOfLongestSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int s=lengthOfLongestSubstring.solute("abgsgga");
		System.out.println(s);
	}
	public static int solute(String s){
		int [] locs=new int [256];//保存字符上一次出现的位置
        for(int i=0;i<locs.length;i++){
        	locs[i]=-1;
        }
        
        int idx = -1, max = 0;//idx为当前子串的开始位置-1
        for (int i = 0; i < s.length(); i++)
        {
            if (locs[s.charAt(i)] > idx)//如果当前字符出现过，那么当前子串的起始位置为这个字符上一次出现的位置+1
            {
                idx = locs[s.charAt(i)];
            }

            if (i - idx > max)
            {
                max = i - idx;
            }

            locs[s.charAt(i)] = i;
        }
        return max;
		
	}
}
