import java.nio.charset.StandardCharsets;

public class Encryptor
{
    /** A two-dimensional array of single-character strings, instantiated in the constructor */
    private String[][] letterBlock;

    /** The number of rows of letterBlock, set by the constructor */
    private int numRows;

    /** The number of columns of letterBlock, set by the constructor */
    private int numCols;

    /** Constructor*/
    public Encryptor(int r, int c)
    {
        letterBlock = new String[r][c];
        numRows = r;
        numCols = c;
    }

    public String[][] getLetterBlock()
    {
        return letterBlock;
    }

    /** Places a string into letterBlock in row-major order.
     *
     *   @param str  the string to be processed
     *
     *   Postcondition:
     *     if str.length() < numRows * numCols, "A" in each unfilled cell
     *     if str.length() > numRows * numCols, trailing characters are ignored
     */
    public void fillBlock(String str)
    {
        for (int row = 0; row < letterBlock.length; row++) {
            for (int col = 0; col < letterBlock[0].length; col++) {
                if (row * numCols + col < str.length() ) {
                    letterBlock[row][col] = str.charAt(row * numCols + col) + "";
                } else  {
                    letterBlock[row][col] = "A";
                }
            }
        }
    }

    /** Extracts encrypted string from letterBlock in column-major order.
     *
     *   Precondition: letterBlock has been filled
     *
     *   @return the encrypted string from letterBlock
     */
     public String encryptBlock()
    {
        String returnStr = "";
        for (int column = 0; column < letterBlock[0].length; column++) {
            for (int row = 0; row < letterBlock.length; row++) {
                returnStr += letterBlock[row][column];
            }
        }
        return returnStr;
    }

    /** Encrypts a message.
     *
     *  @param message the string to be encrypted
     *
     *  @return the encrypted message; if message is the empty string, returns the empty string
     */
     public String encryptMessage(String message)
    {
        String str = "";
        while (message.length() > numRows * numCols) {
            fillBlock(message);
            str += encryptBlock();
            message = message.substring(numCols * numRows);
        }
        fillBlock(message);
        str += encryptBlock();
        return str;
    }

    /**  Decrypts an encrypted message. All filler 'A's that may have been
     *   added during encryption will be removed, so this assumes that the
     *   original message (BEFORE it was encrypted) did NOT end in a capital A!
     *
     *   NOTE! When you are decrypting an encrypted message,
     *         be sure that you have initialized your Encryptor object
     *         with the same row/column used to encrypted the message! (i.e.
     *         the ???encryption key??? that is necessary for successful decryption)
     *         This is outlined in the precondition below.
     *
     *   Precondition: the Encryptor object being used for decryption has been
     *                 initialized with the same number of rows and columns
     *                 as was used for the Encryptor object used for encryption.
     *
     *   @param encryptedMessage  the encrypted message to decrypt
     *
     *   @return  the decrypted, original message (which had been encrypted)
     *
     *   TIP: You are encouraged to create other helper methods as you see fit
     *        (e.g. a method to decrypt each section of the decrypted message,
     *         similar to how encryptBlock was used)
     */
    public String decryptMessage(String encryptedMessage)
    {
        String str = "";
        fillBlock(encryptedMessage);
        while (str.charAt(str.length() -1) == 'A') {
            str = str.substring(0, str.length()-1);
        }
        return str;
    }

    public String decryptBlock() {
        String returnStr = "";
        for (int column = 0; column < letterBlock[0].length; column++) {
            for (int row = 0; row < letterBlock.length; row++) {
                returnStr += letterBlock[row][column];
            }
        }
        return returnStr;
    }


}