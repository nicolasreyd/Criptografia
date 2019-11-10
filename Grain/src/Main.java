import java.util.Scanner;

public class Main {

    private final Grain grain;

    public Main() {
        this.grain = new Grain();
    }

    public void run() {
        this.grain.init("absolute", "california"); //iv, key
        String keystream = this.grain.filter();
        System.out.println("Keystream : " + keystream);
        Scanner input = new Scanner(System.in);
        System.out.println("Grain Cipher Encrypt/Decrypt");
        System.out.println("1. Encrypt");
        System.out.println("2. Decrypt");
        System.out.print("Select Option : ");
        int option = input.nextInt();
        System.out.println("");

        switch (option) {
            case 1:
                System.out.println("Encrypt");
                System.out.print("Plaintext : ");
                String ciphertext = this.grain.encrypt(input.next());
                System.out.println("Ciphertext : " + ciphertext);
                break;
            case 2:
                System.out.println("Decrypt");
                System.out.print("Ciphertext : ");
                String plaintext = this.grain.decrypt(input.next(), keystream);
                System.out.println("Plaintext : " + plaintext);
                break;
            default:
                System.out.println("Invalid Option");
                break;
        }

    }

    public static void main(String[] args) {
        Main self = new Main();
        self.run();
    }
    
}
