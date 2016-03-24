package tyron.hospiterorder.temp;

public class hhhh {
	public static void main(String[] args){
		char[] num = {'1','2','3','4','5','6','7','8','9','0'};
		char[] array = new char[4];
		for(int i = 0; i < 10; i++){
			array[0] = num[i];
			for(int j = 0; j < 10; j++){
				array[1] = num[j];
				for(int k = 0; k < 10; k++){
					array[2] = num[k];
					for(int l = 0; l < 10; l++){
						array[3] = num[l];
//						if(getjian(array) == 3573)
							System.out.println(new String(array));
					}
				}
			}
		}
	}
	public static int getjian(char[] array){
		int max = 0;
		int min = 0;
		char tmp;
		for(int i = 0; i < 4; i++){
			for(int j = i; j < 3; j++){
				if(array[j] > array[j+1]){
					tmp = array[j];
					array[j] = array[j+1];
					array[j+1] = tmp;
				}
			}
		}
		min = Integer.parseInt(new String(array));
		tmp = array[0];
		array[0] = array[3];
		array[3] = tmp;
		tmp = array[1];
		array[1] = array[2];
		array[2] = tmp;
		max = Integer.parseInt(new String(array));
		return max-min;
	}
}
