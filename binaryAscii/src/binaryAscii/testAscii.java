package binaryAscii;

import java.util.Scanner;

public class testAscii {
	public static final String str = "abcdefghijklmnopqrstuvwxyz";
	public static void main(String[] args) 
    {
		String concat="";
		Scanner sc=new Scanner(System.in);
		Scanner sc2=new Scanner(System.in);
		
      	//ambil input text
		System.out.println("Masukan Text");
        String input = sc.nextLine();
        
        //ambil inputan key
        System.out.println("Masukan Key Subtitusi");
        String inputKey = sc2.nextLine();
        int key = Integer.parseInt(inputKey);
        
        //pisahin semua inputan jadi array char (ascii)
        String inputTerSubtitusi = encrypt(input,key);
        System.out.println(inputTerSubtitusi);
        char [] array=inputTerSubtitusi.toCharArray();
        
        //loop eksekusi inputan (perkarakter)
        for(int i =0;i<array.length;i++)
        {
        	//nampung posisi dari array dan mengubah ke ascii
        	//dengan menampung char ke variabel int
            int z=(int)array[i];
            // menampung hasil dari method decToBin yang 
            // mengubah inputan menjadi biner 
            String binary=decToBin(z);
            //menambah deret angka pada biner dengan angka 0
        	//penambahan akan berhenti jika jumlah angka pada deret biner sudah genap 8
            concat=concat+CheckNumbersOfDigits(binary);
            //memberi batas untuk biner pada tiap karakter dengan karakter "-"
            concat+="-";
        }
        
        //untuk hapus "-" paling belakang
      	System.out.println(removeLastChar(concat));
    }
	
	//subtitusi
	public static String encrypt(String input, int key){
        input=input.toLowerCase();
        String cipher="";
        for (int i=0;i<input.length();i++){ //mengecek posisi dari char pada string yang diinputkan
                                            //dan menjadi argumen
            int posisiChar = str.indexOf(input.charAt(i));
            int keyValue = (posisiChar+key)%26;
            char charPengganti = str.charAt(keyValue);
            cipher=cipher+charPengganti;
        }
        return cipher;
    }
	
	//menambah deret angka pada biner dengan angka 0
	//penambahan akan berhenti jika jumlah angka pada deret biner sudah genap 8
    private static String CheckNumbersOfDigits(String digit) 
    {
    	//menghitung panjang dari inputan parameter
        int diglen=digit.length();
        int add_dig=8-diglen;
        for(int i=0;i<add_dig;i++)
        {
            digit="0"+digit;
        }
        return digit;
    }
    //mengubah inputan ascii menjadi biner
    public static String decToBin(int dec) 
    {
    	// jika isi parameter/inputan adalah 0, maka return 0 dan code
    	// setelah kondisi if ini tidak di run
        if (dec == 0) {
            return "0"; // special case
        }
        final StringBuilder result = new StringBuilder();
        int current = dec;
        while (current != 0) {
        	//bila current ganjil, maka akan mereturn 1
            result.append(current & 1);
            //menggeser ke kanan nilai biner dari current
            //misal nilai dari current adalah 4, berarti binernya adalah 100
            //bila current di shift right (>>) sebanyak 1 maka
            //binernya yang tadinya 100 menjadi 10.
            //nilai biner paling depan (karena baca biner dari kanan ke kiri)
            //yang digeser dan dihapus tidak berpengaruh (bisa 1 atau 0)
            //tetap akan dihapus
            current = current >> 1;
        }
        return result.reverse().toString();
    }
    //menghapus "-" dibelakang, agar terlihat rapi
    private static String removeLastChar(String str) {
        return str.substring(0,str.length()-1);
    }

}
