import java.io.*;

public class BinaryPrimeReader {
    private int numBytes = 1;
    private final DataInputStream inputStream;
    private int numPrimes;



    public BinaryPrimeReader(String url) throws IOException{
        this.inputStream = new DataInputStream(new FileInputStream(url));
        this.numPrimes = (int) inputStream.readLong();
    }

    public int readNextInt() throws IOException {
        int result;
        if (numBytes == 1){
            // read Unsigned 1 byte value
            result = inputStream.readUnsignedByte();
        } else if (numBytes == 2) {
            // read Unsigned 2 byte value
            result = inputStream.readUnsignedShort();
        } else {
            result = ((inputStream.readUnsignedByte() & 0xF) << 16) | ((inputStream.readUnsignedByte() & 0xFF) << 8) | (inputStream.readUnsignedByte() & 0xFF);
        }
        --numPrimes;
        // if prime numbers has ended getting data for the next number of bytes
        if (numPrimes == 0) {
            // Skip the newline character
            inputStream.skipBytes(1);
            // Get the number of prime numbers consisting of numBytes++
            numPrimes = (int) inputStream.readLong();
            numBytes++;
        }
        return result;
    }
}
