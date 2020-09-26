
package factorysystem;

public class Validation {
    /**********************************************************/
    public static boolean validateName (String word){
boolean check = true;
for (int i = 0; i < word.length(); i++){
    if (!((word.charAt(i)>='a'&&word.charAt(i)<='z')||(word.charAt(i)>='A'&&word.charAt(i)<='Z')||(word.charAt(i)<=' ')))
        check = false;
}
return check;
} 
    /************************************************************/
  public static boolean validateFractionNumber (String number){
boolean check = true;
int commaCount = 0;
for (int i = 0; i < number.length(); i++){
    if (number.charAt(i) == '.'){
    commaCount ++;
    }
    if (!((number.charAt(i)>='0'&&number.charAt(i)<='9') || (number.charAt(i) == '.'))|| number.charAt(i) ==' ')
        check = false;
}
if (commaCount > 1)
    check = false;
return check;
} 
  /****************************************************/
  public static boolean validateIntegerNumber (String number){
boolean check = true;
for (int i = 0; i < number.length(); i++){
    if (!((number.charAt(i)>='0'&&number.charAt(i)<='9')))
        check = false;
}
return check;
}
}
