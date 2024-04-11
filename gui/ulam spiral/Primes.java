import java.io.*;

public class Primes {

    final int n;
    final String url;

    public Primes(int n, String url) {
        this.n = n;
        this.url = url;
    }

    public int getN() {
        return n;
    }

    public void createBinary() throws IOException {
        FileOutputStream fos = new FileOutputStream(url);
        DataOutputStream dos = new DataOutputStream(fos);

        boolean[] isPrime = new boolean[n + 1];

        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        int count = 0;

        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
            if (i < 256) {
                if (i == 255 || i == n-1) {
                    dos.writeLong(count);
                    for (int j = 2; j <= i; j++) {
                        if (isPrime[j]) {
                            dos.write(getBytes(j, 1));
                        }
                    }
                    dos.writeByte('\n');
                    count = 0;
                }
            } else if (i < 65536) {
                if (i == 65535 || i == n-1) {
                    dos.writeLong(count);
                    for (int j = 257; j <= i; j++) {
                        if (isPrime[j]) {
                            dos.write(getBytes(j, 2));
                        }
                    }
                    dos.writeByte('\n');
                    count = 0;
                }
            } else if (i < 16777216) {
                if (i == 16777215 || i == n-1) {
                    dos.writeLong(count);
                    for (int j = 65536; j <= i; j++) {
                        if (isPrime[j]) {
                            dos.write(getBytes(j, 3));
                        }
                    }
                    dos.writeByte('\n');
                    count = 0;
                }
            }
        }
        dos.close();
        fos.close();
    }

    private static byte[] getBytes(int n, int numBytes) {
        byte[] bytes = new byte[numBytes];

        // generate a byte array from the last index
        for (int i = numBytes - 1; i >= 0; i--) {
            bytes[i] = (byte) (n & 0xff);
            n >>= 8;
        }

        return bytes;
    }

}