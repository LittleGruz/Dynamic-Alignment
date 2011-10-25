package littlegruz.alignment.entities;

public class Deed{
   private String name;
   private int good;
   private int bad;
   
   public Deed(String name, int positive, int negative){
      this.name = name;
      good = positive;
      bad = negative;
   }
   public void setName(String name){
      this.name = name;
   }
   public String getName(){
      return name;
   }
   public void setGood(int good){
      this.good = good;
   }
   public int getGood(){
      return good;
   }
   public void setBad(int bad){
      this.bad = bad;
   }
   public int getBad(){
      return bad;
   }
}
