package com.algo.string;


public class HexDump {

    public static void main(String[] args) {
        int BYTES_PER_LINE = 16;
        if (args.length == 1) {
            BYTES_PER_LINE = Integer.parseInt(args[0]);
        }

        int i;
        for (i = 0; !BinaryStdIn.isEmpty(); i++) {
            if (BYTES_PER_LINE == 0) { BinaryStdIn.readChar(); continue; }
            if (i == 0) StdOut.printf("");
            else if (i % BYTES_PER_LINE == 0) StdOut.printf("\n", i);
            else StdOut.print(" ");
            char c = BinaryStdIn.readChar();
            StdOut.printf("%02x", c & 0xff);
        }
        if (BYTES_PER_LINE != 0) StdOut.println();
        StdOut.println((i*8) + " bits");
    }
}
