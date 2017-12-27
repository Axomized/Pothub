package donation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateCard {
	
	public boolean validateCCNo(String ccNumber){
		boolean isValid = false;
        int s1 = 0, s2 = 0;
        
        if ((ccNumber.length() >= 9 || ccNumber.length() <= 19) && ccNumber.matches("\\d+")) {
        	String reverse = new StringBuffer(ccNumber).reverse().toString();
            for (int i = 0 ; i < reverse.length(); i++) {
                int digit = Character.digit(reverse.charAt(i), 10);
                if (i % 2 == 0) {
                    s1 += digit;
                }
                else {
                    s2 += 2 * digit;
                    if (digit >= 5) {
                        s2 -= 9;
                    }
                }
            }
            if ((s1 + s2) % 10 == 0) {
            	isValid = true;
            }
        }
        else {
        	isValid = false;
        }
        return isValid;
    }
	
	public boolean validateCode(String ccNumber, String securityCode) {
		boolean isValid = false;
		String amexRegex = "^3[47]";
		Pattern pat = Pattern.compile(amexRegex);
		Matcher mat = pat.matcher(ccNumber);
		
		if (mat.find()) {
			if (securityCode.length() == 4) {
				isValid = true;
			}
		}
		else {
			if (securityCode.length() == 3) {
				isValid = true;
			}
		}
		return isValid;
	}

	public static void main(String[] args) {
		
	}

}
