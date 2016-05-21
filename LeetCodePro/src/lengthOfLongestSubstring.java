
public class lengthOfLongestSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int s=lengthOfLongestSubstring.solute("abgsgga");
		System.out.println(s);
	}
	public static int solute(String s){
		int [] locs=new int [256];//�����ַ���һ�γ��ֵ�λ��
        for(int i=0;i<locs.length;i++){
        	locs[i]=-1;
        }
        
        int idx = -1, max = 0;//idxΪ��ǰ�Ӵ��Ŀ�ʼλ��-1
        for (int i = 0; i < s.length(); i++)
        {
            if (locs[s.charAt(i)] > idx)//�����ǰ�ַ����ֹ�����ô��ǰ�Ӵ�����ʼλ��Ϊ����ַ���һ�γ��ֵ�λ��+1
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
