
public class Math {
	
	

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public static int[] conv(int[] x, int[] h) {
        int m = x.length;
        int n = h.length;
        int i=0,j;
        int [] y = new int[m+n-1];
        int [] x1 = new int[m+n-1];
        int [] h1 = new int[m+n-1];

        for(i=0;i<m+n-1;i++) {
            x1[i] = 0;
            h1[i] = 0;
        }

        for(i=0;i<m;i++) {
            x1[i] = x[i];
        }

        for(i=0;i<n;i++) {
            h1[i] = h[i];
        }

        for(i=0;i<m+n-1;i++)
        {
            y[i]=0;
            for(j=0;j<=i;j++)
                y[i] += x1[j] * h1[i - j];

        }
        return y;
    }

    public static int[] deconv(int[] g, int[] f) {
        int[] h = new int[g.length - f.length + 1];
        for (int n = 0; n < h.length; n++) {
            h[n] = g[n];
            int lower = java.lang.Math.max(n - f.length + 1, 0);
            for (int i = lower; i < n; i++)
                h[n] -= h[i] * f[n - i];
            h[n] /= f[0];
        }
        return h;
    }

}
