import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Grain {

    Byte[] lfsr = new Byte[80];
    Byte[] nfsr = new Byte[80];
    private final Byte[] temp_lfsr;
    private final Byte[] temp_nfsr;
    private final Byte[] filter;

    public Grain() {
        this.temp_lfsr = new Byte[160];
        this.temp_nfsr = new Byte[160];
        this.filter = new Byte[160];
    }

    /**
    * Esta funci�n se llama cuando el objeto est� destinado y hace dos cosas:
    * Primero:
    * Convierta el IV en una cadena binaria y luego div�dalo por bit en un �ndice 
    * en la matriz LFSR.
	* Segundo:
	* convierte KEY en una cadena binaria y la separa por bit en un �ndice 
	* en la matriz NFSR.
     * @param iv
     * @param key 
     */
    public void init(String iv, String key) {
        // convertir cadena iv a cadena binaria
        String iv_result = this.stringToBinary(iv);
        // insertarlo en la matriz lfsr
        for (int i = 0; i < iv_result.length(); i++) {
            lfsr[i] = Byte.parseByte(String.valueOf(iv_result.charAt(i)));
        }
        // llenar el �ndice de 64 a 80 con un valor de 1
        for (int i = 64; i < 80; i++) {
            lfsr[i] = 1;
        }
        // convertir cadena clave a cadena binaria
        String result2 = this.stringToBinary(key);
        // insertarlo en la matriz nfsr
        for (int i = 0; i < result2.length(); i++) {
            this.nfsr[i] = Byte.parseByte(String.valueOf(result2.charAt(i)));
        }
    }
    
    /**
     * Convertir una cadena en una cadena binaria
     * @param string: String input
     * @return result: String
     */
    public String stringToBinary(String string) {
        String result = "";
        String tmpStr;
        int tmpInt;
        char[] messChar = string.toCharArray();

        for (int i = 0; i < messChar.length; i++) {
            tmpStr = Integer.toBinaryString(messChar[i]);

            tmpInt = tmpStr.length();
            if (tmpInt != 8) {
                tmpInt = 8 - tmpInt;
                if (tmpInt == 8) {
                    result += tmpStr;
                } else if (tmpInt > 0) {
                    for (int j = 0; j < tmpInt; j++) {
                        result += "0";
                    }
                    result += tmpStr;
                } else {
                    System.err.println("argument 'bits' is too small");
                }
            } else {
                result += tmpStr;
            }
            result += "";
        }

        return result;
    }
    
    /**
     * Convertir cadena binaria a hexadecimal
     * @param string: String input
     * @return result: String
     */
    public String stringBinaryToHex(String string) {
        return new BigInteger(string, 2).toString(16);
    }
    
    /**
     * Convertir cadenas hexadecimales en cadenas binarias
     * @param hex: String
     * @return String
     */
    public String hexToBinary(String hex) {
        StringBuilder binStrBuilder = new StringBuilder();
        int c = 1;
        
        for (int i = 0; i < hex.length() - 1; i += 2) {

            String output = hex.substring(i, (i + 2));

            int decimal = Integer.parseInt(output, 16);

            String binStr = Integer.toBinaryString(decimal);
            int len = binStr.length();
            StringBuilder sbf = new StringBuilder();
            if (len < 8) {

                for (int k = 0; k < (8 - len); k++) {
                    sbf.append("0");
                }
                sbf.append(binStr);
            } else {
                sbf.append(binStr);
            }

            c++;
            binStrBuilder.append(sbf.toString());
        }

        return binStrBuilder.toString();
    }
    
     /**
     * Convierta cadenas binarias a array hexadecimal
     * @param filter: array
     * @return result: String
     */
    public String[] stringBinaryToHexArray(String filter) {
        String[] output = new String[40];
        int startFrom = 0;

        for (int i = 0; i < 40; i++) {
            output[i] = Integer.toHexString(
                    Integer.parseInt(filter.substring(startFrom, startFrom + 3))
            );
            startFrom += 3;
        }

        System.out.println(Arrays.toString(output));

        return output;
    }

    public void lfsr() {
        Byte[] data = this.lfsr;
        Byte xor;

        for (int i = 1; i <= 160; i++) {
            // calcular xor basado en el c�lculo en grano
            xor = (byte) (data[62] ^ data[51] ^ data[38] ^ data[23] ^ data[13] ^ data[0]);
            // la matriz debe convertirse a List para poder rotar
            List<Byte> data_list = Arrays.asList(data);
            // deslice la matriz de 1 posici�n en sentido horario
            Collections.rotate(data_list, 1);
            this.temp_lfsr[i - 1] = data_list.get(0);
            data_list.set(0, xor);
        }

    }

    public void nfsr() {
        Byte[] data = this.nfsr;
        Byte xor;
        
        for (int i = 1; i < 161; i++) {
            // calcular xor basado en c�lculos en grain
            xor = (byte) (data[0] ^ data[63] ^ data[60]
                    ^ data[52] ^ data[45] ^ data[37]
                    ^ data[33] ^ data[28] ^ data[21]
                    ^ data[15] ^ data[19] ^ data[0]
                    ^ (data[63] & data[60]) ^ (data[37] & data[33])
                    ^ (data[15] & data[9]) ^ (data[60] & data[52] & data[45])
                    ^ (data[33] & data[28] & data[21])
                    ^ (data[63] & data[45] & data[28] & data[9])
                    ^ (data[60] & data[52] & data[37] & data[33])
                    ^ (data[63] & data[60] & data[21] & data[15])
                    ^ (data[63] & data[60] & data[52] & data[45] & data[37])
                    ^ (data[33] & data[28] & data[21] & data[15] & data[9])
                    ^ (data[52] & data[45] & data[37] & data[33] & data[28] & data[21]));
            // La matriz se debe convertir a una Lista para rotarla
            List<Byte> data_list = Arrays.asList(data);
            // conjunto de diapositivas 1 posici�n en sentido horario
            Collections.rotate(data_list, 1);
            this.temp_nfsr[i - 1] = data_list.get(0);
            data_list.set(0, xor);
        }

    }
    
    /**
     * La funci�n de filtro se utiliza para generar keystream
     * @return String
     */
    public String filter() {
        // diapositiva matriz de lfsr
        this.lfsr();
        // diapositiva matriz nfsr
        this.nfsr();
        // definir la variable de c�lculo xor
        Byte x0 = this.temp_lfsr[3];
        Byte x1 = this.temp_lfsr[25];
        Byte x2 = this.temp_lfsr[46];
        Byte x3 = this.temp_lfsr[64];
        Byte x4 = this.temp_nfsr[63];
        Byte f_h = (byte) (x1 ^ x4 ^ (x0 & x3) ^ (x2 & x3) ^ (x3 & x3) ^ (x0 & x1 & x2)
                ^ (x0 & x2 & x3) ^ (x0 & x2 & x4) ^ (x1 & x2 & x4) ^ (x2 & x3 & x4));

        String string_filter = "";
        for (int i = 0; i < 160; i++) {
            this.filter[i] = (byte) (this.temp_nfsr[i] ^ f_h);
            string_filter += Byte.toString(this.filter[i]);
        }
        
        return this.stringBinaryToHex(string_filter);
    }

    public String encrypt(String input) {
        String result = this.stringToBinary(input);
        String result_string = "";
        Byte[] result_array = new Byte[result.length()];
        Byte[] result_xor_array = new Byte[result.length()];

        for (int i = 0; i < result_array.length; i++) {
            result_array[i] = Byte.parseByte(String.valueOf(result.charAt(i)));
            result_xor_array[i] = (byte) (this.filter[i] ^ result_array[i]);
            result_string += result_xor_array[i];
        }
        return this.stringBinaryToHex(result_string);
    }

    public String decrypt(String cipher, String keystream) {
        String a = this.hexToBinary(cipher);
        Byte[] a_array = new Byte[a.length()];
        String b = this.hexToBinary(keystream);
        Byte[] b_array = new Byte[b.length()];
        String plain_binary = "";
        String plain = "";
        Byte[] hasil = new Byte[a.length()];

        for (int i = 0; i < a_array.length; i++) {
            a_array[i] = Byte.parseByte(String.valueOf(a.charAt(i)));
        }

        for (int i = 0; i < b_array.length; i++) {
            b_array[i] = Byte.parseByte(String.valueOf(b.charAt(i)));
        }

        for (int i = 0; i < a.length(); i++) {
            hasil[i] = (byte) (b_array[i] ^ a_array[i]);
            plain_binary += hasil[i];
        }

        for (int i = 0; i <= plain_binary.length() - 8; i += 8) {
            int k = Integer.parseInt(plain_binary.substring(i, i + 8), 2);
            plain += (char) k;
        }

        return plain;
    }

}
