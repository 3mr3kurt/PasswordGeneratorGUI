import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public final class PWAlg{
  private static final String lowercase = "abcdefghijklmnopqrstuvwxyz";
  private static final String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String numbers = "0123456789";
  private static final String symbols = "!@#$%&*()_+-=[]|,./?><";
  private boolean includeLower;
  private boolean includeUpper;
  private boolean includeSymbols;
  private boolean includeNumbers;


  private PWAlg(PasswordGeneratorBuilder builder){
    this.includeLower = builder.includeLower;
    this.includeUpper = builder.includeUpper;
    this.includeSymbols = builder.includeSymbols;
    this.includeNumbers = builder.includeNumbers;
  }

  public static class PasswordGeneratorBuilder{
    private boolean includeLower;
    private boolean includeUpper;
    private boolean includeSymbols;
    private boolean includeNumbers;

    public PasswordGeneratorBuilder(){
      this.includeLower = false;
      this.includeUpper = false;
      this.includeSymbols = false;
      this.includeNumbers = false;
    }
    public PasswordGeneratorBuilder includeLower(boolean includeLower){
      this.includeLower = includeLower;
      return this;
    }
    public PasswordGeneratorBuilder includeUpper(boolean includeUpper){
      this.includeUpper = includeUpper;
      return this;
    }
    public PasswordGeneratorBuilder includeSymbols(boolean includeSymbols){
      this.includeSymbols = includeSymbols;
      return this;
    }
    public PasswordGeneratorBuilder includeNumbers(boolean includeNumbers){
      this.includeNumbers = includeNumbers;
      return this;
    }
    public PWAlg build(){
      return new PWAlg(this);
    }
  }
  public String generate(int length){
    if (length<=0){
      return "";
    }
    StringBuilder password = new StringBuilder(length);
    Random random = new Random(System.nanoTime());

    List<String> charCategories = new ArrayList<>(4);
    if (includeLower){
      charCategories.add(lowercase);
    }
    if (includeUpper){
      charCategories.add(uppercase);
    }
    if (includeNumbers){
      charCategories.add(numbers);
    }
    if (includeSymbols){
      charCategories.add(symbols);
    }

    for (int i = 0;i<length;i++){
      String charCategory = charCategories.get(random.nextInt(charCategories.size()));
      int position = random.nextInt(charCategory.length());
      password.append(charCategory.charAt(position));
    }
    return new String(password);
  }
}
