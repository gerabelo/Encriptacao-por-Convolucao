public class Main {    
	
    static final int ascii_range = 620;
    
    /**
	 * @param args
	 */
    
	public static void main(String[] args) {
		String mensagem = "http://io.acad.athabascau.ca/~oscar/ebook/JavaGameProgramming.pdf";
		String chave = "4m4z0n1@";
	    
		try {
			System.out.println(encripta(mensagem,chave).toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static String decripta(String mensagem, String chave) {
        try {
            String chaveSHA512 = SHA.sha512(chave.toString());
            String entrada = desmascarar(mensagem.toString());

            String[] tokens = entrada.split(" ");
            int[] y = new int[tokens.length];
            int i = 0;

            for (int j=0;j < tokens.length;j++) {                
                y[i++] = Integer.parseInt(tokens[j]);
            }

            int[] h = new int[chaveSHA512.length()];
            for (i = 0; i < chaveSHA512.length(); i++) {
                h[i] = chaveSHA512.charAt(i);
            }

            int[] x = Math.deconv(y, h);
            String saida = "";

            for (i = 0; i < x.length; i++) {
                saida = saida + Character.toString((char) x[i]);
            }

            return saida;
        } catch (Exception d) {
        	return null;
        }
    }
    
    
    public static String encripta(String mensagem, String chave) {
        try {
            String chaveSHA512 = SHA.sha512(chave.toString());
            String entrada = mensagem.toString();

            int i = 0;

            int[] x = new int[entrada.length()];

            for (i = 0; i < entrada.length(); i++) {
                x[i] = entrada.charAt(i);
            }

            int[] h = new int[chaveSHA512.length()];

            for (i = 0; i < chaveSHA512.length(); i++) {
                h[i] = chaveSHA512.charAt(i);
            }

            int[] y = Math.conv(x, h);

            String saida = "";

            for (i = 0; i < y.length; i++) {
                saida = saida + String.valueOf(y[i]) + " ";
            }

            return mascarar(saida);
            
        } catch (Exception e) {
        	return null;
        }
    }

    public static String mascarar(String entrada) {
        String saida = "";
        String tmp = "";
        String[] tokens = entrada.split(" ");
        int tokenLength = 0;

        for (int j=0;j < tokens.length;j++) {
            tmp = tokens[j];
            tokenLength = tmp.length();

            for (int i=0;i<tokenLength;i++) {
                saida = saida+Character.toString((char) (ascii_range + Integer.parseInt(tmp.substring(i,i+1))));
            }
            saida = saida+" ";
        }

        return saida;
    }

    public static String desmascarar(String entrada) {

        String saida = "";
        String tmp = "";
        String[] tokens = entrada.split("\\s");
        int tokenLength = 0;
        int tokeni = 0;        
        

        for (int j=0;j < tokens.length;j++) {
            tmp = tokens[j];
            tokenLength = tmp.length();

            for (int i=0;i < tokenLength; i++) {
                tokeni = tmp.charAt(i)-ascii_range;
                saida = saida+Integer.toString(tokeni);
            }
            saida = saida+" ";
        }
        return saida;
    }

}
