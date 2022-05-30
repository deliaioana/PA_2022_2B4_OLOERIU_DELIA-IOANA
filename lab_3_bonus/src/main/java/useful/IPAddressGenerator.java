package useful;

import java.util.Random;

public class IPAddressGenerator {
    public String randomIP() {
        String IP = "";
        Random rand = new Random();
        for(int i=0; i<3; ++i){
            IP = IP + rand.nextInt(255);
            IP = IP + '.';
        }
        IP = IP + rand.nextInt(255);
        return IP;
    }
}
